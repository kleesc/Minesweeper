import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MinePane extends JPanel implements Serializable {
    private JButton grid[][];
    private MineSweeperGame currentGame;
    private int rows;
    private int columns;
    private boolean pressed[][];

    public MinePane(MineSweeperGame currentGame) {
        this.currentGame = currentGame;
        rows = currentGame.getRows();
        columns = currentGame.getColumns();
        pressed = new boolean[rows][columns];
        setLayout(new GridLayout(rows, columns));

        int mineField[][] = currentGame.getMineField();
        grid = new JButton[rows][columns];
        for(int i = 0; i < mineField.length; i++) {
            for(int j = 0; j < mineField[i].length; j++) {
                grid[i][j] = new JButton();
                grid[i][j].addActionListener(new ButtonClick(currentGame, i, j));
                add(grid[i][j]);
            }
        }
    }
    
    public class ButtonClick implements ActionListener {
    	private int i;
    	private int j;
    	
    	public ButtonClick(MineSweeperGame currentGame, int i, int j) {
    		this.i = i;
    		this.j = j;
    	}
    	
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            
            if(e.getSource() instanceof JButton) {
                JButton btn = (JButton) e.getSource();
                btn.setBackground(Color.RED);
                btn.setText(Integer.toString(currentGame.getSquare(i, j)));
                if(currentGame.getSquare(i, j) == 0 && pressed[i][j] == false) {
                	pressed[i][j] = true;
                	if(i + 1 < rows)
                		grid[i + 1][j].doClick();
                	if(j + 1 < columns)
                		grid[i][j + 1].doClick();
                	if(i - 1 >= 0)
                		grid[i - 1][j].doClick();
                	if(j - 1 >= 0)
                		grid[i][j - 1].doClick();
                	if(i + 1 < rows && j + 1 < columns)
                		grid[i + 1][j + 1].doClick();
                	if(i + 1 < rows && j - 1 >= 0)
                		grid[i + 1][j - 1].doClick();
                	if(i - 1 >= 0 && j + 1 < columns)
                		grid[i - 1][j + 1].doClick();
                	if(i - 1 >= 0 && j - 1 >= 0)
                		grid[i - 1][j - 1].doClick();
                }
                pressed[i][j] = true;
            }
        }
    }
}
