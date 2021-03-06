package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.Player;
import model.RowGameModel;
import view.RowGameGUI;

public class RowGameController {
	public RowGameModel gameModel;
	public RowGameGUI gameView;
	public String mode;

	/**
	 * Creates a new game initializing the GUI
	 * 
	 * @param mode the game mode inputted by the user
	 */
	public RowGameController(String mode) {
		gameModel = new RowGameModel();
		gameView = new RowGameGUI(this);

		gameModel.register(gameView);

		this.mode = mode;

		this.resetGame();
	}

	/**
	 * Internal method for getting the block index object
	 * 
	 * @param block the block we want to get the index of
	 * @return the block index of the block
	 */
	private BlockIndex getBlockIndex(JButton block) {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				if (block == gameView.boardGameView.blocks[row][column]) {
					return new BlockIndex(row, column);
				}
			} // end for column
		} // end for row

		return null;
	}

	/**
	 * Sets the game mode that is being used for this instance
	 */
	public class gameMode {
		private GameModeInterface gm;

		/**
		 * The constructor for the game mode
		 * 
		 * @param gm An instance of the game mode interface
		 */
		public gameMode(GameModeInterface gm) {
			this.gm = gm;
		}

		/**
		 * Calls the CheckIsLegalMove method to check whether the move follows the game
		 * mode rules
		 * 
		 * @param blockIndex the block the user is trying to make their move in
		 * @param gameModel  the current game
		 * @param reset      whether the game is being reset
		 * @return
		 */
		public boolean checkLegal(BlockIndex blockIndex, RowGameModel gameModel, int reset) {

			return gm.CheckIsLegalMove(blockIndex, gameModel, reset);
		}

	}

	/**
	 * Moves the current player into the given block.
	 *
	 * @param block The block to be moved to by the current player
	 */
	public void move(JButton block) {
		// Check the pre-conditions for a legal move
		BlockIndex blockIndex = this.getBlockIndex(block);
		gameMode gm = new gameMode(new TicTacToeRules(blockIndex, gameModel, 0));

		if (mode.equals("1")) {
			gm = new gameMode(new TicTacToeRules(blockIndex, gameModel, 0));
		} else if (mode.equals("2")) {
			gm = new gameMode(new ThreeInARowRules(blockIndex, gameModel, 0));
		} else {
			System.out.println("Please try again with a valid game mode.");
		}

		boolean legal = gm.checkLegal(blockIndex, gameModel, 0);

		if (!legal) {
			return;
		}

		if ((blockIndex == null) ||
				(gameModel.blocksData[blockIndex.getRow()][blockIndex.getColumn()].getIsLegalMove() == false)) {
			return;
		}

		gameModel.setMovesLeft(gameModel.getMovesLeft() - 1);

		if (gameModel.getPlayer().equals(Player.PLAYER_1)) {
			// Check whether player 1 won
			if (block == gameView.boardGameView.blocks[0][0]) {
				gameModel.blocksData[0][0].setContents("X");
				gameModel.blocksData[0][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
							gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][0].getContents())
									&&
									gameModel.blocksData[1][0].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))
							||
							(gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[0][1]) {
				gameModel.blocksData[0][1].setContents("X");
				gameModel.blocksData[0][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
							gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][1].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[0][2]) {
				gameModel.blocksData[0][2].setContents("X");
				gameModel.blocksData[0][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
							gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()))
							||
							(gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][2].getContents())
									&&
									gameModel.blocksData[1][2].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][0]) {
				gameModel.blocksData[1][0].setContents("X");
				gameModel.blocksData[1][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
							gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][2].getContents()))
							||
							(gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents())
									&&
									gameModel.blocksData[0][0].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][1]) {
				gameModel.blocksData[1][1].setContents("X");
				gameModel.blocksData[1][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
							gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][2].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents())
									&&
									gameModel.blocksData[0][1].getContents()
											.equals(gameModel.blocksData[2][1].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents())
									&&
									gameModel.blocksData[0][0].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents())
									&&
									gameModel.blocksData[0][2].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][2]) {
				gameModel.blocksData[1][2].setContents("X");
				gameModel.blocksData[1][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
							gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[1][0].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][0]) {
				gameModel.blocksData[2][0].setContents("X");
				gameModel.blocksData[2][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
							gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][0].getContents())
									&&
									gameModel.blocksData[1][0].getContents()
											.equals(gameModel.blocksData[0][0].getContents()))
							||
							(gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][2].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][1]) {
				gameModel.blocksData[2][1].setContents("X");
				gameModel.blocksData[2][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()) &&
							gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][1].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][2]) {
				gameModel.blocksData[2][2].setContents("X");
				gameModel.blocksData[2][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_2);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
							gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()))
							||
							(gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][2].getContents())
									&&
									gameModel.blocksData[1][2].getContents()
											.equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][0].getContents()))) {
						gameModel.setFinalResult("Player 1 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			}
		} else {
			// Check whether player 2 won
			if (block == gameView.boardGameView.blocks[0][0]) {
				gameModel.blocksData[0][0].setContents("O");
				gameModel.blocksData[0][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
							gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][0].getContents())
									&&
									gameModel.blocksData[1][0].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))
							||
							(gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[0][1]) {
				gameModel.blocksData[0][1].setContents("O");
				gameModel.blocksData[0][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()) &&
							gameModel.blocksData[0][0].getContents().equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][1].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[0][2]) {
				gameModel.blocksData[0][2].setContents("O");
				gameModel.blocksData[0][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[0][1].getContents()) &&
							gameModel.blocksData[0][1].getContents().equals(gameModel.blocksData[0][0].getContents()))
							||
							(gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][2].getContents())
									&&
									gameModel.blocksData[1][2].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][0]) {
				gameModel.blocksData[1][0].setContents("O");
				gameModel.blocksData[1][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][1].getContents()) &&
							gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][2].getContents()))
							||
							(gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[0][0].getContents())
									&&
									gameModel.blocksData[0][0].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][1]) {
				gameModel.blocksData[1][1].setContents("O");
				gameModel.blocksData[1][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[1][0].getContents()) &&
							gameModel.blocksData[1][0].getContents().equals(gameModel.blocksData[1][2].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][1].getContents())
									&&
									gameModel.blocksData[0][1].getContents()
											.equals(gameModel.blocksData[2][1].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][0].getContents())
									&&
									gameModel.blocksData[0][0].getContents()
											.equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[1][1].getContents().equals(gameModel.blocksData[0][2].getContents())
									&&
									gameModel.blocksData[0][2].getContents()
											.equals(gameModel.blocksData[2][0].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[1][2]) {
				gameModel.blocksData[1][2].setContents("O");
				gameModel.blocksData[1][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[0][2].getContents()) &&
							gameModel.blocksData[0][2].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[1][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[1][0].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][0]) {
				gameModel.blocksData[2][0].setContents("O");
				gameModel.blocksData[2][0].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
							gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][0].getContents())
									&&
									gameModel.blocksData[1][0].getContents()
											.equals(gameModel.blocksData[0][0].getContents()))
							||
							(gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][2].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][1]) {
				gameModel.blocksData[2][1].setContents("O");
				gameModel.blocksData[2][1].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()) &&
							gameModel.blocksData[2][0].getContents().equals(gameModel.blocksData[2][2].getContents()))
							||
							(gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][1].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			} else if (block == gameView.boardGameView.blocks[2][2]) {
				gameModel.blocksData[2][2].setContents("O");
				gameModel.blocksData[2][2].setIsLegalMove(false);
				gameModel.setPlayer(Player.PLAYER_1);
				if (gameModel.getMovesLeft() < 7) {
					if ((gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[2][1].getContents()) &&
							gameModel.blocksData[2][1].getContents().equals(gameModel.blocksData[2][0].getContents()))
							||
							(gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][2].getContents())
									&&
									gameModel.blocksData[1][2].getContents()
											.equals(gameModel.blocksData[0][2].getContents()))
							||
							(gameModel.blocksData[2][2].getContents().equals(gameModel.blocksData[1][1].getContents())
									&&
									gameModel.blocksData[1][1].getContents()
											.equals(gameModel.blocksData[0][0].getContents()))) {
						gameModel.setFinalResult("Player 2 wins!");
						endGame();
					} else if (gameModel.getMovesLeft() == 0) {
						gameModel.setFinalResult(RowGameModel.GAME_END_NOWINNER);
					}
				}
			}
		}

		gameView.update(gameModel);
	}

	/**
	 * Ends the game disallowing further player turns.
	 */
	public void endGame() {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				gameModel.blocksData[row][column].setIsLegalMove(false);
			}
		}

		gameView.update(gameModel);
	}

	/**
	 * Resets the game to be able to start playing again.
	 */
	public void resetGame() {
		for (int row = 0; row < 3; row++) {
			for (int column = 0; column < 3; column++) {
				gameModel.blocksData[row][column].reset();
				gameModel.blocksData[row][column].setIsLegalMove(true);
			}
		}
		gameModel.setPlayer(Player.PLAYER_1);
		gameModel.setMovesLeft(9);
		gameModel.setFinalResult(null);

		gameView.update(gameModel);
	}
}
