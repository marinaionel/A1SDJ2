package clientX.model;

import clientX.networking.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameModel implements Model {
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;

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
    public void setClient(Client client) {
        this.client = client;
    }
}
