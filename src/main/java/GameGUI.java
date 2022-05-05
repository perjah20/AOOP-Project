import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        centerComponent = createCenterComponent();
        inputMap = centerComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
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
            addArrowkeyListeners(buttons[i]);
        }
        return buttonContainer;
    }

    private void addArrowkeyListeners(JComponent component) {
        component.addKeyListener(new KeyListener() {
           @Override
           public void keyPressed(KeyEvent e) {
               switch (e.getKeyCode()){
                   case KeyEvent.VK_UP -> northButtonPressed();
                   case KeyEvent.VK_DOWN -> southButtonPressed();
                   case KeyEvent.VK_LEFT -> westButtonPressed();
                   case KeyEvent.VK_RIGHT -> eastButtonPressed();
                   default -> System.out.println("Key has been pressed but something went wrong");
               }
           }
           @Override
           public void keyReleased(KeyEvent e) {
           }
           @Override
           public void keyTyped(KeyEvent e) {}
       });
    }

    private JTextArea textArea;
    private JComponent centerComponent;
    private InputMap inputMap;
}
