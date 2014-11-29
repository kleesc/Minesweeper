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
        init();

        game = new MineSweeperGame();
        info = new InfoPane(game);
        grid = new MinePane(game);

        getContentPane().add(grid);
        getContentPane().add(info, BorderLayout.EAST);
    }

    public MineSweeper() {
        init();

        game = new MineSweeperGame();
        info = new InfoPane(game);
        grid = new MinePane(game);

        getContentPane().add(grid);
        getContentPane().add(info, BorderLayout.EAST);
    }

    public void init() {
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Menu */
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        /* New Game */
        JMenuItem itm = new JMenuItem("New Game");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int rows, columns, mines = 0;

                    JPanel textPane = new JPanel();
                    JTextField field1 = new JTextField(3);  
                    textPane.add(field1);
                    JTextField field2 = new JTextField(3);
                    textPane.add(field2);
                    JTextField field3 = new JTextField(3);
                    textPane.add(field3);

                    boolean valid = false;
                    do {
                        int ok = JOptionPane.showConfirmDialog(null, textPane, 
                                                               "New Game: Current game will be lost",
                                                               JOptionPane.OK_CANCEL_OPTION);
                        if(ok == JOptionPane.OK_OPTION) {
                            try {
                                rows = Integer.parseInt(field1.getText());
                                columns = Integer.parseInt(field2.getText());
                                mines = Integer.parseInt(field3.getText());
                                valid = true;

                                getContentPane().remove(grid);
                                getContentPane().invalidate();
                                getContentPane().add(grid = new MinePane(new MineSweeperGame(30,30)));
                                getContentPane().revalidate();
                                
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Enter 3 Integers");
                            }
                        } else {
                            break;
                        }
                    } while(!valid);
                }
            });
        menu.add(itm);

        /* Save */
        itm = new JMenuItem("Save");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
        menu.add(itm);

        /* Load */
        itm = new JMenuItem("Load");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                }
            });
        menu.add(itm);

        /* Exit */
        itm = new JMenuItem("Exit");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(JOptionPane.showConfirmDialog(null,
                                                     "Are you sure? Current game will be lost.", 
                                                     "Exit", JOptionPane.YES_NO_OPTION) == 0) {
                        System.exit(0);
                    }
                }
            });
        // Add ActionListener to item
        menu.add(itm);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public void run() {   
        setVisible(true);
    }
}
