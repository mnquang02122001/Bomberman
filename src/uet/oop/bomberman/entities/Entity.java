package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements IRender {
    protected final int MAX_ANIMATE = 6300;
    public List<Bomb> bombs = new ArrayList<>();
    public static int width=31;
    public static int height=13;
    public double xUnit;
    public double yUnit;
    protected int value;
    protected double x;
    protected double y;
    protected Sprite sprite;
    protected int direction = -1;
    protected boolean moving = false;
    protected boolean alive = true;
    protected int animate = 0;
    public static int[] check=new int[width*height];

    public Entity(double xUnit, double yUnit, Sprite sprite) {
        this.xUnit=xUnit;
        this.yUnit=yUnit;
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.sprite = sprite;
        this.value=(int)(xUnit+0.75)*width+(int)(yUnit+0.75);
    }
    public void updateLocation(){
        x = xUnit * Sprite.SCALED_SIZE;
        y = yUnit * Sprite.SCALED_SIZE;
        value=(int)(xUnit+0.75)*width+(int)(yUnit+0.75);
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(sprite.getFxImage(), y, x);
    }

    public abstract void update();
    public boolean checkUp(){
        return Entity.check[(int)(xUnit-0.25)*Entity.width+(int)(yUnit)]==0;
    }
    public boolean checkSideLeft(){
        return Entity.check[(int)(yUnit)+Entity.width*(int)(xUnit)]==0;
    }
    public boolean checkSideRight(){
        return Entity.check[(int)(yUnit+0.5)+Entity.width*(int)(xUnit)]==0;
    }
    public boolean checkSideUp(){
        return Entity.check[(int)(xUnit)*Entity.width+(int)(yUnit)]==0;
    }
    public boolean checkSideDown(){
        return Entity.check[(int)(xUnit+0.75)*Entity.width+(int)(yUnit)]==0;
    }

    public boolean checkDown(){
        return Entity.check[(int)(xUnit+1)*Entity.width+(int)(yUnit)]==0;
    }

    public boolean checkLeft(){
        return Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit)]==0;
    }

    public boolean checkRight(){
        return Entity.check[(int)(yUnit+0.75)+Entity.width*(int)(xUnit)]==0;
    }

}