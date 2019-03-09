package clientX.view.results;

import clientX.viewModel.results.ResultsViewModel;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.model.Player;

public class ResultsView {
    @FXML
    TableView<String> tableView;
    @FXML
    TableColumn<String, Player> playerColumn;
    @FXML
    TableColumn<Integer, Player> scoreColumn;
    private ResultsViewModel resultsViewModel;

    public void init(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("Player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView.itemsProperty().bind((ObservableValue<? extends ObservableList<String>>) resultsViewModel.getItemsList());
    }
}
