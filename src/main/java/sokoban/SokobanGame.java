package sokoban;

public class SokobanGame  {
    private SokobanGameModel sokobanGameModel;
    private SokobanGameGUI sokobanGameGUI;
    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        sokobanGameModel = new SokobanGameModel(9,8);
        sokobanGameGUI = new SokobanGameGUI(9,8);
        sokobanGameGUI.setController(new SokobanController(sokobanGameGUI,sokobanGameModel));
        sokobanGameModel.addGameObserver(sokobanGameGUI);
        sokobanGameModel.addGameObserver(new SokobanConsole());
        sokobanGameModel.updateGameGrid(SokobanInfo.level1);

    }
}
