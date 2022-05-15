package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveButton implements ButtonStrategy{

    @Override
    public void executeMethod(SokobanGameModel sokobanGameModel) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("src/main/java/sokoban/SokobanGameModel.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            sokobanGameModel.saveGame();
            out.writeObject(sokobanGameModel);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
