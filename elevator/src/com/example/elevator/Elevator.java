package com.example.elevator;

import java.util.TreeSet;

public class Elevator {
    private final String id;
    private int floor;
    private ElevatorState state;
    private final TreeSet<Integer> pendingFloors;

    public Elevator(String id, int initialFloor) {
        this.id = id;
        this.floor = initialFloor;
        this.state = ElevatorState.IDLE;
        this.pendingFloors = new TreeSet<>();
    }

    public String getId()           { return id; }
    public int getCurrentFloor()    { return floor; }
    public ElevatorState getState() { return state; }
    public boolean isIdle()         { return state == ElevatorState.IDLE; }

    public void enqueueFloor(int target) {
        pendingFloors.add(target);
        if (target > floor) {
            state = ElevatorState.MOVING_UP;
        } else if (target < floor) {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    public void step() {
        if (pendingFloors.isEmpty()) {
            state = ElevatorState.IDLE;
            return;
        }

        // decide movement direction
        if (state == ElevatorState.MOVING_UP || floor < pendingFloors.first()) {
            floor++;
        } else if (state == ElevatorState.MOVING_DOWN || floor > pendingFloors.last()) {
            floor--;
        }

        // check if we reached a requested floor
        if (pendingFloors.remove(floor)) {
            System.out.println("  >> " + id + " stopped at floor " + floor);
        }

        // recalculate state
        if (pendingFloors.isEmpty()) {
            state = ElevatorState.IDLE;
        } else if (pendingFloors.first() > floor) {
            state = ElevatorState.MOVING_UP;
        } else {
            state = ElevatorState.MOVING_DOWN;
        }
    }

    @Override
    public String toString() {
        return id + " {floor=" + floor + ", " + state
                + ", pending=" + pendingFloors + "}";
    }
}
