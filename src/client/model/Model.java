package client.model;

import client.networking.iClient;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);

    public void setiClient(iClient iClient);

    public iClient getiClient();

}
