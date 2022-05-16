package Game2048;

import Game2048.buttonStrategies.ButtonStrategyI;
import sokoban.buttonStrategies.ButtonStrategy;

public class GameController2048 {
    private final GameModel2048 gameModel2048;

    public GameController2048(GameModel2048 aGameModel2048) {
        this.gameModel2048 = aGameModel2048;
    }

    public void handleButtonPress(ButtonStrategyI buttonStrategy){
        buttonStrategy.executeMethod(gameModel2048);
    }
}
