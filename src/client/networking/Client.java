package client.networking;

import java.io.IOException;
import java.net.Socket;

public class Client implements  iClient {

    Socket socket;
    SocketHandler socketHandler;

    public Client() {
        try {
            socket = new Socket("localhost",1999);
            socketHandler=new SocketHandler(socket,this);
            new Thread(socketHandler).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected...");

    }

    @Override
    public void startGame(String playerName) {
        socketHandler.startGame(playerName);
    }

    @Override
    public void place(int row, int column) {

    }
}
