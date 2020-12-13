package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.powers.PowerUpSpeed;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Enemy extends Entity {
    //private static int timeWaiting=10;
    private static int timeMove = 20;
    protected int countDie;
    private boolean isDie;
    private double speedMax;
    private int moveWait;
    private int maxSpeedCount;
    protected AI ai;

    public Enemy(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        ai = new AI();
        alive = true;
        life=1;
        speedMax=0.200;
        moveWait=5;
        maxSpeedCount=4;
        countDie=80;
        isDie=false;
        setMoving(true);
    }

    public void randomDirection() {
        if(isMoving()) {
            int rand = ai.calculateDir();
            if (rand == 0) {
                setGoEast(true);
                setGoNorth(false);
                setGoSouth(false);
                setGoWest(false);
            }
            if (rand == 1) {
                setGoEast(false);
                setGoNorth(true);
                setGoSouth(false);
                setGoWest(false);
            } else if (rand == 2) {
                setGoEast(false);
                setGoNorth(false);
                setGoSouth(true);
                setGoWest(false);
            } else if (rand == 3) {
                setGoEast(false);
                setGoNorth(false);
                setGoSouth(false);
                setGoWest(true);
            }
        }
        else{
            setGoEast(false);
            setGoWest(false);
            setGoSouth(false);
            setGoNorth(false);
        }
    }
    public void meetSpeedUp(){
        for(int i = 0; i< BombermanGame.listItem.size(); i++) {
            double X = xUnit - BombermanGame.listItem.get(i).xUnit;
            double Y = yUnit - BombermanGame.listItem.get(i).yUnit;
            if (X * X + Y * Y <= 0.500 * 0.500) {
                if (BombermanGame.listItem.get(i) instanceof PowerUpSpeed) {
                    if (maxSpeedCount > 0) {
                        speedMax += 0.05;
                        maxSpeedCount--;
                    }
                    BombermanGame.listItem.get(i).setAlive(false);
                }
            }
        }
    }

    public void die(){
        if(countDie>0){
            countDie--;
        }
        else setAlive(false);
    }
    public void update(){


        if(danger){
            if(!checkDanger()){
                isDie=true;
                setMoving(false);
            }
        }
        if(isDie) die();
        if (timeMove > 0) {
            timeMove--;
        } else {
            timeMove = 20;
            randomDirection();
        }
        if(moveWait>0) moveWait--;
        else {
            move(speedMax);
            moveWait=6;
        }
        animate();

    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        }
        super.render(gc);
    }
}
