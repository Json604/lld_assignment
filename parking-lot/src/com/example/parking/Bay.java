package com.example.parking;

import java.util.Map;
import java.util.TreeMap;

public class Bay {
    private final String bayCode;
    private final BaySize size;
    private final int level;
    private final TreeMap<String, Integer> proximityMap;
    private boolean taken;

    public Bay(String bayCode, BaySize size, int level,
               Map<String, Integer> proximityMap) {
        this.bayCode = bayCode;
        this.size = size;
        this.level = level;
        this.proximityMap = new TreeMap<>(proximityMap);
        this.taken = false;
    }

    public String getBayCode() {
        return bayCode;
    }

    public BaySize getSize() {
        return size;
    }

    public int getLevel() {
        return level;
    }

    public boolean isTaken() {
        return taken;
    }

    public int proximityTo(String gateId) {
        return proximityMap.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    public void markInUse() {
        this.taken = true;
    }

    public void markFree() {
        this.taken = false;
    }

    @Override
    public String toString() {
        return bayCode + " [size=" + size + ", level=" + level + ", taken=" + taken + "]";
    }
}
