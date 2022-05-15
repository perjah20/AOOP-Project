package sokoban.buttonStrategies;

import sokoban.SokobanGameModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadButton implements ButtonStrategy{
    @Override
    public void executeMethod(SokobanGameModel sokobanGameModel) {
        try {
            FileInputStream fileIn = new FileInputStream("src/main/java/sokoban/SokobanGameModel.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            SokobanGameModel loadedSokobanGameModel = (SokobanGameModel) in.readObject();
            in.close();
            fileIn.close();
            //printInfo(loadedSokobanGameModel);
            sokobanGameModel.updateGameGrid(loadedSokobanGameModel.getSave());
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("SokobanGameModel class not found");
            c.printStackTrace();
        }
    }

    private void printInfo(SokobanGameModel sokobanGameModel) {
        System.out.println(sokobanGameModel.getLastEvent());
        System.out.println(sokobanGameModel.getGameState());
        System.out.println(sokobanGameModel.getColumns());
        System.out.println(sokobanGameModel.getRows());
        int[][] save = sokobanGameModel.getSave();
        System.out.println(save.length);
        System.out.println(save[0].length);
        for (int[] row :save) {
            String string = "[";
            for (int column :row) {
                string += column+",";
            }
            System.out.println(string+"]");
        }
        System.out.println();
    }
}
