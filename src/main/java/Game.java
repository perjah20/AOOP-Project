import javax.swing.*;

public abstract class Game {
    private GameModel gameModel;
    private GameGUI gameGUI;

    public Game(GameModel aGameModel, GameGUI aGameGUI){
        gameModel = aGameModel;
        gameGUI = aGameGUI;
    }
}
