package com.example.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        var bays = new ArrayList<Bay>();

        // level 1: two-wheeler and standard bays
        bays.add(createBay("S-1", BaySize.TWO_WHEELER_BAY, 1, 10, 50));
        bays.add(createBay("S-2", BaySize.TWO_WHEELER_BAY, 1, 20, 40));
        bays.add(createBay("M-1", BaySize.STANDARD_BAY, 1, 15, 45));
        bays.add(createBay("M-2", BaySize.STANDARD_BAY, 1, 25, 35));

        // level 2: standard and oversized bays
        bays.add(createBay("M-3", BaySize.STANDARD_BAY, 2, 30, 20));
        bays.add(createBay("L-1", BaySize.OVERSIZED_BAY, 2, 40, 10));
        bays.add(createBay("L-2", BaySize.OVERSIZED_BAY, 2, 45, 15));

        var garage = new GarageManager(bays);

        System.out.println("=== Multilevel Garage System ===\n");
        System.out.println("Current availability: " + garage.availability());

        // 1. park a car arriving through entrance A
        var car = new Automobile("KA-01-1234", AutoCategory.CAR);
        var carEntry = LocalDateTime.of(2025, 1, 15, 10, 0);
        var carReceipt = garage.parkVehicle(car, carEntry, BaySize.STANDARD_BAY, "GATE-A");
        System.out.println("\nAssigned: " + carReceipt);

        // 2. park a two-wheeler arriving through entrance B
        var bike = new Automobile("KA-02-5678", AutoCategory.TWO_WHEELER);
        var bikeEntry = LocalDateTime.of(2025, 1, 15, 10, 30);
        var bikeReceipt = garage.parkVehicle(bike, bikeEntry, BaySize.TWO_WHEELER_BAY, "GATE-B");
        System.out.println("Assigned: " + bikeReceipt);

        // 3. park a two-wheeler in a standard bay (smaller automobile in bigger bay)
        var bike2 = new Automobile("KA-03-9999", AutoCategory.TWO_WHEELER);
        var bike2Entry = LocalDateTime.of(2025, 1, 15, 11, 0);
        var bike2Receipt = garage.parkVehicle(bike2, bike2Entry, BaySize.STANDARD_BAY, "GATE-A");
        System.out.println("Assigned (two-wheeler in standard bay): " + bike2Receipt);

        // 4. park a bus arriving through entrance B
        var bus = new Automobile("KA-04-0001", AutoCategory.BUS);
        var busEntry = LocalDateTime.of(2025, 1, 15, 9, 0);
        var busReceipt = garage.parkVehicle(bus, busEntry, BaySize.OVERSIZED_BAY, "GATE-B");
        System.out.println("Assigned: " + busReceipt);

        System.out.println("\nAvailability after assignments: " + garage.availability());

        // 5. car departs after 3 hours
        var carDeparture = LocalDateTime.of(2025, 1, 15, 13, 0);
        var carCharges = garage.processExit(carReceipt, carDeparture);
        System.out.println("\nCar departed. Total charges: Rs " + carCharges
                + " (3 hrs at Rs " + BaySize.STANDARD_BAY.getPricePerHour() + "/hr)");

        // 6. two-wheeler in standard bay departs after 2 hours
        // charges apply at standard bay rate since it occupied a standard bay
        var bike2Departure = LocalDateTime.of(2025, 1, 15, 13, 0);
        var bike2Charges = garage.processExit(bike2Receipt, bike2Departure);
        System.out.println("Two-wheeler (standard bay) departed. Total charges: Rs " + bike2Charges
                + " (2 hrs at Rs " + BaySize.STANDARD_BAY.getPricePerHour() + "/hr)");

        // 7. bus departs after 5 hours
        var busDeparture = LocalDateTime.of(2025, 1, 15, 14, 0);
        var busCharges = garage.processExit(busReceipt, busDeparture);
        System.out.println("Bus departed. Total charges: Rs " + busCharges
                + " (5 hrs at Rs " + BaySize.OVERSIZED_BAY.getPricePerHour() + "/hr)");

        System.out.println("\nFinal availability: " + garage.availability());
    }

    private static Bay createBay(String code, BaySize size, int level,
                                 int distFromA, int distFromB) {
        var distances = new HashMap<String, Integer>();
        distances.put("GATE-A", distFromA);
        distances.put("GATE-B", distFromB);
        return new Bay(code, size, level, distances);
    }
}
