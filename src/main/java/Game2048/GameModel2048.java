package Game2048;

import tileGame.TileGameModel;

public class GameModel2048 extends TileGameModel {
    public GameModel2048() {
        this.updateGameGrid(new int[4][4]);
        resetGame();
    }

    public void move(Direction direction) {
        System.out.println("invoked");
        switch (direction) {
            case LEFT -> {
                shiftLeft();
                mergeLeft();
                shiftLeft();
            }
            case RIGHT -> {
                shiftRight();
                mergeRight();
                shiftRight();
            }
            case UP -> {
                shiftUp();
                mergeUp();
                shiftUp();
            }
            case DOWN -> {
                shiftDown();
                mergeDown();
                shiftDown();
            }
        }
        updateObservers();
    }

    private void shiftLeft() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 3;) {
                if (shiftTiles(row,col,0,1))
                    col = 0;
                else
                    col++;
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
                if (shiftTiles(row,col,0,-1))
                    col = 3;
                else
                    col--;
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
                if (shiftTiles(row,col,1,0))
                    row = 0;
                else
                    row++;
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
                if (shiftTiles(row,col,-1,0))
                    row = 3;
                else
                    row--;
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
        }
    }

    private boolean shiftTiles(int row, int col, int offsetY, int offsetX) {


        int currentTileValue = this.getTileState(row, col);
        int nextTileValue = this.getTileState(row + offsetY, col + offsetX);
        if (currentTileValue == 0 && nextTileValue != 0) {
            setTileState(nextTileValue, row, col);
            setTileState(0,row + offsetY, col + offsetX);
            return true;
        } else return false;
    }

    private void generateRandomNumber() {
        if (Math.random() >= 0.9);
    }
    enum Direction {LEFT,RIGHT,UP,DOWN}
}