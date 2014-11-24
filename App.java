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


        MineSweeperGame game = new MineSweeperGame(10, 10);

        for(int i = 0; i < game.getMineField().length; i++) {
            for(int j = 0; j < game.getMineField()[i].length; j++) {
                System.out.print(game.getMineField()[i][j] + " ");
            }
            System.out.println();
        }




        
        if(args.length == 0) {
            
        } else if(args.length == 2) {

        } else if(args.length == 3) {

        } else {
            System.out.println("java App [ROWS] [COLUMNS] [MAXMINES]");
        }

        MineSweeper ms = new MineSweeper();
        ms.run();

    }
}
