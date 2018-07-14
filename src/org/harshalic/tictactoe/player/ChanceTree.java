package org.harshalic.tictactoe.player;

import org.harshalic.tictactoe.Cell;
import org.harshalic.tictactoe.Utils;
import org.harshalic.tictactoe.output.ConsoleWriter;

import java.util.ArrayList;

public class ChanceTree {
    private static final int MAX = 19700;

    public static double[] chanceWin = new double[MAX];
    public static double[] chanceNoLoss = new double[MAX];

    private static char[][] state = new char[3][3];

    public static void buildTree() {
        for (int i = 0; i < MAX; i++) {
            chanceWin[i] = chanceNoLoss[i] = -1;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = Utils.EmptyBoard[i][j];
            }
        }
        buildTree(0, 'X', 'O');
    }

    private static void buildTree(int root, char player, char other) {
        if (chanceWin[root] > -1) {
            return;
        }

        if (Utils.isGameWon(state, other)) {
            // Other has won with player's turn,
            // Player's chance at this state is 0.
            chanceWin[root] = chanceNoLoss[root] = 0.0;
            return;
        }

        if (Utils.isGameWon(state, player)) {
            // Player has won in this state.
            chanceWin[root] = chanceNoLoss[root] = 1.0;
            return;
        }

        ArrayList<Cell> cells = Utils.getEmptyCells(state);
        if (cells.isEmpty()) {
            // Player can't win from this state.
            // Player can't lose from this state.
            chanceWin[root] = 0.0;
            chanceNoLoss[root] = 1.0;
            return;
        }

        ArrayList<Integer> children = new ArrayList<>();
        for (Cell cell : cells) {
            state[cell.x][cell.y] = player;
            int numRepresentation = Utils.getNumRepresentation(state);
            children.add(numRepresentation);
            buildTree(numRepresentation, other, player);
            state[cell.x][cell.y] = ' ';

            // The current state is as good as for the player as the children are bad for other.
            // Chances of win for player at this point by taking this move are same as
            // chances of loss for other player.
            chanceWin[root] = Math.max(chanceWin[root], 1 - chanceNoLoss[numRepresentation]);
            // No loss = win || draw.
            // Chance of player having no loss at this state with taking this move is
            // chance of other at the other state for complement of win.
            chanceNoLoss[root] = Math.max(chanceNoLoss[root], 1 - chanceWin[numRepresentation]);
        }

        // (new ConsoleWriter()).writeGameState(state);
        // System.out.println(String.format("i, %d, toPlay, %c, chance(win), %5.4f, chance(noloss), %5.4f", root, player,
        //        chanceWin[root], chanceNoLoss[root]));
        // System.out.println();
    }

}
