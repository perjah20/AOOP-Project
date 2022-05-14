package sokoban;

public class SokobanGame  {

    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        SokobanGameModel sokobanGameModel = new SokobanGameModel();
        SokobanGameGUI sokobanGameGUI = new SokobanGameGUI();
        sokobanGameGUI.setController(new SokobanController(sokobanGameModel));
        sokobanGameModel.addGameObserver(sokobanGameGUI);
        sokobanGameModel.addGameObserver(new SokobanConsole());
        sokobanGameModel.updateGameGrid(SokobanInfo.getLevel(0));

    }
}
