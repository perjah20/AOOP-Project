package sokoban;

import tileGame.TileGame;

public class SokobanGame extends TileGame<SokobanGameModel,SokobanGameGUI,SokobanController> {

    /**
     * Instantiates a Sokoban game session.
     *
     */
    public SokobanGame() {
        super(new SokobanGameModel(),new SokobanGameGUI(),new SokobanController());
        getTileGameModel().addGameObserver(new SokobanConsole());
        getTileGameModel().addGameObserver(new SokobanSounds());
        this.getTileGameModel().updateGameGrid(SokobanInfo.getLevel(0));
    }

}
