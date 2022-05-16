package Game2048;


import tileGame.TileGameController;

public class Game2048 {
    private int[][] start = {
            {0,0,0,2},
            {0,0,0,2},
            {0,0,0,2},
            {0,0,0,2}
        };

    public Game2048() {
        GameModel2048 gameModel2048 = new GameModel2048();
        GameGUI2048 gameGUI2048 = new GameGUI2048(new TileGameController(gameModel2048));
        gameModel2048.addGameObserver(gameGUI2048);
        gameModel2048.addGameObserver(new Console2048());
        gameModel2048.updateGameGrid(start);
        //gameModel2048.move(3);
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
