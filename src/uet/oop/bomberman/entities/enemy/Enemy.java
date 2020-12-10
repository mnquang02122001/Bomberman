package uet.oop.bomberman.entities.enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.AI.AI;
import uet.oop.bomberman.entities.Entity;

public abstract class Enemy extends Entity {
    //private static int timeWaiting=10;
    private static int timeMove = 20;
    protected AI ai;

    public Enemy(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        ai = new AI();
        alive = true;
        life=1;

    }

    public void randomDirection() {
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

    public void update() {

        Wait();
        if(danger){
            if(!checkDanger()) die();
        }
        if (timeMove > 0) {
            timeMove--;


        } else {
            timeMove = 20;
            randomDirection();
        }
        move(0.050);
        animate();

    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        }
        super.render(gc);
    }
}
