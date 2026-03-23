package com.example.parking;

public class Entrance {
    private final String entranceId;

    public Entrance(String entranceId) {
        this.entranceId = entranceId;
    }

    public String getEntranceId() {
        return entranceId;
    }

    @Override
    public String toString() {
        return "Entrance-" + entranceId;
    }
}
