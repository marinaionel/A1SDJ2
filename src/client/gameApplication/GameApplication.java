package client.gameApplication;

import client.model.GameModel;
import client.model.Model;
import client.networking.Client;
import client.view.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new GameModel();
        Client client = new Client(model);
        ViewHandler viewHandler = new ViewHandler(primaryStage, model);
        model.setiClient(client);
        viewHandler.start();
    }
}
