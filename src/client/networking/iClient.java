package client.networking;

public interface iClient {
    void startGame(String playerName);

    void place(int row, int column);

    void changeLabelStatus(String status);
}
