package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private static final int WRONG_DIRECTION_PENALTY = 120;
    private final List<Elevator> fleet;

    public ElevatorController(List<Elevator> fleet) {
        this.fleet = new ArrayList<>(fleet);
    }

    public void dispatch(Request req) {
        Elevator chosen = null;
        int lowestCost = Integer.MAX_VALUE;

        for (Elevator e : fleet) {
            int c = evaluateCost(e, req);
            if (c < lowestCost) {
                lowestCost = c;
                chosen = e;
            }
        }

        if (chosen != null) {
            chosen.enqueueFloor(req.getFloor());
            System.out.println("Assigned " + req + " => " + chosen.getId());
        }
    }

    private int evaluateCost(Elevator e, Request req) {
        int dist = Math.abs(e.getCurrentFloor() - req.getFloor());

        if (e.isIdle()) {
            return dist;
        }

        boolean goingUp = e.getState() == ElevatorState.MOVING_UP;
        boolean requestAbove = req.getFloor() >= e.getCurrentFloor();

        // elevator heading toward the request in the matching direction
        if (goingUp && req.getDirection().isUp() && requestAbove) {
            return dist;
        }
        if (!goingUp && !req.getDirection().isUp() && !requestAbove) {
            return dist;
        }

        return WRONG_DIRECTION_PENALTY + dist;
    }

    public void step() {
        for (Elevator e : fleet) {
            e.step();
        }
    }

    public void printStatus() {
        for (Elevator e : fleet) {
            System.out.println("  " + e);
        }
    }
}
