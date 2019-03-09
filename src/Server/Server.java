package Server;

import server.PlayerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//This class starts the server, waits for incoming connections and starts the PlayerHandler thread when a player joins
public class Server {

    public Server() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(1999);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server started...");
        while (true) {
            Socket newClient;
            try {
                newClient = socket.accept();
                new Thread(new PlayerHandler(newClient)).start();
                System.out.println("Player connected...");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
