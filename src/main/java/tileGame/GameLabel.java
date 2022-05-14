package tileGame;

import javax.swing.*;

public class GameLabel extends JLabel {
    private int tileValue;
    public GameLabel() {
        this.tileValue = 0;
    }
    public GameLabel(int tileValue) {
        this.tileValue = tileValue;
    }

    public int getTileValue() {
        return tileValue;
    }

    public void setTileValue(int tileValue) {
        this.tileValue = tileValue;
    }
}
