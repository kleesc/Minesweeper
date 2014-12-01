import java.lang.Math;
import java.io.*;
import javax.swing.JOptionPane;

public class MineSweeperGame implements Serializable {
    private int mineField[][];
    private int lives;
    private int score;
    private int shields;

    private HighscoreList scores;

    public MineSweeperGame() {
        this.lives = 3;
        this.score = 0;
        this.shields = 0;
        this.mineField = MineFieldGenerator.generate(10, 10);
        this.scores = new HighscoreList();

        loadHighscores();
    }

    public MineSweeperGame(int rows, int columns) {
        this.lives = 3;
        this.score = 0;
        this.shields = 0;
        this.mineField = MineFieldGenerator.generate(rows, columns);
        this.scores = new HighscoreList();

        loadHighscores();
    }

    public MineSweeperGame(int rows, int columns, int maxMines) {
        this.lives = 3;
        this.score = 0;
        this.shields = 0;
        this.mineField = MineFieldGenerator.generate(rows, columns, maxMines);
        this.scores = new HighscoreList();

        loadHighscores();
    }

    public MineSweeperGame(MineSweeperGame game) {
        this.lives = game.getLives();
        this.score = game.getScore();
        this.shields = game.getShields();
        this.mineField = game.getMineField();
        this.scores = new HighscoreList();

        loadHighscores();
    }

    public int getScore() {
        return this.score;
    }

    public int setScore(int score) {
        this.score = score;
        return score;
    }
    
    public int getLives() {
        return this.lives;
    }

    public int setLives(int lives) {
        this.score = lives;
        return lives;
    }

    public int loseLife() {
        return --this.lives;
    }

    public int getShields() {
        return this.shields;
    }

    public int addShield() {
        this.shields += 3;
        return shields;
    }

    public int loseShield() {
        return --this.shields;
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

    public boolean gameOver() {
        boolean over = lives == 0;
        if (over) {
            String name = JOptionPane.showInputDialog("Enter your name: ");
            scores.add(name, score);
            scores.save();
        }
        return over;
    }
    
    public void loadHighscores() {
        // Load Manager from disk if it exists
        try {
            FileInputStream f = new FileInputStream("Highscores.data");
            ObjectInput input = new ObjectInputStream (f);
            this.scores = (HighscoreList)input.readObject();

            f.close();
            input.close();
        }
        catch(ClassNotFoundException e) {
            System.out.println("Class not found.");
            // e.printStackTrace();
        }
        catch(FileNotFoundException e) {
            System.out.println("No previous scores found.");
            // e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public HighscoreList.Highscore[] getTopScores(){
        return scores.getTopScores();
    }
}
