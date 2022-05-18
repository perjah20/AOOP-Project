package sokoban;

import sokoban.buttonStrategies.ButtonStrategy;
import sokoban.buttonStrategies.LoadButton;
import sokoban.buttonStrategies.MoveButton;
import sokoban.buttonStrategies.SaveButton;
import tileGame.GameLabel;
import tileGame.TileGameGUI;


import javax.swing.*;
import java.awt.*;

import static sokoban.SokobanInfo.*;
import static sokoban.SokobanInfo.Directions.NORTH;
import static sokoban.SokobanInfo.Directions.SOUTH;
import static sokoban.SokobanInfo.Directions.WEST;
import static sokoban.SokobanInfo.Directions.EAST;

public class SokobanGameGUI extends TileGameGUI<SokobanController,SokobanGameModel> {
    /**
     * Creates a base for a tile base game
     */
    public SokobanGameGUI() {

    }

    @Override
    public void updateGameObserver(SokobanGameModel gameModel) {
        switch (gameModel.getLastEvent()) {
                case GAME_WON       -> showText("You beat the level!");
                case TRIED_TO_MOVE  -> showText("You cant move that way");
                case RESET_GAME     -> showText("You reset the level");
                case MOVED_PLAYER   -> showText("You moved your player");
                case MOVED_BOX      -> showText("You pushed a box");
            }
        if (gameModel.getRows() != getRowLength() || gameModel.getColumns() != getColLength()) {
            this.add(createGrid(gameModel.getRows(), gameModel.getColumns()), BorderLayout.CENTER);
        }
        for (int i = 0; i < gameModel.getRows(); i++) {
            for (int j = 0; j < gameModel.getColumns(); j++) {
                if (this.getTile(i,j).getTileValue() != gameModel.getTileState(i,j))
                    setTile(getTile(i, j), gameModel.getTileState(i,j));
            }
        }
        this.pack();
    }

    private void setTile(GameLabel tile, int value) {
        switch (value) {
            case COBBLESTONE -> {
                tile.setIcon(getImageIcon("wall.png"));
                tile.setTileValue(COBBLESTONE);
            }
            case PLAYER -> {
                tile.setIcon(getImageIcon("player.png"));
                tile.setTileValue(PLAYER);
            }
            case FILLEDBOX -> {
                tile.setIcon(getImageIcon("cratemarked.png"));
                tile.setTileValue(FILLEDBOX);
            }
            case BOX -> {
                tile.setIcon(getImageIcon("crate.png"));
                tile.setTileValue(BOX);
            }
            case DOT -> {
                tile.setIcon(getImageIcon("blankmarked.png"));
                tile.setTileValue(DOT);
            }
            case SAND -> {
                tile.setIcon(getImageIcon("blank.png"));
                tile.setTileValue(SAND);
            }
            default -> tile.setBackground(new Color(222, 214, 173));
        }
    }

    private ImageIcon getImageIcon(String image) {
        return new ImageIcon(new ImageIcon(pathToImages + image).getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH
        ));
    }

    @Override
    protected void northButtonPressed() { this.getTileGameController().handleButtonPress(new MoveButton(NORTH));}
    @Override
    protected void eastButtonPressed() {
        this.getTileGameController().handleButtonPress(new MoveButton(EAST));
    }
    @Override
    protected void southButtonPressed() {
        this.getTileGameController().handleButtonPress(new MoveButton(SOUTH));
    }
    @Override
    protected void westButtonPressed() {
        this.getTileGameController().handleButtonPress(new MoveButton(WEST));
    }

    @Override
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JButton[] buttons = new JButton[3];
        buttons[0] = new JButton("Reset Game");  buttons[0].addActionListener(e ->
                this.getTileGameController().handleButtonPress((ButtonStrategy<SokobanGameModel>) gameModel -> gameModel.resetLevel()));
        buttons[1] = new JButton("Save Game");   buttons[1].addActionListener(e ->
                this.getTileGameController().handleButtonPress(new SaveButton()));
        buttons[2] = new JButton("Load Game");   buttons[2].addActionListener(e ->
                this.getTileGameController().handleButtonPress(new LoadButton()));
        for (JButton button : buttons) {
            menuBar.add(button);
        }
        return menuBar;
    }


    private final String pathToImages = "src/main/java/sokoban/icons/";
}