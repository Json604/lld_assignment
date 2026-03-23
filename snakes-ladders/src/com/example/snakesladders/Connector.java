package com.example.snakesladders;

public class Connector {
    private final int origin;
    private final int target;

    public Connector(int origin, int target) {
        this.origin = origin;
        this.target = target;
    }

    public int getOrigin() {
        return origin;
    }

    public int getTarget() {
        return target;
    }

    public boolean isSnake() {
        return target < origin;
    }

    @Override
    public String toString() {
        var label = isSnake() ? "Snake" : "Ladder";
        return label + "(" + origin + " => " + target + ")";
    }
}
