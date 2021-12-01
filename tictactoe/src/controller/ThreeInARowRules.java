package controller;

import model.RowGameModel;

public class ThreeInARowRules implements GameModeInterface {
    public ThreeInARowRules(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
    }

    @Override
    public boolean CheckIslegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
        if (reset == 1) {
            if (blockIndex.getRow() == 2)
                return true;
            else
                return false;
        } else {
            if ((blockIndex == null) ||
                    (gameModel.blocksData[blockIndex.getRow()][blockIndex.getColumn()].getIsLegalMove() == false)) {
                return false;
            }
            if (blockIndex.getRow() != 2) {
                if ((gameModel.blocksData[blockIndex.getRow() + 1][blockIndex.getColumn()].getIsLegalMove() == true)) {
                    return false;
                } else
                    return true;
            }

            if (blockIndex.getRow() != 0) {
                gameModel.blocksData[blockIndex.getRow() - 1][blockIndex.getColumn()].setIsLegalMove(true);
                return true;
            }
        }
        return false;
    }
}
