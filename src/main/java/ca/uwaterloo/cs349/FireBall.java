package ca.uwaterloo.cs349;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class FireBall extends Entity {
    boolean isLeft;
    int lifeTime;
    boolean isCollide = false;

    FireBall(int x, int y, int lvl, boolean isLeft, ImageView fireSprite, Pane pane) {
        this.x = x;
        this.y = y;
        this.isLeft = isLeft;
        this.sprite = fireSprite;
        this.sprite.relocate(x, y);
        this.pane = pane;

        if (lvl == 1) {
            lifeTime = 5000;
        } else if (lvl == 2) {
            lifeTime = 3500;
        } else {
            lifeTime = 1500;
        }
        if (isLeft) {
            this.sprite.setScaleX(-1);
        }
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public boolean getIsCollide() {
        return isCollide;
    }

    @Override
    public void update() {
        lifeTime -= 25;
        if (lifeTime == 0) {

        } else if (isLeft) {
            this.x -= 1;

        } else {
            this.x += 1;
        }
        this.sprite.relocate(x, y);
    }

    @Override
    public void processInput(Boolean isLeft) {

    }
}
