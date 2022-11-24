package client;

import database.Database;
import database.QA;
import gamepackage.GamePackage;

import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Client {

    private static final String ip = "localhost";
    private static final int port = 12345;

    public GamePackage gp = new GamePackage();

    ClientProtocol protocol = new ClientProtocol(this);
    //GameFrame gf = new GameFrame(this);

    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;

    //LOCAL VALUES
    public int localRoundScore = 0;
    public int localTotalScore = 0;

    //TEMP
    Database database = new Database();
    BufferedImage bufferedImage;

    //Constructor som sätter namnet i en GamePackage och skickar in GamePackage till
    //GameFrame för att rita upp Username direkt
    public Client() {
        String username;
        username = "Test";
        gp.setName(username);
        gp.setImage(0);
        gp.setGameState(1);
        protocol.update();
    }

    //Connect function för att connecta till servern och skapa upp Protocol (kanske ändras var vi skapar upp protocol).
    public void connect() {

        try {
            socket = new Socket(ip, port);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            output.writeObject(gp);
            output.flush();
            Object object;
            while ((object = input.readObject()) != null) {
                if (object instanceof GamePackage gamePackage) {
                    gp = gamePackage;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Skickar GamePackage till servern och tar emot GamePackage från servern varje gång den används.
    public void sendAndReceive() {
        try {
            output.writeObject(gp);
            output.flush();
            Object object;
            while ((object = input.readObject()) != null) {
                if (object instanceof GamePackage gamePackage) {
                    gp = gamePackage;
                    protocol.update();

                    //TEST
                    System.out.println(gp);
                    break;
                } else if (object instanceof String) {
                    System.out.println(object);
                    break;
                } else {
                    System.out.println("Hit skulle du inte komma");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
        client.sendAndReceive();
        client.sendAndReceive();
        client.sendAndReceive();
        client.sendAndReceive();
        client.sendAndReceive();
        client.sendAndReceive();
    }
}
