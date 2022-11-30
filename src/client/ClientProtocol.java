package client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientProtocol {

    Client client;

    int questionCount = 0;

    boolean opponentIsNotSet = true;
    public boolean categoryTimeOut = false;
    public boolean answerTimeOut = false;

    private static final int SET_CATEGORY_STATE = 1;
    private static final int GET_CATEGORY_STATE = 2;
    private static final int WAIT_STATE = 3;
    private static final int RESULT_STATE = 4;
    private static final int ROUND_STATE = 5;
    private static final int AUTO_WIN_STATE = 6;
    private static final int GIVE_UP_STATE = 7;

    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update() {

        if(client.gp.getOpponent().getName() != null && opponentIsNotSet){
            client.gf.setUpOpponentInfo(client.gp.getOpponent().getName(), client.gp.getOpponent().getImageID(), client.gp.getOpponent().getWins());
            opponentIsNotSet = false;
        }

        switch (client.gp.getGameState()){
            case SET_CATEGORY_STATE ->{
                getRandomCategory();
                client.gf.GUIState(6);
                client.gf.toggleTimer();
                categoryTimeOut = true;
            } case WAIT_STATE ->{
                client.gp.setGameState(GET_CATEGORY_STATE);
                client.sendAndReceive();
            } case ROUND_STATE ->{
                client.gp.setCategoryID(0);
                nextQuestion();
                answerTimeOut = true;
            } case RESULT_STATE ->{
                setScore(client.gp.getAnswersMap(), client.gp.getOpponent().getScoreMap());
                client.gf.lbScore.setText("Score: " + client.gp.getTotalScore());
                client.gf.GUIState(7);
                if(client.gp.getIWon() == 1){
                    client.gf.lbInfoMessage.setText("WON!");
                } else if (client.gp.getIWon() == 3){
                    client.gf.lbInfoMessage.setText("LOST");
                } else {
                    client.gf.lbInfoMessage.setText("DRAW");
                }
                if(client.gp.getIWon() == 1 && client.isUser){
                    client.user.setWins(1);
                    client.connectToLoginServer(client.user);
                }
                resetGamePackage();
            } case AUTO_WIN_STATE -> {
                client.gf.lbWaitMessage.setText("Opponent gave up");
                client.gf.btGiveUp.setVisible(false);
                client.gf.btBack.setVisible(true);
                showScore();
                if(client.isUser){
                    client.user.setWins(1);
                    client.connectToLoginServer(client.user);
                }
                resetGamePackage();
            } case GIVE_UP_STATE -> {
                client.gf.GUIState(3);
                resetGamePackage();
            }
        }
    }

    private void getRandomCategory() {
        List<String> categoryList = new ArrayList<>(List.of("0", "1", "2", "3", "4", "5"));
        Collections.shuffle(categoryList);
        client.gf.setCategories(categoryList.get(0), categoryList.get(1), categoryList.get(2));
    }

    public void nextQuestion(){
        if(questionCount == client.gp.getQA().size() && client.gp.lastRound){
            client.gp.setGameState(RESULT_STATE);
            //client.gp.setWaiting(false);
            showScore();
            client.sendAndReceive();
        } else if(questionCount == client.gp.getQA().size()){
            questionCount = 0;
            nextCategory();
        } else {
            client.currentQuestion = client.gp.getQA().get(questionCount);
            client.gf.setUpQA(client.currentQuestion);
            client.gf.GUIState(4);
            client.gf.toggleTimer();
            answerTimeOut = true;
            questionCount++;
        }
    }
    private void nextCategory() {
        if (client.gp.choseCategory){
            client.gp.setGameState(SET_CATEGORY_STATE);
            //client.gp.setWaiting(false);
            getRandomCategory();
            showScore();
            client.gf.paintAndSleep(4000);

            client.gf.GUIState(6);
            client.gf.toggleTimer();
            categoryTimeOut = true;
        } else {
            client.gp.setGameState(GET_CATEGORY_STATE);
            showScore();
            client.sendAndReceive();
        }
    }
    public void resetGamePackage(){
        client.gf.chatScreen.chatNow = false;
        client.rounds = 0;
        questionCount = 0;
        opponentIsNotSet = true;
        categoryTimeOut = false;
        answerTimeOut = false;
        client.gp = null;
        client.gf.tpChat.selectAll();
        client.gf.tpChat.replaceSelection("");
        client.setUpGP();
    }

    public void timedOut() {

        if (categoryTimeOut) {
            client.gp.setCategoryID(setRandomCategory());
            System.out.println(client.gp.getCategoryID());
            categoryTimeOut = false;
            client.sendAndReceive();
        } else if (answerTimeOut) {
            client.gp.getAnswersMap()[client.rounds] = 2;
            client.rounds++;
            answerTimeOut = false;
            client.protocol.nextQuestion();
        }
    }
    public void setScore(int[] answersMap, int[] scoreMap) {
        int countRounds = 0;
        int scoreID = 0;
        int opponentScoreID = 0;
        for (int i = 0; i < answersMap.length; i++) {
            if(answersMap[i] != 0){
                scoreID = scoreID * 10 + answersMap[i];
                opponentScoreID = opponentScoreID * 10 + scoreMap[i];

                if(i == 2 || i == 5 || i == 8 || i == 11 || i == 14 || i == 17){
                    countRounds++;
                    client.gf.setScoreIcon(countRounds, scoreID, opponentScoreID);

                    opponentScoreID = 0;
                    scoreID = 0;
                }
            }
        }
    }
    private int setRandomCategory(){
        List<String> random = new ArrayList<>(List.of(client.gf.btCategory1.getName(), client.gf.btCategory2.getName(), client.gf.btCategory3.getName()));
        Collections.shuffle(random);
        return Integer.parseInt(random.get(1));
    }
    private void showScore(){
        setScore(client.gp.getAnswersMap(), client.gp.getOpponent().getScoreMap());
        client.gf.lbScore.setText("Score: " + client.gp.getTotalScore());
        client.gf.GUIState(7);
    }
}