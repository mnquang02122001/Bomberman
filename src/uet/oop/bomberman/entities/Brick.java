package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void update() {
        if(check[value]==-1) {
            img = Sprite.bomb_exploded2.getFxImage();
        }
        else{
            if(check[value]==1)
                img = Sprite.brick.getFxImage();
            else img=Sprite.grass.getFxImage();
        }
    }

}
