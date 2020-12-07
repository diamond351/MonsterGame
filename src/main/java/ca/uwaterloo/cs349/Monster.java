package ca.uwaterloo.cs349;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Monster extends Entity {
    boolean isLeft;
    boolean hitFire = false;
    boolean hitPlayer = false;
    int moveSpeed;
    int knockBackDistance = 400;
    int knockBackSpeed = 20;

    Monster(int x, int y, int lvl, boolean isLeft, ImageView monsterSprite, Pane pane) {
        this.x = x;
        this.y = y;
        this.isLeft = isLeft;
        this.sprite = monsterSprite;
        this.sprite.relocate(x, y);
        this.pane = pane;

        if (isLeft) {
            this.x = 1280;
        }
        if (lvl == 1) {
            moveSpeed = 1;
        } else if (lvl == 2) {
            moveSpeed = 2;
        } else {
            moveSpeed = 6;
        }
        if (!isLeft) {
            this.sprite.setScaleX(-1);
        }
    }

    public boolean getHitFire() {
        return hitFire;
    }

    @Override
    public void update() {
        if (!hitPlayer) {
            if (isLeft) {
                this.x -= moveSpeed;
            } else {
                this.x += moveSpeed;
            }
        } else {
            if (knockBackDistance > 0) {
                if (isLeft) {
                    this.x += knockBackSpeed;
                } else {
                    this.x -= knockBackSpeed;
                }

                knockBackDistance -= 25;
            } else {
                hitPlayer = false;
                knockBackDistance = 400;
            }

        }
        this.sprite.relocate(x, y);
    }

    @Override
    public void processInput(Boolean isLeft) {

    }
}
