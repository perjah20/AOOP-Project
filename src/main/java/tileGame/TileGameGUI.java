package tileGame;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class TileGameGUI extends JFrame implements GameObserver {
    protected abstract void northButtonPressed();
    protected abstract void eastButtonPressed();
    protected abstract void southButtonPressed();
    protected abstract void westButtonPressed();
    protected abstract JMenuBar createMenuBar();

    /**
     * Creates a base for *ett spel att ha*
     */
    public TileGameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(createTextAndButtonContainer(), BorderLayout.SOUTH);
        JMenuBar menuBar = createMenuBar();
        if (menuBar != null)
            this.setJMenuBar(menuBar);
        this.add(createGrid(1,1),BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
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
        buttons[0] = new JButton("North");  buttons[0].addActionListener(e -> northButtonPressed());
        buttons[1] = new JButton("West");   buttons[1].addActionListener(e -> westButtonPressed());
        buttons[2] = new JButton("East");   buttons[2].addActionListener(e -> eastButtonPressed());
        buttons[3] = new JButton("South");  buttons[3].addActionListener(e -> southButtonPressed());
        for (int i = 0; i < 4; i++) {
            buttonContainer.add(buttons[i], positions[i]);
        }
        return buttonContainer;
    }

    /**
     * Adds Java KeyBindings to a component which will be executed if window is focused.
     * @param component - To add KeyBindings to.
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
                    public void actionPerformed(ActionEvent e) { buttons[0].doClick();}
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[3].doClick(); }
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[1].doClick(); }
                },
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) { buttons[2].doClick(); }
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
        gameGrid = new JPanel(new GridLayout(rows, columns));
        tiles = new JLabel[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                JLabel tile = new JLabel();
                tile.setHorizontalAlignment(JLabel.CENTER);
                tile.setOpaque(true);
                tiles[i][j] = tile;
                gameGrid.add(tile);
            }
        }
        return gameGrid;
    }

    protected JLabel getTile(int row, int column) {
        return tiles[row][column];
    }

    public JLabel[][] getTiles() {
        return tiles;
    }

    //TODO Add functions to manipulate the gameGrid
    //  "Update a specific tile on the gameGrid"
    //  "Get status of specific tile on the gameGrid"

    //TODO Add a iterator for the gameGrid.

    //TODO Add a abstract start functions that populates the gameGrid. Perhaps return an array containing values.

    // TODO Add a function that converts GameModel gamegrid values to sokoban tiles.

    // TODO Consider adding a function that can change dimensions of gamegrid


    private JTextArea textArea; /** Used to display text of choice. **/
    private JButton[] buttons;  /** Just used so I could add key bindings **/
    private JLabel[][] tiles;   /** Used to access and manipulate the tiles **/
    protected JPanel gameGrid;
}
