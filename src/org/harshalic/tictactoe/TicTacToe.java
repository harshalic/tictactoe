package org.harshalic.tictactoe;

public class TicTacToe {
    private IReader reader;
    private IWriter writer;
    private IPlayer player;

    public TicTacToe(IReader reader, IWriter writer, IPlayer player) {
        this.reader = reader;
        this.writer = writer;
        this.player = player;
    }

    public boolean playMove() {
        char[][] gameState = this.reader.readGameState();
        if (this.player.playMove(gameState)) {
            this.writer.writeGameState(gameState);
            return true;
        }
        return false;
    }

}
