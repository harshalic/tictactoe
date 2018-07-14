package org.harshalic.tictactoe.output;

import org.harshalic.tictactoe.IWriter;

public class MemoryWriter implements IWriter {
    public char[][] state;

    public MemoryWriter() {
        this.state = new char[3][3];
    }

    @Override
    public void writeGameState(char[][] puzzle) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.state[i][j] = puzzle[i][j];
            }
        }
    }

    public char[][] getGameState() {
        return this.state;
    }
}
