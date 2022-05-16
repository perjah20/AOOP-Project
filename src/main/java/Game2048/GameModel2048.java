package Game2048;

import tileGame.TileGameModel;

public class GameModel2048 extends TileGameModel {
    public GameModel2048() {

    }

    public void move(int direction) {
        System.out.println("invoked");
        int tileMerged = 0;
        int col = 0;
        int row = 0;
        int currentTileValue = 0;
        int nextTileValue = 0;
        switch (direction) {
            case 1:
                // Move Left
                for (row = 0; row < 4; row++) {
                    col = 0;
                    tileMerged = 0;
                    while(col < 3) {
                        currentTileValue = this.getTileState(row,col);
                        nextTileValue = this.getTileState(row,col+1);
                        if(currentTileValue == 0 && nextTileValue != 0)
                        {
                            setTileState(nextTileValue,row,col);
                            setTileState(0,row,col + 1);
                            //col = tileMerged;
                            col = 0;
                        }
                        else if (currentTileValue == nextTileValue && currentTileValue != 0)
                        {
                            setTileState(currentTileValue + nextTileValue,row,col);
                            setTileState(0,row,col + 1);
                            //tileMerged = col;
                        }
                        else col++;
                    }
                }
                break;
            case 2:
                // Move right
                for (row = 0; row < 4; row++) {
                    col = 3;
                    tileMerged = 3;
                    while(col > 0) {
                        currentTileValue = this.getTileState(row,col);
                        nextTileValue = this.getTileState(row,col - 1);
                        if(currentTileValue == 0 && nextTileValue != 0)
                        {
                            setTileState(nextTileValue,row,col);
                            setTileState(0,row,col - 1);
                            //col = tileMerged;
                            col = 3;
                        }
                        else if (currentTileValue == nextTileValue)
                        {
                            setTileState(currentTileValue + nextTileValue,row,col);
                            setTileState(0,row,col - 1);
                            tileMerged = col;
                        }
                        else col--;
                    }
                }
                break;
            case 3:
                // Move up
                for (col = 0; col < 4; col++) {
                    row = 0;
                    tileMerged = 0;
                    while(row < 3) {
                        currentTileValue = this.getTileState(row,col);
                        nextTileValue = this.getTileState(row+1,col);
                        if(currentTileValue == 0 && nextTileValue != 0)
                        {
                            setTileState(nextTileValue,row,col);
                            setTileState(0,row+1,col);
                            //row = tileMerged;
                            row = 0;
                        }
                        else if (currentTileValue == nextTileValue)
                        {
                            setTileState(currentTileValue + nextTileValue,row,col);
                            setTileState(0,row+1,col);
                            tileMerged = row;
                        }
                        else row++;
                    }
                }
                break;
            case 4:
                // Move down
                for (col = 0; col < 4; col++) {
                    row = 3;
                    tileMerged = 3;
                    while(row > 0) {
                        currentTileValue = this.getTileState(row,col);
                        nextTileValue = this.getTileState(row-1,col);
                        if(currentTileValue == 0 && nextTileValue != 0)
                        {
                            setTileState(nextTileValue,row,col);
                            setTileState(0,row-1,col);
                            //row = tileMerged;
                            row = 3;
                        }
                        else if (currentTileValue == nextTileValue)
                        {
                            setTileState(currentTileValue + nextTileValue,row,col);
                            setTileState(0,row-1,col);
                            tileMerged = row;
                        }
                        else row--;
                    }
                }
                break;
        }
    updateObservers();
    }

}
