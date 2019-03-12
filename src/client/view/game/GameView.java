package client.view.game;

import client.viewModel.game.GameViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.model.Game;

public class GameView {
    private GameViewModel gameViewModel;
    @FXML
    Label errorLabel;
    @FXML
    Label usernameLabel;
    @FXML
    Label turn;
    @FXML
    Label playerType;
    @FXML
    Label winnerLabel;
    @FXML
    Button r1c1;
    @FXML
    Button r1c2;
    @FXML
    Button r1c3;
    @FXML
    Button r2c1;
    @FXML
    Button r2c2;
    @FXML
    Button r2c3;
    @FXML
    Button r3c1;
    @FXML
    Button r3c2;
    @FXML
    Button r3c3;

    public void placer1c1() {
        gameViewModel.place(1, 1);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer1c2() {
        gameViewModel.place(1, 2);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer1c3() {
        gameViewModel.place(1, 3);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer2c1() {
        gameViewModel.place(2, 1);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer2c2() {
        gameViewModel.place(2, 2);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer2c3() {
        gameViewModel.place(2, 3);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer3c1() {
        gameViewModel.place(3, 1);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer3c2() {
        gameViewModel.place(3, 2);
        gameViewModel.setErrorLabelStatus();
    }

    public void placer3c3() {
        gameViewModel.place(3, 3);
        gameViewModel.setErrorLabelStatus();
    }

    public void init(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
        playerType.setText(gameViewModel.getMySign() == Game.Sign.ZERO ? "O" : "X");
        usernameLabel.setText(gameViewModel.getPlayerName());
        winnerLabel.setText("");
        errorLabel.setText("");
        gameViewModel.errorLabelProperty().bind(errorLabel.textProperty());
    }
}