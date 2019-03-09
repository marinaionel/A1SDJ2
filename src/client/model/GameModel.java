package client.model;

import client.networking.iClient;
import shared.model.Game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameModel implements Model {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private iClient iClient;
    private Game game;


    private String lobbyStatus;

    public GameModel() {
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
    public void updateBoard(Game game) {

        support.firePropertyChange("Board update", null, game);
    }

    @Override
    public void gameFinished() {

    }


}
