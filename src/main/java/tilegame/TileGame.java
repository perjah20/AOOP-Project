package tilegame;

/**
 * The abstract class TileGame allows programmers to
 * develop their own tile based game by supplying
 * a TileGameModel, a TileGameGUI, and a TileGameController.
 * @param <T> A game model that extends the TileGameModel class.
 * @param <S> A graphical user interface that extends the TileGameGUI class.
 * @param <U> A controller for allowing any user interface to control the game.
 * @see tilegame.TileGameController
 * @see tilegame.TileGameModel
 * @see tilegame.TileGameGUI
 */
public abstract class TileGame<T extends TileGameModel,
                                    S extends TileGameGUI<U,T>,
                                        U extends TileGameController<T>> {

    /**
     * This constructor initiates a game with the supplied
     * parameters.
     * @param aTileGameModel The Game Model you want to use
     * @param aTileGameGUI  The GUI to represent the GameModel
     * @param aTileGameController The controller to alter the Game Model.
     */
    public TileGame(T aTileGameModel, S aTileGameGUI, U aTileGameController ){
        tileGameModel = aTileGameModel;
        tileGameController = aTileGameController;
        tileGameController.addGameModel(tileGameModel);
        this.tileGameGUI = aTileGameGUI;
        tileGameGUI.setGameController(tileGameController);
        tileGameModel.addGameObserver(tileGameGUI);
    }

    /**
     * This method returns the game model
     * @return TileGameModel object.
     */
    protected T getTileGameModel() {
        return tileGameModel;
    }

    /**
     * This method returns the graphical user interface
     * @return TileGameGUI object.
     */
    protected S getTileGameGUI() {
        return tileGameGUI;
    }

    /**
     * This method returns the controller for the game model
     * @return TileGameController object.
     */
    protected U getTileGameController() {
        return tileGameController;
    }

    private final T tileGameModel;
    private final S tileGameGUI;
    private final U tileGameController;
}