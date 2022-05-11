package sokoban;

import tileGame.TileGame;
import tileGame.TileGameGUI;
import tileGame.TileGameModel;

public class SokobanGame extends TileGame {
    private SokobanGameModel sokobanGameModel;

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
                sokobanGameModel.moveCharacter(SokobanGameModel.directions.NORTH);
            }

            @Override
            protected void eastButtonPressed() {
                sokobanGameModel.moveCharacter(SokobanGameModel.directions.EAST);
            }

            @Override
            protected void southButtonPressed() {
                sokobanGameModel.moveCharacter(SokobanGameModel.directions.SOUTH);
            }

            @Override
            protected void westButtonPressed() {
                sokobanGameModel.moveCharacter(SokobanGameModel.directions.WEST);
            }
        };
    }


    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        super(9,8 );
        getTileGameModel().updateGameGrid(new SokobanInfo().getLevel(0));
        sokobanGameModel = (SokobanGameModel) getTileGameModel();
    }
}
