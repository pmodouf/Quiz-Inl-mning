package gamepackage;

import java.io.Serializable;

public class Opponent implements Serializable {

    String name;
    int totalScore;
    int ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Opponent{" +
                "name='" + name + '\'' +
                ", totalScore=" + totalScore +
                ", ID=" + ID +
                '}';
    }
}
