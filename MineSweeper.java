import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;

/*
 * Main window
 */
public class MineSweeper extends JFrame implements Serializable {
    private MineSweeperGame game;
    private InfoPane info;
    private MinePane grid;
    
    int rowsOp = 10, columnsOp = 10, minesOp = 100; // Options

    public MineSweeper(int rows, int columns, int mines) {
        init();

        game = new MineSweeperGame(rows, columns, mines);
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
                    if(JOptionPane.showConfirmDialog(null,
                                                     "New Game: Current game will be lost",
                                                     "New Game", JOptionPane.YES_NO_OPTION) == 0) {
                        clear();
                        update(new MineSweeper(rowsOp, columnsOp, minesOp));
                        getContentPane().add(MineSweeper.this.grid);
                        getContentPane().add(MineSweeper.this.info, BorderLayout.EAST);
                        getContentPane().revalidate();
                    } else {
                        
                    }
                }
            });
        menu.add(itm);

        /* Options */
        itm = new JMenuItem("Options");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
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
                                                               "Options",
                                                               JOptionPane.OK_CANCEL_OPTION);
                        if(ok == JOptionPane.OK_OPTION) {
                            try {
                                rowsOp = Integer.parseInt(field1.getText());
                                columnsOp = Integer.parseInt(field2.getText());
                                minesOp = Integer.parseInt(field3.getText());
                                valid = true;
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Enter 3 Integers: Rows, Columns, MaxMines");
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
                    MineSweeper.save(MineSweeper.this);
                }
            });
        menu.add(itm);

        /* Load */
        itm = new JMenuItem("Load");
        itm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(MineSweeper.load() == null) {
                        JOptionPane.showMessageDialog(null, "Failed to load game");
                    } else {
                        clear();
                        update(MineSweeper.load());
                        getContentPane().add(grid);
                        getContentPane().add(info, BorderLayout.EAST);
                        getContentPane().revalidate();
                    }
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
        menu.add(itm);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public static void save(MineSweeper currentGame) {
        String path = JOptionPane.showInputDialog("Enter file name: ");
                
        if(path != null) {
            try {
                ObjectOutputStream out = 
                    new ObjectOutputStream(new FileOutputStream(new File(path)));
                out.writeObject(currentGame);
            }
            catch(FileNotFoundException e) {
                System.out.println("No previous state found, will create new file on save.");
                // e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static MineSweeper load() {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {  
            String path = chooser.getSelectedFile().getName();
            try(
                FileInputStream f = new FileInputStream(path);
                ObjectInput input = new ObjectInputStream (f);
                ) {
                    return (MineSweeper)input.readObject();
                }
            catch(ClassNotFoundException e) {
                System.out.println("Class not found.");
                // e.printStackTrace();
            }
            catch(FileNotFoundException e) {
                System.out.println("No previous state found, will create new file on save.");
                // e.printStackTrace();
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void clear() {
        getContentPane().remove(grid);
        getContentPane().remove(info);
        getContentPane().invalidate();
    }

    public void run() {   
        setVisible(true);
    }

    public void update(MineSweeper newGame) {
        this.game = newGame.getMineSweeperGame();
        this.grid = newGame.getMinePane();
        this.info = newGame.getInfoPane();
    }

    public MineSweeperGame getMineSweeperGame() {
        return game;
    }

    public MinePane getMinePane() {
        return grid;
    }

    public InfoPane getInfoPane() {
        return info;
    }
}
