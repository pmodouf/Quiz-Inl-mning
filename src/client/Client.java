package client;

import database.Guest;
import database.User;
import gamepackage.GamePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static final String ip = "localhost";
    private static final int port = 12345;

    public GamePackage gp;
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
//Method to start the chat and initiate the game server (both inout and output streams)
    public void connect() {
        gf.chatScreen.chatNow();
        gf.chatScreen.chatNow = true;
        try {
            socket = new Socket(ip, port);
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            writeAndRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// Method to send an object to the connection and then wait for an answer. Main point of communication.
    private void writeAndRead() throws IOException, ClassNotFoundException {
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
    }
// Method to return an object for the user depending on existing user or "guest". Also sends information about user options.
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
// Method to initiate game package.
    public void setUpGP() {
        gp = new GamePackage();
        if (isUser){
            gp.setName(user.getName());
            gp.setWins(user.getWins());
            gp.setImageID(user.getImage());
        }else{
            gp.setName(guest.getName());
            gp.setWins(0);
            gp.setImageID("guest");
        }
    }
// Refer to method writeAndRead
    public void sendAndReceive() {
        try {
            writeAndRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Client();
    }
}
