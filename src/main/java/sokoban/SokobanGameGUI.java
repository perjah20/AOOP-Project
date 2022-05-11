package sokoban;

import tileGame.TileGameGUI;

import javax.swing.*;
import java.awt.*;

abstract class SokobanGameGUI extends TileGameGUI {
    /**
     * Creates a base for *ett spel att ha*
     *
     * @param rows
     * @param columns
     */
    public SokobanGameGUI(int rows, int columns) {
        super(rows, columns);
    }

    @Override
    public void updateGameObserver(int[][] gameState) {
        for (int i = 0; i < gameState.length; i++) {
            for (int j = 0; j < gameState[0].length; j++) {
                setTile(getTile(i,j),gameState[i][j]);
            }
        }
        this.pack();
    }

    private void setTile(JLabel tile, int value) {
        switch(value) {
            case SokobanInfo.COBBLESTONE -> tile.setIcon(getImageIcon("wall.png"));
            case SokobanInfo.CHARACTER ->  tile.setIcon(getImageIcon("player.png"));
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
    private final String pathToImages = "src/main/java/sokoban/icons/";
}