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
                    System.out.println(request);

                    //1. place taken or not your turn -> update label from view
                    if (request.contains(RequestCodes.PLACE_TAKEN))
                        client.cantPlace(RequestCodes.PLACE_TAKEN);
                    if (request.contains(RequestCodes.NOT_YOUR_TURN))
                        client.cantPlace(RequestCodes.NOT_YOUR_TURN);
                    //2. player placed
                    if (request.contains(RequestCodes.PLAYER_PLACED)) {
                        String[] arr = request.split("\\|");
                        client.validPlace(Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), (arr[3].equals("X") ? Game.Sign.CROSS : Game.Sign.ZERO));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (!request.contains(RequestCodes.WIN) && !request.equals(RequestCodes.FULL_BOARD));
            if (request.contains(RequestCodes.WIN)) {
                client.finishedGame(request.split("\\|")[2], request.split("\\|")[1]);
            }

            if (request.equals(RequestCodes.FULL_BOARD)) {
                client.draw(request);
            }

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
