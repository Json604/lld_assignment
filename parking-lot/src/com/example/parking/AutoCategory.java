package com.example.parking;

public enum AutoCategory {
    TWO_WHEELER,
    CAR,
    BUS;

    // determines whether this automobile category can be accommodated in the given bay size
    // uses ordinal comparison: TWO_WHEELER(0) <= any, CAR(1) <= STANDARD(1)/OVERSIZED(2), BUS(2) <= OVERSIZED(2)
    public boolean canAccommodate(BaySize baySize) {
        return this.ordinal() <= baySize.ordinal();
    }
}
