package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Enemy {
    public Minvo(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void chooseImg() {
        if (goNorth || goSouth) {
            img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, animate, 60).getFxImage();
            return;
        }
        if (goEast || goWest) {
            img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, animate, 60).getFxImage();
            return;
        }
        img = Sprite.minvo_dead.getFxImage();
    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        }
        super.render(gc);
    }
}
