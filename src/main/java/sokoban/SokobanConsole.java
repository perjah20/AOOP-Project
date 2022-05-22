package sokoban;

import tilegame.GameObserver;

/**
 * The SokobanConsole class is a class that shows a different view of the game board.
 */
public class SokobanConsole implements GameObserver<SokobanGameModel> {
    /**
     * Prints the last event and
     * displays a model of the current game map in the console.
     * @param gameModel The game model to get information from
     */
    @Override
    public void updateGameObserver(SokobanGameModel gameModel) {
        System.out.println("LastEvent: "+gameModel.getLastEvent());
        int[][] gameState = gameModel.getGameState();
        for (int[] row :gameState) {
            StringBuilder string = new StringBuilder("|\t");
            for (int column :row) {
                string.append(column).append("\t");
            }
            System.out.println(string.append("|"));
        }
        System.out.println();
    }
}
