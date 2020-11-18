package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements IRender {
    protected final int MAX_ANIMATE = 6300;
    public static int width=31;
    public static int height=13;
    public double xUnit;
    public double yUnit;
    public List<Bomb> bombs = new ArrayList<>();
    protected double x;
    protected double y;
    protected Sprite sprite;
    protected int direction = -1;
    protected int moving = -1;
    protected boolean alive = true;
    protected int animate = 0;
    public int value;
    public static int []check= new int[width * height];

    public Entity(double xUnit, double yUnit, Sprite sprite) {
        this.xUnit=xUnit;
        this.yUnit=yUnit;
        this.value=(int)this.yUnit*width+(int)this.xUnit;
        this.x = this.xUnit * Sprite.SCALED_SIZE;
        this.y = this.yUnit * Sprite.SCALED_SIZE;
        this.sprite = sprite;
    }
    public void updateLocation(){
        this.x = this.xUnit * Sprite.SCALED_SIZE;
        this.y = this.yUnit * Sprite.SCALED_SIZE;
        this.value=(int)this.yUnit*width+(int)this.xUnit;
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

    public double getxUnit() {
        return xUnit;
    }

    public double getyUnit() {
        return yUnit;
    }


    public int getValue() {
        return value;
    }

    public int getMoving() {
        return moving;
    }

    public static int[] getCheck() {
        return check;
    }

    public void setMoving(int moving) {
        this.moving = moving;
    }

    public void setxUnit(double xUnit) {
        this.xUnit = xUnit;
    }

    public void setyUnit(double yUnit) {
        this.yUnit = yUnit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void setCheck(int[] check) {
        Entity.check = check;
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
    public int upper(double a){
        return (a-(double)(int)a==0)?(int)a-1:(int)a;
    }
    public int down(double a){
        return (int)(a+1);
    }
}
