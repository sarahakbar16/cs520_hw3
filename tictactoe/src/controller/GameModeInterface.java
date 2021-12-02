package controller;

import model.RowGameModel;

/**
 * Interface that handles the game mode the user inputs (either tictactoe or three in a row)
 */
public interface GameModeInterface {
    /**
     * Checks to see whether the move the user is making is legal according to the game mode rules 
     * @param blockIndex the block the user is trying to make their move in
     * @param gameModel the current game
     * @param reset whether the game is being reset
     * @return
     */
    public boolean CheckIsLegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset);
}