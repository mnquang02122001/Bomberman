package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


public class Grass extends Entity {
    int time=0;
    private boolean isExplode = false;

    public Grass(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
    public void render(GraphicsContext gc){
        if(check[value]==-1) {
            if (time < 150) {
                time++;
                img = Sprite.bomb_exploded2.getFxImage();
            } else {
                time=0;
            }
        }
        else{
            img = Sprite.grass.getFxImage();
        }
        super.render(gc);
    }
}
