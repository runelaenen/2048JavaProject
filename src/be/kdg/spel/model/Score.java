package be.kdg.spel.model;

/**
 * Created by Rune on 4/02/2015.
 */
public class Score {
    private int score;

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }
}
