package sokoban;

import tileGame.TileGameModel;

import java.io.Serializable;
import java.util.Stack;

import static sokoban.SokobanInfo.*;
import static sokoban.SokobanInfo.Events.*;

public class SokobanGameModel extends TileGameModel implements Serializable {
    /**
     * Constructs a GameModel object.
     *
     */
    public SokobanGameModel() {
        currentLevel = 0;
        tileStack = new Stack<>();
        resetLevel();
    }

    private void setCharacterPosition() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (getTileState(i,j) == PLAYER) {
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
        if (getTileState(playerLocationY + Y,playerLocationX + X) == COBBLESTONE)
            return false;
        if (getTileState(playerLocationY + Y,playerLocationX + X) < BOX)
            return true;
        else return (getTileState(playerLocationY + Y*2,playerLocationX + X*2)< BOX);
    }

    private void gameOver() {
        lastEvent = GAME_WON;
        System.out.println("You won!");
    }

    protected void gameWon() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (getTileState(i,j) == BOX)
                    return;
            }
        }
        lastEvent = GAME_WON;
        currentLevel++;
        updateGameGrid(getLevel(currentLevel));
    }

    public void resetLevel() {
        lastEvent = RESET_GAME;
        while (!tileStack.empty())
            tileStack.pop();
        tileStack.push(SAND);
        updateGameGrid(getLevel(currentLevel));
    }

    public void saveGame() {
        save = this.getGameState().clone();
    }

    public Events getLastEvent() {
        return lastEvent;
    }

    public int[][] getSave() {
        return save;
    }

    private final Stack<Integer> tileStack;
    private int[][] save;
    private int currentLevel;
    private boolean lastCratePushed;
    private int playerLocationY;
    private int playerLocationX;
    private Events lastEvent;

    final private int UP = -1, LEFT = -1, RIGHT= 1, DOWN = 1;
}