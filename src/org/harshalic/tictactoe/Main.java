package org.harshalic.tictactoe;

import org.harshalic.tictactoe.input.MemoryReader;
import org.harshalic.tictactoe.output.ConsoleWriter;
import org.harshalic.tictactoe.output.MemoryWriter;
import org.harshalic.tictactoe.player.*;

public class Main {


    private int numXwins = 0;
    private int numOwins = 0;
    private int numDraws = 0;

    public static void main(String[] args) {
        ChanceTree.buildTree();
        StatisticalTree.buildTree();


        Main program = new Main();
        // program.playGames(100, 100000, new RandomPlayer(-1), new RandomPlayer(-1));
        // program.playGames(100, 100000, new RandomPlayer(-1), new StatisticalPlayer());
        // program.playGames(100, 100000, new RandomPlayer(-1), new OptimalPlayer());

        // program.playGames(100, 100000, new StatisticalPlayer(), new RandomPlayer(-1));
        // program.playGames(100, 100000, new StatisticalPlayer(), new StatisticalPlayer());
        // program.playGames(100, 100000, new StatisticalPlayer(), new OptimalPlayer());

        // program.playGames(100, 100000, new OptimalPlayer(), new RandomPlayer(-1));
        // program.playGames(100, 100000, new OptimalPlayer(), new StatisticalPlayer());
        program.playGames(100, 100000, new OptimalPlayer(), new OptimalPlayer());
    }

    public void playGames(int numIterations, int numRounds,IPlayer playerType1, IPlayer playerType2) {
        for (int iterations = 0; iterations < numIterations; iterations++) {
            numXwins = numOwins = numDraws = 0;
            for (int game = 0; game < numRounds; game++) {
                this.playGame(false, playerType1, playerType2);
            }

            System.out.println(String.format("#wins(X), %d, #wins(O), %d, #draws, %d", numXwins, numOwins, numDraws));
        }
    }

    public void playGame(boolean printEndState, IPlayer playerType1, IPlayer playerType2) {
        MemoryReader memoryReader1 = new MemoryReader();
        MemoryReader memoryReader2 = new MemoryReader();
        MemoryWriter memoryWriter1 = new MemoryWriter();
        MemoryWriter memoryWriter2 = new MemoryWriter();

        TicTacToe player1 = new TicTacToe(memoryReader1, memoryWriter1, playerType1);
        TicTacToe player2 = new TicTacToe(memoryReader2, memoryWriter2, playerType2);

        memoryReader1.setGameState(Utils.EmptyBoard);
        while (true) {
            if (player1.playMove()) {
                if (Utils.isGameWon(memoryWriter1.getGameState(), 'X')) {
                    if (printEndState) {
                        (new ConsoleWriter()).writeGameState(memoryWriter1.getGameState());
                    }
                    numXwins++;
                    return;
                }
            } else {
                if (printEndState) {
                    (new ConsoleWriter()).writeGameState(memoryWriter1.getGameState());
                }
                numDraws++;
                return;
            }
            memoryReader2.setGameState(memoryWriter1.getGameState());

            if (player2.playMove()) {
                if (Utils.isGameWon(memoryWriter2.getGameState(), 'O')) {
                    if (printEndState) {
                        (new ConsoleWriter()).writeGameState(memoryWriter2.getGameState());
                    }
                    numOwins++;
                    return;
                }
            } else {
                if (printEndState) {
                    (new ConsoleWriter()).writeGameState(memoryWriter2.getGameState());
                }
                numDraws++;
                return;
            }
            memoryReader1.setGameState(memoryWriter2.getGameState());
        }
    }
}
