package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;
import tileGame.ButtonStrategy;

import static sokoban.SokobanInfo.Directions;


public class MoveButton implements ButtonStrategy<SokobanGameModel> {
    Directions direction;

    public MoveButton(Directions aDirection) {
        this.direction = aDirection;
    }

    @Override
    public void executeMethod(SokobanGameModel gameModel) {
        gameModel.moveCharacter(direction);
    }
}
