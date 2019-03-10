package client.networking;

import shared.model.Game;
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


            //waiting for one more person to press PLAY in order to start the game, or waiting for game joined message
            if (request.equals(RequestCodes.WAITING_FOR_OPPONENT)) {
                client.changeLabelStatus("Waiting for opponent...");
                try {
                    request = inFromServer.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(request);
                if (request.contains(RequestCodes.JOINED_GAME)) {
                    client.joinGame(request.split("\\|")[1].equals("X") ? Game.Sign.CROSS : Game.Sign.ZERO);
                }
            } else if (request.contains(RequestCodes.JOINED_GAME)) {
                client.joinGame(request.split("\\|")[1].equals("X") ? Game.Sign.CROSS : Game.Sign.ZERO);
            }


            //players are in the game now
            do {
                try {
                    request = inFromServer.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //player tried to place, but it was in invalid move
                if (request.equals(RequestCodes.CANT_PLACE)) {

                }

                //someone placed, the request has this form:    placed|column|row|sign
                if (request.contains(RequestCodes.PLAYER_PLACED)) {

                }
            } while (!request.equals(RequestCodes.GAME_FINISHED));


        }
    }

    //when a player pressed "PLAY" button
    public void startGame(String playerName) {
        try {
            outToServer.writeUTF(RequestCodes.PLAY + "|" + playerName);
            outToServer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void place(int row, int column, Game.Sign sign) {
        try {
            outToServer.writeUTF(RequestCodes.TRY_PLACE + "|" + row + "|" + column + "|" + (sign == Game.Sign.ZERO ? "O" : "X"));
            outToServer.flush();
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
