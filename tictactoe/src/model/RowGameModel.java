package model;

import view.RowGameView;

import java.util.HashSet;
import java.util.Set;

public class RowGameModel {
    
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    public RowBlockModel[][] blocksData = new RowBlockModel[3][3];

    /**
     * The current player taking their turn
     */
    private Player player = Player.PLAYER_1;
    private int movesLeft = 9;

    private String finalResult = null;

    /**
     * Hashmap for observers
     */
    Set<RowGameView> observers = new HashSet<>();

    /**
     * Creates a RowGameModel class with block data for a 3x3 grid
     */
    public RowGameModel() {
        super();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row
    }

    /**
     * Returns the current player
     * 
     * @return player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Sets the current player
     * 
     * @param player the current player
     */
    public void setPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("The player must be non-null.");
        }
        this.player = player;

        for (RowGameView observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Returns moves left
     * 
     * @return amount of moves left
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
     * Sets the amount of moves left
     * 
     * @param movesLeft moves left in the game
     */
    public void setMovesLeft(int movesLeft) {
        this.movesLeft = movesLeft;

        for (RowGameView observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Gets the final result of the game
     * 
     * @return the final result
     */
    public String getFinalResult() {
        return this.finalResult;
    }

    /**
     * Sets the final result of the game
     * 
     * @param finalResult rhe final result
     */
    public void setFinalResult(String finalResult) {
        this.finalResult = finalResult;

        for (RowGameView observer : observers) {
            observer.update(this);
        }

    }

    public void register(RowGameView observer) {
        this.observers.add(observer);
    }

    public void unregister(RowGameView observer) {
        this.observers.remove(observer);
    }
}
