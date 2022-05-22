package tilegame;

/**
 * The public class TileGameController allows you
 * to modify a game model.
 * @param <T> A game model that extends the TileGameModel class.
 */
public abstract class TileGameController<T extends TileGameModel> {
    /**
     * This method allows the user to add a
     * game model.
     *
     * @param aGameModel - This is the Game Model object you want to be able to manipulate.
     */
    public void addGameModel(T aGameModel) {
        gameModel = aGameModel;
    }

    /**
     * This method allows the user to execute custom
     * algorithms on the game model object.
     *
     * @param buttonStrategy - The algorithm to be executed on the game model.
     */
    public void handleButtonPress(ButtonStrategy<T> buttonStrategy){
        buttonStrategy.executeMethod(gameModel);
    }

    /**
     * Used for modifying the game model.
     */
    private T gameModel;
}
