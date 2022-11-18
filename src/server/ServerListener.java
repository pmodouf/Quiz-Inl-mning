package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    final private int port = 12345;
    int count = 0;
    //Clienthandler obj
    //Server protocoll obj

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
            //Clienthandler obj = new clienthandler
            //Server protocoll obj = new Server protocoll
        }else{
            //server handler 1 = new server handler
            //server handler 2 = new server handler
            //1.start
            //2.start
            count = 0;
        }
    }
}









