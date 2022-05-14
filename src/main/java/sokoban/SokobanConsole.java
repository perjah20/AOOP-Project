package sokoban;

import tileGame.GameObserver;
import tileGame.TileGameModel;

public class SokobanConsole implements GameObserver {
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
