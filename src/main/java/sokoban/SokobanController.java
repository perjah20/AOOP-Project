package sokoban;

import sokoban.buttonStrategies.ButtonStrategy;

public class SokobanController {
    private SokobanGameGUI sokobanGameGUI;
    private SokobanGameModel sokobanGameModel;

    public SokobanController(SokobanGameGUI aSokobanGameGUI, SokobanGameModel aSokobanGameModel) {
        this.sokobanGameGUI = aSokobanGameGUI;
        this.sokobanGameModel = aSokobanGameModel;
    }

    public void handleButtonPress(ButtonStrategy buttonStrategy){
        sokobanGameModel.processButton(buttonStrategy);
    }

}
