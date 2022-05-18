package Game2048;

import tileGame.TileGameModel;

import static Game2048.GameModel2048.Events.*;

public class GameModel2048 extends TileGameModel {
    public GameModel2048() {
        this.updateGameGrid(new int[4][4]);
        resetGame();
    }

    public void move(Direction direction) {
        if (lastEvent != GAME_WON && lastEvent != GAME_OVER) {
            modified = false;
            switch (direction) {
                case LEFT -> {
                    shiftLeft();
                    mergeLeft();
                    shiftLeft();
                    lastDirection = Direction.LEFT;
                }
                case RIGHT -> {
                    shiftRight();
                    mergeRight();
                    shiftRight();
                    lastDirection = Direction.RIGHT;
                }
                case UP -> {
                    shiftUp();
                    mergeUp();
                    shiftUp();
                    lastDirection = Direction.UP;
                }
                case DOWN -> {
                    shiftDown();
                    mergeDown();
                    shiftDown();
                    lastDirection = Direction.DOWN;
                }
            }
            generateRandomNumber();
            updateObservers();
        }
    }

    private void shiftLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3;) {
                col = shiftTiles(row, col, 0, 1) ? 0 : col+1;
            }
        }
    }

    private void mergeLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3;) {
                mergeTiles(row,col,0,1);
                col++;
            }
        }
    }

    private void shiftRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0;) {
                col = shiftTiles(row,col,0,-1) ? 3 : col-1;
            }
        }
    }

    private void mergeRight() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0;) {
                mergeTiles(row,col,0,-1);
                col--;
            }
        }
    }

    private void shiftUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3;) {
                row = shiftTiles(row,col,1,0) ? 0 : row+1;
            }
        }
    }

    private void mergeUp() {
        for (int col = 0; col < 4; col++) {
            for (int row = 0; row < 3;) {
                mergeTiles(row,col,1,0);
                row++;
            }
        }
    }

    private void shiftDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0;) {
                row = shiftTiles(row,col,-1,0) ? 3 : row-1;
            }
        }
    }

    private void mergeDown() {
        for (int col = 0; col < 4; col++) {
            for (int row = 3; row > 0;) {
                mergeTiles(row,col,-1,0);
                row--;
            }
        }
    }

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

    private void countEmpty() {
        emptyTiles = 0;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getTileState(i,j) == 0) emptyTiles += 1;
            }
        }
    }

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

    public Events getLastEvent() {
        return lastEvent;
    }

    public Direction getLastDirection() {
        return lastDirection;
    }

    public boolean isModified() {
        return modified;
    }

    private int emptyTiles;
    private boolean modified;
    private Events lastEvent;
    private Direction lastDirection;

    public  enum Direction {LEFT,RIGHT,UP,DOWN}
    enum Events {RESET_GAME, GAME_OVER, GAME_WON}

}