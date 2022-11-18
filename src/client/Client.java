package client;
import gamepackage.GamePackage;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static final String ip = "localhost";
    private static final int port = 12345;

    public GamePackage gp = new GamePackage();

    //GameFrame gf;
    //ClientSideProtocol protocol;         //this.protocol = new ClientSideProtocol();
    ObjectOutputStream output;
    ObjectInputStream input;



    //Constructor som sätter namnet i en GamePackage och skickar in GamePackage till
    //GameFrame för att rita upp Username direkt
    public Client() {
        String username;
        while(true) {
            username = JOptionPane.showInputDialog(null,"What's your username?");
            if(username != null) {
                if (username.length() > 0) {
                    break;
                }
                JOptionPane.showMessageDialog(null, "Your username has to be longer then 0 in length");
            }
        }
        gp.setName(username);
        //this.gf = new GameFrame(gp);

        //TEMP
        System.out.println(gp.getName());
    }

    //Connect function för att connecta till servern och skapa upp Protocol (kanske ändras var vi skapar upp protocol).
    public void connect(){
        try(Socket socket = new Socket(ip, port)) {
            this.output = new ObjectOutputStream(socket.getOutputStream());
            this.input = new ObjectInputStream(socket.getInputStream());
            sendAndReceive();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Skickar GamePackage till servern och tar emot GamePackage från servern varje gång den används.
    public void sendAndReceive(){
        try {
            output.writeObject(gp);
            output.flush();
            Object object;
            while((object = input.readObject()) != null){
                if(object instanceof GamePackage gamePackage) {
                    gp = gamePackage;
                    System.out.println(gp.toString());
                    break;
                } else if (object instanceof String){
                    System.out.println(object);
                    break;
                } else {
                    System.out.println("Hit skulle du inte komma");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Client client = new Client();
       client.connect();
        //client.sendAndReceive();
    }
}
