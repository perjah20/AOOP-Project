package tilegame;

/**
 * This interface class allows the programmers to
 * create observers to observe a TileGameModel
 * @param <T> A game model that extends the tileGameModel class.
 * @see tilegame.TileGameModel
 */
public interface GameObserver<T extends TileGameModel> {
    /**
     * This method is used to update an observer
     * @param gameModel A game model to read changes from.
     */
    void updateGameObserver(T gameModel);
}
