package game2048;

import tilegame.TileGameModel;

import static game2048.GameModel2048.Events.*;

/**
 * The GameModel2048 is the central component which directly
 * manages the data, logic and rules of the game.
 */
public class GameModel2048 extends TileGameModel {
    /**
     * These are the directions in which you can move the character
     */
    public  enum Direction {LEFT,RIGHT,UP,DOWN}

    /**
     * Instantiates the 2048GameModel Object.
     */
    public GameModel2048() {
        this.updateGameGrid(new int[4][4]);
        resetGame();
    }


    /**
     * This method will reset the game and generate 2 new tiles.
     */
    public void resetGame() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                setTileState(0,i,j);
            }
        }
        emptyTiles = 0;
        modified = true;
        generateRandomNumber();
        generateRandomNumber();
        lastEvent = RESET_GAME;
        updateObservers();
    }

    /**
     * This method moves the tiles in a specified direction,
     * if the player has not beaten or lost the game.
     *
     * It updates the lastDirection and lastEvent.
     * @param direction The direction to move the tiles.
     */
    public void move(Direction direction) {
        if (lastEvent != GAME_WON && lastEvent != GAME_OVER) {
            modified = false;
            switch (direction) {
                case LEFT -> {
                    shiftLeft();
                    mergeLeft();
                    shiftLeft();
                    lastDirection = Direction.LEFT;
                    lastEvent = MOVED_LEFT;
                }
                case RIGHT -> {
                    shiftRight();
                    mergeRight();
                    shiftRight();
                    lastDirection = Direction.RIGHT;
                    lastEvent = MOVED_RIGHT;
                }
                case UP -> {
                    shiftUp();
                    mergeUp();
                    shiftUp();
                    lastDirection = Direction.UP;
                    lastEvent = MOVED_UP;
                }
                case DOWN -> {
                    shiftDown();
                    mergeDown();
                    shiftDown();
                    lastDirection = Direction.DOWN;
                    lastEvent = MOVED_DOWN;
                }
            }
            generateRandomNumber();
            updateObservers();
        }
    }

    /**
     * Gets the last occurred event.
     * @return The last occurred event.
     */
    public Events getLastEvent() {
        return lastEvent;
    }

    /**
     * Gets the last moved direction.
     * @return The last moved direction.
     */
    public Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * Returns true of false whether the board has been modified or not.
     * @return True if board has been modified, false if it has not been modified.
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Shift all tiles that can be shifted to the left.
     */
    private void shiftLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3;) {
                col = shiftTiles(row, col, 0, 1) ? 0 : col+1;
            }
        }
    }

    /**
     * Merges all tiles that can be merged to the left.
     */
    private void mergeLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3;) {
                mergeTiles(row,col,0,1);
                col++;
            }
        }
    }

    /**
     * Shifts all tiles that can be moved to the right
     */
    private void shiftRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0;) {
                col = shiftTiles(row,col,0,-1) ? 3 : col-1;
            }
        }
    }

    /**
     * Merges all tiles that can be merged to the right.
     */
    private void mergeRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0;) {
                mergeTiles(row,col,0,-1);
                col--;
            }
        }
    }

    /**
     * Shifts all tiles that can be moved upwards
     */
    private void shiftUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3;) {
                row = shiftTiles(row,col,1,0) ? 0 : row+1;
            }
        }
    }

    /**
     * Merges all tiles that can be merged upwards.
     */
    private void mergeUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3;) {
                mergeTiles(row,col,1,0);
                row++;
            }
        }
    }

    /**
     * Shifts all tiles that can be moved downwards
     */
    private void shiftDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0;) {
                row = shiftTiles(row,col,-1,0) ? 3 : row-1;
            }
        }
    }

    /**
     * Merges all tiles that can be merged downwards.
     */
    private void mergeDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0;) {
                mergeTiles(row,col,-1,0);
                row--;
            }
        }
    }

    /**
     * This method merges two tiles if they have the same value and
     * sets the old tile to 0.
     * @param row The current row position
     * @param col The current column position
     * @param offsetY This is used to select the next row to compare with.
     * @param offsetX This is used to select the next column to compare with.
     */
    private void mergeTiles(int row, int col, int offsetY, int offsetX) {
        int currentTileValue = this.getTileState(row, col);
        int nextTileValue = this.getTileState(row + offsetY, col + offsetX);
        if (currentTileValue == nextTileValue && currentTileValue != 0) {
            setTileState(currentTileValue + nextTileValue, row, col);
            setTileState(0, row + offsetY, col + offsetX);
            if (currentTileValue == 1024) lastEvent = GAME_WON;
            modified = true;
        }
    }

    /**
     * This method moves a tile to the current tile if:
     * the current tile is 0 and the next tile is not 0.
     * @param row The current row position
     * @param col The current column position
     * @param offsetY This is used to select the next row to compare with.
     * @param offsetX This is used to select the next column to compare with.
     * @return True if we moved a tile, false if not. 
     */
    private boolean shiftTiles(int row, int col, int offsetY, int offsetX) {
        int currentTileValue = this.getTileState(row, col);
        int nextTileValue = this.getTileState(row + offsetY, col + offsetX);
        if (currentTileValue == 0 && nextTileValue != 0) {
            setTileState(nextTileValue, row, col);
            setTileState(0,row + offsetY, col + offsetX);
            modified = true;
            return true;
        } else return false;
    }

    /**
     * This method generates a either 2 or a 4 on the playing board where there is empty
     * tiles left and the board as been modified.
     * If there are no empty tiles left, the game will check if it is game over.
     */
    private void generateRandomNumber() {
        countEmpty();
        if(emptyTiles == 0) gameOver();
        if(modified) {
            int k = emptyTiles;
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    if (getTileState(i, j) == 0) {
                        k--;
                        if (Math.random() < 0.2 || k == 0) {
                            if ((Math.random() >= 0.9)) {
                                setTileState(4, i, j);
                            } else {
                                setTileState(2, i, j);
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will check if the there are any moves left the player can make.
     * If the player is out of moves, it will set lastEvent to GAME_OVER and update observers.
     */
    private void gameOver() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns() - 1 ; j++) {
                if (getTileState(i,j) == getTileState(i,j+1))
                    return;
            }
        }
        for (int i = 0; i < getColumns() - 1; i++) {
            for (int j = 0; j < getRows(); j++) {
                if (getTileState(i,j) == getTileState(i + 1,j))
                    return;
            }
        }
        lastEvent = GAME_OVER;
        updateObservers();
    }

    /**
     * Will count the amount of empty tiles on the playing board.
     */
    private void countEmpty() {
        emptyTiles = 0;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getTileState(i,j) == 0) emptyTiles += 1;
            }
        }
    }

    private int emptyTiles;
    private boolean modified;
    private Events lastEvent;
    private Direction lastDirection;

    /**
     * This is the events of the game, and they are used for keeping track of which event has occurred the game is in.
     */
    enum Events {RESET_GAME, GAME_OVER, GAME_WON, MOVED_LEFT, MOVED_UP, MOVED_DOWN, MOVED_RIGHT}

}