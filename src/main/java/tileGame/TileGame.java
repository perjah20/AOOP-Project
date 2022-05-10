package tileGame;

public abstract class TileGame {
    protected TileGameModel tileGameModel;
    protected TileGameGUI tileGameGUI;

    protected abstract TileGameModel addTileGameModel(int rows, int columns) ;
    protected abstract TileGameGUI addTileGameGUI(int rows, int columns);

    /**
     * Creates a new tile based game with specified dimensions
     * @param rows - Amount of tiles in vertically (y-axis).
     * @param columns- Amount of tiles in horizontally (x-axis).
     */
    public TileGame(int rows, int columns){
        tileGameModel = addTileGameModel(rows, columns);
        tileGameGUI = addTileGameGUI(rows, columns);
        tileGameModel.addGameObserver(tileGameGUI);
    }

    protected TileGameModel getTileGameModel() {
        return tileGameModel;
    }

    protected TileGameGUI getTileGameGUI() {
        return tileGameGUI;
    }
}