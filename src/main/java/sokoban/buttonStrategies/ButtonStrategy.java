package sokoban.buttonStrategies;

import tileGame.TileGameModel;

public interface ButtonStrategy<T extends TileGameModel> {
    void executeMethod(T gameModel);
}
