import java.util.*;
import java.io.*;

public class HighscoreList implements Serializable {
    private ArrayList<Highscore> scores;

    // Comparator cannot be serialized, hence transient
    transient private Comparator<Highscore> scoreComparator = new Comparator<Highscore>() {
        @Override
        public int compare(Highscore s1, Highscore s2) {
            return s2.getScore() - s1.getScore();
        }
    };
    
    public HighscoreList() {
        scores = new ArrayList<Highscore> (10);
    }

    public void add(String name, int score) {
        this.scores.add(new Highscore(name, score));
    }

    public void clear() {
        this.scores.clear();
    }

    public Highscore[] getTopScores() {
        Highscore topScores[] = scores.toArray(new Highscore[scores.size()]);
        Arrays.sort(topScores, scoreComparator);
        return topScores;
    }

    class Highscore implements Serializable {
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

    public void save() {
        try {
            ObjectOutputStream out = 
                new ObjectOutputStream(new FileOutputStream(new File("Highscores.data")));
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
