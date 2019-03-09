package server.model;

import java.net.Socket;

public class Player {

    private Socket socket;
    private String name;

    public Player(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }
}
