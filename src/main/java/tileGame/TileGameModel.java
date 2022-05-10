package tileGame;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public abstract class TileGameModel implements ChangeListener {

    protected abstract void gameOver();

    protected abstract void gameWon();

    /**
     * Constructs a GameModel object.
     *
     * @param rows    Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public TileGameModel(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Number of rows and columns has to be positive int's.");
        gameGrid = new int[rows][columns];
    }

    /**
     * Updates the gamegrid to a new game grid
     *
     * @param newGameGrid - A new gamegrid
     * @precondition - newGameGrid has to be of same size as current gameGrid.
     */
    protected void updateGameGrid(int[][] newGameGrid) {
        // TODO Allow user to update the whole gameGrid:
        //  - Check if newGameGrid is the same size as current gamegrid
        //  - Use some smart algorithm to copy everything from newGameGrid to current this.gamegrid.
        //  Perhaps use System.arraycopy or just double forloop.
    }

    /**
     * Gets an element at a particular row and column position
     *
     * @param i - Specified Row
     * @param j - Specified Column
     * @return Element at specified position
     */
    public int getTileState(int i, int j) {
        return gameGrid[i][j];
    }

    public int getTileState(int position) {
        return gameGrid[getRow(position)][getColumn(position)];
    }

    /**
     * Sets an element at a particular row and column position
     *
     * @param value - New value you want to set at particular position
     * @param i     - Specified Row
     * @param j     - Specified Column
     */
    public void setTileState(int value, int i, int j) {
        gameGrid[i][j] = value;
    }

    public void setTileState(int value, int position) {
        gameGrid[getRow(position)][getColumn(position)] = value;
    }

    public int[][] getGameState() {
        return gameGrid;
    }

    /**
     * Adds a observer to observe the GameModel
     *
     * @param gameObserver - Observer to observe the GameModel.
     */
    public void addGameObserver(GameObserver gameObserver) {
        gameObservers.add(gameObserver);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        for (GameObserver gameObserver : gameObservers)
            gameObserver.updateGameObserver(this.gameGrid);
    }

    private int getRow(int position) {
        return position / getRows();
    }

    private int getColumn(int position) {
        return position % getColumns();
    }

    /**
     * Gets the number of rows in a matrix
     *
     * @return The number of rows in the matrix
     */
    private int getRows() {
        return gameGrid.length;
    }

    /**
     * Gets the number of columns in a matrix
     *
     * @return The number of columns in the matrix
     */
    private int getColumns() {
        return gameGrid[0].length;
    }

    private final int[][] gameGrid;
    ArrayList<GameObserver> gameObservers = new ArrayList<>();
}
