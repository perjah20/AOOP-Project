package sokoban;

import tileGame.TileGameModel;

public class SokobanGameModel extends TileGameModel {
    /**
     * Constructs a GameModel object.
     *
     * @param rows    Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public SokobanGameModel(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    protected void gameOver() {

    }

    @Override
    protected void gameWon() {

    }
}
