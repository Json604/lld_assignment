package com.example.snakesladders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class BoardGenerator {
    private final Random random;

    public BoardGenerator() {
        this.random = new Random();
    }

    public GameBoard build(int n, DifficultyLevel difficulty) {
        var totalCells = n * n;

        // adjust difficulty ranges relative to board dimensions
        var snakeMinDrop = Math.max(1, totalCells * difficulty.getSnakeMinDrop() / 100);
        var snakeMaxDrop = Math.max(snakeMinDrop + 1, totalCells * difficulty.getSnakeMaxDrop() / 100);
        var ladderMinClimb = Math.max(1, totalCells * difficulty.getLadderMinClimb() / 100);
        var ladderMaxClimb = Math.max(ladderMinClimb + 1, totalCells * difficulty.getLadderMaxClimb() / 100);

        // track cells already assigned to a connector endpoint
        Set<Integer> reservedCells = new HashSet<>();
        reservedCells.add(1);
        reservedCells.add(totalCells);

        // origin -> target mapping for cycle detection
        Map<Integer, Integer> transitions = new HashMap<>();

        List<Connector> allConnectors = new ArrayList<>();

        // generate n snakes
        for (var i = 0; i < n; i++) {
            var snake = generateSnake(totalCells, snakeMinDrop, snakeMaxDrop,
                    reservedCells, transitions);
            if (snake != null) {
                allConnectors.add(snake);
            }
        }

        // generate n ladders
        for (var i = 0; i < n; i++) {
            var ladder = generateLadder(totalCells, ladderMinClimb, ladderMaxClimb,
                    reservedCells, transitions);
            if (ladder != null) {
                allConnectors.add(ladder);
            }
        }

        return new GameBoard(totalCells, allConnectors);
    }

    private Connector generateSnake(int totalCells, int minDrop, int maxDrop,
                                    Set<Integer> reservedCells, Map<Integer, Integer> transitions) {
        for (var attempt = 0; attempt < 100; attempt++) {
            var drop = minDrop + random.nextInt(maxDrop - minDrop + 1);
            var head = drop + 1 + random.nextInt(totalCells - drop - 1);
            var tail = head - drop;

            if (tail < 1 || head >= totalCells) continue;
            if (reservedCells.contains(head) || reservedCells.contains(tail)) continue;
            // prevent cycles: tail must not coincide with another connector's origin
            if (transitions.containsKey(tail)) continue;

            reservedCells.add(head);
            reservedCells.add(tail);
            transitions.put(head, tail);
            return new Connector(head, tail);
        }
        return null;
    }

    private Connector generateLadder(int totalCells, int minClimb, int maxClimb,
                                     Set<Integer> reservedCells, Map<Integer, Integer> transitions) {
        for (var attempt = 0; attempt < 100; attempt++) {
            var climb = minClimb + random.nextInt(maxClimb - minClimb + 1);
            var start = 2 + random.nextInt(totalCells - climb - 2);
            var end = start + climb;

            if (end >= totalCells || start < 2) continue;
            if (reservedCells.contains(start) || reservedCells.contains(end)) continue;
            // prevent cycles: end must not coincide with another connector's origin
            if (transitions.containsKey(end)) continue;

            reservedCells.add(start);
            reservedCells.add(end);
            transitions.put(start, end);
            return new Connector(start, end);
        }
        return null;
    }
}
