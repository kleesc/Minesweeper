import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MinePane extends JPanel implements Serializable {
    private CustomButton grid[][];
    private MineSweeperGame currentGame;

    public MinePane(MineSweeperGame currentGame) {
        int rows = currentGame.getRows(), columns = currentGame.getColumns();
        
        setLayout(new GridLayout(rows, columns));
        this.currentGame = currentGame;

        int mineField[][] = currentGame.getMineField();
        grid = new CustomButton[rows][columns];

        for(int i = 0; i < mineField.length; i++) {
            for(int j = 0; j < mineField[i].length; j++) {
                grid[i][j] = new CustomButton(i, j, mineField[i][j]);
                grid[i][j].addActionListener(new ButtonClick());
                add(grid[i][j]);
            }
        }
    }
    
    public class ButtonClick implements ActionListener {
    	
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            
            if(e.getSource() instanceof JButton) {
                JButton btn = (JButton) e.getSource();
                btn.setBackground(Color.GREEN);
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
