package sokoban;

import tilegame.TileGameModel;

import java.io.Serializable;
import java.util.Stack;

import static sokoban.SokobanInfo.*;
import static sokoban.SokobanInfo.Events.*;

/**
 * The SokobanGameModel is the central component which directly
 * manages the data, logic and rules of the game.
 */
public class SokobanGameModel extends TileGameModel implements Serializable {
    /**
     * Constructs a SokobanGameModel object.
     */
    public SokobanGameModel(int [][][] setOfLeveles) {
        tileStack = new Stack<>();
        levels = makeCopyOf3DArray(setOfLeveles);
        tileStack.push(SAND);
    }

    /**
     * Initiates the game by loading the first level
     * and setting lastEvent to start game.
     * Lastly informs all observers.
     */
    public void startGame() {
        currentLevel = 0;
        lastEvent = START_GAME;
        updateGameGrid(makeCopyOf2DArray(this.levels[currentLevel]));
    }

    /**
     * Locates where the player is, then tries to move the player in the given direction.
     * @param direction The direction where the player wants to move.
     */
    public void moveCharacter(Directions direction){
        lastEvent = TRIED_TO_MOVE;
        filledCratePush = false;
        setCharacterPosition();
        switch (direction) {
            case NORTH -> moveCharacterDirection(direction, UP, 0);
            case WEST -> moveCharacterDirection(direction, 0, LEFT);
            case EAST -> moveCharacterDirection(direction, 0, RIGHT);
            case SOUTH -> moveCharacterDirection(direction, DOWN, 0);
        }
        updateObservers();
        if (filledCratePush) gameWon();
    }

    /**
     * Resets the level to its original state and informs observers of changes.
     */
    public void resetLevel() {
        lastEvent = RESET_GAME;
        while (!tileStack.empty())
            tileStack.pop();
        tileStack.push(SAND);
        updateGameGrid(makeCopyOf2DArray(levels[currentLevel]));
    }

    /**
     * Clones the current state to save
     */
    public void saveGame() {
        save = makeCopyOf2DArray(this.getGameState());
    }

    /**
     * This method returns the latest event of the game
     * @return The last event.
     */
    public Events getLastEvent() {
        return lastEvent;
    }

    /**
     * This method will update our current SokobanGameModel with
     * values from a previously serialized SokobanGameModel.
     * @param savedModel The serialized SokobanGameModel
     */
    public void getSave(SokobanGameModel savedModel) {
        this.tileStack = savedModel.tileStack;
        this.save = savedModel.save;
        this.currentLevel = savedModel.currentLevel;
        this.filledCratePush = savedModel.filledCratePush;
        this.playerLocationY = savedModel.playerLocationY;
        this.playerLocationX = savedModel.playerLocationX;
        this.lastEvent = savedModel.lastEvent;
        this.levels = savedModel.levels;
        this.updateGameGrid(makeCopyOf2DArray(save));
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                string.append(this.getTileState(i, j)).append("  ");
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Finds the position of our player and updates
     * our player location variables.
     */
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
        if (nextLocation == CRATE) {
            tileStack.push(SAND);
            moveCrate(direction);
        } else if (nextLocation == FILLEDBOX) {
            tileStack.push(DOT);
            moveCrate(direction);
        } else tileStack.push(nextLocation);
    }

    /**
     * Moves the character to the given direction and sets the tile that the player was
     * just located at to its original tile.
     * @param direction The direction where the player wants to move.
     * @param Y Direction in Y axis, so which row player are trying to move to.
     * @param X Direction in X axis, so which column the crate is trying to move to.
     */
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

    /**
     * Checks if player has completed a level and if player has completed a level
     * it will load the next level as long as we have levels to load.
     * Ends with informing observers of changes
     */
    private void gameWon() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (getTileState(i,j) == CRATE)
                    return;
            }
        }
        lastEvent = GAME_WON;
        currentLevel++;
        if (currentLevel < levels.length)
            updateGameGrid(makeCopyOf2DArray(levels[currentLevel]));
    }

    /**
     * Moves the crate in the given direction by calling the method {@link #crateMover(int, int)} crateMover}.
     * @param direction The direction where the crate is supposed to move.
     */
    private void moveCrate(Directions direction) {
        switch (direction) {
            case NORTH -> crateMover(UP, 0);
            case WEST -> crateMover(0, LEFT);
            case EAST -> crateMover(0, RIGHT);
            case SOUTH -> crateMover(DOWN, 0);
        }
        if (filledCratePush) lastEvent = FILLED_CRATE;
        else lastEvent = MOVED_CRATE;
    }

    /**
     * Determines what action to perform on the moved crate. For example changing the crate
     * into a filled crate if it was pushed onto a dot.
     * @param Y Direction in Y axis, so which row the crate is trying to move to.
     * @param X Direction in X axis, so which column the crate is trying to move to.
     */
    private void crateMover(int Y, int X){

        int crateNextLocation = getTileState(playerLocationY + Y + Y, playerLocationX + X + X);
        if (crateNextLocation == DOT) {
            filledCratePush = true;
            setTileState(FILLEDBOX,playerLocationY + Y + Y,playerLocationX + X + X);
        }
        else setTileState(CRATE,playerLocationY + Y + Y,playerLocationX + X + X);
    }

    /**
     * Checks if it is possible for the player to move in a certain direction.
     * @param Y Direction in Y axis, so which row player are trying to move to.
     * @param X Direction in X axis, so which column player are trying to move to.
     * @return True if possible, false if not.
     */
    private boolean isValidMove(int Y, int X) {
        if (getTileState(playerLocationY + Y,playerLocationX + X) == COBBLESTONE)
            return false;
        if (getTileState(playerLocationY + Y,playerLocationX + X) < CRATE)
            return true;
        else return (getTileState(playerLocationY + Y*2,playerLocationX + X*2)< CRATE);
    }

    /**
     * Used to store the tile that the player is currently standing on.
     */
    private Stack<Integer> tileStack;
    /**
     * Used to store the current state of the game grid.
     */
    private int[][] save;
    /**
     * Used to keep track of which level the player is on.
     */
    private int currentLevel;
    /**
     *  Used to keep track if the latest move was a crate that was pushed to a dot.
     */
    private boolean filledCratePush;
    /**
     * Used to keep track of which row the player is located
     */
    private int playerLocationY;
    /**
     * Used to keep track of which column the player is located
     */
    private int playerLocationX;
    /**
     * Used to inform observers of the latest event.
     */
    private Events lastEvent;
    /**
     * Used to store the levels.
     */
    private int[][][] levels;

    /**
     * Used in our moving algorithms.
     */
    final private int UP = -1, LEFT = -1, RIGHT= 1, DOWN = 1;
}