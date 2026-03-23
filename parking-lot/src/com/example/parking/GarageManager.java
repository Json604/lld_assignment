package com.example.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GarageManager {
    private final List<Bay> bays;
    private int receiptSequence = 0;

    public GarageManager(List<Bay> bays) {
        this.bays = new ArrayList<>(bays);
    }

    // locates the closest compatible bay from the specified entrance, assigns the automobile, and returns a receipt
    public Receipt parkVehicle(Automobile automobile, LocalDateTime entryTime,
                               BaySize requestedSize, String entranceId) {
        var selectedBay = findNearestBay(automobile, requestedSize, entranceId);

        if (selectedBay == null) {
            throw new IllegalStateException("No open " + requestedSize
                    + " bay available for " + automobile);
        }

        selectedBay.markInUse();
        receiptSequence++;
        var code = "R-" + receiptSequence;
        return new Receipt(code, automobile, selectedBay, entryTime);
    }

    // computes the total charge and frees up the bay
    public int processExit(Receipt receipt, LocalDateTime exitTime) {
        var totalAmount = receipt.computeCharges(exitTime);
        receipt.getBay().markFree();
        return totalAmount;
    }

    // reports how many bays of each size are currently unoccupied
    public Map<BaySize, Long> availability() {
        return bays.stream()
                .filter(bay -> !bay.isTaken())
                .collect(Collectors.groupingBy(Bay::getSize, Collectors.counting()));
    }

    // private helper that scans all bays to find the nearest unoccupied bay matching the requested size
    private Bay findNearestBay(Automobile automobile, BaySize requestedSize, String entranceId) {
        return bays.stream()
                .filter(bay -> !bay.isTaken())
                .filter(bay -> bay.getSize() == requestedSize)
                .filter(bay -> automobile.getCategory().canAccommodate(bay.getSize()))
                .min(Comparator.comparingInt(bay -> bay.proximityTo(entranceId)))
                .orElse(null);
    }
}
