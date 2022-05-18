package tileGame;

public abstract class TileGame<T extends TileGameModel,S extends TileGameGUI, U extends TileGameController<T>> {
    private final T tileGameModel;
    private final S tileGameGUI;
    private final U tileGameController;

    /**
     * Creates a new tile based game with specified dimensions
     */
    public TileGame(T aTileGameModel, S aTileGameGUI, U aTileGameController ){
        tileGameModel = aTileGameModel;
        tileGameController = aTileGameController;
        tileGameController.addGameModel(tileGameModel);
        this.tileGameGUI = aTileGameGUI;
        tileGameGUI.setGameController(tileGameController);
        tileGameModel.addGameObserver(tileGameGUI);
    }

    protected T getTileGameModel() {
        return tileGameModel;
    }
    protected S getTileGameGUI() {
        return tileGameGUI;
    }
    protected TileGameController<T> getTileGameController() {
        return tileGameController;
    }
}