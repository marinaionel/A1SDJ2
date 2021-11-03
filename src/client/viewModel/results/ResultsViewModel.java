package client.viewModel.results;

import client.model.Model;
import client.view.ViewHandler;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.model.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class ResultsViewModel {
    private Model model;
    private ViewHandler viewHandler;
    private ListProperty<Player> listOfPlayers;

    public ResultsViewModel(Model model, ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
        this.model = model;
        listOfPlayers = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ListProperty<Player> getItemsList() {
        ArrayList<Player> list = new ArrayList<>(Arrays.asList(model.getPlayers()));
        ObservableList<Player> list1 = FXCollections.observableArrayList(list);
        Platform.runLater(() -> {
            listOfPlayers.setValue(list1);
        });
        System.out.println(list.get(0));
        return listOfPlayers;
    }

    public void tryAgain() {
        viewHandler.openLobby();
    }
}