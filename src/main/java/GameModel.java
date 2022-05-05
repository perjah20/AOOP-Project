public class GameModel {

    /**
     * Constructs a GameModel object.
     * @param rows Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public GameModel(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Number of rows and columns has to be positive int's.");
        gameGrid = new int[rows][columns];
        rowLength = rows;
        columnLength = columns;
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

    private int getRow(int position) {
        return position/rowLength;
    }
    private int getColumn(int position) {
        return position%columnLength;
    }

    private final int[][] gameGrid;
    private final int rowLength,columnLength;
}
