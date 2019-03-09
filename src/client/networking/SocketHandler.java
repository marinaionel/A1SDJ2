package client.networking;

import shared.model.RequestCodes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler implements Runnable {
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;
    private Client client;

    public SocketHandler(Socket socket, Client client) {
        this.client=client;
        try {
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
            this.inFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public void startGame(String playerName){
        try {
            outToServer.writeUTF(RequestCodes.PLAY+"|"+playerName);
            outToServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
