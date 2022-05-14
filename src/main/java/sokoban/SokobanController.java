package sokoban;

import sokoban.buttonStrategies.ButtonStrategy;

public class SokobanController {
    private final SokobanGameModel sokobanGameModel;

    public SokobanController(SokobanGameModel aSokobanGameModel) {
        this.sokobanGameModel = aSokobanGameModel;
    }

    public void handleButtonPress(ButtonStrategy buttonStrategy){
        buttonStrategy.executeMethod(sokobanGameModel);
    }
}
