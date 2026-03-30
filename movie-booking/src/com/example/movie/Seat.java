package com.example.movie;

public class Seat {
    private final String id;
    private final int row;
    private final int col;
    private final SeatCategory category;
    private boolean reserved;

    public Seat(String id, int row, int col, SeatCategory category) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.category = category;
        this.reserved = false;
    }

    public String getId()             { return id; }
    public int getRow()               { return row; }
    public int getCol()               { return col; }
    public SeatCategory getCategory() { return category; }
    public boolean isReserved()       { return reserved; }

    public void reserve() { this.reserved = true; }
    public void free()    { this.reserved = false; }

    @Override
    public String toString() {
        return id + "[" + category + " Rs" + category.getPrice()
                + " " + (reserved ? "taken" : "open") + "]";
    }
}
