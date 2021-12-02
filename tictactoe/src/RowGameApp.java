import controller.RowGameController;

public class RowGameApp {
    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        String mode = "1"; // Set mode to tictactoe by default
        if (args.length > 0) {
            mode = args[0];
        }

        RowGameController game = new RowGameController(mode);
        game.gameView.gui.setVisible(true);
    }
}