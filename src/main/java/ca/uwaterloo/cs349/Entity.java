package ca.uwaterloo.cs349;


import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class Entity {
    int x;
    int y;
    ImageView sprite;
    Pane pane;

    public void draw() {
        pane.getChildren().add(sprite);
    }

    public abstract void update();
    public abstract void processInput(Boolean isLeft);
}
