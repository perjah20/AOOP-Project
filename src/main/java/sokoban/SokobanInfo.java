package sokoban;

/**
 * This class holds necessary information used in multiple classes.
 */
public class SokobanInfo {
    public enum Directions {NORTH, WEST ,EAST, SOUTH}
    public enum Events {START_GAME, RESET_GAME, MOVED_CRATE, FILLED_CRATE, MOVED_PLAYER, TRIED_TO_MOVE, GAME_WON}
    public static final int SAND = 1, PLAYER = 2, DOT = 3, CRATE = 4, FILLEDBOX = 5, COBBLESTONE = 6;
    private static final int S = SAND, P = PLAYER, D = DOT, B = CRATE, F = FILLEDBOX, C = COBBLESTONE, E = -1;

    private static final int[][]
            level0 = new int[][] {
                {C,C,C,C,C,C},
                {C,S,S,S,S,C},
                {C,S,P,B,S,C},
                {C,S,S,D,S,C},
                {C,S,S,S,S,C},
                {C,C,C,C,C,C},
            },
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
            },
            gameOver = new int[][] {
                            {C,C,C,C,C,C,C,C,C},
                            {C,C,C,C,C,C,C,C,C},
                            {C,C,D,C,C,C,D,C,C},
                            {C,C,C,C,C,C,C,C,C},
                            {C,F,C,C,P,C,C,F,C},
                            {C,C,F,C,C,C,F,C,C},
                            {C,C,C,F,F,F,C,C,C},
                            {C,C,C,C,C,C,C,C,C},
                            {C,C,C,C,C,C,C,C,C}
            };

    public static final int[][][] sokobanLevels = {
            new int[][] {
                    {C,C,C,C,C,C},
                    {C,S,S,S,S,C},
                    {C,S,P,B,S,C},
                    {C,S,S,D,S,C},
                    {C,S,S,S,S,C},
                    {C,C,C,C,C,C},
            },
            new int[][] {
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
            new int[][] {
                    {C,C,C,C,C,C,C,C,C},
                    {C,S,S,S,S,C,S,D,C},
                    {C,C,C,S,S,B,S,S,C},
                    {C,S,P,B,S,C,B,S,C},
                    {C,S,S,D,S,B,S,S,C},
                    {C,C,C,C,S,C,S,S,C},
                    {C,S,S,S,D,C,S,S,C},
                    {C,S,B,S,D,C,D,S,C},
                    {C,C,C,C,C,C,C,C,C}
            },
            new int[][] {
                    {C,C,C,C,C,C,C,C,C},
                    {C,C,C,C,C,C,C,C,C},
                    {C,C,D,C,C,C,D,C,C},
                    {C,C,C,C,C,C,C,C,C},
                    {C,F,C,C,P,C,C,F,C},
                    {C,C,F,C,C,C,F,C,C},
                    {C,C,C,F,F,F,C,C,C},
                    {C,C,C,C,C,C,C,C,C},
                    {C,C,C,C,C,C,C,C,C}
            }
    };



    public static int[][] getLevel(int level){
        int [][] levelCopy;
        switch (level) {
            case 1:
                levelCopy = new int[level1.length][];
                for(int i = 0; i < level1.length; i++)
                    levelCopy[i] = level1[i].clone();
                return levelCopy;
            case 2:
                levelCopy = new int[level2.length][];
                for(int i = 0; i < level2.length; i++)
                    levelCopy[i] = level2[i].clone();
                return levelCopy;
            case 3:
                levelCopy = new int[gameOver.length][];
                for(int i = 0; i < gameOver.length; i++)
                    levelCopy[i] = gameOver[i].clone();
                return levelCopy;
            default:
                levelCopy = new int[level0.length][];
                for(int i = 0; i < level0.length; i++)
                    levelCopy[i] = level0[i].clone();
                return levelCopy;
        }
    }
}
