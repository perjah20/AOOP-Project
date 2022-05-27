package game2048;

import tilegame.TileGame;

/**
 * The Game2048 class holds the method for instantiating the game.
 */
public class Game2048 extends TileGame<GameModel2048,GameGUI2048,GameController2048> {
    /**
     * Instantiates 2048 game session.
     */
    public Game2048() {
        super(new GameModel2048(), new GameGUI2048(), new GameController2048());
        getTileGameModel().addGameObserver(new Console2048());
        getTileGameModel().updateObservers();
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
