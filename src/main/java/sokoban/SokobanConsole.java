package sokoban;

import tileGame.GameObserver;

public class SokobanConsole implements GameObserver<SokobanGameModel> {
    @Override
    public void updateGameObserver(SokobanGameModel gameModel) {
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
