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
    private StringProperty playerName;
    private StringProperty lobbyStatusLabel;

    public LobbyViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;

        model.addListener("Lobby Status", this::lobbyStatusChanged);
        lobbyStatusLabel = new SimpleStringProperty();
    }

    public void setPlayerName(String playerName1) {
        playerName = new SimpleStringProperty(playerName1);
        model.getPlayer().setPlayerName(playerName1);
    }

    private void lobbyStatusChanged(PropertyChangeEvent event) {
        Platform.runLater(() -> lobbyStatusLabel.setValue((String) event.getNewValue()));
    }

    public StringProperty lobbyStatusLabelProperty() {
        return lobbyStatusLabel;
    }

    public void startGame() {
        model.getiClient().startGame(playerName.getValueSafe());
    }

}
