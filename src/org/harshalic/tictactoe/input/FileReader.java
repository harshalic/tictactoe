package org.harshalic.tictactoe.input;

import org.harshalic.tictactoe.IReader;

public class FileReader implements IReader {
    @Override
    public char[][] readGameState() {
        // TODO
        return new char[3][3];
    }
}
