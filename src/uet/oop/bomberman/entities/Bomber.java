package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {
    private List<Bomb> bombList = new ArrayList<>();
    public Bomber(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void addBomb(Bomb bomb){
        bombList.add(bomb);
    }
    public void clearBomb(){
        bombList.clear();
    }

    public List<Bomb> getBombList() {
        return bombList;
    }
    public void placeBomb(int x, int y){
        bombList.add(new Bomb(x, y, Sprite.bomb.getFxImage()));
    }

    public void chooseImg() {
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
        if(alive){
            chooseImg();
        }
        else{
            img = Sprite.player_dead1.getFxImage();
        }
        super.render(gc);
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
        move();
        animate();
    }
}