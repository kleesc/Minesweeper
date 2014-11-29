import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

/*
 * Main window
 */
public class MineSweeper extends JFrame {
    private MineSweeperGame game;
    private InfoPane info;
    private MinePane grid;

    public MineSweeper(int rows, int columns, int mines) {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Menu */
        JMenuBar menuBar = new JMenuBar();
        //JMenu 

        game = new MineSweeperGame();
        info = new InfoPane(game);
        grid = new MinePane(game);

        getContentPane().add(grid);
        getContentPane().add(info, BorderLayout.EAST);
    }

    public MineSweeper() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Menu */
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itm = new JMenuItem("Save");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    getContentPane().remove(grid);
                    getContentPane().invalidate();
                    getContentPane().add(grid = new MinePane(new MineSweeperGame(30,30)));
                    getContentPane().revalidate();
                }
            });
        menu.add(itm);
        itm = new JMenuItem("Load");
        // Add Action listener to item
        menu.add(itm);
        itm = new JMenuItem("Options");
        // Add ActionListener to item
        menu.add(itm);
        itm = new JMenuItem("Exit");
        itm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
        menu.add(itm);
        menuBar.add(menu);

        setJMenuBar(menuBar);
        

        game = new MineSweeperGame();
        info = new InfoPane(game);
        grid = new MinePane(game);

        getContentPane().add(grid);
        getContentPane().add(info, BorderLayout.EAST);
    }

    public void run() {   
        setVisible(true);
    }
}
