package client.viewModel.lobby;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;

public class LobbyViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private StringProperty username;
    private StringProperty lobbyStatusLabel;

    public LobbyViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        model.addListener("Lobby Status", this::lobbyStatusChanged);
        username = new SimpleStringProperty();
        lobbyStatusLabel = new SimpleStringProperty();
    }

    private void lobbyStatusChanged(PropertyChangeEvent event) {
        Platform.runLater(() -> lobbyStatusLabel.setValue((String) event.getNewValue()));
    }

    public StringProperty lobbyStatusLabelProperty() {
        return lobbyStatusLabel;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void startGame() {
        model.getiClient().startGame("asd");
    }
}
