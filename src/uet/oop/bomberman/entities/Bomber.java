package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {
    public static boolean danger = false;
    private List<Bomb> bombList = new ArrayList<>();
    private int life;

    public Bomber(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        life = 3;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void addBomb(Bomb bomb) {
        bombList.add(bomb);
    }

    public void clearBomb() {
        bombList.clear();
    }

    public void placeBomb(double x, double y) {
        if (bombList.isEmpty())
            bombList.add(new Bomb(x, y, Sprite.bomb.getFxImage(), true));
    }

    @Override
    public void chooseImg() {
        if (goNorth) {
            img = Sprite.player_up.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goSouth) {
            img = Sprite.player_down.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goWest) {
            img = Sprite.player_left.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goEast) {
            img = Sprite.player_right.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 10).getFxImage();
            }
            return;
        }

        img = Sprite.player_down.getFxImage();

    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        } else {
            img = Sprite.player_dead1.getFxImage();
        }
        super.render(gc);
    }

    @Override
    public void update() {
        move(0.125);
        animate();
    }

}