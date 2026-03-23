package com.example.snakesladders;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class GameSession {
    private final GameBoard board;
    private final List<Player> players;
    private final Queue<Player> turnQueue;
    private final Dice dice;
    private int nextRank;

    public GameSession(GameBoard board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = new ArrayList<>(players);
        this.turnQueue = new LinkedList<>(players);
        this.dice = dice;
        this.nextRank = 0;
    }

    public void play() {
        displaySetup();

        while (countRemainingPlayers() >= 2) {
            var current = turnQueue.poll();
            if (current == null) break;

            if (current.isFinished()) continue;

            executeTurn(current);

            // re-enqueue the player if they haven't completed
            if (!current.isFinished()) {
                turnQueue.add(current);
            }
        }

        // assign final rank to any remaining active player
        for (var player : players) {
            if (!player.isFinished()) {
                nextRank++;
                player.setRank(nextRank);
                player.setFinished(true);
            }
        }

        showRankings();
    }

    private void executeTurn(Player player) {
        var roll = dice.roll();
        var oldPos = player.getPosition();
        var newPos = oldPos + roll;

        System.out.print(player.getName() + " got " + roll + " | position " + oldPos);

        // cannot advance past the final square
        if (newPos > board.lastSquare()) {
            System.out.println(" -> blocked (exceeds square " + board.lastSquare() + ")");
            return;
        }

        // check whether a connector (snake/ladder) exists at this square
        var resolved = board.resolvePosition(newPos);
        if (resolved != newPos) {
            var kind = resolved < newPos ? "Bitten by snake!" : "Climbed a ladder!";
            System.out.println(" -> " + newPos + " " + kind + " -> " + resolved);
            newPos = resolved;
        } else {
            System.out.println(" -> " + newPos);
        }

        player.moveTo(newPos);

        if (newPos == board.lastSquare()) {
            nextRank++;
            player.setRank(nextRank);
            player.setFinished(true);
            System.out.println("  " + player.getName() + " finished! Rank: #" + nextRank);
        }
    }

    private int countRemainingPlayers() {
        return (int) players.stream()
                .filter(p -> !p.isFinished())
                .count();
    }

    private void displaySetup() {
        System.out.println("\n--- Snakes and Ladders Game ---");
        System.out.println("Total squares: " + board.getSize());
        System.out.println("Snakes:  " + board.getSnakes());
        System.out.println("Ladders: " + board.getLadders());
        System.out.println("Participants: " + players);
        System.out.println();
    }

    private void showRankings() {
        System.out.println("\n--- Game Results ---");
        for (var r = 1; r <= players.size(); r++) {
            for (var p : players) {
                if (p.getRank() == r) {
                    System.out.println("#" + r + " - " + p.getName());
                }
            }
        }
    }
}
