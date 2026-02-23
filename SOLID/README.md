# SOLID Refactoring Exercises

Refactored solutions for exercises 1–6 from the LLD101 assignment.

Each exercise had a working but messy codebase that violated a specific SOLID principle. I broke apart the god-classes, introduced interfaces where needed, and used composition to keep things extensible.

- ex01, ex02 — SRP (Student Onboarding, Cafeteria Billing)
- ex03, ex04 — OCP (Eligibility Engine, Hostel Fee Calculator)
- ex05, ex06 — LSP (File Exporters, Notification Senders)

## How to run

```bash
cd SOLID/ex01/src
javac *.java
java Demo01
```

Same pattern for ex02–ex06 (Demo02, Demo03, etc).

Java 17, no external libraries, default package.
