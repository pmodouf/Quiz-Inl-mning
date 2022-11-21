package client;

public class ClientProtocol {

    private static final int FIRST_INIT = 0;
    private static final int GAME_ACTIVE = 1;
    private static final int END_GAME = 2;
    private static final int REPEAT_REQUEST = 3;

    Client client;

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
        if(imageNumber == 0){
            client.bufferedImage = client.database.loadImage("boy1");
        } else if(imageNumber == 1){
            client.bufferedImage = client.database.loadImage("girl1");
        } else if(imageNumber == 2){
            client.bufferedImage = client.database.loadImage("man1");
        } else if(imageNumber == 3){
            client.bufferedImage = client.database.loadImage("old1");
        } else if(imageNumber == 4){
            client.bufferedImage = client.database.loadImage("old2");
        } else if(imageNumber == 5){
            client.bufferedImage = client.database.loadImage("women1");
        }
    }

}
