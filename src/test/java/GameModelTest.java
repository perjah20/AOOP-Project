import sokoban.SokobanGameModel;
import sokoban.SokobanInfo;
import tileGame.GameObserver;
import tileGame.TileGameModel;

public class GameModelTest {
    public static void main(String[] args) {


        SokobanGameModel sokobanGameModel = new SokobanGameModel(9, 8) {
            @Override
            protected void gameOver() {
                // if (box.getTileAdjecent > 22)
                // updateGameGrid to game over screen/menu

            }

            @Override
            protected void gameWon() {
                // loopa igenom gameGrid och kolla efter
                // icke ifyllda lådor. Om det inte finns
                // så har man vunnit.
                // UpdateGameGrid to win screen/menu


            }
        };
        sokobanGameModel.addGameObserver(gameState -> {
            for (int[] row :gameState) {
                String string = "[";
                for (int column :row) {
                    string += column+",";
                }
                System.out.println(string+"]");
            }
        });
        sokobanGameModel.updateGameGrid(SokobanInfo.level1);
    }
}
