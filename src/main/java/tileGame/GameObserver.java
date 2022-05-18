package tileGame;

public interface GameObserver<T extends TileGameModel> {
    void updateGameObserver(T gameModel);
}
