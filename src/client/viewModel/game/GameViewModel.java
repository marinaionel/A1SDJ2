package client.viewModel.game;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import shared.model.Game;

import java.beans.PropertyChangeEvent;

public class GameViewModel {
    private Model model;
    private ViewHandler viewHandler;


    public GameViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        model.addListener("Join game", this::joinGame);
    }

    //when an opponent is found this method will be called
    void joinGame(PropertyChangeEvent evt) {
        //TODO get sign from evt and display it in GUI
        Platform.runLater(() -> viewHandler.openGame());
    }

    public void place(int row, int column) {
        model.getiClient().place(row, column, model.getMySign());
    }

    public Game.Sign getMySign() {
        return model.getMySign();
    }

    public String getPlayerName() {
        return model.getPlayer().getPlayerName();
    }
}
