package be.kdg.spel.model;

/**
 * Created by Rune on 4/02/2015.
 */
public class Score {


    private int score;
    private int best;



    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void setBest(int best) {
        this.best = best;
    }

    public int getBest() {
        return best;
    }
}
