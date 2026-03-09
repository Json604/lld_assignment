package com.example.map;

/**
 * Represents a single data point marker.
 *
 * Extrinsic (unique): lat, lng, label
 * Intrinsic (shared): style
 */
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style;

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat()        { return lat; }
    public double getLng()        { return lng; }
    public String getLabel()      { return label; }
    public MarkerStyle getStyle() { return style; }
}
