package ca.uwaterloo.cs349;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;

public class Player extends Entity {
    boolean isLeft = true;

    Player(int x, int y, ImageView sprite, Pane pane) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.sprite.relocate(x, y);
        this.pane = pane;
    }

    @Override
    public void update() {
        if (isLeft) {
            this.sprite.setScaleX(1);

        } else {
            this.sprite.setScaleX(-1);
        }
    }

    @Override
    public void processInput(Boolean isLeft) {
        this.isLeft = isLeft;
    }
}
