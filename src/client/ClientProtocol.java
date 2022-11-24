package client;

import database.QA;
import properties.GameProperties;
import utility.StaticImageHandler;

public class ClientProtocol {

    private static final int FIRST_INIT = 0;
    private static final int GAME_ACTIVE = 1;
    private static final int END_GAME = 2;
    private static final int REPEAT_REQUEST = 3;
    private static final int CHOOSE_CATEGORY = 4;

    //TEMP För att hålla koll på gameFrame scenes
    final int loginScreenState = 1;
    final int createAccountScreenState = 2;
    final int homeScreenState = 3;
    final int gameScreenState = 4;
    final int waitScreenState = 5;
    final int categoryScreenState = 6;
    final int scoreScreenState = 7;
    final int optionScreenState = 8;
    final int leaderboardState = 9;

    Client client;

    String[] Bilderna = {"boy1","girl1","man1","old1","old2","women1"};

    GameProperties properties = new GameProperties();

    //TEMP
    QA qa = new QA();

    int counter = 0;


    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update() {
        /*
        switch(client.gp.getGameState()){
            case FIRST_INIT -> {
                client.gp.setAnswersMap(new int[properties.getRounds() * properties.getQuestions()]);

            }
            case GAME_ACTIVE -> {
                //TEMP
                qa.loadQA(client.gp.getCategoryID());
                client.gp.setQA(qa.getList());


                client.gf.GUIState(gameScreenState);
                if(counter < properties.getQuestions()){
                    loadRoundGame(counter);
                    counter++;
                } else {
                    client.localTotalScore += client.localRoundScore;
                    client.localRoundScore = 0;
                    client.gp.setGameState(END_GAME);
                }
            }
            case END_GAME -> {
               client.gp.setTotalScore(client.localTotalScore);
            }
        }

         */
    }

    private void loadImage(int imageNumber){
        client.bufferedImage = StaticImageHandler.loadImage(Bilderna[imageNumber]);
    }

    /*private void loadRoundGame(int i){
        client.gf.tpQuestion.setText(client.gp.getQA().get(i)[0]);
        client.gf.btAnswer1.setText(client.gp.getQA().get(i)[1]);
        client.gf.btAnswer2.setText(client.gp.getQA().get(i)[2]);
        client.gf.btAnswer3.setText(client.gp.getQA().get(i)[3]);
        client.gf.btAnswer4.setText(client.gp.getQA().get(i)[4]);
        client.gf.correctAnswer = client.gp.getQA().get(i)[5];
    }

     */

}






