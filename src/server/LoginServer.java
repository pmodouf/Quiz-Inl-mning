package server;

import database.Database;
import database.User;
import gamepackage.GamePackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LoginServer {

    Database db;

    final private int port = 555555;
    public LoginServer(){
        db = new Database();
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                connectClient(server);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectClient(ServerSocket server) throws IOException {
        Socket ss = server.accept();
        try (ObjectOutputStream send = new ObjectOutputStream(ss.getOutputStream());
             ObjectInputStream receive = new ObjectInputStream(ss.getInputStream())) {

            Object clientRequest;
            while ((clientRequest = receive.readObject()) != null){
                if (clientRequest instanceof String[] login) {
                    if(login.length == 3){
                        if(db.createUser(login[0],login[1],login[2])){
                            send.writeObject(db.getUser(login[0]));
                        } else {
                            send.writeObject(new User());
                        }
                    } else {
                        if(db.validateUser(login[0], login[1])){
                            send.writeObject(db.getUser(login[0]));
                        }else{
                            send.writeObject(new User());
                        }
                    }
                }
            }

        }catch (IOException e) {
            System.out.println(e.getClass());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new LoginServer();
    }
}
