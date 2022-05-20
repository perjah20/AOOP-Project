package UnitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sokoban.SokobanGameModel;
import static sokoban.SokobanInfo.Events.*;
import static sokoban.SokobanInfo.Directions.*;

import static org.junit.jupiter.api.Assertions.*;

class SokobanGameModelTest {
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

    @BeforeEach
    void setUp() {
        //sokobanGameModel = null;
    }

    @Test
    void playerMovingIntoCollision() {
        // Testing Collision
        SokobanGameModel sokobanGameModel = new SokobanGameModel(new int[][][]{testLevelCollision});
        sokobanGameModel.updateGameGrid(testLevelCollision);
        sokobanGameModel.moveCharacter(NORTH);
        assertEquals(testLevelCollision,sokobanGameModel.getGameState());
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(EAST);
        assertEquals(testLevelCollision,sokobanGameModel.getGameState());
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(WEST);
        assertEquals(testLevelCollision,sokobanGameModel.getGameState());
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
        sokobanGameModel.moveCharacter(SOUTH);
        assertEquals(testLevelCollision,sokobanGameModel.getGameState());
        assertEquals(TRIED_TO_MOVE, sokobanGameModel.getLastEvent());
    }

    @Test
    void playerPushingCrateNorth() {
        SokobanGameModel sokobanGameModelPushing = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModelPushing.updateGameGrid(testLevelPushing);
        sokobanGameModelPushing.moveCharacter(NORTH);
        assertEquals(4,sokobanGameModelPushing.getTileState(0,2));
        assertEquals(2,sokobanGameModelPushing.getTileState(1,2));
        assertEquals(MOVED_CRATE, sokobanGameModelPushing.getLastEvent());
    }

    @Test
    void playerPushingCrateWest() {
        SokobanGameModel sokobanGameModelPushing = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModelPushing.updateGameGrid(testLevelPushing);
        sokobanGameModelPushing.moveCharacter(WEST);
        assertEquals(5,sokobanGameModelPushing.getTileState(2,0));
        assertEquals(2,sokobanGameModelPushing.getTileState(2,1));
        assertEquals(FILLED_CRATE, sokobanGameModelPushing.getLastEvent());
    }

    @Test
    void playerPushingCrateEast() {
        SokobanGameModel sokobanGameModelPushing = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModelPushing.updateGameGrid(testLevelPushing);
        sokobanGameModelPushing.moveCharacter(EAST);
        assertEquals(2,sokobanGameModelPushing.getTileState(2,3));
        assertEquals(5,sokobanGameModelPushing.getTileState(2,4));
        assertEquals(FILLED_CRATE, sokobanGameModelPushing.getLastEvent());
    }

    @Test
    void playerPushingCrateSouth() {
        SokobanGameModel sokobanGameModelPushing = new SokobanGameModel(new int[][][]{testLevelPushing});
        sokobanGameModelPushing.updateGameGrid(testLevelPushing);
        sokobanGameModelPushing.moveCharacter(SOUTH);
        assertEquals(2,sokobanGameModelPushing.getTileState(3,2));
        assertEquals(4,sokobanGameModelPushing.getTileState(4,2));
        assertEquals(MOVED_CRATE, sokobanGameModelPushing.getLastEvent());
    }

    @Test
    void playerMovingNorth() {
        SokobanGameModel sokobanGameModelMoving = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModelMoving.updateGameGrid(testLevelMoving);
        sokobanGameModelMoving.moveCharacter(NORTH);
        assertEquals(1,sokobanGameModelMoving.getTileState(2,2));
        assertEquals(2,sokobanGameModelMoving.getTileState(1,2));
        assertEquals(MOVED_PLAYER, sokobanGameModelMoving.getLastEvent());
    }

    @Test
    void playerMovingWest() {
        SokobanGameModel sokobanGameModelMoving = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModelMoving.updateGameGrid(testLevelMoving);
        sokobanGameModelMoving.moveCharacter(WEST);
        assertEquals(1,sokobanGameModelMoving.getTileState(2,2));
        assertEquals(2,sokobanGameModelMoving.getTileState(2,1));
        assertEquals(MOVED_PLAYER, sokobanGameModelMoving.getLastEvent());
    }

    @Test
    void playerMovingEast() {
        SokobanGameModel sokobanGameModelMoving = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModelMoving.updateGameGrid(testLevelMoving);
        sokobanGameModelMoving.moveCharacter(EAST);
        assertEquals(1,sokobanGameModelMoving.getTileState(2,2));
        assertEquals(2,sokobanGameModelMoving.getTileState(2,3));
        assertEquals(MOVED_PLAYER, sokobanGameModelMoving.getLastEvent());
    }

    @Test
    void playerMovingSouth() {
        SokobanGameModel sokobanGameModelMoving = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModelMoving.updateGameGrid(testLevelMoving);
        sokobanGameModelMoving.moveCharacter(SOUTH);
        assertEquals(1,sokobanGameModelMoving.getTileState(2,2));
        assertEquals(2,sokobanGameModelMoving.getTileState(3,2));
        assertEquals(MOVED_PLAYER, sokobanGameModelMoving.getLastEvent());
    }

    @Test
    void resetLevel() {
        SokobanGameModel sokobanGameModelMoving = new SokobanGameModel(new int[][][]{testLevelMoving});
        sokobanGameModelMoving.updateGameGrid(testLevelMoving);
        sokobanGameModelMoving.moveCharacter(NORTH);
        assertEquals(1,sokobanGameModelMoving.getTileState(2,2));
        assertEquals(2,sokobanGameModelMoving.getTileState(1,2));
        sokobanGameModelMoving.resetLevel();
        assertEquals(testLevelMoving,sokobanGameModelMoving.getGameState());
        assertEquals(RESET_GAME, sokobanGameModelMoving.getLastEvent());
    }
}