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
        tileStack.push(SAND);
    }

    private void setCharacterPosition() {
        int[][] gameGrid = getGameState();

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                if (gameGrid[i][j] == 2) {
                    playerLocationY = i;
                    playerLocationX = j;
                    return;
                }
            }
        }
    }

    public void moveCharacter(int direction){
        // lokalisera karaktären
        // Kolla vilken riktning vi ska gå
        // Kolla vilken tile som finns på nästa position

        setCharacterPosition();
        switch (direction){
            case NORTH:
                if (isValidMove(-1,0)) {
                    nextLocation = getTileState(playerLocationY - 1, playerLocationX);
                    //currentLocation = getTileState(playerLocationY,playerLocationX);
                    previousLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(NORTH);
                    }
                    if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(NORTH);
                    }
                    nextLocation = CHARACTER;
                    setTileState(previousLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY - 1, playerLocationX);
                    break;
                }

            case WEST:
                if (isValidMove(0,-1)) {
                    nextLocation = getTileState(playerLocationY, playerLocationX - 1);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(NORTH);
                    }
                    if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(NORTH);
                    }
                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY, playerLocationX - 1);
                    break;
                }

            case EAST:
                if (isValidMove(0,1)) {
                    nextLocation = getTileState(playerLocationY, playerLocationX + 1);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(NORTH);
                    }
                    if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(NORTH);
                    }
                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY, playerLocationX + 1);
                    break;
                }

            case SOUTH:
                if (isValidMove(1,0)) {
                    nextLocation = getTileState(playerLocationY + 1, playerLocationX);
                    currentLocation = getTileState(playerLocationY, playerLocationX);
                    //int previousLocation = currentLocation;
                    currentLocation = tileStack.pop();
                    if (nextLocation == 10) {
                        tileStack.push(SAND);
                        moveCrate(NORTH);
                    }
                    if (nextLocation == 15) {
                        tileStack.push(DOT);
                        moveCrate(NORTH);
                    }
                    nextLocation = CHARACTER;
                    setTileState(currentLocation, playerLocationY, playerLocationX);
                    setTileState(nextLocation, playerLocationY + 1, playerLocationX);
                    break;
                }
        }
        updateObservers();
    }

    private boolean isValidMove(int Y, int X) {
        int[][] gameGrid = getGameState();
        if (gameGrid[playerLocationY + Y][playerLocationX + X] < COBBLESTONE)
            return (gameGrid[playerLocationY + Y*2][playerLocationX + X*2] < BOX);
        return false;
    }

    public void moveCrate(int direction) {
        //int[][] gameGrid = getGameState();
        switch (direction){
            case NORTH:
                if (isValidMove(-1,0));
                nextLocation = getTileState(playerLocationY -1, playerLocationX);
                currentLocation = getTileState(playerLocationY,playerLocationX);
                int previousLocation = currentLocation;
                currentLocation = tileStack.pop();
                if (nextLocation == 10) {
                    tileStack.push(SAND);
                    moveCrate(NORTH);
                }
                if (nextLocation == 15) tileStack.push(DOT);
                // repa
                break;
            case WEST:
                if (isValidMove(0,-1));
                break;
            case EAST:
                if (isValidMove(0,1));
                break;
            case SOUTH:
                if (isValidMove(1,0))
                    break;
        }
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
    int previousLocation;
    int currentLocation;
    int nextLocation;

    int SAND = 1, CHARACTER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;
    final int NORTH = 1, WEST = 2, EAST = 3, SOUTH = 4;
}