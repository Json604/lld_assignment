package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * The actual singleton class.
 * I used the static inner holder approach because it gives lazy init
 * plus thread safety for free (class loading is thread-safe in JVM).
 *
 * Also added a flag to stop reflection from creating a second object,
 * and readResolve so deserialization doesnt break the singleton either.
 */
public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final Map<String, Long> counters = new HashMap<>();

    // tracks whether constructor has been called once already
    private static boolean instanceExists = false;

    private MetricsRegistry() {
        // if reflection tries to call this again, blow up
        if (instanceExists) {
            throw new RuntimeException("Singleton violated — use getInstance()");
        }
        instanceExists = true;
    }

    // inner holder class — JVM only loads this when someone calls getInstance()
    private static class RegistryHolder {
        private static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    public static MetricsRegistry getInstance() {
        return RegistryHolder.INSTANCE;
    }

    // deserialization hook — always return the real singleton
    @Serial
    private Object readResolve() {
        return getInstance();
    }

    // --- counter operations (synchronized for thread safety) ---

    public synchronized void setCount(String key, long val) {
        counters.put(key, val);
    }

    public synchronized void increment(String key) {
        long current = getCount(key);
        counters.put(key, current + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    // returns a copy so caller cant mess with the internal map
    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
