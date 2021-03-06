import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.Math;
import java.util.Random;

public class MinePane extends JPanel implements Serializable {
    private MineSweeperGame currentGame;
    private InfoPane info;

    private CustomButton grid[][];
    private int rows, columns;


    public MinePane(MineSweeperGame currentGame, InfoPane info) {
        this.currentGame = currentGame;
        this.info = info;

        rows = currentGame.getRows();
        columns = currentGame.getColumns();
        
        setLayout(new GridLayout(rows, columns));

        int mineField[][] = currentGame.getMineField();
        grid = new CustomButton[rows][columns];

        Random rgen = new Random();

        for(int i = 0; i < mineField.length; i++) {
            for(int j = 0; j < mineField[i].length; j++) {
                grid[i][j] = new CustomButton(i, j, mineField[i][j]);
                grid[i][j].bonus = rgen.nextInt(Math.abs(5000 - 0 + 1)) + 1;

                grid[i][j].addActionListener(new ButtonClick());
                grid[i][j].setText("" +  grid[i][j].getValue());
                add(grid[i][j]);
            }
        }
    }
    
    public class ButtonClick implements ActionListener {
    	
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton) {
                CustomButton btn = (CustomButton) e.getSource();
                pressed(btn.getXPos(), btn.getYPos());
            }
        }
    }

    /*
     * |---|---|---|
     * |i-1|i-1|i-1|
     * |j-1| j |j+1|
     * |---|---|---|
     * | i | 0 | i |
     * |j-1| 0 |j+1|
     * |---|---|---|
     * |i+1|i+1|i+1|
     * |j-1| j |j+1|
     * |---|---|---|
     * 
     */
    private void pressed(int x, int y) {
        /* Need to check if revealed else the subroutine will recheck already checked zeros */
        if (outOfBound(x, y) || grid[x][y].revealed == true) {
            return;
        }

        /* Open surroundings on ZERO */
        if(grid[x][y].getValue() == 0 ) {
            grid[x][y].setText("" + grid[x][y].getValue());
            grid[x][y].setBackground(Color.GREEN); // Test
            grid[x][y].revealed = true;

            /* Score */
            info.updateScore(currentGame.setScore(currentGame.getScore() + 1));
            
            /* Top */
            pressed(x - 1, y);
            /* Top-Right */
            pressed(x - 1, y + 1);
            /* Right */
            pressed(x, y + 1);
            /* Bottom-right */
            pressed(x + 1, y + 1);
            /* Bottom */
            pressed(x + 1, y);
            /* Bottom-left */
            pressed(x + 1, y - 1);
            /* Left */
            pressed(x, y - 1);
            /* Top-left */
            pressed(x - 1, y - 1);

        } else if(grid[x][y].getValue() < 0) { // If bomb is clicked
            grid[x][y].setText("*");
            grid[x][y].setBackground(Color.RED);
            grid[x][y].revealed = true;

            /* Score */
            info.updateScore(currentGame.setScore(currentGame.getScore() - 1));

            /* Lives */
            if(currentGame.getShields() > 0) {
                info.updateShields(currentGame.loseShield());
            } else {
                info.updateLives(currentGame.loseLife());
                if(currentGame.gameOver()) { // Game Over
                    revealAll();
                }
            }

        } else { // Positive non zero click
            grid[x][y].setText("" + grid[x][y].getValue());
            grid[x][y].setBackground(Color.GREEN);
            grid[x][y].revealed = true;
            System.out.println(grid[x][y].bonus);

            if(grid[x][y].bonus == 5000) {
                info.updateLives(currentGame.setLives(5000)); // Pseudo Immortality
            }

            if(grid[x][y].bonus % 3 == 0 && grid[x][y].bonus % 9 == 0 && grid[x][y].bonus % 27 == 0) {
                info.updateShields(currentGame.addShield()); // Pseudo Add 3 shields
            }
         
            /* Score */
            info.updateScore(currentGame.setScore(currentGame.getScore() + 1));
        }
    }

    private boolean outOfBound(int x, int y) {
        return x < 0 || x > rows - 1 || y < 0 || y > columns - 1;
    }

    private void revealAll() {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j].setText("" + grid[i][j].getValue());
                grid[i][j].revealed = true;
                if(grid[i][j].getValue() < 0) {
                    grid[i][j].setBackground(Color.RED);
                } else {
                    grid[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }

    private class CustomButton extends JButton {
        /* 
         * Do NOT name getters getX and getY because it overrides the parent's Component members
         * Causing the button to get stacked on the panel
         * Wasted too much time before realizing... 
         */
        private int x, y, value;
        boolean revealed = false;
        int bonus = 0;

        public CustomButton(int x, int y, int value) {
            super();
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int getXPos() {
            return this.x;
        }

        public int getYPos() {
            return this.y;
        }

        public int getValue() {
            return this.value;
        }
    }
}
