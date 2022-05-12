package sokoban;

import tileGame.GameObserver;
import tileGame.TileGameModel;

import java.util.Stack;

import static sokoban.SokobanInfo.BOX;
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
        tileStack.push(SokobanInfo.SAND);
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
    private boolean isValidMove(int Y, int X) {
        int[][] gameGrid = getGameState();
        if (gameGrid[playerLocationY + Y][playerLocationX + X] == COBBLESTONE)
            return false;
        if (gameGrid[playerLocationY + Y][playerLocationX + X] < BOX)
            return true;
        else return (gameGrid[playerLocationY + Y*2][playerLocationX + X*2] < BOX); //TODO Fix so that the array does not go out of bounds when we reach edges
    }

    public void moveCharacter(directions direction){
        setCharacterPosition();
        switch (direction){
            case NORTH:
                if (isValidMove(-1,0)) {
                nextLocation = getTileState(playerLocationY - 1, playerLocationX);
                currentLocation = getTileState(playerLocationY, playerLocationX);
                //int previousLocation = currentLocation;
                currentLocation = tileStack.pop();
                if (nextLocation == 10) {
                    tileStack.push(SAND);
                    moveCrate(direction);
                } else if (nextLocation == 15) {
                    tileStack.push(DOT);
                    moveCrate(direction);
                } else tileStack.push(nextLocation);
                nextLocation = CHARACTER;
                setTileState(currentLocation, playerLocationY, playerLocationX);
                setTileState(nextLocation, playerLocationY - 1, playerLocationX);
            }break;


            case WEST:
                if (isValidMove(0,-1)) {
                    nextLocation = getTileState(playerLocationY, playerLocationX - 1);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(direction);
                    } else if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(direction);
                    } else tileStack.push(nextLocation);
                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY, playerLocationX - 1);
                }break;

            case EAST:
                if (isValidMove(0,1)) {
                    nextLocation = getTileState(playerLocationY, playerLocationX + 1);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(direction);
                    } else if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(direction);
                    } else tileStack.push(nextLocation);

                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY, playerLocationX + 1);
                }break;

            case SOUTH:
                if (isValidMove(1,0)) {
                    nextLocation = getTileState(playerLocationY + 1, playerLocationX);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(direction);
                    } else if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(direction);
                    } else tileStack.push(nextLocation);
                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY + 1, playerLocationX);
                }break;
        }
        updateObservers();
    }


    public void moveCrate(directions direction) {
        //int[][] gameGrid = getGameState();
        switch (direction){
            case NORTH:
                crateNextLocation = getTileState(playerLocationY -2,playerLocationX);
                if (crateNextLocation == DOT)
                    setTileState(FILLEDBOX,playerLocationY -2,playerLocationX);
                else setTileState(BOX,playerLocationY -2,playerLocationX);
                break;
            case WEST:
                    crateNextLocation = getTileState(playerLocationY,playerLocationX -2);
                    if (crateNextLocation == DOT)
                        setTileState(FILLEDBOX,playerLocationY,playerLocationX -2);
                    else setTileState(BOX,playerLocationY,playerLocationX -2);
                break;
            case EAST:
                crateNextLocation = getTileState(playerLocationY,playerLocationX +2);
                if (crateNextLocation == DOT)
                    setTileState(FILLEDBOX,playerLocationY,playerLocationX +2);
                else setTileState(BOX,playerLocationY,playerLocationX +2);
                break;
            case SOUTH:
                    crateNextLocation = getTileState(playerLocationY +2,playerLocationX);
                if (crateNextLocation == DOT)
                    setTileState(FILLEDBOX,playerLocationY +2,playerLocationX);
                else setTileState(BOX,playerLocationY +2,playerLocationX);
                break;
        }
        updateObservers();
    }

    @Override
    protected void gameOver() {

    }

    @Override
    protected void gameWon() {

    }

    private Stack<Integer> tileStack;
    int playerLocationY;
    int playerLocationX;
    int crateCurrentLocation;
    int crateNextLocation;
    int currentLocation;
    int nextLocation;

    int SAND = 1, CHARACTER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;

    //    final int NORTH = 1, WEST = 2, EAST = 3, SOUTH = 4;
    enum directions {NORTH, WEST ,EAST, SOUTH}

}