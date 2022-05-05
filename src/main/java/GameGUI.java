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

    public void showText(String text) {
        textArea.selectAll();
        textArea.replaceSelection(text);
    }

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

    private JPanel createButtonContainer() {
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        String[] positions = {BorderLayout.NORTH, BorderLayout.WEST, BorderLayout.EAST, BorderLayout.SOUTH};
        JButton[] buttons = new JButton[4];
        buttons[0] = new JButton("North");  buttons[0].addActionListener(e -> northButtonPressed());
        buttons[1] = new JButton("West");   buttons[1].addActionListener(e -> westButtonPressed());
        buttons[2] = new JButton("East");   buttons[2].addActionListener(e -> eastButtonPressed());
        buttons[3] = new JButton("South");  buttons[3].addActionListener(e -> southButtonPressed());
        for (int i = 0; i < 4; i++) {
            buttonContainer.add(buttons[i], positions[i]);
        }
        return buttonContainer;
    }

    private void addArrowkeyListeners(JComponent component) {
        InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
        String[] actionMapKeys = {
                "invokeNorthButtonPressed",
                "invokeSouthButtonPressed",
                "invokeWestButtonPressed",
                "invokeEastButtonPressed"
        };

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0,true),"invokeNorthButtonPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0,true),"invokeSouthButtonPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0,true),"invokeWestButtonPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0,true),"invokeEastButtonPressed");

        ActionMap actionMap = component.getActionMap();

        actionMap.put("invokeNorthButtonPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                northButtonPressed();
            }
        });
        actionMap.put("invokeSouthButtonPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                southButtonPressed();
            }
        });
        actionMap.put("invokeWestButtonPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                westButtonPressed();
            }
        });
        actionMap.put("invokeEastButtonPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eastButtonPressed();
            }
        });
    }

    private JTextArea textArea;
}
