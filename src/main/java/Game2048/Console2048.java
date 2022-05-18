package Game2048;

import tileGame.GameObserver;
import tileGame.TileGameModel;

import java.lang.reflect.Method;

public class Console2048 implements GameObserver {
    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        System.out.println("\nUpdating console");
        try {
            Method getLastEvent = gameModel.getClass().getDeclaredMethod("getLastEvent");
            Method getLastDirection = gameModel.getClass().getDeclaredMethod("getLastDirection");
            Method isModified = gameModel.getClass().getDeclaredMethod("isModified");
            System.out.println(getLastEvent.invoke(gameModel));
            System.out.println(getLastDirection.invoke(gameModel));
            System.out.println(isModified.invoke(gameModel));
        } catch(Exception ignored) {}
        for (int i = 0; i < gameModel.getRows(); i++) {
            for (int j = 0; j < gameModel.getColumns(); j++) {
                System.out.print(gameModel.getTileState(i,j)+ "  ");
            }
            System.out.print("\n");
        }
    }
}
