/**
 * App --- An upgraded version of the original Minesweeper game in which there can be more than one bomb 
 * on the same square in the field and the possibility of the player to get a reward by finding a tresure while clearing the field.
 *@author Kenny Lee Sin Cheong
 *@author Mathieu Wrzesien
 *@author Leo Tan Trung Nguyen
 *
 */
import java.util.*;

public class App {
    public static void main(String[] args) {
        /* Main */
        MineSweeper ms;
        
        if(args.length == 0) {
            ms = new MineSweeper();
            ms.run();
        } else if(args.length == 2) {
            try {
                ms = new MineSweeper(Integer.parseInt(args[0]), 
                                     Integer.parseInt(args[1]));
                ms.run();
            } catch(NumberFormatException e) {
                System.out.println("java App [ROWS] [COLUMNS] [MAXMINES]");
            }
        } else if(args.length == 3) {
            try {
                ms = new MineSweeper(Integer.parseInt(args[0]), 
                                     Integer.parseInt(args[1]), 
                                     Integer.parseInt(args[2]));
                ms.run();
            } catch(NumberFormatException e) {
                System.out.println("java App [ROWS] [COLUMNS] [MAXMINES]");
            }
        } else {
            System.out.println("java App [ROWS] [COLUMNS] [MAXMINES]");
        }       
        
    }
}
