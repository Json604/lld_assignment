package com.example.map;

import java.util.List;

/**
 * Main entry point demonstrating the flyweight pattern.
 */
public class App {

    public static void main(String[] args) {
        int totalMarkers = 30_000;

        MapDataSource dataSource = new MapDataSource();
        List<MapMarker> markerList = dataSource.loadMarkers(totalMarkers);

        MapRenderer renderer = new MapRenderer();
        renderer.render(markerList);

        System.out.println();
        System.out.println("Verify style object sharing with QuickCheck:");
        System.out.println("  java com.example.map.QuickCheck");
    }
}
