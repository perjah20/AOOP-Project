package sokoban;

import tileGame.TileGameModel;
import java.util.Stack;

import static sokoban.SokobanInfo.SAND;
import static sokoban.SokobanInfo.PLAYER;
import static sokoban.SokobanInfo.DOT;
import static sokoban.SokobanInfo.BOX;
import static sokoban.SokobanInfo.FILLEDBOX;
import static sokoban.SokobanInfo.COBBLESTONE;

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
        tileStack.push(SAND);
    }

    private void setCharacterPosition() {
        int[][] gameGrid = getGameState();

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (gameGrid[i][j] == PLAYER) {
                    playerLocationY = i;
                    playerLocationX = j;
                    return;
                }
            }
        }
    }

    /**
     * Checks what type of tile the next location is and push
     * it to the stack. Activates method moveCrate if it is a crate.
     * @param nextLocation Holds the value of the next location.
     * @param direction The direction of the object.
     */
    private void checkNextTile(int nextLocation, directions direction){
        if (nextLocation == BOX) {
            tileStack.push(SAND);
            moveCrate(direction);
        } else if (nextLocation == FILLEDBOX) {
            tileStack.push(DOT);
            moveCrate(direction);
        } else tileStack.push(nextLocation);
    }

    private void moveCharacterDirection(directions direction, int Y, int X){

        if (isValidMove(Y,X)) {
            int nextLocation = getTileState(playerLocationY + Y, playerLocationX + X);
            int currentLocation = tileStack.pop();
            checkNextTile(nextLocation, direction);

            nextLocation = PLAYER;
            setTileState(currentLocation, playerLocationY, playerLocationX);
            setTileState(nextLocation, playerLocationY + Y, playerLocationX + X);
        }
    }

    public void moveCharacter(directions direction){
        setCharacterPosition();
        switch (direction) {
            case NORTH -> moveCharacterDirection(direction, UP, 0);
            case WEST -> moveCharacterDirection(direction, 0, LEFT);
            case EAST -> moveCharacterDirection(direction, 0, RIGHT);
            case SOUTH -> moveCharacterDirection(direction, DOWN, 0);
        }
        updateObservers();
        gameWon();
    }

    public void moveCrate(directions direction) {
        switch (direction) {
            case NORTH -> crateMover(UP, 0);
            case WEST -> crateMover(0, LEFT);
            case EAST -> crateMover(0, RIGHT);
            case SOUTH -> crateMover(DOWN, 0);
        }
        updateObservers();
    }

    private void crateMover(int Y, int X){
        int crateNextLocation = getTileState(playerLocationY + Y + Y, playerLocationX + X + X);
        if (crateNextLocation == DOT)
            setTileState(FILLEDBOX,playerLocationY + Y + Y,playerLocationX + X + X);
        else setTileState(BOX,playerLocationY + Y + Y,playerLocationX + X + X);
    }



    private boolean isValidMove(int Y, int X) {
        int[][] gameGrid = getGameState();
        if (gameGrid[playerLocationY + Y][playerLocationX + X] == COBBLESTONE)
            return false;
        if (gameGrid[playerLocationY + Y][playerLocationX + X] < BOX)
            return true;
        else return (gameGrid[playerLocationY + Y*2][playerLocationX + X*2] < BOX); //TODO Fix so that the array does not go out of bounds when we reach edges
    }

    @Override
    protected void gameOver() {

    }

    @Override
    protected void gameWon() {
        int[][] gameGrid = getGameState();

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (gameGrid[i][j] == BOX)
                    return;
            }
        }
        //TileGameGUI.showText("You finished first level. Now on level 2");
        updateGameGrid(SokobanInfo.level2);
    }

    private Stack<Integer> tileStack;
    private int playerLocationY;
    private int playerLocationX;

    final private int UP = -1, LEFT = -1, RIGHT= 1, DOWN = 1;
    enum directions {NORTH, WEST ,EAST, SOUTH}
}