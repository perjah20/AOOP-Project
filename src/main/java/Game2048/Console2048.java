package Game2048;

import tileGame.GameObserver;
import tileGame.TileGameModel;

public class Console2048 implements GameObserver {
    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        for (int i = 0; i < gameModel.getRows(); i++) {
            for (int j = 0; j < gameModel.getColumns(); j++) {
                System.out.print(gameModel.getTileState(i,j)+ "  ");
            }
            System.out.print("\n");
        }
    }
}
