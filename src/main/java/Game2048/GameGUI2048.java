package Game2048;
import tileGame.GameLabel;
import tileGame.TileGameController;
import tileGame.TileGameGUI;
import tileGame.TileGameModel;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;
import java.util.HashMap;

import static Game2048.GameModel2048.Direction.*;
import static Game2048.GameModel2048.Direction;

public class GameGUI2048 extends TileGameGUI {
    private TileGameController gameController;

    public GameGUI2048(TileGameController aGameController) {
        gameController = aGameController;
        this.add(createGrid(4,4));
        this.setResizable(false);
        for (int i = 0; i < numbers.length; i++)
            tileColors.put(numbers[i],colors[i]);

        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColLength(); j++) {
                GameLabel tile = getTile(i,j);
                tile.setBorder(BorderFactory.createLineBorder(borderColor,5));
                tile.setPreferredSize(new Dimension(100,100));
                tile.setFont(new Font(tile.getFont().getName(), Font.PLAIN, 34));
            }
        }
        this.pack();
    }
    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        System.out.println("updating Game GUI");
        int value;
        for (int i = 0; i < this.getRowLength(); i++) {
            for (int j = 0; j < this.getColLength(); j++) {
                value = gameModel.getTileState(i,j);
                GameLabel tile = getTile(i,j);
                if ((value == 0))
                    tile.setText("");
                else
                    tile.setText(Integer.toString(value));
                Color color = tileColors.get(value);
                if (color != null)
                    tile.setBackground(tileColors.get(value));
                else
                    tile.setBackground(new Color(62,57,51));
            }
        }
    }

    @Override
    protected void northButtonPressed() {
        gameController.handleButtonPress(gameModel -> {
            try {
                Method move = gameModel.getClass().getDeclaredMethod("move", Direction.class);
                move.invoke(gameModel, UP);
            } catch(Exception ignored) {}
        });
    }

    @Override
    protected void eastButtonPressed() {
        gameController.handleButtonPress(gameModel -> {
            try {
                Method move = gameModel.getClass().getDeclaredMethod("move", Direction.class);
                move.invoke(gameModel, RIGHT);
            } catch(Exception ignored) {}
        });
    }

    @Override
    protected void southButtonPressed() {
        gameController.handleButtonPress(gameModel -> {
            try {
                Method move = gameModel.getClass().getDeclaredMethod("move", Direction.class);
                move.invoke(gameModel, DOWN);
            } catch(Exception ignored) {}
        });
    }

    @Override
    protected void westButtonPressed() {
        gameController.handleButtonPress(gameModel -> {
            try {
                Method move = gameModel.getClass().getDeclaredMethod("move", Direction.class);
                move.invoke(gameModel, LEFT);
            } catch(Exception ignored) {}
        });
    }

    @Override
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton button = new JButton("Reset Game");
        button.addActionListener( e-> gameController.handleButtonPress(gameModel -> {
            try {
                Method resetGame = gameModel.getClass().getDeclaredMethod("resetGame");
                resetGame.invoke(gameModel);
            } catch(Exception ignored) {}
        }));
        menuBar.add(button);
        return menuBar;
    }

    HashMap<Integer,Color> tileColors = new HashMap<>();

    private final Color[] colors = {
            new Color(204,192,179), // 0
            new Color(238,228,218), // 2
            new Color(237,224,200), // 4
            new Color(242,177,121), // 8
            new Color(245,149,99),  // 16
            new Color(246,124,95),  // 32
            new Color(246,124,95),  // 32
            new Color(246,94,59),   // 128
            new Color(237,204,97),  // 256
            new Color(237,200,80),  // 512
            new Color(237,197,63),  // 1024
            new Color(237,194,46)   // 2048
    };
    private final int[] numbers =  {0,2,4,8,16,32,64,128,256,512,1024,2048};

    private final Color borderColor = new Color(187,173,160);

}
