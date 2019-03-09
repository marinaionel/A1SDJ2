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
        this.client = client;
        try {
            this.outToServer = new ObjectOutputStream(socket.getOutputStream());
            this.inFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String request = "";
        while (true) {
            try {
                request = inFromServer.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println(request);


            //waiting for one more person to press PLAY in order to start the game
            if (request.equals(RequestCodes.WAITING_FOR_OPPONENT)) {
                client.changeLabelStatus("Waiting for opponent...");
                try {
                    request = inFromServer.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            //opponent found, the game begins
            if (request.equals(RequestCodes.OPPONENT_FOUND)) {
                client.changeLabelStatus("Opponent found...");
            }

            //player tried to place, but it was in invalid move
            if (request.equals(RequestCodes.CANT_PLACE)) {

            }

            //someone placed, the request has this form:    placed|column|row|sign
            if (request.contains(RequestCodes.PLAYER_PLACED)) {

            }


            if (request.contains(RequestCodes.GAME_FINISHED)) {

            }
        }
    }

    public void startGame(String playerName) {
        try {
            outToServer.writeUTF(RequestCodes.PLAY + "|" + playerName);
            outToServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
