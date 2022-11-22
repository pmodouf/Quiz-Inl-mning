package gamepackage;

import java.io.Serializable;
import java.util.Arrays;

public class Opponent implements Serializable {

    String name;
    int[] ScoreMap;
    int totalScore;
    int image;

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

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScoreMap(int[] scoreMap) {
        ScoreMap = scoreMap;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setAll(String name, int[] ScoreMap, int image, int totalScore) {
        this.name = name;
        this.ScoreMap = ScoreMap;
        this.image = image;
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "name='" + name + '\'' +
                ", ScoreMap=" + Arrays.toString(ScoreMap) +
                ", image=" + image +
                '}';
    }
}
