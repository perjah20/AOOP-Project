public abstract class TileGame {
    private TileGameModel tileGameModel;
    private GameGUI gameGUI;

    protected abstract TileGameModel addGameModel();
    protected abstract GameGUI addGameGUI();

    /**
     * Creates a new tile based game with specified dimensions
     * @param rows - Amount of tiles in vertically (y-axis).
     * @param columns- Amount of tiles in horizontally (x-axis).
     */
    public TileGame(int rows, int columns){
        tileGameModel = addGameModel();
        gameGUI = addGameGUI();
    }
}
