package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public abstract class Enemy extends Entity {
    private static int timeMove=2;
    protected AI ai;
    public Enemy(double xUnit, double yUnit, Image img){
        super(xUnit, yUnit, img);
        alive = true;
    }
    public void randomDirection(){
        Random rd=new Random();
        int rand= rd.nextInt()%4;
        if(rand==0) {
            setGoEast(true);
            setGoNorth(false);
            setGoSouth(false);
            setGoWest(false);
            return ;
        }
        if(rand==1) {
            setGoEast(false);
            setGoNorth(true);
            setGoSouth(false);
            setGoWest(false);
            return ;
        }
        if(rand==2) {
            setGoEast(false);
            setGoNorth(false);
            setGoSouth(true);
            setGoWest(false);
            return ;
        }
        if(rand==3) {
            setGoEast(false);
            setGoNorth(false);
            setGoSouth(false);
            setGoWest(true);
            return ;
        }
    }
    public void update(){

        if(timeMove>0&&isMoving()){
            timeMove--;
        }
        else{
            timeMove=2;
            randomDirection();
        }
        animate();
        move();
    }
    public void render(GraphicsContext gc){
        if(alive){
            chooseImg();
        }
        super.render(gc);
    }

    public void move(){
        super.move();
        /*
        if(alive){
            int x_ = 0, y_ = 0;
            int dir = ai.calculateDir();
            if(dir == 0) {
                x_++;
                goEast = true;
                goWest = false;
                goNorth = false;
                goSouth = false;
            }
            if(dir == 1){
                x_ --;
                goWest = true;
                goEast = false;
                goNorth = false;
                goSouth = false;
            }
            if(dir == 2){
                y_ ++;
                goNorth = true;
                goWest = false;
                goEast = false;
                goSouth = false;
            }
            if(dir == 3){
                y_ --;
                goSouth = true;
                goWest = false;
                goNorth = false;
                goEast = false;
            }
            if(x_ != 0 || y_ != 0){
                x += x_;
                y += y_;
                moving = true;
            }
            else{
                moving = false;
            }
        }

        */
    }
}
