package clientX.networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientSocketHandler implements Runnable {
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;
    private SocketClient client;

    public ClientSocketHandler(ObjectOutputStream outToServer, ObjectInputStream inFromServer, SocketClient client1) {
        client = client1;
        this.outToServer = outToServer;
        this.inFromServer = inFromServer;
    }

    @Override
    public void run() {

    }
}
