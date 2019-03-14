package client.view.game;

import client.viewModel.game.GameViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import shared.model.Game;

public class GameView {
    private GameViewModel gameViewModel;
    @FXML
    Label errorLabelForButtons;
    @FXML
    Button seeResults;
    @FXML
    Button tryAgain;
    @FXML
    Label errorLabel;
    @FXML
    Label usernameLabel;
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

    public void onTryAgainButton() {
        if (winnerLabel.getText() == null) {
            errorLabelForButtons.setText("You can try again after finishing the game!");
        } else {
            gameViewModel.tryAgain();
        }
    }

    public void onSeeResultsButton() {
        if (winnerLabel.getText() == null) {
            errorLabelForButtons.setText("You can see the results table after finishing the game!");
        } else {
            gameViewModel.openResults();
        }
    }

    public void placer1c1() {
        gameViewModel.place(1, 1);
        errorLabelForButtons.setText("");
    }

    public void placer1c2() {
        gameViewModel.place(1, 2);
        errorLabelForButtons.setText("");
    }

    public void placer1c3() {
        gameViewModel.place(1, 3);
        errorLabelForButtons.setText("");
    }

    public void placer2c1() {
        gameViewModel.place(2, 1);
        errorLabelForButtons.setText("");
    }

    public void placer2c2() {
        gameViewModel.place(2, 2);
        errorLabelForButtons.setText("");
    }

    public void placer2c3() {
        gameViewModel.place(2, 3);
        errorLabelForButtons.setText("");
    }

    public void placer3c1() {
        gameViewModel.place(3, 1);
        errorLabelForButtons.setText("");
    }

    public void placer3c2() {
        gameViewModel.place(3, 2);
        errorLabelForButtons.setText("");
    }

    public void placer3c3() {
        gameViewModel.place(3, 3);
        errorLabelForButtons.setText("");
    }

    public void init(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
        playerType.setText(gameViewModel.getMySign() == Game.Sign.ZERO ? "O" : "X");
        usernameLabel.setText(gameViewModel.getPlayerName());
        winnerLabel.setText("");
        errorLabel.setText("");
        errorLabel.textProperty().bindBidirectional(gameViewModel.errorLabelProperty());
        errorLabelForButtons.setText("");
        r1c1.textProperty().bindBidirectional(gameViewModel.r1c1Property());
        r1c2.textProperty().bindBidirectional(gameViewModel.r1c2Property());
        r1c3.textProperty().bindBidirectional(gameViewModel.r1c3Property());
        r2c1.textProperty().bindBidirectional(gameViewModel.r2c1Property());
        r2c2.textProperty().bindBidirectional(gameViewModel.r2c2Property());
        r2c3.textProperty().bindBidirectional(gameViewModel.r2c3Property());
        r3c1.textProperty().bindBidirectional(gameViewModel.r3c1Property());
        r3c2.textProperty().bindBidirectional(gameViewModel.r3c2Property());
        r3c3.textProperty().bindBidirectional(gameViewModel.r3c3Property());
        winnerLabel.textProperty().bindBidirectional(gameViewModel.winnerStatusLabelProperty());
    }
}