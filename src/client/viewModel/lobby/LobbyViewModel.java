package client.viewModel.lobby;

import client.model.Model;
import client.view.ViewHandler;
import javafx.beans.property.StringProperty;

public class LobbyViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private StringProperty username;

    public LobbyViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void startGame() {
        model.getiClient().startGame("asd");
    }
}
