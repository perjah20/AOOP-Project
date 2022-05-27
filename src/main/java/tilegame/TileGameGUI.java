package tilegame;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The abstract class TileGameGUI allows programmers to
 * represent their TileGameModel graphically.
 * It comes with 4 buttons the programmer can choose what to do with.
 * The arrow keys on the keyboard will execute the same code as the buttons.
 * It also allows the developer to add a menu bar but can omit this.
 * This class implements the GameObserver interface.
 *
 * @param <T> A controller for allowing the TileGameGUI to communicate
 *           with the game model.
 * @param <S> A Game Model to allow for easier implementation of GameObserver.
 * @see tilegame.TileGameModel
 * @see tilegame.TileGameController
 */
public abstract class TileGameGUI<T extends TileGameController<S>,
        S extends TileGameModel> extends JFrame implements GameObserver<S> {
    protected abstract void northButtonPressed();
    protected abstract void eastButtonPressed();
    protected abstract void southButtonPressed();
    protected abstract void westButtonPressed();
    protected abstract JMenuBar createMenuBar();

    /**
     * Creates a base GUI for the user to use when representing the game model.
     */
    public TileGameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(createTextAndButtonContainer(), BorderLayout.SOUTH);
        this.setResizable(false);
        JMenuBar menuBar = createMenuBar();
        if (menuBar != null)
            this.setJMenuBar(menuBar);
        this.add(createGrid(1,1),BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    /**
     * This method adds a game controller to allow communication between
     * the GUI and game model.
     * @param aTileGameController This is the controller to be used.
     */
    public void setGameController(T aTileGameController) {
        this.tileGameController = aTileGameController;
    }

    /**
     * Selects all text in the JTextArea window and replaces it supplied text.
     * @param text - Text to replace current text in JTextArea
     */
    public void showText(String text) {
        textArea.selectAll();
        textArea.replaceSelection(text);
    }

    /**
     * This method gets the north button of the GUI
     * @return North JButton
     */
    protected JButton getNorthButton() {
        return buttons[NORTH];
    }

    /**
     * This method gets the west button of the GUI
     * @return West JButton
     */
    protected JButton getWestButton() {
        return  buttons[WEST];
    }

    /**
     * This method gets the east button of the GUI
     * @return East JButton
     */
    protected JButton getEastButton() {
        return buttons[EAST];
    }

    /**
     * This method gets the south button of the GUI
     * @return South JButton
     */
    protected JButton getSouthButton() {
        return buttons[SOUTH];
    }

    /**
     * This method gets the text area
     * @return JTextArea
     */
    protected JTextArea getTextArea() {
        return textArea;
    }

    /**
     * This method returns a GameLabel object at location (row, column)
     * @param row This is the position of the row
     * @param column This is the position of the column
     * @return A GameLabel object.
     */
    protected GameLabel getTile(int row, int column) {
        return tiles[row][column];
    }

    /**
     * This method returns the controller for the game model
     * @return A T TileGameController object
     */
    protected T getTileGameController() {
        return tileGameController;
    }

    /**
     * This method returns the amount of rows in the game grid
     * @return The number of rows.
     */
    public int getRowLength() { return tiles.length; }

    /**
     * This method returns the amount of columns in the game grid
     * @return The number of columns.
     */
    public int getColLength() { return tiles[0].length; }

    /**
     * Creates a JPanel containing a JTextArea and a button container.
     * @return JPanel containing a JTextArea and a button container.
     */
    private JPanel createTextAndButtonContainer() {
        JPanel textAndButtonContainer = new JPanel();
        textAndButtonContainer.setLayout(new BorderLayout());
        textAndButtonContainer.add(createButtonContainer(),BorderLayout.WEST);
        textArea = new JTextArea("Sample Text");
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        addArrowkeyListeners(textArea);
        textAndButtonContainer.add(textArea,BorderLayout.CENTER);
        return textAndButtonContainer;
    }

    /**
     * Creates a JPanel containing 4 buttons with methods connected to them.
     * @return A JPanel containing 4 buttons.
     */
    private JPanel createButtonContainer() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        String[] positions = {BorderLayout.NORTH, BorderLayout.WEST, BorderLayout.EAST, BorderLayout.SOUTH};
        buttons = new JButton[4];
        buttons[NORTH] = new JButton("North");  buttons[NORTH].addActionListener(e -> northButtonPressed());
        buttons[WEST] = new JButton("West");   buttons[WEST].addActionListener(e -> westButtonPressed());
        buttons[EAST] = new JButton("East");   buttons[EAST].addActionListener(e -> eastButtonPressed());
        buttons[SOUTH] = new JButton("South");  buttons[SOUTH].addActionListener(e -> southButtonPressed());
        for (int i = 0; i < 4; i++) {
            buttonContainer.add(buttons[i], positions[i]);
        }
        return buttonContainer;
    }

    /**
     * Adds Java KeyBindings to a component which will be
     * executed if window is focused.
     * @param component This is the component you want to add the key bindings to.
     */
    private void addArrowkeyListeners(JComponent component) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = component.getActionMap();
        int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
        String[] actionMapKeys = {
                "invokeNorthButtonPressed",
                "invokeSouthButtonPressed",
                "invokeWestButtonPressed",
                "invokeEastButtonPressed"
        };
        Action[] actions = {
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[NORTH].doClick();}
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[SOUTH].doClick(); }
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[WEST].doClick(); }
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[EAST].doClick(); }
                }
        };
        for (int i = 0; i < 4; i++) {
            inputMap.put(KeyStroke.getKeyStroke(keys[i],0,true),actionMapKeys[i]);
            actionMap.put(actionMapKeys[i],actions[i]);
        }
    }

    /**
     * Creates a game grid and populates it with JLabels for user to modify with text or icons.
     * @param rows Amount of tiles in vertically (y-axis).
     * @param columns Amount of tiles in horizontally (x-axis).
     * @return A JPanel containing rows * columns JLabels.
     */
    protected JPanel createGrid(int rows, int columns) {
        if(gameGrid != null) this.remove(gameGrid);
        gameGrid = new JPanel(new GridLayout(rows, columns));
        tiles = new GameLabel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                GameLabel tile = new GameLabel();
                tile.setHorizontalAlignment(JLabel.CENTER);
                tile.setOpaque(true);
                tiles[i][j] = tile;
                gameGrid.add(tile);
            }
        }
        return gameGrid;
    }



    /**
     *  Used to display text of choice.
     */
    private JTextArea textArea;
    /**
     * Just used to add key bindings
     */
    private JButton[] buttons;
    /**
     * Used to access and manipulate the tiles
     */
    private GameLabel[][] tiles;
    /**
     * Used for removing or adding to the JFrame
     */
    private JPanel gameGrid;
    /**
     * Used to communicate the game model.
     */
    private T tileGameController;

    private final int NORTH = 0, WEST = 1, EAST = 2, SOUTH = 3;
}
