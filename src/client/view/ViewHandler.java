package client.view;

import client.model.Model;
import client.view.game.GameView;
import client.view.lobby.LobbyView;
import client.view.results.ResultsView;
import client.viewModel.ViewModelProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private ViewModelProvider viewModelProvider;
    private Stage mainStage;

    public ViewHandler(Stage stage, Model model) {
        mainStage = stage;
        viewModelProvider = new ViewModelProvider(model, this);
    }

    public void start() {
        openLobby();
    }

    public void openLobby() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("lobby/lobby.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LobbyView view = loader.getController();
        view.init(viewModelProvider.getLobbyViewModel());
        mainStage.setTitle("Lobby");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openGame() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("game/game.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameView view = loader.getController();
        view.init(viewModelProvider.getGameViewModel());
        mainStage.setTitle("Game");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openResults() {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("results/results.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultsView view = loader.getController();
        view.init(viewModelProvider.getResultsViewModel());
        mainStage.setTitle("Results");

        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
