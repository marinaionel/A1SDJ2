package client.model;

import client.networking.iClient;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameModel implements Model {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private iClient iClient;

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
    public void setiClient(iClient iClient) {
        this.iClient = iClient;
    }
}
