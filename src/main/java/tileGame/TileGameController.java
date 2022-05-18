package tileGame;

import sokoban.buttonStrategies.ButtonStrategy;

public class TileGameController<T extends TileGameModel> {
    private T gameModel;

    public TileGameController() {}

    public void addGameModel(T aGameModel) {
        gameModel = aGameModel;
    }

    public void handleButtonPress(ButtonStrategy buttonStrategy){
        buttonStrategy.executeMethod(gameModel);
    }
}
