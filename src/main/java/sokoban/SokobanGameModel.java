package sokoban;

import tileGame.TileGameModel;
import java.util.Stack;

import static sokoban.SokobanInfo.*;
import static sokoban.SokobanInfo.Events.*;

public class SokobanGameModel extends TileGameModel {
    /**
     * Constructs a GameModel object.
     *
     */
    public SokobanGameModel() {
        currentLevel = 0;
        tileStack = new Stack<>();
        tileStack.push(SAND);
    }

    private void setCharacterPosition() {
        gameGrid = getGameState();

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
    private void checkNextTile(int nextLocation, Directions direction){
        if (nextLocation == BOX) {
            tileStack.push(SAND);
            moveCrate(direction);
        } else if (nextLocation == FILLEDBOX) {
            tileStack.push(DOT);
            moveCrate(direction);
        } else tileStack.push(nextLocation);
    }

    private void moveCharacterDirection(Directions direction, int Y, int X){
        if (isValidMove(Y,X)) {
            lastEvent = MOVED_PLAYER;
            int nextLocation = getTileState(playerLocationY + Y, playerLocationX + X);
            int currentLocation = tileStack.pop();
            checkNextTile(nextLocation, direction);

            nextLocation = PLAYER;
            setTileState(currentLocation, playerLocationY, playerLocationX);
            setTileState(nextLocation, playerLocationY + Y, playerLocationX + X);
        }
    }

    public void moveCharacter(Directions direction){
        lastEvent = TRIED_TO_MOVE;
        lastCratePushed = false;
        setCharacterPosition();
        switch (direction) {
            case NORTH -> moveCharacterDirection(direction, UP, 0);
            case WEST -> moveCharacterDirection(direction, 0, LEFT);
            case EAST -> moveCharacterDirection(direction, 0, RIGHT);
            case SOUTH -> moveCharacterDirection(direction, DOWN, 0);
        }
        updateObservers();
        if (lastCratePushed) gameWon();
    }

    public void moveCrate(Directions direction) {
        lastEvent = MOVED_BOX;
        switch (direction) {
            case NORTH -> crateMover(UP, 0);
            case WEST -> crateMover(0, LEFT);
            case EAST -> crateMover(0, RIGHT);
            case SOUTH -> crateMover(DOWN, 0);
        }
    }

    private void crateMover(int Y, int X){

        int crateNextLocation = getTileState(playerLocationY + Y + Y, playerLocationX + X + X);
        if (crateNextLocation == DOT) {
            lastCratePushed = true;
            setTileState(FILLEDBOX,playerLocationY + Y + Y,playerLocationX + X + X);
        }
        else setTileState(BOX,playerLocationY + Y + Y,playerLocationX + X + X);
    }

    private boolean isValidMove(int Y, int X) {
        if (gameGrid[playerLocationY + Y][playerLocationX + X] == COBBLESTONE)
            return false;
        if (gameGrid[playerLocationY + Y][playerLocationX + X] < BOX)
            return true;
        else return (gameGrid[playerLocationY + Y*2][playerLocationX + X*2] < BOX);
    }

    @Override
    protected void gameOver() {
        lastEvent = GAME_WON;
        System.out.println("You won!");
    }

    @Override
    protected void gameWon() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (gameGrid[i][j] == BOX)
                    return;
            }
        }
        if (currentLevel == 0) {
            updateGameGrid(getLevel(1));
            currentLevel++;
        } else if (currentLevel == 1) {
            updateGameGrid(getLevel(2));
            currentLevel++;
        } else if (currentLevel == 2) {
            updateGameGrid(getLevel(3));
            currentLevel++;
        } else if (currentLevel == 3) {
            gameOver();
        }
    }

    public void resetLevel() {
        updateGameGrid(getLevel(currentLevel));
    }

    public void saveGame() {
        save = gameGrid.clone();
        System.out.println("Implement Save Game method");
    }

    public void loadGame() {
        updateGameGrid(save);
        System.out.println("Implement Load game method");
    }

    public Events getLastEvent() {
        return lastEvent;
    }

    private final Stack<Integer> tileStack;
    private int[][] gameGrid;
    private int[][] save;
    private int currentLevel;
    private boolean lastCratePushed;
    private int playerLocationY;
    private int playerLocationX;
    private Events lastEvent;

    final private int UP = -1, LEFT = -1, RIGHT= 1, DOWN = 1;
}