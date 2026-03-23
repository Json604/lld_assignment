package com.example.snakesladders;

public enum DifficultyLevel {
    //                snakeMinDrop%, snakeMaxDrop%, ladderMinClimb%, ladderMaxClimb%
    BEGINNER(         1,             5,             5,               15),
    EXPERT(           10,            20,            1,               5);

    private final int snakeMinDrop;
    private final int snakeMaxDrop;
    private final int ladderMinClimb;
    private final int ladderMaxClimb;

    DifficultyLevel(int snakeMinDrop, int snakeMaxDrop,
                    int ladderMinClimb, int ladderMaxClimb) {
        this.snakeMinDrop = snakeMinDrop;
        this.snakeMaxDrop = snakeMaxDrop;
        this.ladderMinClimb = ladderMinClimb;
        this.ladderMaxClimb = ladderMaxClimb;
    }

    public int getSnakeMinDrop() {
        return snakeMinDrop;
    }

    public int getSnakeMaxDrop() {
        return snakeMaxDrop;
    }

    public int getLadderMinClimb() {
        return ladderMinClimb;
    }

    public int getLadderMaxClimb() {
        return ladderMaxClimb;
    }

    public static DifficultyLevel fromInput(String input) {
        var normalized = input.trim().toUpperCase();
        if (normalized.equals("EASY")) {
            return BEGINNER;
        } else if (normalized.equals("HARD")) {
            return EXPERT;
        }
        return valueOf(normalized);
    }
}
