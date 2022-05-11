import tileGame.TileGameModel;

public class GameModelTest {

    public void testModel() {

    }

    public static void main(String[] args) {


        new TileGameModel(6,6) {

            @Override
            public int getTileState(int i, int j) {
                return super.getTileState(i, j);
            }

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
    }

    int SAND = 1, CHARACTER = 2, BOX = 3, DOT = 4, FILLEDBOX = 7, COBBLESTONE = 20;


}

/**
 *      Sand = 1                        10   10   10   10   10   10
 *      Character = 2                   10    1    1    1    1   10
 *      Cobblestone = 3                 10    1    2    4    1   10
 *      Box = 4                         10    1    1    3    1   10
 *      Dot = 5                         10    1    1    1    1   10
 *      Filled box = 9 (Box + Dot)      10   10   10   10   10   10
 *
 *
 */
