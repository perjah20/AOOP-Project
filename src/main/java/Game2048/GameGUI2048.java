package Game2048;
import tileGame.GameLabel;
import tileGame.TileGameController;
import tileGame.TileGameGUI;
import tileGame.TileGameModel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class GameGUI2048 extends TileGameGUI {
    private TileGameController gameController;

    public GameGUI2048(TileGameController aGameController) {
        gameController = aGameController;
        this.remove(gameGrid);
        this.createGrid(4,4);

    }
    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        System.out.println("updating Game GUI");
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColLength(); j++) {
                //if (this.getTile(i,j).getTileValue() != gameModel.getTileState(i,j))
                    getTile(i,j).setText(Integer.toString(gameModel.getTileState(i,j)));
                    getTile(i,j).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    getTile(i,j).setSize(new Dimension(100,100));
                    getTile(i,j).setBackground(new Color(222, 214, 173));
            }
        }
        this.pack();
    }

    @Override
    protected void northButtonPressed() {
        gameController.handleButtonPress(gameModel -> {
            try {
                Method move = gameModel.getClass().getDeclaredMethod("move", int.class);
                move.invoke(gameModel, 1);
            } catch(Exception ignored) {}
        });
    }

    @Override
    protected void eastButtonPressed() {

    }

    @Override
    protected void southButtonPressed() {

    }

    @Override
    protected void westButtonPressed() {

    }

    @Override
    protected JMenuBar createMenuBar() {
        return null;
    }
}
