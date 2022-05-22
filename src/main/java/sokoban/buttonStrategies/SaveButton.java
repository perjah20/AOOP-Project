package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;
import tilegame.ButtonStrategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * This class is used in SokobanGameGUI to serialize a SokobanGameModel object.
 * It is added to the Save Game button in the menu bar.
 * @see sokoban/SokobanGameGUI.java#createMenuBar()
 */
public class SaveButton implements ButtonStrategy<SokobanGameModel> {

    /**
     * This method will attempt to save a SokobanGameModel object as
     * "src/main/java/sokoban/SokobanGameModel.ser"
     * @param gameModel This is the SokobanGameModel you want to serialize.
     */
    @Override
    public void executeMethod(SokobanGameModel gameModel) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/sokoban/SokobanGameModel.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            gameModel.saveGame();
            out.writeObject(gameModel);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
