package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;


public class Bomber extends Entity {
    public Bomber(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public void render(GraphicsContext gc) {
        if (direction == 0) {
            sprite = Sprite.player_up;
            if(alive)
                sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 200);
            setMoving(0);
        } else if (direction == 1) {
            sprite = Sprite.player_down;
            if (alive) {
                sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 200);

            }
            setMoving(1);
        } else if (direction == 2) {
            sprite = Sprite.player_left;
            if (alive) {
                sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 200);
            }
            setMoving(2);
        } else if (direction == 3) {
            sprite = Sprite.player_right;
            if (alive) {
                sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 200);
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