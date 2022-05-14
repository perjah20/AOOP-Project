package sokoban;

import tileGame.GameObserver;
import tileGame.TileGameModel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class SokobanSounds implements GameObserver {

    public SokobanSounds() {
        /*
        File audioFile = new File("src/main/java/sokoban/sounds/footstep_carpet_002 (1).wav").getAbsoluteFile();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
         */
    }

    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        try {
            Method getLastEvent = gameModel.getClass().getMethod("getLastEvent", new Class[] {});
            SokobanInfo.Events lastEvent = (SokobanInfo.Events) getLastEvent.invoke(gameModel);
            switch (lastEvent) {
                case GAME_WON:
                    /*clip.start();
                    clip.setMicrosecondPosition(0);
                     */
                    System.out.println("Generate sound GAME_WON");
                    break;
                case TRIED_TO_MOVE:
                    /*clip.start();
                    clip.setMicrosecondPosition(0);
                     */
                    System.out.println("Generate sound TRIED_TO_MOVE");
                    break;
                case RESET_GAME:
                    /*clip.start();
                    clip.setMicrosecondPosition(0);
                     */
                    System.out.println("Generate sound RESET_GAME");
                    break;
                case MOVED_PLAYER:
                    /*clip.start();
                    clip.setMicrosecondPosition(0);
                     */
                    System.out.println("Generate sound MOVED_PLAYER");
                    break;
                case MOVED_BOX:
                    /*clip.start();
                    clip.setMicrosecondPosition(0);
                     */
                    System.out.println("Generate sound MOVED_BOX");
                    break;
            }
        } catch (Exception exception){}
    }
}
