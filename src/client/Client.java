package client;

import database.Database;
import database.Guest;
import database.User;
import gamepackage.GamePackage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static final String ip = "localhost";
    private static final int port = 12345;

    public GamePackage gp = new GamePackage();
    public int rounds = 0;
    ClientProtocol protocol = new ClientProtocol(this);

    GameFrame gf = new GameFrame(this);
    Socket socket;
    ObjectOutputStream output;
    ObjectInputStream input;

    String[] currentQuestion;

    User user;
    Guest guest;

    boolean isUser = true;


    public Client() {

    }

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
                    protocol.update();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectToLoginServer(Object obj){

        try(Socket ss = new Socket("localhost", 55555);
            ObjectOutputStream send = new ObjectOutputStream(ss.getOutputStream());
            ObjectInputStream receive = new ObjectInputStream(ss.getInputStream())) {

            send.writeObject(obj);

            Object received;
            while((received = receive.readObject()) != null){
                if(received instanceof User u){
                    user = u;
                    isUser = true;
                    setUpGP();
                    break;
                }else if(received instanceof Guest g){
                    guest = g;
                    isUser = false;
                    setUpGP();
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpGP() {
        if (isUser){
            gp.setName(user.getName());
            gp.setWins(user.getWins());
            gp.setImageID(user.getImage());
        }else{
            gp.setName(guest.getName());
            gp.setWins(0);
            gp.setImageID("boy1");
        }
    }

    public void sendAndReceive() {
        try {
            output.writeObject(gp);
            output.flush();
            Object object;
            while ((object = input.readObject()) != null) {

                if (object instanceof GamePackage gamePackage) {
                    gp = gamePackage;
                    protocol.update();

                    System.out.println(gp);
                    break;
                } else if (object instanceof String) {
                    System.out.println(object);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
