package gamepackage;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class GamePackage implements Serializable {
    GamePackage opponent;
    BufferedImage image;
    String name;
    String message;
    int ID;
    int categoryID;
    String[] QA;
    boolean gameStart;
    int gameState = 0;
    boolean waiting;
    int[] answers;
    int totalScore;

    public GamePackage getOpponent() {
        return opponent;
    }

    public void setOpponent(GamePackage opponent) {
        this.opponent = opponent;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
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

    public String[] getQA() {
        return QA;
    }

    public void setQA(String[] QA) {
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
}
