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
        ai = new AI();
        alive = true;
    }
    public void randomDirection(){
        int rand= ai.calculateDir();
        if(rand==0) {
            setGoEast(true);
            setGoNorth(false);
            setGoSouth(false);
            setGoWest(false);
        }
        if(rand==1) {
            setGoEast(false);
            setGoNorth(true);
            setGoSouth(false);
            setGoWest(false);
        }
        else if(rand==2) {
            setGoEast(false);
            setGoNorth(false);
            setGoSouth(true);
            setGoWest(false);
        }
        else if(rand==3) {
            setGoEast(false);
            setGoNorth(false);
            setGoSouth(false);
            setGoWest(true);
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

}
