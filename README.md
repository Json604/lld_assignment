# LLD Refactoring Exercises

A collection of small Java refactorings and design-demo projects used for learning low-level design (LLD).

Projects
--------
- `SOLID/` — Refactored solutions for exercises 1–6 from the LLD101 assignment.
- `immutable-tickets/` — Immutable design exercise: an immutable `IncidentTicket` with a Builder and safe update semantics.
- `singleton-metrics/` — Singleton pattern exercise: a thread-safe, serialization-safe `MetricsRegistry` with checks for concurrency, reflection and serialization attacks.
- `adapter-payments/` — Adapter pattern: unified payment processing interface wrapping incompatible payment SDKs.
- `flyweight-markers/` — Flyweight pattern: memory-optimized map marker rendering with shared style objects.
- `proxy-reports/` — Proxy pattern: document access control with lazy loading and caching.

SOLID (ex01–ex06)
-----------------
Each exercise had a working but messy codebase that violated a specific SOLID principle. The solutions break apart god-classes, introduce interfaces where needed, and use composition to keep things extensible.

- ex01, ex02 — SRP (Student Onboarding, Cafeteria Billing)
- ex03, ex04 — OCP (Eligibility Engine, Hostel Fee Calculator)
- ex05, ex06 — LSP (File Exporters, Notification Senders)

How to run
----------
SOLID examples (each has its own Demo):

    cd SOLID/ex01/src
    javac *.java
    java Demo01

Repeat the same pattern for ex02–ex06 (run `Demo02`, `Demo03`, etc).

Immutable Tickets
-----------------
Demo of immutable data design for incident tickets.

    cd immutable-tickets/src
    javac com/example/tickets/*.java TryIt.java
    java TryIt

Singleton Metrics
-----------------
Demo of a robust singleton implementation plus small checks.

    cd singleton-metrics/src
    javac com/example/metrics/*.java
    java com.example.metrics.App

You can also run the individual checks for the `singleton-metrics` project:

    java com.example.metrics.ConcurrencyCheck
    java com.example.metrics.ReflectionAttack
    java com.example.metrics.SerializationCheck

Adapter Payments
----------------
Demonstrates the Adapter pattern for payment processing.

    cd adapter-payments/src
    javac com/example/payments/*.java
    java com.example.payments.App

Flyweight Markers
-----------------
Demonstrates the Flyweight pattern for memory-efficient marker rendering.

    cd flyweight-markers/src
    javac com/example/map/*.java
    java com.example.map.App

You can verify style object sharing with:

    java com.example.map.QuickCheck

Proxy Reports
-------------
Demonstrates the Proxy pattern for document access control and lazy loading.

    cd proxy-reports/src
    javac com/example/reports/*.java
    java com.example.reports.App

You can run a quick verification with:

    java com.example.reports.QuickCheck

Notes
-----
- Java 17 is used for these exercises.
- No external libraries; most examples use the default package or simple package layouts under `com.example`.
