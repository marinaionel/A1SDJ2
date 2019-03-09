package server;

import server.model.Game;
import shared.model.RequestCodes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class PlayerHandler implements Runnable {

    private Socket socket;
    private boolean isInGame;
    private Game.Sign sign;

    public PlayerHandler(Socket socket) {
        this.socket = socket;
    }

    public void joinGame() {
    }

    @Override
    public void run() {
        ObjectInputStream socketIn = null;
        String request = "";
        try {
            socketIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                request = socketIn.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (request) {
                case RequestCodes.PLAY:
                    GameCreator.tryPlay(this);
                    break;
                default:
                    System.out.println("did not understood u m8");
                    break;
            }
        }
    }

}
