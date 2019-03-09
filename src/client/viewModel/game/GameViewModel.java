package client.viewModel.game;

import client.model.Model;
import client.view.ViewHandler;

public class GameViewModel {
    private Model model;
    private ViewHandler viewHandler;

    public GameViewModel(Model model, ViewHandler viewHandler) {
        this.model = model;
        this.viewHandler = viewHandler;
    }


}
