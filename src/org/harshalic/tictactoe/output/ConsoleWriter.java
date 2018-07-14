package org.harshalic.tictactoe.output;

import org.harshalic.tictactoe.IWriter;

public class ConsoleWriter implements IWriter {
    @Override
    public void writeGameState(char[][] puzzle) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(puzzle[i][j]);
                if (j != 2) {
                    System.out.print('|');
                }
            }
            System.out.println();
            if (i != 2) {
                System.out.println("-----");
            }
        }
    }
}
