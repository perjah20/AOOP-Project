package sokoban;

import tilegame.TileGame;
/**
 * The SokobanGame class holds the method for instantiating the game.
 */
public class SokobanGame extends TileGame<SokobanGameModel,SokobanGameGUI,SokobanController> {

    /**
     * Instantiates a Sokoban game session.
     * Adds a console observer and a sound observer.
     */
    public SokobanGame() {
        super(new SokobanGameModel(SokobanInfo.sokobanLevels),new SokobanGameGUI(),new SokobanController());
        getTileGameModel().addGameObserver(new SokobanConsole());
        getTileGameModel().addGameObserver(new SokobanSounds());
        this.getTileGameModel().startGame();
    }

    public static void main(String[] args) {
        new SokobanGame();
    }
}
