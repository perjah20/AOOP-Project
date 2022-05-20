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
        switch (gameModel.getLastEvent()) {
            case GAME_WON      -> System.out.println("Generate sound GAME_WON");
            case TRIED_TO_MOVE -> System.out.println("Generate sound TRIED_TO_MOVE");
            case RESET_GAME    -> System.out.println("Generate sound RESET_GAME");
            case MOVED_PLAYER  -> System.out.println("Generate sound MOVED_PLAYER");
            case MOVED_CRATE   -> System.out.println("Generate sound MOVED_BOX");
            case FILLED_CRATE  -> System.out.println("Generate filled box sound");
        }
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
