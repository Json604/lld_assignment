package com.example.map;

import java.util.List;

/**
 * Displays markers in console output.
 */
public class MapRenderer {

    public void render(List<MapMarker> markers) {
        System.out.println("Displaying " + markers.size() + " data points...");
        int displayed = 0;

        for (MapMarker marker : markers) {
            if (displayed < 8) {
                System.out.println(formatMarker(marker));
                displayed++;
            }
        }

        if (markers.size() > displayed) {
            System.out.println("... (" + (markers.size() - displayed) + " additional points hidden)");
        }
    }

    private String formatMarker(MapMarker marker) {
        return String.format("%s @ (%.4f, %.4f) appearance=%s",
                marker.getLabel(), marker.getLat(), marker.getLng(), marker.getStyle());
    }
}
