# Elevator System

## Class Diagram

```
+---------------------+       +---------------------+
|      <<enum>>       |       |      <<enum>>       |
|      Direction      |       |   ElevatorState     |
|---------------------|       |---------------------|
| UP                  |       | IDLE                |
| DOWN                |       | MOVING_UP           |
|---------------------|       | MOVING_DOWN         |
| +isUp(): boolean    |       |---------------------|
+---------------------+       | +isMoving(): boolean|
                               +---------------------+

+---------------------+
|       Request       |
|---------------------|
| -targetFloor: int   |
| -dir: Direction     |
+---------------------+
          |
          | dispatched to
          v
+----------------------------------------------+
|                  Elevator                    |
|----------------------------------------------|
| -id: String                                  |
| -floor: int                                  |
| -state: ElevatorState                        |
| -pendingFloors: TreeSet<Integer>             |
|----------------------------------------------|
| +enqueueFloor(target): void                  |
| +step(): void                                |
| +isIdle(): boolean                           |
+----------------------------------------------+
          ^
          | manages
+----------------------------------------------+
|             ElevatorController               |
|----------------------------------------------|
| -fleet: List<Elevator>                       |
| -WRONG_DIRECTION_PENALTY: int                |
|----------------------------------------------|
| +dispatch(req): void                         |
| +evaluateCost(e, req): int                   |
| +step(): void                                |
| +printStatus(): void                         |
+----------------------------------------------+
          ^
          | uses
+----------------------------------------------+
|                  Building                    |
|----------------------------------------------|
| -floors: int                                 |
| -controller: ElevatorController              |
|----------------------------------------------|
| +requestElevator(floor, dir): void           |
| +step(): void                                |
| +printStatus(): void                         |
+----------------------------------------------+
```

## Design Approach

### Movement Logic
Every `Elevator` keeps a `TreeSet<Integer>` of pending floors. The sorted nature of the set means we always know the highest and lowest target. On each `step()`, the elevator moves one floor closer to the next pending floor, removes it on arrival, and recalculates its state. When no floors remain the elevator becomes IDLE.

### Cost-Based Dispatch
`ElevatorController.dispatch()` selects the best elevator for a request via `evaluateCost()`:
- **Idle elevator**: cost equals the straight-line distance.
- **Same-direction, heading toward caller**: cost equals the distance (the request is on its route).
- **Wrong direction or moving away**: cost = `WRONG_DIRECTION_PENALTY` (120) + distance — this ensures such elevators are only chosen as a last resort.

The elevator with the minimum cost is assigned the request.

### Building Facade
`Building` is the public-facing entry point. It validates the floor number, wraps it into a `Request`, and hands it to the controller. Callers never interact with elevators directly.

### Step-Driven Simulation
The system advances by calling `building.step()`, which ticks every elevator forward by one floor. This makes it straightforward to trace and test behaviour at each point in time.

## How to Build and Run

```bash
cd elevator
javac -d out src/com/example/elevator/*.java
java -cp out com.example.elevator.App
```
