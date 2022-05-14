package sokoban;

import sokoban.buttonStrategies.ButtonStrategy;

public class SokobanController {
    private final SokobanGameGUI sokobanGameGUI;
    private final SokobanGameModel sokobanGameModel;

    public SokobanController(SokobanGameGUI aSokobanGameGUI, SokobanGameModel aSokobanGameModel) {
        this.sokobanGameGUI = aSokobanGameGUI;
        this.sokobanGameModel = aSokobanGameModel;
    }

    public void handleButtonPress(ButtonStrategy buttonStrategy){
        buttonStrategy.executeMethod(sokobanGameModel);
    }
}
