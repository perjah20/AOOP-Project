package sokoban;

import sokoban.buttonStrategies.MoveButton;
import tileGame.TileGameGUI;


import javax.swing.*;
import java.awt.*;

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
    public void updateGameObserver(int[][] gameState) {
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
            case SokobanInfo.COBBLESTONE -> tile.setIcon(getImageIcon("wall.png"));
            case SokobanInfo.PLAYER ->  tile.setIcon(getImageIcon("player.png"));
            case SokobanInfo.FILLEDBOX -> tile.setIcon(getImageIcon("cratemarked.png"));
            case SokobanInfo.BOX ->   tile.setIcon(getImageIcon("crate.png"));
            case SokobanInfo.DOT -> tile.setIcon(getImageIcon("blankmarked.png"));
            case SokobanInfo.SAND ->   tile.setIcon(getImageIcon("blank.png"));
            default -> tile.setBackground(new Color(222,214,173));
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