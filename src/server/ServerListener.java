package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    final private int port = 12345;
    int count = 0;
    ServerProtocol spHolder;
    ClientHandler chHolder;

    ServerListener() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                connectClient(server);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void connectClient(ServerSocket server) throws IOException {
        Socket client = server.accept();
        count++;
        if(count ==1){
            spHolder = new ServerProtocol();
            chHolder = new ClientHandler(client, spHolder, count);
        }else{
            ClientHandler client1 = chHolder;
            ClientHandler client2 = new ClientHandler(client, spHolder, count);
            client1.start();
            client2.start();
            count = 0;
        }
    }

    public static void main(String[] args) {
      new ServerListener();
    }
}









