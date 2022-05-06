import java.util.ArrayList;

public abstract class GameModel extends GameGUI {

    protected abstract void gameOver();
    protected abstract void gameWon();

    /**
     * Constructs a GameModel object.
     * @param rows Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public GameModel(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Number of rows and columns has to be positive int's.");
        gameGrid = new int[rows][columns];
    }

    public GameModel(int[][] aGameGrid) {
        this.gameGrid = aGameGrid;
    }


    /**
     * Gets an element at a particular row and column position
     * @param i - Specified Row
     * @param j - Specified Column
     * @return Element at specified position
     */
    public int getTileState(int i, int j) {
        return gameGrid[i][j];
    }

    public int getTileState(int position) { return gameGrid[getRow(position)][getColumn(position)];}

    /**
     * Sets an element at a particular row and column position
     * @param value - New value you want to set at particular position
     * @param i - Specified Row
     * @param j - Specified Column
     */
    public void setTileState(int value, int i, int j) {
        gameGrid[i][j] = value;
    }

    public void setTileState(int value, int position) { gameGrid[getRow(position)][getColumn(position)] = value;}

    public int[][] getGameState() {
        return gameGrid;
    }

    /**
     * Adds a observer to observe the GameModel
     * @param gameObserver - Observer to observe the GameModel.
     */
    public void addGameObserver(GameObserver gameObserver) {
        gameObservers.add(gameObserver);
    }

    /**
     * Informs all gameObservers that the gameState has been changed.
     */
    private void updateObservers() {
        for (GameObserver gameObserver : gameObservers)
            gameObserver.updateGameObserver(this.gameGrid);
    }

    private int getRow(int position) { return position/getRows(); }
    private int getColumn(int position) { return position%getColumns(); }

    /**
     * Gets the number of rows in a matrix
     * @return The number of rows in the matrix
     */
    private int getRows() {
        return gameGrid.length;
    }
    /**
     * Gets the number of columns in a matrix
     * @return The number of columns in the matrix
     */
    private int getColumns() {
        return gameGrid[0].length;
    }

    private final int[][] gameGrid;
    ArrayList<GameObserver> gameObservers = new ArrayList<>();
}
