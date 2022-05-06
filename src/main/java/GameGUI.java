import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class GameGUI extends JFrame {
    public abstract JComponent createCenterComponent();

    public abstract void northButtonPressed();
    public abstract void eastButtonPressed();
    public abstract void southButtonPressed();
    public abstract void westButtonPressed();

    /**
     * Creates a base for *ett spel att ha*
     */
    public GameGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(createTextAndButtonContainer(), BorderLayout.SOUTH);
        JComponent centerComponent = createCenterComponent();
        addArrowkeyListeners(centerComponent);
        this.add(centerComponent,BorderLayout.CENTER);
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


    private JTextArea textArea;
    private JButton[] buttons;
}
