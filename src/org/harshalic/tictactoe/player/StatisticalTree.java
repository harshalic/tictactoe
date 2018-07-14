package org.harshalic.tictactoe.player;

import org.harshalic.tictactoe.Cell;
import org.harshalic.tictactoe.Utils;
import org.harshalic.tictactoe.output.ConsoleWriter;

import java.util.ArrayList;

public class StatisticalTree {
    private static final int MAX = 19700;

    public static int[] numXWins = new int[MAX];
    public static int[] numOWins = new int[MAX];
    public static int[] numDraws = new int[MAX];

    public static double[] winPercentage = new double[MAX];
    public static double[] noLossPercentage = new double[MAX];

    private static char[][] state = new char[3][3];

    public static void buildTree() {
        for (int i = 0; i < MAX; i++) {
            numXWins[i] = numOWins[i] = numDraws[i] = -1;
            winPercentage[i] = noLossPercentage[i] = -1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = Utils.EmptyBoard[i][j];
            }
        }

        buildTree(0, 'X', 'O');
    }

    private static void buildTree(int root, char player, char other) {
        if (numDraws[root] >= 0) {
            return;
        }

        if (Utils.isGameWon(state, 'X')) {
            numXWins[root] = 1;
            numOWins[root] = numDraws[root] = 0;
            return;
        }

        if (Utils.isGameWon(state, 'O')) {
            numOWins[root] = 1;
            numXWins[root] = numDraws[root] = 0;
            return;
        }

        int draws, xWins, oWins;
        draws = xWins = oWins = 0;
        ArrayList<Cell> cells = Utils.getEmptyCells(state);

        if (cells.isEmpty()) {
            numDraws[root] = 1;
            numXWins[root] = numOWins[root] = 0;
            return;
        }

        ArrayList<Integer> children = new ArrayList<>();
        for (Cell cell : cells) {
            state[cell.x][cell.y] = player;
            int numRepresentation = Utils.getNumRepresentation(state);
            children.add(numRepresentation);
            buildTree(numRepresentation, other, player);
            state[cell.x][cell.y] = ' ';
            draws += numDraws[numRepresentation];
            xWins += numXWins[numRepresentation];
            oWins += numOWins[numRepresentation];
        }

        numDraws[root] = draws;
        numXWins[root] = xWins;
        numOWins[root] = oWins;

        // (new ConsoleWriter()).writeGameState(state);
        // System.out.println(String.format("i, %d, #X, %d, #O, %d, #Draws, %d, nextTurn, %c", root, numXWins[root], numOWins[root], numDraws[root], player));
        // System.out.println();
    }
}
