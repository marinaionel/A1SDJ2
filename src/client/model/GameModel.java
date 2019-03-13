package client.model;

import client.networking.iClient;
import shared.model.Game;
import shared.model.Player;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameModel implements Model {
    private PropertyChangeSupport support;
    private iClient iClient;
    private Game game;
    private Player player;
    private Game.Sign mySign;

    public GameModel() {
        player = new Player();
        support = new PropertyChangeSupport(this);
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        if (eventName == null || "".equals(eventName)) {
            support.addPropertyChangeListener(listener);
        } else {
            support.addPropertyChangeListener(eventName, listener);
        }
    }

    @Override
    public void setLobbyStatus(String status) {
        support.firePropertyChange("Lobby Status", null, status);
    }

    @Override
    public void setiClient(iClient iClient) {
        this.iClient = iClient;
    }

    @Override
    public iClient getiClient() {
        return this.iClient;
    }

    @Override
    public void joinGame(Game.Sign sign) {
        mySign = sign;
        support.firePropertyChange("Join game", null, sign);
        game = new Game();
    }

    @Override
    public Game.Sign getMySign() {
        return mySign;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void cantPlace(String reason) {
        support.firePropertyChange("can't place", null, reason);
    }

    @Override
    public void validPlace(int row, int column, Game.Sign sign) {
        game.place(row, column, sign);
        support.firePropertyChange("placed", null, game);
    }

    @Override
    public void finishedGame(String winner, String sign) {
        support.firePropertyChange("GameFinished", null, winner + "|" + sign);
    }

    @Override
    public void draw() {
        support.firePropertyChange("draw", null, "It's a draw! Game over!");
    }
}
