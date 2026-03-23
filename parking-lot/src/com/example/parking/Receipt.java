package com.example.parking;

import java.time.Duration;
import java.time.LocalDateTime;

public class Receipt {
    private final String receiptCode;
    private final Automobile automobile;
    private final Bay bay;
    private final LocalDateTime entryTime;

    public Receipt(String receiptCode, Automobile automobile,
                   Bay bay, LocalDateTime entryTime) {
        this.receiptCode = receiptCode;
        this.automobile = automobile;
        this.bay = bay;
        this.entryTime = entryTime;
    }

    public String getReceiptCode() {
        return receiptCode;
    }

    public Automobile getAutomobile() {
        return automobile;
    }

    public Bay getBay() {
        return bay;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    /**
     * Computes parking charges based on the allocated bay's hourly rate
     * and total hours spent inside the garage.
     */
    public int computeCharges(LocalDateTime exitTime) {
        var duration = Duration.between(entryTime, exitTime).toHours();
        if (duration < 1) {
            duration = 1;
        }
        var rate = bay.getSize().getPricePerHour();
        return (int) (duration * rate);
    }

    @Override
    public String toString() {
        return "Receipt#" + receiptCode
                + " | " + automobile
                + " | bay=" + bay.getBayCode()
                + " (" + bay.getSize() + ")"
                + " | entered=" + entryTime;
    }
}
