package client.viewModel.lobby;

import client.model.Model;
import client.view.ViewHandler;

public class LobbyViewModel {
    private Model model;
    private ViewHandler viewHandler;

    public LobbyViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
    }

    public void startGame(){
        model.getiClient().startGame("asd");
    }
}
