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
        sgm = new SokobanGameModel(rows,columns);
        sgm.getGameState();
        return new SokobanGameGUI(rows, columns) {
            @Override
            protected void northButtonPressed() {
                sgm.moveCharacter(1);
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
        tileGameModel.updateGameGrid(SokobanInfo.level1);
    }
    SokobanGameModel sgm;
    //TileGameModel tileGameModel;
}






/*
int[][] {
                {0,0,6,6,6,6,6,0},
                {6,6,6,1,1,1,6,1},
                {6,2,5,3,1,1,6,0},
                {6,6,6,1,3,2,6,0},
                {6,2,6,6,3,1,6,0},
                {6,1,6,1,2,1,6,6},
                {6,3,1,4,3,3,2,6},
                {6,1,1,1,2,1,1,6},
                {6,6,6,6,6,6,6,6}
        });
*/

