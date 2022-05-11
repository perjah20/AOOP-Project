package sokoban;

import tileGame.TileGame;
import tileGame.TileGameGUI;
import tileGame.TileGameModel;

public class SokobanGame extends TileGame {

    @Override
    protected TileGameModel addTileGameModel(int rows, int columns) {
        return new TileGameModel(rows, columns) {
            @Override
            protected void gameOver() {

            }

            @Override
            protected void gameWon() {

            }
        };
    }

    @Override
    protected TileGameGUI addTileGameGUI(int rows, int columns) {
        return new SokobanGameGUI(rows, columns) {
            @Override
            protected void northButtonPressed() {

            }

            @Override
            protected void eastButtonPressed() {

            }

            @Override
            protected void southButtonPressed() {

            }

            @Override
            protected void westButtonPressed() {

            }
        };
    }


    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        super(9,8 );
        System.out.print("");
        tileGameModel.updateGameGrid(new SokobanInfo().getLevel(0));
    }
}
