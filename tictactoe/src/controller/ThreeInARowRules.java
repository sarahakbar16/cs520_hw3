package controller;

import model.RowGameModel;

/**
 * Class that implements the rules for three in a row
 */
public class ThreeInARowRules implements GameModeInterface {

    /**
     * Constructor for ThreeInARowRules
     * 
     * @param blockIndex
     * @param gameModel
     * @param reset
     */
    public ThreeInARowRules(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
    }

    /**
    * Implementation of CheckIsLegalMove according to Three in a row rules 
    */
    @Override
    public boolean CheckIsLegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset) {
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
