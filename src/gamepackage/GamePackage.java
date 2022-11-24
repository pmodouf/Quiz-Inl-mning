package gamepackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePackage implements Serializable {

    Opponent opponent = new Opponent();

    int image;
    int ID;
    String name;
    String message;

    int categoryID;

    ArrayList<String[]> QA;
    int[] AnswersMap;
    int gameState = 0;

    int roundScore;
    int totalScore;

    public boolean choseCategory = false;
    public boolean gameStart;
    public boolean waiting = false;
    public  boolean lastRound = false;

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

    public int getRoundScore() {
        return roundScore;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
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

    public int[] getAnswersMap() {
        return AnswersMap;
    }

    public void setAnswersMap(int[] answers) {
        this.AnswersMap = answers;
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
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", categoryID=" + categoryID +
                ", QA=" + QA +
                ", answers=" + Arrays.toString(AnswersMap) +
                ", gameState=" + gameState +
                ", roundScore=" + roundScore +
                ", totalScore=" + totalScore +
                ", choseCategory=" + choseCategory +
                ", gameStart=" + gameStart +
                ", waiting=" + waiting +
                ", lastRound=" + lastRound +
                '}';
    }
}