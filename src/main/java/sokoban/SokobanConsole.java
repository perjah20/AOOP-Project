package sokoban;

import tileGame.GameObserver;
import tileGame.TileGameModel;

/**
 * The SokobanConsole class is a class that shows a different view of the game board.
 */
public class SokobanConsole implements GameObserver {
    /**
     * Displays a model of the current game map in the console.
     * @param gameModel
     */
    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        int[][] gameState = gameModel.getGameState();
            for (int[] row :gameState) {
                String string = "[";
                for (int column :row) {
                    string += column+",";
                }
                System.out.println(string+"]");
            }
            System.out.println();
    }
}
