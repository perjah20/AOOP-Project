package sokoban;

import tileGame.GameObserver;

/**
 * The SokobanConsole class is a class that shows a different view of the game board.
 */
public class SokobanConsole implements GameObserver<SokobanGameModel> {
    /**
     * Displays a model of the current game map in the console.
     * @param gameModel
     */
    @Override
    public void updateGameObserver(SokobanGameModel gameModel) {
        int[][] gameState = gameModel.getGameState();
            for (int[] row :gameState) {
                StringBuilder string = new StringBuilder("[");
                for (int column :row) {
                    string.append(column).append(",");
                }
                System.out.println(string+"]");
            }
            System.out.println();
    }
}
