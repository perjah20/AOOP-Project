package sokoban;

import tileGame.GameObserver;

public class SokobanConsole implements GameObserver {
    @Override
    public void updateGameObserver(int[][] gameState) {
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
