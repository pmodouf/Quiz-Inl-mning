package gamepackage;

import properties.GameProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePackage implements Serializable {

    Opponent opponent = new Opponent();

    String imageID;
    int ID;
    String name;
    String message;
    int wins;
    int categoryID = 0;

    ArrayList<String[]> QA;
    int[] AnswersMap;
    int gameState = 0;
    int totalScore;
    int iWon = 0;

    public boolean choseCategory = false;
    public boolean waiting = false;
    public  boolean lastRound = false;

    public int getIWon() {
        return iWon;
    }

    public void setIWon(int iWon) {
        this.iWon = iWon;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setOpponent(Opponent opponent) {
        this.opponent = opponent;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
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

        return "{" +
                ", ID=" + ID +
                ", categoryID=" + categoryID +
                ", QA=" + QA +
                ", gameState=" + gameState +
                ", choseCategory=" + choseCategory +
                ", waiting=" + waiting +
                '}';
//        return "GamePackage{" +
//                "opponent=" + opponent +
//                ", image=" + imageID +
//                ", ID=" + ID +
//                ", name='" + name + '\'' +
//                ", message='" + message + '\'' +
//                ", categoryID=" + categoryID +
//                ", QA=" + QA +
//                ", answers=" + Arrays.toString(AnswersMap) +
//                ", gameState=" + gameState +
//                ", totalScore=" + totalScore +
//                ", choseCategory=" + choseCategory +
//                ", waiting=" + waiting +
//                ", lastRound=" + lastRound +
//                '}';
    }
}