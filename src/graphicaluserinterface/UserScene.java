package graphicaluserinterface;

import javafx.scene.Scene;

abstract public class UserScene {
    String title;
    abstract public Scene getScene();
    abstract public void setTitle(String title);
}
