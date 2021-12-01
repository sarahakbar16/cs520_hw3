import controller.RowGameController;

public class RowGameApp 
{
    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        String mode = args[0]; 
        System.out.println("My mode is" + args[0]); 
        RowGameController game = new RowGameController(mode);
        game.gameView.gui.setVisible(true);
    }
}