package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;
import static sokoban.SokobanGameModel.Directions;


public class MoveButton implements ButtonStrategy {
    Directions direction;

    public MoveButton(Directions aDirection) {
        this.direction = aDirection;
    }

    @Override
    public void executeMethod(SokobanGameModel sokobanGameModel) {
        sokobanGameModel.moveCharacter(direction);
    }
}
