package com.example.snakesladders;

import java.util.Random;

public class Dice {
    private final Random random;
    private final int sides;

    public Dice() {
        this(6);
    }

    public Dice(int sides) {
        this.random = new Random();
        this.sides = sides;
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }

    public int getSides() {
        return sides;
    }
}
