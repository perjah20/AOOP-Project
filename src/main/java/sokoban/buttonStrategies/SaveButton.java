package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveButton implements ButtonStrategy<SokobanGameModel>{

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
