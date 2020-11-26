package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int explodeTime = 300;
    public int explodeExistTime = 10;
    private boolean isExplode = false;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    @Override
    public void render(GraphicsContext gc){
        if(isExplode){
            img = Sprite.bomb_exploded2.getFxImage();
        }
        else{
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage();
        }
        super.render(gc);
    }
    @Override
    public void update() {
        if(explodeTime > 0){
            isExplode = false;
            explodeTime--;
        }
        else{
            isExplode = true;
        }
        animate();
    }
}
