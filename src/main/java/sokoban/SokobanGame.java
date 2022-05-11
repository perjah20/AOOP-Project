package sokoban;

import tileGame.TileGame;
import tileGame.TileGameGUI;
import tileGame.TileGameModel;

public class SokobanGame  {
    private SokobanGameModel sokobanGameModel;
    private SokobanGameGUI sokobanGameGUI;
    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        sokobanGameModel = new SokobanGameModel(9,8);
        sokobanGameGUI = new SokobanGameGUI(9,8) {
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
        sokobanGameModel.addGameObserver(sokobanGameGUI);
        sokobanGameModel.addGameObserver(sokobanGameModel);
        sokobanGameModel.addGameObserver(gameState -> {
            for (int[] row :gameState) {
                String string = "[";
                for (int column :row) {
                    string += column+",";
                }
                System.out.println(string+"]");
            }
            System.out.println();
        });
        sokobanGameModel.updateGameGrid(SokobanInfo.level1);

    }
}
