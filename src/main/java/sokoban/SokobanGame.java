package sokoban;

/**
 * The SokobanGame class holds the method for instantiating the game.
 */
public class SokobanGame  {

    /**
     * Instantiates a Sokoban game session.
     */
    public SokobanGame() {
        SokobanGameModel sokobanGameModel = new SokobanGameModel();
        SokobanGameGUI sokobanGameGUI = new SokobanGameGUI();
        sokobanGameGUI.setController(new SokobanController(sokobanGameModel));
        sokobanGameModel.addGameObserver(sokobanGameGUI);
        sokobanGameModel.addGameObserver(new SokobanConsole());
        sokobanGameModel.addGameObserver(new SokobanSounds());
        sokobanGameModel.updateGameGrid(SokobanInfo.getLevel(0));
    }
}
