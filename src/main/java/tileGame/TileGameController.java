package tileGame;

import Game2048.buttonStrategies.ButtonStrategyI;

public class TileGameController {
    private final TileGameModel gameModel;

    public TileGameController(TileGameModel aGameModel) {
        this.gameModel = aGameModel;
    }

    public void handleButtonPress(ButtonStrategyI buttonStrategy){
        buttonStrategy.executeMethod(gameModel);
    }
}
