import java.util.*;
import java.io.*;

public class HighscoreList implements Serializable {
    List<Highscore> list;

    public HighscoreList() {
        list = new ArrayList<Highscore>();
    }

    class Highscore {
        private String name;
        private int score;
        
        public Highscore(String name, int score) {
            this.name = name;
            this.score = score;
        }
        
        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
        
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
