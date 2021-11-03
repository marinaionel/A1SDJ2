package client.networking;

import shared.model.Game;
import shared.model.Player;

public interface iClient {
    void play(String playerName);

    void place(int row, int column, Game.Sign sign);

    void changeLabelStatus(String status);

    void startGame(Game.Sign sign);

    void cantPlace(String reason);

    void validPlace(int row, int column, Game.Sign sign);

    void finishedGame(String winner, String sign);

    void draw();

    Player[] getResultsTable();
}
