package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Produces marker data for visualization.
 * Leverages MarkerStyleFactory to share identical style configurations.
 */
public class MapDataSource {

    private static final String[] ICON_SHAPES = {"DIAMOND", "STAR", "TRIANGLE"};
    private static final String[] ICON_COLORS = {"PURPLE", "CYAN", "YELLOW", "MAGENTA"};
    private static final int[] ICON_SIZES = {8, 11, 13, 15};

    private final MarkerStyleFactory styleCache = new MarkerStyleFactory();

    public List<MapMarker> loadMarkers(int count) {
        Random random = new Random(42);
        List<MapMarker> result = new ArrayList<>(count);

        for (int idx = 0; idx < count; idx++) {
            double latitude = 40.7000 + random.nextDouble() * 0.1500;
            double longitude = -74.0000 + random.nextDouble() * 0.1500;
            String tag = "Point-" + idx;

            String iconShape = ICON_SHAPES[random.nextInt(ICON_SHAPES.length)];
            String iconColor = ICON_COLORS[random.nextInt(ICON_COLORS.length)];
            int iconSize = ICON_SIZES[random.nextInt(ICON_SIZES.length)];
            boolean isFilled = random.nextBoolean();

            MarkerStyle sharedStyle = styleCache.get(iconShape, iconColor, iconSize, isFilled);
            result.add(new MapMarker(latitude, longitude, tag, sharedStyle));
        }
        return result;
    }
}
