package sokoban;

import tileGame.GameObserver;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SokobanSounds implements GameObserver<SokobanGameModel> {

    public SokobanSounds() {


    }

    public void playSound(String filename) {
        try {
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
    public void updateGameObserver(SokobanGameModel gameModel) {
            switch (gameModel.getLastEvent()) {
                case GAME_WON:
                    playSound("src/main/java/sokoban/sounds/You_Win.wav");
                    break;
                case TRIED_TO_MOVE:
                    playSound("src/main/java/sokoban/sounds/Tried_To_Move.wav");
                    break;
                case RESET_GAME:
                    playSound("src/main/java/sokoban/sounds/Reset_Level.wav");
                    break;
                case MOVED_PLAYER:
                    playSound("src/main/java/sokoban/sounds/Moved_Player.wav");
                    break;
                case MOVED_CRATE:
                    playSound("src/main/java/sokoban/sounds/Moved_Crate.wav");
                    break;
                case FILLED_CRATE:
                    playSound("src/main/java/sokoban/sounds/Crate_Filled.wav");
            }
    }
}
