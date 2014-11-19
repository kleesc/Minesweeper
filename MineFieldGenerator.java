import java.lang.Math;
import java.util.Random;

public class MineFieldGenerator {
    private static Random rgen = new Random();

    /**
     * Generates a minefield of size m x n
     * Negative numbers represent mines
     * Maximum number of mines is (m-1)(n-1) (rule from wikipedia)
     */
    public static int[][] generate(int m, int n) {
        int field[][] = new int[m][n];

        /* 
         * Minimum number of 30% of field size as mines 
         * Maximum of (m - 1) * (n - 1)
         */
        int minMines = (int)Math.round(0.15 * m * n);
        int maxMines = (m - 1) * (n - 1);
        int numMines = rgen.nextInt(maxMines - minMines + 1 ) + minMines;

        while(numMines != 0) {
            field[rgen.nextInt(m)][rgen.nextInt(n)]--;
            numMines--;
        }
        
        fill(field);
        return field;
    }

    /**
     * Generates a minefield of size m x n
     * Negative numbers represent mines
     * No maximum number of mines
     */
    public static int[][] generate(int m, int n, int maxMines) {
        int field[][] = new int[m][n];

        /* 
         * Minimum number of 30% of field size as mines 
         */
        int minMines = (int)Math.round(0.15 * m * n);
        int numMines = rgen.nextInt(maxMines - minMines + 1 ) + minMines;

        while(numMines != 0) {
            field[rgen.nextInt(m)][rgen.nextInt(n)]--;
            numMines--;
        }
        
        fill(field);
        return field;
    }

    private static void fill(int field[][]) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j] != 0) {
                    continue;
                } 
                if(i == j && i == 0) { // Top-left corner
                    field[i][j] = (((field[i+1][j] < 0) ? 
                                    Math.abs(field[i+1][j]) : 0) +
                                   ((field[i+i][j+1] < 0) ?
                                    Math.abs(field[i+1][j+1]) : 0) + 
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0));
                } else if(i == j && i == field.length -1) { // Bottom-right
                    field[i][j] = (((field[i-1][j] < 0) ? 
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j-1] < 0) ?
                                    Math.abs(field[i-1][j-1]) : 0) + 
                                   ((field[i][j-1] < 0) ?
                                    Math.abs(field[i][j-1]) : 0));
                } else if(j == field[i].length - 1 && i == 0) { // Top-right corner
                    field[i][j] = (((field[i][j-1] < 0) ? 
                                    Math.abs(field[i][j-1]) : 0) +
                                   ((field[i+1][j-1] < 0) ?
                                    Math.abs(field[i+1][j-1]) : 0) + 
                                   ((field[i+1][j] < 0) ?
                                    Math.abs(field[i+1][j]) : 0));
                } else if(i == field.length - 1 && j == 0) { // Bottom-left corner
                    field[i][j] = (((field[i-1][j] < 0) ? 
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j+1] < 0) ?
                                    Math.abs(field[i-1][j+1]) : 0) + 
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0));
                } else if(j == 0) { // Left edge
                    field[i][j] = (((field[i-1][j] < 0) ? 
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j+1] < 0) ?
                                    Math.abs(field[i-1][j+1]) : 0) + 
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0) +
                                   ((field[i+1][j+1] < 0) ?
                                    Math.abs(field[i+1][j+1]) : 0) +
                                   ((field[i+1][j] < 0) ?
                                    Math.abs(field[i+1][j]) : 0));
                } else if(j == field[i].length) { // Right edge
                    field[i][j] = (((field[i-1][j] < 0) ? 
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j-1] < 0) ?
                                    Math.abs(field[i-1][j-1]) : 0) + 
                                   ((field[i][j-1] < 0) ?
                                    Math.abs(field[i][j-1]) : 0) +
                                   ((field[i+1][j-1] < 0) ?
                                    Math.abs(field[i+1][j]) : 0) +
                                   ((field[i+1][j] < 0) ?
                                    Math.abs(field[i+1][j]) : 0));
                } else if(i == 0) { // Top edge
                    field[i][j] = (((field[i][j-1] < 0) ? 
                                    Math.abs(field[i][j-1]) : 0) +
                                   ((field[i+1][j-1] < 0) ?
                                    Math.abs(field[i+1][j-1]) : 0) + 
                                   ((field[i+1][j] < 0) ?
                                    Math.abs(field[i+1][j]) : 0) +
                                   ((field[i+1][j+1] < 0) ?
                                    Math.abs(field[i+1][j+1]) : 0) +
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0));
                } else if(i == field.length) { // Bottom edge
                    field[i][j] = (((field[i][j-1] < 0) ? 
                                    Math.abs(field[i][j-1]) : 0) +
                                   ((field[i-1][j-1] < 0) ?
                                    Math.abs(field[i-1][j-1]) : 0) + 
                                   ((field[i-1][j] < 0) ?
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j+1] < 0) ?
                                    Math.abs(field[i-1][j+1]) : 0) +
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0));
                } else if(i > 0 && j > 0 && 
                          i < field.length - 1 && j < field.length - 1) {
                    field[i][j] = (((field[i][j-1] < 0) ? 
                                    Math.abs(field[i][j-1]) : 0) +
                                   ((field[i-1][j-1] < 0) ?
                                    Math.abs(field[i-1][j-1]) : 0) + 
                                   ((field[i-1][j] < 0) ?
                                    Math.abs(field[i-1][j]) : 0) +
                                   ((field[i-1][j+1] < 0) ?
                                    Math.abs(field[i-1][j+1]) : 0) +
                                   ((field[i][j+1] < 0) ?
                                    Math.abs(field[i][j+1]) : 0) + 
                                   ((field[i+1][j+1] < 0) ?
                                    Math.abs(field[i+1][j+1]) : 0) +
                                   ((field[i+1][j] < 0) ?
                                    Math.abs(field[i+1][j]) : 0) + 
                                   ((field[i+1][j-1] < 0) ?
                                    Math.abs(field[i+1][j-1]) : 0));
                }
            }
        }
    }

    /* For testing purposes */
    public static void main(String[] args) {
        int f[][] = generate(4,4);
        for(int i = 0; i < f.length; i++) {
            for(int j = 0; j < f[i].length; j++) {
                System.out.print(f[i][j] + " |  ");
            }
            System.out.println();
        }
    }
}
