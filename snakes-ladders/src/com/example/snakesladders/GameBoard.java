package com.example.snakesladders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameBoard {
    private final int size;
    private final List<Connector> connectors;
    private final Map<Integer, Integer> transitions;

    public GameBoard(int size, List<Connector> connectors) {
        this.size = size;
        this.connectors = new ArrayList<>(connectors);

        // build transition lookup for quick position resolution
        this.transitions = new HashMap<>();
        for (var connector : connectors) {
            transitions.put(connector.getOrigin(), connector.getTarget());
        }
    }

    public int getSize() {
        return size;
    }

    public int lastSquare() {
        return size;
    }

    public List<Connector> getSnakes() {
        return connectors.stream()
                .filter(Connector::isSnake)
                .collect(Collectors.toList());
    }

    public List<Connector> getLadders() {
        return connectors.stream()
                .filter(c -> !c.isSnake())
                .collect(Collectors.toList());
    }

    public List<Connector> getConnectors() {
        return connectors;
    }

    // determines the final position after landing on a cell
    public int resolvePosition(int cell) {
        return transitions.getOrDefault(cell, cell);
    }
}
