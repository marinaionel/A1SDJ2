package client.networking;

import client.model.Model;
import shared.model.Game;
import shared.model.Player;

import java.io.IOException;
import java.net.Socket;

public class Client implements iClient {

    private Socket socket;
    private SocketHandler socketHandler;
    private Model model;

    public Client(Model model) {
        this.model = model;
        try {
            socket = new Socket("localhost", 1999);
            socketHandler = new SocketHandler(socket, this);
            new Thread(socketHandler).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected...");

    }

    @Override
    public void play(String playerName) {
        socketHandler.startGame(playerName);
    }

    @Override
    public void place(int row, int column, Game.Sign sign) {
        socketHandler.place(row, column, sign);
    }

    @Override
    public void changeLabelStatus(String status) {
        model.setLobbyStatus(status);
    }

    @Override
    public void startGame(Game.Sign sign) {
        model.joinGame(sign);
    }

    @Override
    public void cantPlace(String reason) {
        model.cantPlace(reason);
    }

    @Override
    public void validPlace(int row, int column, Game.Sign sign) {
        model.validPlace(row, column, sign);
    }

    @Override
    public void finishedGame(String winner, String sign) {
        model.finishedGame(winner, sign);
    }

    @Override
    public void draw() {
        model.draw();
    }

    @Override
    public Player[] getResultsTable() {
        return socketHandler.getResultsTables();
    }

}
