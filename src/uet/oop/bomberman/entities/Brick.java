package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    int time=0;
    @Override
    public void update() {

    }
    public void render(GraphicsContext gc){
        if(check[value]==-1) {
            if (time < 150) {
                time++;
                img = Sprite.bomb_exploded2.getFxImage();
            } else {
                check[value]=0;
                time=0;
            }
        }
        else{
            if(check[value]==1)
                img = Sprite.brick.getFxImage();
            else img=Sprite.grass.getFxImage();
        }
        super.render(gc);
    }
}
