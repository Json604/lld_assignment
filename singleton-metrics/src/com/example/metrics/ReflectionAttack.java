package com.example.metrics;

import java.lang.reflect.Constructor;

// tries to create a second instance using reflection
// this should be blocked by our constructor guard
public class ReflectionAttack {

    public static void main(String[] args) throws Exception {
        MetricsRegistry original = MetricsRegistry.getInstance();

        // grab the private constructor and force access
        Constructor<MetricsRegistry> ctor = MetricsRegistry.class.getDeclaredConstructor();
        ctor.setAccessible(true);

        try {
            MetricsRegistry hacked = ctor.newInstance();
            // if we get here, the guard didn't work
            System.out.println("Original hash : " + System.identityHashCode(original));
            System.out.println("Hacked hash   : " + System.identityHashCode(hacked));
            System.out.println("FAIL — reflection created a new instance");
        } catch (Exception e) {
            System.out.println("PASS — reflection blocked: " + e.getCause().getMessage());
        }
    }
}
