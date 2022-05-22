package game2048;

import tilegame.GameObserver;

/**
 * The Console2048 class is a class that is used to see the
 * actual GameModel2048 values. It is primarily used for debugging.
 */
public class Console2048 implements GameObserver<GameModel2048> {

    public Console2048() {}
    /**
     * Prints the last event, direction, if the board has been modified
     * and displays a model of the current game map in the console.
     * @param gameModel The 2048 game model to get information from
     */
    @Override
    public void updateGameObserver(GameModel2048 gameModel) {
        System.out.println("\nUpdating console");
        System.out.println(gameModel.getLastEvent());
        System.out.println(gameModel.getLastDirection());
        System.out.println(gameModel.isModified());
        for (int i = 0; i < gameModel.getRows(); i++) {
            for (int j = 0; j < gameModel.getColumns(); j++) {
                System.out.print(gameModel.getTileState(i,j)+ "  ");
            }
            System.out.print("\n");
        }
    }
}
