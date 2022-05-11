package sokoban;

import tileGame.TileGameModel;

public class SokobanGameModel extends TileGameModel {
    /**
     * Constructs a GameModel object.
     *
     * @param rows    Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     */
    public SokobanGameModel(int rows, int columns) {
        super(rows, columns);
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

    public void moveCharacter( int direction){
        // lokalisera karaktären
        // Kolla vilken riktning vi ska gå
        // Kolla vilken tile som finns på nästa position

        setCharacterPosition();
        int[][] gameGrid = getGameState();
        switch (direction){
            case NORTH:
                if (isValidMove(-1,0));
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

    private boolean isValidMove(int Y, int X) {
        int[][] gameGrid = getGameState();
        if (gameGrid[playerLocationY + Y][playerLocationX + X] < COBBLESTONE)
            return (gameGrid[playerLocationY + Y*2][playerLocationX + X*2] < FILLEDBOX);
        return false;
    }

    public void moveBox(int nextTileNumber, int direction){
        if (nextTileNumber == 20){
            // setText = "Unnable to move box";
        }
        if (nextTileNumber == 3){

            moveBox();
        }

    }

    @Override
    protected void gameOver() {

    }

    @Override
    protected void gameWon() {

    }

    int playerLocationY;
    int playerLocationX;

    int SAND = 1, CHARACTER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;
    final int NORTH = 1, WEST = 2, EAST = 3, SOUTH = 4;
}