Immutable Design — Incident Tickets
====================================

Problem
-------
We have a support ticket tool called **QuickDesk** where `IncidentTicket` objects
were originally mutable (public setters, multiple constructors, validation all over the place).
This caused bugs where tickets got changed after creation — messed up audit trails etc.

Goal
----
Refactor so that:

1. `IncidentTicket` is fully immutable (private final fields, no setters)
2. Tickets are created using a fluent Builder pattern
3. All validation is centralized inside `Builder.build()`
4. "Updates" return a brand-new ticket (`toBuilder()` approach), original stays untouched
5. The tags list is defensively copied — caller cant tamper with internal state

How to run
----------
    cd immutable-tickets/src
    javac com/example/tickets/*.java TryIt.java
    java TryIt

Files
-----
- `IncidentTicket` — the immutable data class + nested Builder
- `TicketService` — creates/modifies tickets (always returns new objects)
- `Validation` — all the checking logic in one place
- `TryIt` — demo that exercises creation, assignment, escalation, tag safety
