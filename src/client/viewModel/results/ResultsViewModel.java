package client.viewModel.results;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Player;

import java.beans.PropertyChangeEvent;
import java.util.List;

public class ResultsViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private ListProperty<Player> listOfPlayers;

    public ResultsViewModel(Model model, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.model = model;
        listOfPlayers = new SimpleListProperty<>(FXCollections.observableArrayList());
        model.addListener("array results", this::receivedResultsTable);
    }

    private void receivedResultsTable(PropertyChangeEvent event) {
        Player[] arr = (Player[]) event.getNewValue();
        ObservableList<Player> list = (ObservableList<Player>) List.of(arr);
        Platform.runLater(() -> {
            listOfPlayers.setValue(list);
        });
    }

    public ListProperty<Player> getItemsList() {
        return listOfPlayers;
    }

    public void tryAgain() {
        viewHandler.openLobby();
    }
}