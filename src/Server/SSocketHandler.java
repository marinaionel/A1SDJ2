package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SSocketHandler implements Runnable {

    private ServerModel serverModel;
    private SocketServer socketServer;

    @Override
    public void run() {

        socketServer = new SocketServer(this);


        try {
            ServerSocket welcomeSocket = new ServerSocket(2910);

        while(true)
        {
            Socket socket = welcomeSocket.accept();
            socketServer.addClient(socket);


        }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setServer(SocketServer socketServer)
    {
        this.socketServer = socketServer;
    }
}
