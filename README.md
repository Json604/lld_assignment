# LLD Refactoring Exercises

A collection of small Java refactorings and design-demo projects used for learning low-level design (LLD).

Projects
--------
- `SOLID/` — Refactored solutions for exercises 1–6 from the LLD101 assignment.
- `immutable-tickets/` — Immutable design exercise: an immutable `IncidentTicket` with a Builder and safe update semantics.
- `singleton-metrics/` — Singleton pattern exercise: a thread-safe, serialization-safe `MetricsRegistry` with checks for concurrency, reflection and serialization attacks.

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

Notes
-----
- Java 17 is used for these exercises.
- No external libraries; most examples use the default package or simple package layouts under `com.example`.
