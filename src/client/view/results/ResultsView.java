package client.view.results;

import client.viewModel.results.ResultsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.model.Player;

public class ResultsView {
    @FXML
    Button tryAgain;
    @FXML
    TableView<Player> tableView;
    @FXML
    TableColumn<String, Player> playerColumn;
    @FXML
    TableColumn<String, Player> scoreColumn;
    private ResultsViewModel resultsViewModel;

    public void init(ResultsViewModel resultsViewModel) {
        this.resultsViewModel = resultsViewModel;
        playerColumn.setCellValueFactory(new PropertyValueFactory<>("Player"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("Score"));
        tableView.itemsProperty().bind(resultsViewModel.getItemsList());
    }

    public void onTryAgainButton() {
        resultsViewModel.tryAgain();
    }
}
