package tileGame;

import javax.swing.*;

/**
 * A class that extends JLabel but has added private field.
 * The private field used is to store the value from the game model
 * to be used when comparing.
 */
public class GameLabel extends JLabel {

    /**
     * Constructs a new GameLabel object with initial value of 0.
     */
    public GameLabel() {
        this.tileValue = 0;
    }

    /**
     * Constructs a new GameLabel object with initial value of tileValue.
     * @param tileValue Value to assign the GameLabel.
     */
    public GameLabel(int tileValue) {
        this.tileValue = tileValue;
    }

    /**
     * This method gets the value of the GameLabel object
     * @return The value of the GameLabel object
     */
    public int getTileValue() {
        return tileValue;
    }

    /**
     * This method sets the value of the GameLabel object
     * @param tileValue Value to assign the GameLabel
     */
    public void setTileValue(int tileValue) {
        this.tileValue = tileValue;
    }
    private int tileValue;
}
