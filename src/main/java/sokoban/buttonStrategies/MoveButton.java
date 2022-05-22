package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;
import tileGame.ButtonStrategy;

import static sokoban.SokobanInfo.Directions;

/**
 * This class is used to allow SokobanGameGUI to control SokobanGameModel
 * in which direction the user want to move the player.
 */
public class MoveButton implements ButtonStrategy<SokobanGameModel> {
    private final Directions direction;

    /**
     * Creates a new button with a direction for which to move the player.
     * @param aDirection This is the direction to which to move the player
     */
    public MoveButton(Directions aDirection) {
        this.direction = aDirection;
    }

    /**
     * This method executes the move character method in SokobanGameModel.
     * @see sokoban/SokobanGameModel.java#moveCharacter(Directions direction)
     * @param gameModel This is the SokobanGameModel object to execute the method on.
     */
    @Override
    public void executeMethod(SokobanGameModel gameModel) {
        gameModel.moveCharacter(direction);
    }
}
