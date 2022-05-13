package sokoban;

public class SokobanGame  {

    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        SokobanGameModel sokobanGameModel = new SokobanGameModel();
        SokobanGameGUI sokobanGameGUI = new SokobanGameGUI();
        sokobanGameGUI.setController(new SokobanController(sokobanGameGUI,sokobanGameModel));
        sokobanGameModel.addGameObserver(sokobanGameGUI);
        sokobanGameModel.addGameObserver(new SokobanConsole());
        sokobanGameModel.updateGameGrid(SokobanInfo.level1);

    }
}
