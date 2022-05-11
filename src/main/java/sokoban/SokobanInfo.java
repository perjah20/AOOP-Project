package sokoban;

public class SokobanInfo {
    public static final int[][]
            level1 = new int[][] {
                    {0,0,6,6,6,6,6,0},
                    {6,6,6,1,1,1,6,0},
                    {6,2,5,3,1,1,6,0},
                    {6,6,6,1,3,2,6,0},
                    {6,2,6,6,3,1,6,0},
                    {6,1,6,1,2,1,6,6},
                    {6,3,1,4,3,3,2,6},
                    {6,1,1,1,2,1,1,6},
                    {6,6,6,6,6,6,6,6}
            },
            level2 = new int[][] {
                    {0,0,6,6,6,6,6,0},
                    {6,6,6,1,1,1,6,0},
                    {6,2,5,3,1,1,6,0},
                    {6,6,6,1,3,2,6,0},
                    {6,2,6,6,3,1,6,0},
                    {6,1,6,1,2,1,6,6},
                    {6,3,1,4,3,3,2,6},
                    {6,1,1,1,2,1,1,6},
                    {6,6,6,6,6,6,6,6}
            };


    public int[][] getLevel(int level){
        switch (level) {
            case 2: return level2;
            default: return level1;
        }
    }

    public static final int SAND = 1, PLAYER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;
}
