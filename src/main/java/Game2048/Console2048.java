package Game2048;

import tileGame.GameObserver;

public class Console2048 implements GameObserver<GameModel2048> {

    public Console2048() {}
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
