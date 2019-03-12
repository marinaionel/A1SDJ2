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
    private String lobbyStatus;
    private Game.Sign mySign;
    private String message;

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
        lobbyStatus = status;
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
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

}
