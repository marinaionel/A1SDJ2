package client.viewModel.game;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.model.Game;

import java.beans.PropertyChangeEvent;

public class GameViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private StringProperty errorLabel;
    private StringProperty winnerStatusLabel;

    private StringProperty r1c1;
    private StringProperty r1c2;
    private StringProperty r1c3;
    private StringProperty r2c1;
    private StringProperty r2c2;
    private StringProperty r2c3;
    private StringProperty r3c1;
    private StringProperty r3c2;
    private StringProperty r3c3;

    public GameViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
        errorLabel = new SimpleStringProperty();
        model.addListener("Join game", this::joinGame);
        model.addListener("can't place", this::setErrorLabelStatus);
        model.addListener("placed", this::validPlacing);
        model.addListener("GameFinished", this::finishedGame);
        model.addListener("draw", this::draw);
        r1c1 = new SimpleStringProperty();
        r1c2 = new SimpleStringProperty();
        r1c3 = new SimpleStringProperty();
        r2c1 = new SimpleStringProperty();
        r2c2 = new SimpleStringProperty();
        r2c3 = new SimpleStringProperty();
        r3c1 = new SimpleStringProperty();
        r3c2 = new SimpleStringProperty();
        r3c3 = new SimpleStringProperty();
        winnerStatusLabel = new SimpleStringProperty();
    }

    private void draw(PropertyChangeEvent event) {
        Platform.runLater(() -> winnerStatusLabel.setValue((String) event.getNewValue()));
    }

    private void finishedGame(PropertyChangeEvent event) {
        Platform.runLater(() -> {
            String[] arr = ((String) event.getNewValue()).split("\\|");
            winnerStatusLabel.setValue("The winner is " + arr[0] + ", sign: " + arr[1]);
        });
    }

    public StringProperty r1c1Property() {
        return r1c1;
    }

    public StringProperty r1c2Property() {
        return r1c2;
    }

    public StringProperty r1c3Property() {
        return r1c3;
    }

    public StringProperty r2c1Property() {
        return r2c1;
    }

    public StringProperty r2c2Property() {
        return r2c2;
    }

    public StringProperty r2c3Property() {
        return r2c3;
    }

    public StringProperty r3c1Property() {
        return r3c1;
    }

    public StringProperty r3c2Property() {
        return r3c2;
    }

    public StringProperty r3c3Property() {
        return r3c3;
    }

    public void openResults() {
        model.refreshPlayerList();
        viewHandler.openResults();
    }

    private void validPlacing(PropertyChangeEvent event) {
        refreshBoard();
    }

    public void refreshBoard() {
        Platform.runLater(() -> {
            Game game = model.getGame();
            r1c1.setValue(signToString(game.getPlace(0, 0)));
            r1c2.setValue(signToString(game.getPlace(0, 1)));
            r1c3.setValue(signToString(game.getPlace(0, 2)));
            r2c1.setValue(signToString(game.getPlace(1, 0)));
            r2c2.setValue(signToString(game.getPlace(1, 1)));
            r2c3.setValue(signToString(game.getPlace(1, 2)));
            r3c1.setValue(signToString(game.getPlace(2, 0)));
            r3c2.setValue(signToString(game.getPlace(2, 1)));
            r3c3.setValue(signToString(game.getPlace(2, 2)));
            errorLabel.setValue("");
        });
    }

    private String signToString(Game.Sign sign) {
        if (sign == Game.Sign.ZERO)
            return "O";
        if (sign == Game.Sign.CROSS)
            return "X";
        return "";
    }

    public void resetWinnerLabel() {
        Platform.runLater(() -> winnerStatusLabel.setValue(""));
    }

    //when an opponent is found this method will be called
    private void joinGame(PropertyChangeEvent evt) {
        refreshBoard();
        resetWinnerLabel();
        Platform.runLater(() -> viewHandler.openGame());
    }

    public void place(int row, int column) {
        model.getiClient().place(row - 1, column - 1, model.getMySign());
    }

    public Game.Sign getMySign() {
        return model.getMySign();
    }

    public StringProperty winnerStatusLabelProperty() {
        return winnerStatusLabel;
    }

    public StringProperty errorLabelProperty() {
        return errorLabel;
    }

    public String getPlayerName() {
        return model.getPlayer().getPlayerName();
    }

    private void setErrorLabelStatus(PropertyChangeEvent event) {
        Platform.runLater(() -> errorLabel.setValue((String) event.getNewValue()));
    }

    public void tryAgain() {
        viewHandler.openLobby();
    }
}
