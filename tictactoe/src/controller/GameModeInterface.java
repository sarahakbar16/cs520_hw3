package controller;

import model.RowGameModel;

public interface GameModeInterface {
    public boolean CheckIslegalMove(BlockIndex blockIndex, RowGameModel gameModel, int reset);
}