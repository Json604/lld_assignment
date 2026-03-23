package com.example.parking;

public enum BaySize {
    TWO_WHEELER_BAY(10),
    STANDARD_BAY(20),
    OVERSIZED_BAY(50);

    private final int pricePerHour;

    BaySize(int pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public int getPricePerHour() {
        return pricePerHour;
    }
}
