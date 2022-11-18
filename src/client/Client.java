package client;
import gamepackage.GamePackage;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private static final String ip = "localhost";
    private static final int port = 12345;

    static String username;

    //GameFrame gf;
    //ClientSideProtocol protocol;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    GamePackage gp = new GamePackage();

    public Client() {
        //this.gf = new GameFrame();
        gp.setName(username);
    }

    public void connect(){
        try(Socket socket = new Socket(ip, port)) {
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
            //this.protocol = new ClientSideProtocol();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void sendAndReceive(){
        try {
            objectOutputStream.writeObject("hello");
            objectOutputStream.flush();

            objectOutputStream.writeObject(gp);
            objectOutputStream.flush();

            Object object;
            while((object = objectInputStream.readObject()) != null){
                if(object instanceof String) {
                    System.out.println(object);
                    break;
                } else if (object instanceof GamePackage gamePackage){
                    gp = gamePackage;
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
        username = JOptionPane.showInputDialog("What's your username?");
        Client client = new Client();
        client.connect();
        client.sendAndReceive();
    }
}
