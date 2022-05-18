package tileGame;

public interface ButtonStrategy<T extends TileGameModel> {
    void executeMethod(T gameModel);
}
