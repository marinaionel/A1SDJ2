package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {



    public Server() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(5125);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket newClient;
            try {
                newClient = socket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }




}
