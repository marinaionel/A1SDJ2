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
        playerName = new SimpleStringProperty();
        lobbyStatusLabel = new SimpleStringProperty();
        model.addListener("Lobby Status", this::lobbyStatusChanged);
    }

    public void setPlayerName(String playerName1) {
        playerName.setValue(playerName1);
        model.getPlayer().setPlayerName(playerName1);
    }

    private void lobbyStatusChanged(PropertyChangeEvent event) {
        Platform.runLater(() -> lobbyStatusLabel.setValue((String) event.getNewValue()));
    }

    public void resetLabel() {
        Platform.runLater(() -> lobbyStatusLabel.setValue(""));
    }

    public StringProperty lobbyStatusLabelProperty() {
        return lobbyStatusLabel;
    }

    public void play() {
        model.getiClient().play(playerName.getValueSafe());
    }
}
