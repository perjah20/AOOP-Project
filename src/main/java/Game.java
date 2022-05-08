import javax.swing.*;

public abstract class Game {
    private GameModel gameModel;
    private GameGUI gameGUI;

    /**
     * Constructs a new Game object with supplied GameGUI and GameModel.
     * @param aGameModel - A GameModel to represent the game in an abstract way.
     * @param aGameGUI - A interactive GUI that allows you to see and play the game.
     */
    public Game(GameModel aGameModel, GameGUI aGameGUI){
        gameModel = aGameModel;
        gameGUI = aGameGUI;
    }


}
