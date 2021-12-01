package controller;

import model.RowGameModel;

public class TicTacToeRules implements GameModeInterface {

    public TicTacToeRules(BlockIndex blockIndex, RowGameModel gameModel, int reset) {}

    @Override
    public boolean CheckIslegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
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
