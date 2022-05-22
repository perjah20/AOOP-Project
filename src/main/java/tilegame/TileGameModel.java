package tilegame;

import java.util.ArrayList;

/**
 * The class TileGameModel allows programmers to
 * develop their own tile game model and manipulate it.
 */
public abstract class TileGameModel  {

    /**
     * Constructs a GameModel object of size 1x1.
     */
    public TileGameModel() {
        gameGrid = new int[1][1];
    }

    /**
     * Updates the game grid to a new game grid and informs observes of the update.
     * @param newGameGrid This is the new game grid
     * @throws IllegalArgumentException if game grid is smaller than 1x1
     */
    public void updateGameGrid(int[][] newGameGrid) {
        if (newGameGrid.length < 1 || newGameGrid[0].length < 1)
            throw new IllegalArgumentException("Illegal dimension, has to be at least 1x1");
        gameGrid = newGameGrid;
        updateObservers();
    }

    /**
     * Gets an element at a particular row and column position
     * @param row - Specified Row
     * @param column - Specified Column
     * @throws IndexOutOfBoundsException if row or column are outside game grid.
     * @return Element at specified position
     *
     */
    public int getTileState(int row, int column) {
        return gameGrid[row][column];
    }

    /**
     * Gets an element at tile position counting from 0 to rows * columns.
     * @param position A position between 0 and rows * columns
     * @throws IndexOutOfBoundsException if position is outside game grid.
     * @return Element at specified position
     */
    public int getTileState(int position) {
        return gameGrid[getRow(position)][getColumn(position)];
    }

    /**
     * Sets an element at a particular row and column position
     * @param value - New value you want to set at particular position
     * @param row - Specified Row
     * @param column - Specified Column
     * @throws IndexOutOfBoundsException if position is outside game grid.
     */
    public void setTileState(int value, int row, int column) {
        gameGrid[row][column] = value;
    }

    /**
     * Gets an element at tile position counting from 0 to rows * columns.
     * @param position A position between 0 and rows * columns
     * @throws IndexOutOfBoundsException if position is outside game grid.
     */
    public void setTileState(int value, int position) {
        gameGrid[getRow(position)][getColumn(position)] = value;
    }

    /**
     * Gets the grid for the game
     * @return A 2D-array containing the current game grid.
     */
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
    public void updateObservers() {
        for (GameObserver gameObserver : gameObservers)
            gameObserver.updateGameObserver(this);
    }

    /**
     * Calculates which row a position corresponds to.
     * @param position a tile position counting from 0 to rows*columns.
     * @return A row
     */
    private int getRow(int position) { return position/getRows(); }
    /**
     * Calculates which column a position corresponds to.
     * @param position a tile position counting from 0 to rows*columns.
     * @return A column
     */
    private int getColumn(int position) { return position%getColumns(); }

    /**
     * Gets the number of rows in a matrix
     * @return The number of rows in the matrix
     */
    public int getRows() {
        return gameGrid.length;
    }
    /**
     * Gets the number of columns in a matrix
     * @return The number of columns in the matrix
     */
    public int getColumns() {
        return gameGrid[0].length;
    }

    private int[][] gameGrid; /** Our Game Grid for keeping track of game state. **/
    ArrayList<GameObserver> gameObservers = new ArrayList<>();
}
