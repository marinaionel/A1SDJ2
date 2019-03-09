package clientX.viewModel.results;

import clientX.model.GameModel;
import clientX.view.ViewHandler;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import shared.model.Player;

public class ResultsViewModel {
    private ViewHandler viewHandler;
    private ListProperty<Player> listOfPlayers;

    public ResultsViewModel(GameModel model, ViewHandler vh) {
        viewHandler = vh;

        listOfPlayers = new SimpleListProperty<>(FXCollections.observableArrayList());

    }

    public ListProperty<Player> getItemsList() {
        return listOfPlayers;
    }

}