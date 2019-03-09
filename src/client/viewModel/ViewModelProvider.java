package client.viewModel;

import client.model.Model;
import client.view.ViewHandler;
import client.viewModel.game.GameViewModel;
import client.viewModel.lobby.LobbyViewModel;
import client.viewModel.results.ResultsViewModel;

public class ViewModelProvider {
    private GameViewModel gameViewModel;
    private LobbyViewModel lobbyViewModel;
    private ResultsViewModel resultsViewModel;


    public ViewModelProvider(Model model, ViewHandler viewHandler) {
        gameViewModel = new GameViewModel(model, viewHandler);
        lobbyViewModel = new LobbyViewModel(model, viewHandler);
        resultsViewModel=new ResultsViewModel(model,viewHandler);
    }

    public GameViewModel getGameViewModel() {
        return gameViewModel;
    }

    public LobbyViewModel getLobbyViewModel() {
        return lobbyViewModel;
    }

    public ResultsViewModel getResultsViewModel() {
        return resultsViewModel;
    }
}
