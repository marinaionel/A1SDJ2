package client.model;

import client.networking.iClient;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);

    void setLobbyStatus(String status);

    void setiClient(iClient iClient);

    iClient getiClient();

}
