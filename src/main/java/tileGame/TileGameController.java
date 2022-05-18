package tileGame;

public class TileGameController<T extends TileGameModel> {
    private T gameModel;

    public TileGameController() {}

    public void addGameModel(T aGameModel) {
        gameModel = aGameModel;
    }

    public void handleButtonPress(ButtonStrategy<T> buttonStrategy){
        buttonStrategy.executeMethod(gameModel);
    }
}
