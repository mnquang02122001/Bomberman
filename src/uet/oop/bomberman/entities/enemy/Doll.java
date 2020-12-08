package uet.oop.bomberman.entities.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy{
    public Doll(double xUnit, double yUnit, Image img){
        super(xUnit, yUnit, img);
    }
    public void chooseImg() {
        if (goNorth || goSouth) {
            img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 60).getFxImage();
            return;
        }
        if (goEast || goWest) {
            img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 60).getFxImage();
            return;
        }
        img = Sprite.doll_dead.getFxImage();
    }
}
