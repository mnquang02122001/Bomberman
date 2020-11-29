package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {
    private List<Bomb> bombList = new ArrayList<>();
    public Bomber(double xUnit, double yUnit, Image img) {
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
    public void placeBomb(double x, double y){
        bombList.add(new Bomb(x, y, Sprite.bomb.getFxImage(), true));
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


    public void move() {

        if (isGoNorth()&&checkUpOut()&&checkUpIn()) {
            xUnit-=0.25;
            setMoving(true);
            updateLocation();
            return ;
        }
        if (isGoSouth()&&checkDownIn()&&checkDownOut()) {
            xUnit+=0.25;
            setMoving(true);
            updateLocation();
            return ;
        }
        if (isGoWest()&&checkLeftIn()&&checkLeftOut()) {
            yUnit-=0.25;
            setMoving(true);
            updateLocation();
            return ;
        }
        if (isGoEast()&&checkRightIn()&&checkRightOut()) {
            yUnit+=0.25;
            setMoving(true);
            updateLocation();
            return ;
        }
    }

    @Override
    public void update() {
        move();
        animate();
    }
}