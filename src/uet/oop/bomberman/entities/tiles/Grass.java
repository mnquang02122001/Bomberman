package uet.oop.bomberman.entities.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Grass extends Entity {
    private boolean isExplode = false;

    public Grass(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(check[value/width][value%width]==-1) {
            img = Sprite.bomb_exploded2.getFxImage();
        }
        else{
            img = Sprite.grass.getFxImage();
        }
    }

}
