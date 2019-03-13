package client.model;

import client.networking.iClient;
import shared.model.Game;
import shared.model.Player;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);

    void setLobbyStatus(String status);

    void setiClient(iClient iClient);

    iClient getiClient();

    void joinGame(Game.Sign sign);

    Game.Sign getMySign();

    Player getPlayer();

    void cantPlace(String reason);

    void validPlace(int row, int column, Game.Sign sign);

    void finishedGame(String winner, String sign);

    void draw();

    void receiveResultsTable(Player[] arrayOfPlayers);
}
