Singleton Pattern — Metrics Counter
====================================

Problem
-------
We have a small app called **StatHub** that tracks counters globally (like `LOGIN_COUNT`,
`QUERY_FAILURES`, etc). Any part of the app should be able to bump these counters.

The issue is the original code was NOT a proper singleton — it wasn't thread-safe,
reflection could make extra instances, and serialization could break it too.

Goal
----
Refactor `MetricsRegistry` into a clean, thread-safe singleton that:

1. Uses lazy initialization (I went with the static inner holder idiom)
2. Has a private constructor that blocks reflection from creating duplicates
3. Handles serialization properly via `readResolve()`
4. `MetricsLoader` uses `getInstance()` everywhere — no `new MetricsRegistry()`

How to run
----------
    cd singleton-metrics/src
    javac com/example/metrics/*.java
    java com.example.metrics.App

You can also run the individual checks:

    java com.example.metrics.ConcurrencyCheck
    java com.example.metrics.ReflectionAttack
    java com.example.metrics.SerializationCheck

What each file does
-------------------
- `MetricsRegistry` — the singleton itself, stores counters in a HashMap
- `MetricsLoader` — reads initial values from `metrics.properties`
- `App` — main entrypoint, loads props and prints counters
- `ConcurrencyCheck` — 80 threads all calling getInstance, checks only 1 instance
- `ReflectionAttack` — tries to hack a second instance using reflection
- `SerializationCheck` — serialize + deserialize, makes sure same object comes back
