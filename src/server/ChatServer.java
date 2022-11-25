package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServer extends Thread{

    private Socket socket;

    public ChatServer(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

            while(true){
                String input = in.readLine();
//                if (input == null) {
//                    return;
//                }
                out.println(input);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
