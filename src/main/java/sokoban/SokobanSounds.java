package sokoban;

import tilegame.GameObserver;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to generate sounds based on the last event that occurred
 */
public class SokobanSounds implements GameObserver<SokobanGameModel> {
    /**
     * This method will play the specified
     * @param filename Name of soundfile.
     */
    private void playSound(String filename) {
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

    /**
     * Plays a sound based on the last event that occurred in the game model.
     * @param gameModel A game model to read changes from.
     */
    @Override
    public final void updateGameObserver(SokobanGameModel gameModel) {
        final String path = "src/main/java/sokoban/sounds/";
            switch (gameModel.getLastEvent()) {
                case GAME_WON:
                    playSound(path+"You_Win.wav");
                    break;
                case TRIED_TO_MOVE:
                    playSound(path+"Tried_To_Move.wav");
                    break;
                case RESET_GAME:
                    playSound(path+"Reset_Level.wav");
                    break;
                case MOVED_PLAYER:
                    playSound(path+"Moved_Player.wav");
                    break;
                case MOVED_CRATE:
                    playSound(path+"Moved_Crate.wav");
                    break;
                case FILLED_CRATE:
                    playSound(path+"Crate_Filled.wav");
            }
    }
}
