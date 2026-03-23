package com.example.parking;

public class Automobile {
    private final String registrationNumber;
    private final AutoCategory category;

    public Automobile(String registrationNumber, AutoCategory category) {
        this.registrationNumber = registrationNumber;
        this.category = category;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public AutoCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category + " [" + registrationNumber + "]";
    }
}
