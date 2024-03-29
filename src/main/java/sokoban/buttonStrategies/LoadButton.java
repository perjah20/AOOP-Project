package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;
import tilegame.ButtonStrategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * This class is used in SokobanGameGUI to deserialize a SokobanGameModel object.
 * It is added to the Load Game button in the menu bar.
 */
public class LoadButton implements ButtonStrategy<SokobanGameModel> {

    /**
     * This method will attempt to get a serialized SokobanGameModel object at the location
     * "src/main/java/sokoban/SokobanGameModel.ser" and try to
     * deserialize it and update our gameModel with the values from it.
     * @see sokoban.SokobanGameModel#getSave(SokobanGameModel savedModel)
     * @param gameModel This is the SokobanGameModel you want to serialize.
     */
    @Override
    public void executeMethod(SokobanGameModel gameModel) {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/java/sokoban/SokobanGameModel.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            SokobanGameModel loadedSokobanGameModel = (SokobanGameModel) in.readObject();
            in.close();
            fileIn.close();
            gameModel.getSave(loadedSokobanGameModel);
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("SokobanGameModel class not found");
            c.printStackTrace();
        }
    }
}
