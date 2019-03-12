package client.viewModel.game;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import shared.model.Game;
import shared.model.RequestCodes;

import java.beans.PropertyChangeEvent;

public class GameViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private StringProperty errorLabel;

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

    public StringProperty errorLabelProperty() {
        return errorLabel;
    }

    public String getPlayerName() {
        return model.getPlayer().getPlayerName();
    }

    public void setErrorLabelStatus() {
        String str = model.getMessage();
        if (str.contains(RequestCodes.NOT_YOUR_TURN))
            Platform.runLater(() -> errorLabel.setValue("Not your turn!"));
        if (str.contains(RequestCodes.PLACE_TAKEN))
            Platform.runLater(() -> errorLabel.setValue("Place taken!"));
//        if (str.contains(RequestCodes.PLAYER_PLACED)) {
//            Platform.runLater(() -> errorLabel.setValue("It's your turn!"));
//        }
    }
}
