package client;

public class ClientProtocol {

    private static final int FIRST_INIT = 0;
    private static final int GAME_ACTIVE = 1;
    private static final int END_GAME = 2;
    private static final int REPEAT_REQUEST = 3;
    private static final int CHOOSE_CATEGORY = 4;

    Client client;

    String[] Bilderna = {"boy1","girl1","man1","old1","old2","women1"};

    public ClientProtocol(Client client){
        this.client = client;
    }

    public void update(){
        if(client.gp.getGameState() == FIRST_INIT){
            loadImage(client.gp.getImage());
        }
        if(client.gp.getGameState() == END_GAME){
            System.out.println("Opponent Name: " + client.gp.getOpponent().getName());
        }
    }

    public void loadImage(int imageNumber){
        client.bufferedImage = client.database.loadImage(Bilderna[imageNumber]);
    }

}






