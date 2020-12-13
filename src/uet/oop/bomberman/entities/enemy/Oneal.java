package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy{
    public Oneal(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void chooseImg() {
        if (goNorth || goSouth) {
            img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animate, 60).getFxImage();
            return;
        }
        if (goEast || goWest) {
            img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animate, 60).getFxImage();
            return;
        }
        img = Sprite.oneal_dead.getFxImage();
    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        }
        super.render(gc);
    }
}
