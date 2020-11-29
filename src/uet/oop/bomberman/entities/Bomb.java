package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    private int explodeTime = 800;
    public int explodeExistTime = 150;
    private boolean isExplode = false;
    int time=0;
    public Bomb(double xUnit, double yUnit, Image img, boolean bomb){
        super(xUnit, yUnit, img, bomb);
    }

    @Override
    public void render(GraphicsContext gc){
        if(isExplode) {
            if (explodeExistTime>0) {
                explodeExistTime--;
                img = Sprite.bomb_exploded2.getFxImage();
            } else {
                img=Sprite.grass.getFxImage();

            }
        }
        else{
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage();
        }
        super.render(gc);
    }
    @Override
    public void update() {
        int value1 = (int) (xUnit + 1) * width + (int) yUnit;
        int value2 = (int) (xUnit - 1) * width + (int) yUnit;
        int value3 = (int) xUnit * width + (int) yUnit + 1;
        int value4 = (int) xUnit * width + (int) yUnit - 1;
        if(explodeTime > 0){
            isExplode = false;
            explodeTime--;
        }
        else{

            isExplode = true;
            if(explodeExistTime>0) {
                explodeExistTime--;



                if (Entity.check[value1] <= 1) Entity.check[value1] = -1;
                if (Entity.check[value2] <= 1) Entity.check[value2] = -1;
                if (Entity.check[value3] <= 1) Entity.check[value3] = -1;
                if (Entity.check[value4] <= 1) Entity.check[value4] = -1;
            }
            else{
                if (Entity.check[value1] == -1) Entity.check[value1] = 0;
                if (Entity.check[value2] == -1) Entity.check[value2] = 0;
                if (Entity.check[value3] == -1) Entity.check[value3] = 0;
                if (Entity.check[value4] == -1) Entity.check[value4] = 0;

            }

        }
        animate();
    }
}
