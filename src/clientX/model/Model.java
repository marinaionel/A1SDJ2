package clientX.model;

import clientX.networking.Client;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);

    public void setClient(Client client);

}
