package sokoban;

import tileGame.GameObserver;
import tileGame.TileGameModel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class SokobanSounds implements GameObserver {

    public SokobanSounds() {


    }

    public void playSound(String filename) {
        try {
            //File audioFile = new File("src/main/java/sokoban/sounds/footstep_carpet_002 (1).wav").getAbsoluteFile();
            File audioFile = new File(filename);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
            clip.setMicrosecondPosition(0);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex){
            System.out.println("Something went wrong while trying to play sound");
        }

    }

    @Override
    public void updateGameObserver(TileGameModel gameModel) {
        try {
            Method getLastEvent = gameModel.getClass().getMethod("getLastEvent", new Class[] {});
            SokobanInfo.Events lastEvent = (SokobanInfo.Events) getLastEvent.invoke(gameModel);
            switch (lastEvent) {
                case GAME_WON:
                    playSound("src/main/java/sokoban/sounds/You_Win.wav");
                    System.out.println("Generate sound GAME_WON");
                    break;
                case TRIED_TO_MOVE:
                    playSound("src/main/java/sokoban/sounds/Tried_To_Move.wav");
                    System.out.println("Generate sound TRIED_TO_MOVE");
                    break;
                case RESET_GAME:
                    //playSound("src/main/java/sokoban/sounds/Reset_Level.wav");
                    System.out.println("Generate sound RESET_GAME");
                    break;
                case MOVED_PLAYER:
                    //playSound("src/main/java/sokoban/sounds/Moved_Player.wav");
                    System.out.println("Generate sound MOVED_PLAYER");
                    break;
                case MOVED_BOX:
                    playSound("src/main/java/sokoban/sounds/Moved_Crate.wav");
                    System.out.println("Generate sound MOVED_BOX");
                    break;
            }
        } catch (Exception exception){}
    }
}