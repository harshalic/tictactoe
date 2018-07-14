package org.harshalic.tictactoe;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    public static final char[][] EmptyBoard = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '},
    };

    public static char whoseTurn(char[][] puzzle) {
        int x = 0;
        int o = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == 'X') {
                    x++;
                } else if (puzzle[i][j] == 'O') {
                    o++;
                }
            }
        }
        return x > o ? 'O' : 'X';
    }

    public static boolean isGameWon(char[][] state, char player) {
        for (int k = 0; k < 3; k++) {
            if (state[k][0] == player && state[k][1] == player && state[k][2] == player) {
                return true;
            }
            if (state[0][k] == player && state[1][k] == player && state[2][k] == player) {
                return true;
            }
        }
        if (state[0][0] == player && state[1][1] == player && state[2][2] == player) {
            return true;
        }
        if (state[0][2] == player && state[1][1] == player && state[2][0] == player) {
            return true;
        }
        return false;
    }

    public static ArrayList<Cell> getEmptyCells(char[][] puzzle) {
        ArrayList<Cell> cells = new ArrayList<Cell>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle[i][j] == ' ') {
                    cells.add(new Cell(i, j));
                }
            }
        }
        return cells;
    }

    public static int getNumRepresentation(char[][] puzzle) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        charMap.put(' ', 0);
        charMap.put('X', 1);
        charMap.put('O', 2);

        int ans = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ans = ans * 3 + charMap.get(puzzle[i][j]);
            }
        }
        return ans;
    }
}
