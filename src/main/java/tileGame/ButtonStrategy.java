package tileGame;

/**
 * This class is an interface. It allows you to create algorithms that can be run on a game model.
 * It is used in the TileGameController to perform a user specified algorithm on the game model.
 * @param <T> A game model that extends the TileGameModel class.
 */
public interface ButtonStrategy<T extends TileGameModel> {
    void executeMethod(T gameModel);
}
