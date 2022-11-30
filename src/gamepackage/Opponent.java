package gamepackage;

import java.io.Serializable;
import java.util.Arrays;

public class Opponent implements Serializable {

    String name;
    int[] ScoreMap;
    int wins = 0;
    int totalScore;
    String imageID = "";

    public int getWins(){return wins;}
    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int[] getScoreMap() {
        return ScoreMap;
    }

    public String getImageID() {
        return imageID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScoreMap(int[] scoreMap) {
        ScoreMap = scoreMap;
    }

    public void setImage(String imageID) {
        this.imageID = imageID;
    }

    public void setAll(String name, int[] ScoreMap, String imageID, int totalScore, int wins) {
        this.name = name;
        this.ScoreMap = ScoreMap;
        this.imageID = imageID;
        this.totalScore = totalScore;
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "name='" + name + '\'' +
                ", ScoreMap=" + Arrays.toString(ScoreMap) +
                ", image=" + imageID +
                '}';
    }
}
