package client;

import client.model.GameModel;
import client.model.Model;
import client.networking.Client;
import com.sun.webkit.Timer;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model=new GameModel();
        Client client=new Client();
        model.setiClient(client);
    }
}
