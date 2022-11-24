package gamepackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePackage implements Serializable {

    Opponent opponent = new Opponent();

    //BufferedImage image;
    int image;
    String name;
    String message;
    int ID;
    int categoryID;
    ArrayList<String[]> QA;
    //String[] QA;
    boolean gameStart;
    int gameState = 0;
    boolean waiting = false;
    boolean lastRound = false;
    int[] answers;
    int totalScore;

    public boolean choseCategory = false;

    public Opponent getOpponent() {
        return opponent;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isLastRound() {
        return lastRound;
    }

    public void setLastRound(boolean lastRound) {
        this.lastRound = lastRound;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public ArrayList<String[]> getQA() {
        return QA;
    }

    public void setQA(ArrayList<String[]> QA) {
        this.QA = QA;
    }

    public boolean isGameStart() {
        return gameStart;
    }

    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public int[] getAnswers() {
        return answers;
    }

    public void setAnswers(int[] answers) {
        this.answers = answers;
    }

    public void incrementScore(){
        totalScore++;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "GamePackage{" +
                "opponent=" + opponent +
                ", image=" + image +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", ID=" + ID +
                ", categoryID=" + categoryID +
                ", QA=" + QA +
                ", gameStart=" + gameStart +
                ", gameState=" + gameState +
                ", waiting=" + waiting +
                ", lastRound=" + lastRound +
                ", answers=" + Arrays.toString(answers) +
                ", totalScore=" + totalScore +
                ", choseCategory=" + choseCategory +
                '}';
    }
}