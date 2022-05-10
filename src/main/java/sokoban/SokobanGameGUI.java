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
            case WALL ->    tile.setIcon(getImageIcon("wall.png"));
            case PLAYER ->  tile.setIcon(getImageIcon("player.png"));
            case MARKED_CRATE -> tile.setIcon(getImageIcon("cratemarked.png"));
            case CRATE ->   tile.setIcon(getImageIcon("crate.png"));
            case MARKED_BLANK -> tile.setIcon(getImageIcon("blankmarked.png"));
            case BLANK ->   tile.setIcon(getImageIcon("blank.png"));
            default -> tile.setBackground(new Color(222,214,173));
        }
    }

    private ImageIcon getImageIcon(String image) {
        return new ImageIcon(new ImageIcon(pathToImages + image).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH
        ));
    }

    private final int
            WALL         = 6,
            PLAYER       = 5,
            MARKED_CRATE = 4,
            CRATE        = 3,
            MARKED_BLANK = 2,
            BLANK        = 1;
    private final String pathToImages = "src/main/java/sokoban/icons/";

}