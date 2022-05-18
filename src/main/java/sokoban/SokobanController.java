package sokoban;

import sokoban.buttonStrategies.ButtonStrategy;

/**
 * The SokobanController class is a mediator between GameModel and GameGUI.
 */
public class SokobanController {
    private final SokobanGameModel sokobanGameModel;

    /**
     *
     * @param aSokobanGameModel
     */
    public SokobanController(SokobanGameModel aSokobanGameModel) {
        this.sokobanGameModel = aSokobanGameModel;
    }

    public void handleButtonPress(ButtonStrategy buttonStrategy){
        buttonStrategy.executeMethod(sokobanGameModel);
    }
}
