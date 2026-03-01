package com.example.metrics;

import java.util.Map;

// main class for StatHub
// loads counters from file, then shows the singleton is working
public class App {

    public static void main(String[] args) throws Exception {

        MetricsLoader loader = new MetricsLoader();
        MetricsRegistry fromFile = loader.loadFromFile("metrics.properties");

        // both should point to same singleton
        MetricsRegistry global = MetricsRegistry.getInstance();

        System.out.println("From file instance : " + System.identityHashCode(fromFile));
        System.out.println("Global instance    : " + System.identityHashCode(global));
        System.out.println("Same? " + (fromFile == global));

        // bump a counter and print
        global.increment("LOGIN_COUNT");
        global.increment("LOGIN_COUNT");
        System.out.println("\nLOGIN_COUNT after 2 increments = " + global.getCount("LOGIN_COUNT"));

        System.out.println("\nAll counters:");
        for (Map.Entry<String, Long> entry : global.getAll().entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nRun ConcurrencyCheck, ReflectionAttack, SerializationCheck for more tests.");
    }
}
