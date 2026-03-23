package com.example.pen;

public class InkCartridge {

    private final Shade shade;
    private final int capacity;
    private int currentInk;

    public InkCartridge(Shade shade, int capacity) {
        this.shade = shade;
        this.capacity = capacity;
        this.currentInk = capacity;
    }

    public Shade getShade() {
        return shade;
    }

    public int getCurrentInk() {
        return currentInk;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isDepleted() {
        return currentInk <= 0;
    }

    // Drains ink and returns the number of units actually used
    public int useInk(int requested) {
        var actual = Math.min(requested, currentInk);
        currentInk -= actual;
        return actual;
    }

    // Restores ink back to full capacity
    public void restore() {
        currentInk = capacity;
    }

    @Override
    public String toString() {
        return shade + " cartridge (" + currentInk + "/" + capacity + ")";
    }
}
