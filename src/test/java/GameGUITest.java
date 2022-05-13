import org.junit.jupiter.api.Test;
import tileGame.TileGameGUI;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class GameGUITest {

    @Test
    public void testGUI () {

    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File("src/test/java/footstep_carpet_002 (1).wav").getAbsoluteFile();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        new TileGameGUI() {

            @Override
            public void updateGameObserver(int[][] gameState) {

            }

            @Override
            public void northButtonPressed() {
                System.out.println("NorthButtonPressed");
                clip.start();
                clip.setMicrosecondPosition(0);
            }

            @Override
            public void eastButtonPressed() {
                System.out.println("EastButtonPressed");
                clip.start();
                clip.setMicrosecondPosition(0);
            }

            @Override
            public void southButtonPressed() {
                System.out.println("SouthButtonPressed");
                clip.start();
                clip.setMicrosecondPosition(0);
            }

            @Override
            public void westButtonPressed() {
                System.out.println("WestButtonPressed");
                clip.start();
                clip.setMicrosecondPosition(0);
            }

            @Override
            protected JMenuBar createMenuBar() {
                return null;
            }
        };
    }
}
