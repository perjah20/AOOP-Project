package Game2048;

import tileGame.TileGame;

public class Game2048 extends TileGame<GameModel2048,GameGUI2048,GameController2048> {
    private final int[][] start = {
            {1024,1024,512,256},
            {128,64,32,16},
            {8,4,2,0},
            {0,0,0,0},
    };
    public Game2048() {
        super(new GameModel2048(), new GameGUI2048(), new GameController2048());
        getTileGameModel().addGameObserver(new Console2048());
        getTileGameModel().updateObservers();
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
