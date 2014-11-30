import java.lang.Math;
import java.io.*;

public class MineSweeperGame implements Serializable {
    private int mineField[][];
    private int lives;
    private int score;

    public MineSweeperGame() {
        this.lives = 3;
        this.score = 0;
        this.mineField = MineFieldGenerator.generate(10, 10);
    }

    public MineSweeperGame(int rows, int columns) {
        this.lives = 3;
        this.score = 0;
        this.mineField = MineFieldGenerator.generate(rows, columns);
    }

    public MineSweeperGame(int rows, int columns, int maxMines) {
        this.lives = 3;
        this.score = 0;
        this.mineField = MineFieldGenerator.generate(rows, columns, maxMines);
    }

    public MineSweeperGame(MineSweeperGame game) {
        this.lives = game.getLives();
        this.score = game.getScore();
        this.mineField = game.getMineField();
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getLives() {
        return this.lives;
    }

    public void setLives(int lives) {
        this.score = lives;
    }

    public int[][] getMineField() {
        return mineField;
    }
    
    public int getSquare(int i, int j) {
    	return mineField[i][j];
    }

    public int getRows() {
        return mineField.length;
    }

    public int getColumns() {
        return mineField[0].length;
    }

    public int getMines() {
        int mines = 0;
        for(int i = 0; i < mineField.length; i++) {
            for(int j = 0; j < mineField[i].length; j++) {
                if(mineField[i][j] < 0) {
                    mines += mineField[i][j];
                }
            }
        }
        return Math.abs(mines);
    }

    public int loseLife() {
        return --this.lives;
    }

    public int addShield() {
        this.lives += 3;
        return lives;
    }

    
}
