package client.networking;

import shared.model.Game;

public interface iClient {
    void startGame(String playerName);

    void place(int row, int column, Game.Sign sign);

    void changeLabelStatus(String status);

    void joinGame(Game.Sign sign);

    void cantPlace(String reason);

    void validPlace(int row, int column, Game.Sign sign);
}
