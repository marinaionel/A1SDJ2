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
    TextField usernamefield;
    private LobbyViewModel lobbyViewModel;

    public void onPlayButton() {
        lobbyViewModel.startGame();
    }

    public void init(LobbyViewModel lobbyViewModel) {
        this.lobbyViewModel = lobbyViewModel;
//        lobbyViewModel.usernameProperty().bindBidirectional(usernamefield.textProperty());
    }
}
