package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory that manages shared MarkerStyle instances.
 * Returns cached styles for identical shape/color/size/filled combinations.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> stylePool = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String compositeKey = shape + "|" + color + "|" + size + "|" + (filled ? "T" : "F");
        return stylePool.computeIfAbsent(compositeKey, 
            k -> new MarkerStyle(shape, color, size, filled));
    }

    public int cacheSize() {
        return stylePool.size();
    }
}
