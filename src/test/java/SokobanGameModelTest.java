import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sokoban.SokobanGameModel;
import static sokoban.SokobanInfo.Events.*;
import static sokoban.SokobanInfo.Directions.*;

import static org.junit.jupiter.api.Assertions.*;

class SokobanGameModelTest {

    private SokobanGameModel sokobanGameModel;
    private final int[][] testLevelCollision = new int[][] {
            {0,0,6,0,0},
            {0,0,6,0,0},
            {6,4,2,4,4},
            {0,0,5,0,0},
            {0,0,6,0,0}
    },
            testLevelPushing = new int[][] {
                    {0,0,1,0,0},
                    {0,0,5,0,0},
                    {3,5,2,4,3},
                    {0,0,4,0,0},
                    {0,0,1,0,0}
            },
                testLevelMoving = new int[][] {
                        {0,0,1,0,0},
                        {0,0,1,0,0},
                        {1,3,2,3,3},
                        {0,0,1,0,0},
                        {0,0,3,0,0}
                };

    @Test
    void playerMovingIntoCollision() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelCollision});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(NORTH);
        compareGrids(testLevelCollision);
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(EAST);
        compareGrids(testLevelCollision);
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(WEST);
        compareGrids(testLevelCollision);
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(SOUTH);
        compareGrids(testLevelCollision);
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerPushingCrateNorth() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(NORTH);
        assertEquals(4,sokobanGameModel.getTileState(0,2));
        assertEquals(2,sokobanGameModel.getTileState(1,2));
        assertEquals(MOVED_CRATE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerPushingCrateWest() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(WEST);
        assertEquals(5,sokobanGameModel.getTileState(2,0));
        assertEquals(2,sokobanGameModel.getTileState(2,1));
        assertEquals(FILLED_CRATE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerPushingCrateEast() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(EAST);
        assertEquals(2,sokobanGameModel.getTileState(2,3));
        assertEquals(5,sokobanGameModel.getTileState(2,4));
        assertEquals(FILLED_CRATE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerPushingCrateSouth() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(SOUTH);
        assertEquals(2,sokobanGameModel.getTileState(3,2));
        assertEquals(4,sokobanGameModel.getTileState(4,2));
        assertEquals(MOVED_CRATE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerMovingNorth() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(NORTH);
        assertEquals(1,sokobanGameModel.getTileState(2,2));
        assertEquals(2,sokobanGameModel.getTileState(1,2));
        assertEquals(MOVED_PLAYER, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerMovingWest() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(WEST);
        assertEquals(1,sokobanGameModel.getTileState(2,2));
        assertEquals(2,sokobanGameModel.getTileState(2,1));
        assertEquals(MOVED_PLAYER, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerMovingEast() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(EAST);
        assertEquals(1,sokobanGameModel.getTileState(2,2));
        assertEquals(2,sokobanGameModel.getTileState(2,3));
        assertEquals(MOVED_PLAYER, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerMovingSouth() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(SOUTH);
        assertEquals(1,sokobanGameModel.getTileState(2,2));
        assertEquals(2,sokobanGameModel.getTileState(3,2));
        assertEquals(MOVED_PLAYER, sokobanGameModel.getLastEvent());
    }

    @Test
    void resetLevel() {
        sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModel.startGame();
        sokobanGameModel.moveCharacter(NORTH);
        assertEquals(1,sokobanGameModel.getTileState(2,2));
        assertEquals(2,sokobanGameModel.getTileState(1,2));
        sokobanGameModel.resetLevel();
        compareGrids(testLevelMoving);
        assertEquals(RESET_GAME, sokobanGameModel.getLastEvent());
    }

    private void compareGrids(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                assertEquals(grid[i][j], sokobanGameModel.getTileState(i,j));
            }
        }
    }
}