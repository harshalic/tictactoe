package org.harshalic.tictactoe.player;

import org.harshalic.tictactoe.Cell;
import org.harshalic.tictactoe.IPlayer;
import org.harshalic.tictactoe.Utils;

import java.util.ArrayList;

public class StatisticalPlayer implements IPlayer {

    @Override
    public boolean playMove(char[][] puzzle) {
        char turn = Utils.whoseTurn(puzzle);
        ArrayList<Cell> emptyCells = Utils.getEmptyCells(puzzle);
        if (emptyCells.isEmpty()) {
            return false;
        }

        Cell update = null;
        double chance = -1.0;
        for (Cell cell: emptyCells) {
            puzzle[cell.x][cell.y] = turn;
            int c = Utils.getNumRepresentation(puzzle);

            if (turn == 'X') {
                if (chance < StatisticalTree.numXWins[c]) {
                    chance = StatisticalTree.numXWins[c];
                    update = cell;
                }
            } else {
                if (chance < StatisticalTree.numOWins[c]) {
                    chance = StatisticalTree.numOWins[c];
                    update = cell;
                }
            }

            puzzle[cell.x][cell.y] = ' ';
        }

        puzzle[ update.x ][ update.y ] = turn;
        return true;
    }
}
