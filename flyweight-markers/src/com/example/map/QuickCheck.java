package com.example.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Validation utility to verify style object sharing.
 *
 * Before Flyweight: reports high count (near total marker count).
 * After Flyweight: reports low count (limited by unique style combinations).
 */
public class QuickCheck {

    public static void main(String[] args) {
        int markerCount = 20_000;

        MapDataSource source = new MapDataSource();
        List<MapMarker> allMarkers = source.loadMarkers(markerCount);

        Set<Integer> uniqueStyleIds = new HashSet<>();
        for (MapMarker marker : allMarkers) {
            uniqueStyleIds.add(System.identityHashCode(marker.getStyle()));
        }

        System.out.println("Total markers: " + markerCount);
        System.out.println("Unique style objects (identity check): " + uniqueStyleIds.size());
        System.out.println("Maximum expected with Flyweight: <= " + (3 * 4 * 4 * 2) + " (shapes*colors*sizes*filled)");
    }
}
