package org.harshalic.tictactoe.player;

import org.harshalic.tictactoe.Cell;
import org.harshalic.tictactoe.IPlayer;
import org.harshalic.tictactoe.Utils;

import java.util.List;
import java.util.Random;

public class RandomPlayer implements IPlayer {

    private Random random;

    public RandomPlayer(int seed) {
        this.random = seed == -1 ? new Random() : new Random(seed);
    }

    @Override
    public boolean playMove(char[][] puzzle) {
        char turn = Utils.whoseTurn(puzzle);

        List<Cell> cells = Utils.getEmptyCells(puzzle);
        if (cells.isEmpty()) {
            return false;
        }

        int ind = random.nextInt(cells.size());
        puzzle[cells.get(ind).x][cells.get(ind).y] = turn;
        return true;
    }
}
