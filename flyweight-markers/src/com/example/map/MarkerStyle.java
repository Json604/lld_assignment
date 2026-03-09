package com.example.map;

/**
 * Shared visual properties for markers (intrinsic state).
 * Immutable design allows safe reuse across multiple marker instances.
 */
public final class MarkerStyle {

    private final String shape;   // DIAMOND, STAR, TRIANGLE
    private final String color;   // PURPLE, CYAN, YELLOW, MAGENTA
    private final int size;
    private final boolean filled;

    public MarkerStyle(String shape, String color, int size, boolean filled) {
        this.shape = shape;
        this.color = color;
        this.size = size;
        this.filled = filled;
    }

    public String getShape() { return shape; }
    public String getColor() { return color; }
    public int getSize()     { return size; }
    public boolean isFilled() { return filled; }

    @Override
    public String toString() {
        return shape + "|" + color + "|" + size + "|" + (filled ? "T" : "F");
    }
}
