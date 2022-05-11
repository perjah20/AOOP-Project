package sokoban;

import tileGame.TileGameModel;

import java.util.Stack;

public class SokobanGameModel extends TileGameModel {
    /**
     * Constructs a GameModel object.
     *
     * @param rows    Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public SokobanGameModel(int rows, int columns) {
        super(rows, columns);
        tileStack = new Stack<>();
        tileStack.push(SokobanInfo.SAND);
        oldTile = SokobanInfo.SAND;
        setCharacterPosition();
    }

    private void setCharacterPosition() {
        int[][] gameGrid = getGameState();

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (gameGrid[i][j] == SokobanInfo.PLAYER) {
                    playerLocationY = i;
                    playerLocationX = j;
                    return;
                }
            }
        }
    }

    public void moveCharacter(directions direction){
        switch (direction){
            case NORTH-> move(UP,0);
            case WEST -> move(0,LEFT);
            case EAST -> move(0, RIGHT);
            case SOUTH-> move(DOWN,0);
        }
    }

    private void move(int Y, int X) {
        setTileState(oldTile,playerLocationY,playerLocationX); // Restore current tile.
        if(isValidMove(Y,X)) {
            int nextRow = playerLocationY + Y, nextNextRow = playerLocationY + Y * 2;
            int nextColumn = playerLocationX + X, nextNextColumn = playerLocationX + X * 2;
            switch (getTileState(nextRow, nextColumn)) {
                case SokobanInfo.BOX:
                    if (getTileState(nextNextRow, nextNextColumn) == SokobanInfo.DOT)
                        setTileState(SokobanInfo.FILLEDBOX, nextNextRow, nextNextColumn);
                    else
                        setTileState(SokobanInfo.BOX, nextNextRow, nextNextColumn);
                    setTileState(SokobanInfo.SAND, nextRow, nextColumn);
                    break;
                case SokobanInfo.FILLEDBOX:
                    if (getTileState(nextNextRow, nextNextColumn) == SokobanInfo.DOT)
                        setTileState(SokobanInfo.FILLEDBOX, nextNextRow, nextNextColumn);
                    else
                        setTileState(SokobanInfo.BOX, nextNextRow, nextNextColumn);
                    setTileState(SokobanInfo.DOT, nextRow, nextColumn);
                    break;
            }
            oldTile = getTileState(nextRow, nextColumn);
            setTileState(SokobanInfo.PLAYER, nextRow,nextColumn);
            updateObservers();
        }
    }

    private boolean isValidMove(int Y, int X) {
        if (getTileState(playerLocationY + Y,playerLocationX + X) < COBBLESTONE)
            return (getTileState(playerLocationY + Y * 2,playerLocationX + X * 2) < BOX);
        return false;
    }

    @Override
    protected void gameOver() {

    }

    @Override
    protected void gameWon() {

    }

    private int oldTile;
    private Stack<Integer> tileStack;
    private int playerLocationY;
    private int playerLocationX;

    int SAND = 1, CHARACTER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;
    final int UP = -1, LEFT = -1, RIGHT = 1, DOWN = 1;
    enum directions {NORTH, WEST ,EAST, SOUTH}
}