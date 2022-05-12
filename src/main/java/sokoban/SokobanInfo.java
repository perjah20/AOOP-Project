package sokoban;

public class SokobanInfo {
    public static final int SAND = 1, PLAYER = 2, DOT = 3, BOX = 4, FILLEDBOX = 5, COBBLESTONE = 6;
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
                    {C,C,C,C,C,C,C,C,C},
                    {C,S,S,S,S,C,S,D,C},
                    {C,C,C,S,S,B,S,S,C},
                    {C,S,P,B,S,C,B,S,C},
                    {C,S,S,D,S,B,S,S,C},
                    {C,C,C,C,S,C,S,S,C},
                    {C,S,S,S,D,C,S,S,C},
                    {C,S,B,S,D,C,D,S,C},
                    {C,C,C,C,C,C,C,C,C}
            };


    public int[][] getLevel(int level){
        switch (level) {
            case 2: return level2;
            default: return level1;
        }
    }
}
