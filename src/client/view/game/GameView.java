package client.view.game;

import client.viewModel.game.GameViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.model.Game;

public class GameView {
    private GameViewModel gameViewModel;
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
    }

    public void placer1c2() {
        gameViewModel.place(1, 2);
    }

    public void placer1c3() {
        gameViewModel.place(1, 3);
    }

    public void placer2c1() {
        gameViewModel.place(2, 1);
    }

    public void placer2c2() {
        gameViewModel.place(2, 2);
    }

    public void placer2c3() {
        gameViewModel.place(2, 3);
    }

    public void placer3c1() {
        gameViewModel.place(3, 1);
    }

    public void placer3c2() {
        gameViewModel.place(3, 2);
    }

    public void placer3c3() {
        gameViewModel.place(3, 3);
    }

    public void init(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
        playerType.setText(gameViewModel.getMySign() == Game.Sign.ZERO ? "O" : "X");
    }
}