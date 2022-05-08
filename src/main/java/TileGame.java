import javax.swing.*;

public abstract class TileGame {
    private GameModel gameModel;
    private GameGUI gameGUI;

    protected abstract GameModel addGameModel();
    protected abstract GameGUI addGameGUI();

    /**
     * Constructs a new Game object from methods implemented by user.
     */
    public TileGame(){
        gameModel = addGameModel();
        gameGUI = addGameGUI();
    }
}
