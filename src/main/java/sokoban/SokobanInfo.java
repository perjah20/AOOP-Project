package sokoban;

public class SokobanInfo {
    public static final int SAND = 1, PLAYER = 2, DOT = 5, BOX = 10, FILLEDBOX = 15, COBBLESTONE = 20;
    private static final int S = SAND, P = PLAYER, D = DOT, B = BOX, F = FILLEDBOX, C = COBBLESTONE, E = 0;

    public static final int[][]
            level1 = new int[][] {
                {E,E,C,C,C,C,C,E},
                {C,C,C,S,S,S,C,E},
                {C,D,P,B,S,S,C,E},
                {C,C,C,S,B,D,C,E},
                {C,D,C,C,B,S,C,E},
                {C,S,C,S,D,S,C,C},
                {C,B,S,F,B,B,D,C},
                {C,S,S,S,D,S,S,C},
                {C,C,C,C,C,C,C,C}
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

}
