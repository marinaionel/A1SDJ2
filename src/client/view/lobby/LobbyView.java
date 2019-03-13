package client.view.lobby;

import client.viewModel.lobby.LobbyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LobbyView {
    @FXML
    Label playerLabel;
    @FXML
    Button playButton;
    @FXML
    TextField playerName;
    private LobbyViewModel lobbyViewModel;

    public void onPlayButton() {
        if (playerName.getText().equals("") || playerName.getText() == null) {
            playerLabel.setText("write a valid name");
        } else {
            lobbyViewModel.setPlayerName(playerName.getText());
            lobbyViewModel.startGame();
        }
    }

    public void init(LobbyViewModel lobbyViewModel) {
        this.lobbyViewModel = lobbyViewModel;
        playerLabel.textProperty().bindBidirectional(lobbyViewModel.lobbyStatusLabelProperty());
    }
}
