package org.harshalic.tictactoe.input;

import org.harshalic.tictactoe.IReader;

public class MemoryReader implements IReader {
    private char[][] state;

    public MemoryReader() {
        this.state = new char[3][3];
    }

    public void setGameState(char[][] puzzle) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.state[i][j] = puzzle[i][j];
            }
        }
    }

    @Override
    public char[][] readGameState() {
        return this.state;
    }
}
