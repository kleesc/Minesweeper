import javax.swing.*;
import java.io.*;

public class InfoPane extends JPanel implements Serializable {
    private MineSweeperGame currentGame;
    private JLabel lives;
    private JLabel size;
    private JLabel mines;
    private JLabel score;

    public InfoPane(MineSweeperGame currentGame) {
        this.currentGame = currentGame;
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        lives = new JLabel("Lives: " + currentGame.getLives());
        mines = new JLabel("Mines: " + currentGame.getMines());
        score = new JLabel("Score: " + currentGame.getScore());
        size = new JLabel("Size: " + 
                          currentGame.getRows() + " x " +
                          currentGame.getColumns());
        
        add(lives);
        add(score);
        add(size);
        add(mines);
    }
    
    public void updateLives(int n) {
        this.lives.setText("Lives: " + n);
    }

    public void updateSize(int rows, int columns) {
        this.size.setText("Size: " + rows + " x " + columns);
    }

    public void updateMines(int mines) {
        this.mines.setText("Mines: " + mines);
    }

    public void updateScore(int score) {
        this.score.setText("Score: " + score);
    }
}
