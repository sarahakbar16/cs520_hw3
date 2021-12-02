package controller;

import model.RowGameModel;

/**
 * Class that implements the rules for tic tac toe 
 */
public class TicTacToeRules implements GameModeInterface {

    /**
     * Constructor for TicTacToeRules
     * @param blockIndex
     * @param gameModel
     * @param reset
     */
    public TicTacToeRules(BlockIndex blockIndex, RowGameModel gameModel, int reset) {}

    /**
    * Implementation of CheckIsLegalMove according to tictactoe rules 
    */
    @Override
    public boolean CheckIsLegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
        if (reset == 1)
            return true;
        else {
            if ((blockIndex == null) ||
                    (gameModel.blocksData[blockIndex.getRow()][blockIndex.getColumn()].getIsLegalMove() == false))
                return false;
            else
                return true;
        }
    }

}
