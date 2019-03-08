package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer {

    private SSocketHandler socketHandler;

    private ArrayList<Socket> sockets;


    public SocketServer(SSocketHandler socketHandler)
    {
        this.socketHandler = socketHandler;
        sockets = new ArrayList<>();
        socketHandler.setServer(this);
    }

    public void addClient(Socket socket)
    {
        sockets.add(socket);
    }

    public int getNoPlayers()
    {
        return sockets.size();
    }




}
