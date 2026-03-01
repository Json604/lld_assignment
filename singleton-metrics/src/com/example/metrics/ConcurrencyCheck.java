package com.example.metrics;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// spins up a bunch of threads that all call getInstance at the same time
// if the singleton is correct, we should only see 1 unique identity hash
public class ConcurrencyCheck {

    public static void main(String[] args) throws Exception {
        final int numThreads = 80;
        ExecutorService pool = Executors.newFixedThreadPool(numThreads);

        CountDownLatch allReady = new CountDownLatch(numThreads);
        CountDownLatch go = new CountDownLatch(1);
        CountDownLatch finished = new CountDownLatch(numThreads);

        Set<Integer> hashes = ConcurrentHashMap.newKeySet();

        for (int i = 0; i < numThreads; i++) {
            pool.submit(() -> {
                allReady.countDown();
                try {
                    go.await(); // wait for the signal so all threads start together
                    MetricsRegistry reg = MetricsRegistry.getInstance();
                    hashes.add(System.identityHashCode(reg));
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } finally {
                    finished.countDown();
                }
            });
        }

        allReady.await(2, TimeUnit.SECONDS);
        go.countDown(); // fire!
        finished.await(3, TimeUnit.SECONDS);
        pool.shutdownNow();

        System.out.println("Distinct instances: " + hashes.size());
        if (hashes.size() == 1) {
            System.out.println("PASS — singleton held under concurrency");
        } else {
            System.out.println("FAIL — got multiple instances: " + hashes);
        }
    }
}
