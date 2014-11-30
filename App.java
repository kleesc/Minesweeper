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

        HighscoreList hlist = new HighscoreList();
        hlist.add("qwe", 3435);
        hlist.add("kwf", 235);
        hlist.add("gh", 35);
        hlist.add("ven", 568);
        hlist.add("sdn", 10);
        hlist.add("hgjn", 71235);
        hlist.add("uie", 903435);
        hlist.add("ewwewf", 2235);
        hlist.add("gth", 357);
        hlist.add("vendr", 5568);
        hlist.add("j3n", 1670);
        hlist.add("ken", 1235);



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
