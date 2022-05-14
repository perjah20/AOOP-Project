package sokoban;

import sokoban.buttonStrategies.MoveButton;
import tileGame.TileGameGUI;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

import static sokoban.SokobanInfo.*;
import static sokoban.SokobanInfo.Directions.NORTH;
import static sokoban.SokobanInfo.Directions.SOUTH;
import static sokoban.SokobanInfo.Directions.WEST;
import static sokoban.SokobanInfo.Directions.EAST;

public class SokobanGameGUI extends TileGameGUI {
    /**
     * Creates a base for a tile base game
     *
     */
    public SokobanGameGUI() {
    }

    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        try {
            Method getLastEvent = gameModel.getClass().getMethod("getLastEvent", new Class[] {});
            Events lastEvent = (Events) getLastEvent.invoke(gameModel);
            switch (lastEvent) {
                case GAME_WON:
                    showText("You beat the level!");
                    break;
                case TRIED_TO_MOVE:
                    showText("You cant move that way");
                    break;
                case RESET_GAME:
                    showText("You reset the level");
                    break;
                case MOVED_PLAYER:
                    showText("You moved your player");
                    break;
                case MOVED_BOX:
                    showText("You pushed a box");
                    break;
            }

        } catch (Exception exception){}

        int[][] gameState = gameModel.getGameState();
        if (gameState.length == getTiles().length && gameState[0].length == getTiles()[0].length) {
            for (int i = 0; i < gameState.length; i++) {
                for (int j = 0; j < gameState[0].length; j++) {
                    setTile(getTile(i, j), gameState[i][j]);
                }
            }
        } else {
            this.remove(gameGrid);
            this.add(createGrid(gameState.length,gameState[0].length),BorderLayout.CENTER);
            for (int i = 0; i < gameState.length; i++) {
                for (int j = 0; j < gameState[0].length; j++) {
                    setTile(getTile(i, j), gameState[i][j]);
                }
            }
        }
        this.pack();
    }

    private void setTile(JLabel tile, int value) {
        switch(value) {
            case COBBLESTONE:
                tile.setIcon(getImageIcon("wall.png"));
                tile.setText(String.valueOf(COBBLESTONE));
                //tile.set
                break;
            case PLAYER:
                tile.setIcon(getImageIcon("player.png"));
                tile.setText(String.valueOf(PLAYER));
                break;
            case FILLEDBOX:
                tile.setIcon(getImageIcon("cratemarked.png"));
                tile.setText(String.valueOf(FILLEDBOX));
                break;
            case BOX:
                tile.setIcon(getImageIcon("crate.png"));
                tile.setText(String.valueOf(BOX));
                break;
            case DOT:
                tile.setIcon(getImageIcon("blankmarked.png"));
                tile.setText(String.valueOf(DOT));
                break;
            case SAND:
                tile.setIcon(getImageIcon("blank.png"));
                tile.setText(String.valueOf(SAND));
                break;
            default: tile.setBackground(new Color(222,214,173));
        }
    }

    private ImageIcon getImageIcon(String image) {
        return new ImageIcon(new ImageIcon(pathToImages + image).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH
        ));
    }

    @Override
    protected void northButtonPressed() { sokobanController.handleButtonPress(new MoveButton(NORTH));}
    @Override
    protected void eastButtonPressed() {
        sokobanController.handleButtonPress(new MoveButton(EAST));
    }
    @Override
    protected void southButtonPressed() {
        sokobanController.handleButtonPress(new MoveButton(SOUTH));
    }
    @Override
    protected void westButtonPressed() {
        sokobanController.handleButtonPress(new MoveButton(WEST));
    }

    @Override
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton[] buttons = new JButton[3];
        buttons[0] = new JButton("Reset Game");  buttons[0].addActionListener(e ->
                sokobanController.handleButtonPress(SokobanGameModel::resetLevel));
        buttons[1] = new JButton("Save Game");   buttons[1].addActionListener(e ->
                sokobanController.handleButtonPress(SokobanGameModel::saveGame));
        buttons[2] = new JButton("Load Game");   buttons[2].addActionListener(e ->
                sokobanController.handleButtonPress(SokobanGameModel::loadGame));
        for (JButton button : buttons) {
            menuBar.add(button);
        }
        return menuBar;
    }

    public void setController(SokobanController aSokobanController) {
        this.sokobanController = aSokobanController;
    }


    private final String pathToImages = "src/main/java/sokoban/icons/";
    private SokobanController sokobanController;
}