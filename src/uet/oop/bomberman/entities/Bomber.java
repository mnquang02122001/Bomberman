package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


public class Bomber extends Entity {
    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void choose() {
        if (goNorth) {
            img = Sprite.player_up.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 20).getFxImage();
            }
        }
        if (goSouth) {
            img = Sprite.player_down.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 20).getFxImage();
            }
        }
        if (goWest) {
            img = Sprite.player_left.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 20).getFxImage();
            }
        }
        if (goEast) {
            img = Sprite.player_right.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 20).getFxImage();
            }
        }
        if (!moving) {
            img = Sprite.player_down.getFxImage();
        }
    }

    public void render(GraphicsContext gc) {
        move();
        choose();
        gc.drawImage(img, y, x);
    }

    @Override
    public void move() {
        int dx = 0, dy = 0;
        if (goNorth && !goSouth && !goEast && !goWest) dx -= 1;
        if (goSouth && !goNorth && !goEast && !goWest) dx += 1;
        if (goEast && !goSouth && !goNorth && !goWest) dy += 1;
        if (goWest && !goSouth && !goEast && !goNorth) dy -= 1;
        if (dx != 0 || dy != 0) {
            x += dx;
            y += dy;
            moving = true;
        } else {
            moving = false;
        }
    }

    @Override
    public void update() {
        animate();
    }
}