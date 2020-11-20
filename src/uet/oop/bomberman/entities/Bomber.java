package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;


public class Bomber extends Entity {
    public Bomber(double xUnit, int yUnit, Sprite sprite) {
        super(xUnit, yUnit, sprite);
    }


    @Override
    public void render(GraphicsContext gc) {
        if (direction == 0) {
            sprite = Sprite.player_up;
            if (moving == 0&&check[down(xUnit)*width+(int)yUnit]==0) {
                xUnit-=0.25;
                updateLocation();
                sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20);

            }
            setMoving(0);
        } else if (direction == 1) {
            sprite = Sprite.player_down;
            if (moving==1&&check[upper(xUnit)*width+(int)yUnit]==0) {
                xUnit+=0.25;
                updateLocation();
                sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20);
            }
            setMoving(1);
        } else if (direction == 2&&check[(int)xUnit*width+down(yUnit)]==0) {
            sprite = Sprite.player_left;
            if (moving==2&&check[(int)yUnit*width+down(xUnit)]==0) {
                yUnit-=0.25;
                updateLocation();
                sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20);
            }
            setMoving(2);
        } else if (direction == 3) {
            sprite = Sprite.player_right;
            if (moving==3&&check[(int)xUnit*width+upper(yUnit)]==0) {
                yUnit+=0.25;
                updateLocation();
                sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20);
            }
            setMoving(3);
        } else {
            sprite = Sprite.player_right;
        }
        gc.drawImage(sprite.getFxImage(), y, x);
    }

    @Override
    public void update() {
        animate();
    }
}
