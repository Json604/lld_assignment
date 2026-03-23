package com.example.snakesladders;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.print("Board dimension (n for an n x n grid): ");
        var n = scanner.nextInt();

        System.out.print("How many players? ");
        var numPlayers = scanner.nextInt();
        scanner.nextLine();

        var players = new ArrayList<Player>();
        for (var i = 1; i <= numPlayers; i++) {
            System.out.print("Name of player " + i + ": ");
            var name = scanner.nextLine();
            players.add(new Player(name));
        }

        System.out.print("Select difficulty (easy/hard): ");
        var diffInput = scanner.nextLine();
        var difficulty = DifficultyLevel.fromInput(diffInput);

        var board = new BoardGenerator().build(n, difficulty);
        var dice = new Dice();

        var session = new GameSession(board, players, dice);
        session.play();

        scanner.close();
    }
}
