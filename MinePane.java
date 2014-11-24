import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

public class MinePane extends JPanel {
    private JButton grid[][];
    private MineSweeperGame currentGame;

    public MinePane(MineSweeperGame currentGame) {
        this.currentGame = currentGame;

        int rows = currentGame.getRows();
        int columns = currentGame.getColumns();
        
        setLayout(new GridLayout(rows, columns));

        int mineField[][] = currentGame.getMineField();
        grid = new JButton[rows][columns];
        for(int i = 0; i < mineField.length; i++) {
            for(int j = 0; j < mineField[i].length; j++) {
                grid[i][j] = new JButton();
                grid[i][j].addActionListener(new ButtonClick());
                add(grid[i][j]);
            }
        }
    }

    class ButtonClick implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("test");
            
            if(e.getSource() instanceof JButton) {
                JButton btn = (JButton) e.getSource();
                btn.setBackground(Color.RED);
            }
        }
    }
}
