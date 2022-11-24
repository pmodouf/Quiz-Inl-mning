package client;

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

    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update(){
        switch(client.gp.getGameState()){
            case FIRST_INIT -> {
                client.gf.GUIState(waitScreenState);
            }
            case GAME_ACTIVE -> {
            }
        }
    }

    public void loadImage(int imageNumber){
        client.bufferedImage = StaticImageHandler.loadImage(Bilderna[imageNumber]);
    }

}






