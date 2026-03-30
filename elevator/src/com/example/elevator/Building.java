package com.example.elevator;

public class Building {
    private final int floors;
    private final ElevatorController controller;

    public Building(int floors, ElevatorController controller) {
        this.floors = floors;
        this.controller = controller;
    }

    public int getFloors() { return floors; }

    public void requestElevator(int floor, Direction dir) {
        if (floor < 1 || floor > floors) {
            throw new IllegalArgumentException("Invalid floor: " + floor);
        }
        controller.dispatch(new Request(floor, dir));
    }

    public void step() {
        controller.step();
    }

    public void printStatus() {
        controller.printStatus();
    }
}
