package clientX.model;

import java.beans.PropertyChangeListener;

public interface Model {
    void addListener(String name, PropertyChangeListener listener);

}
