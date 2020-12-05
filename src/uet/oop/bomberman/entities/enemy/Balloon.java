package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
    public Balloon(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void chooseImg() {
        if (goNorth || goSouth) {
            img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60).getFxImage();
            return;
        }
        if (goEast || goWest) {
            img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60).getFxImage();
            return;
        }
        img = Sprite.balloom_dead.getFxImage();
    }
}
