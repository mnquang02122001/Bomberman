package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity implements IRender {

    public static int width = BombermanGame.WIDTH;
    public static int height = BombermanGame.HEIGHT;
    public static int[][] check = new int[height][width];
    public static Map<Integer, Entity> link = new HashMap<>();
    public static boolean danger = false;
    protected final int MAX_ANIMATE = 6300;
    public double xUnit;
    public double yUnit;
    public int value;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    protected boolean alive = true;
    protected boolean goNorth, goSouth, goEast, goWest, moving;
    protected Image img;
    protected int animate = 0;
    protected int countToDie;
    protected boolean waitToDie;
    protected int life;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.img = img;
        this.value = (int) xUnit * width + (int) yUnit;
        setAlive(true);
    }

    public Entity(double xUnit, double yUnit, Image img, boolean bomb) {
        if (bomb) {
            this.xUnit = bombLocation((xUnit + 0.0001d));
            this.yUnit = bombLocation(yUnit + 0.0001d);
            updateLocationX();
            updateLocationY();
            setAlive(true);
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean inDanger() {
        return check[value / width][value % width] != -1;
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

    public boolean isGoNorth() {
        return goNorth;
    }

    public void setGoNorth(boolean goNorth) {
        this.goNorth = goNorth;
    }

    public boolean isGoSouth() {
        return goSouth;
    }

    public void setGoSouth(boolean goSouth) {
        this.goSouth = goSouth;
    }

    public boolean isGoEast() {
        return goEast;
    }

    public void setGoEast(boolean goEast) {
        this.goEast = goEast;
    }

    public boolean isGoWest() {
        return goWest;
    }

    public void setGoWest(boolean goWest) {
        this.goWest = goWest;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void updateLocationX() {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.value = (int) xUnit * width + (int) yUnit;
    }

    public void updateLocationY() {
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.value = (int) xUnit * width + (int) yUnit;
    }

    public double bombLocation(double x) {
        if (x - (int) x >= 0.500) return (int) x + 1.000;
        return (int) x;
    }

    protected void animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, y, x);
    }

    public boolean checkRightIn() {

        if ((int) (((xUnit + 0.0001d)) * 1000) % 1000 == 0) {
            return Entity.check[(int) ((xUnit + 0.0001d))][(int) (yUnit + 0.7501)] == 0;

        }

        return Entity.check[(int) ((xUnit + 0.0001d))][(int) (yUnit + 0.7501d)] == 0 && Entity.check[(int) ((xUnit + 1.0001d))][(int) (yUnit + 0.7501d)] == 0;


    }

    public boolean checkLeftIn(double a) {
        if ((int) (((xUnit + 0.0001d)) * 1000) % 1000 == 0)
            return Entity.check[(int) ((xUnit + 0.0001d))][(int) (yUnit - a + 0.0001d)] == 0;
        return Entity.check[(int) ((xUnit + 0.0001d))][(int) (yUnit - a + 0.0001)] == 0 && Entity.check[(int) ((xUnit + 1.0001d))][(int) (yUnit - a + 0.0001)] == 0;

    }

    public boolean checkUpIn(double a) {
        if ((int) y <= yUnit && yUnit <= (int) (yUnit + 0.0001d) + 0.750d)
            return Entity.check[(int) (xUnit - a)][(int) (yUnit + 0.0001d)] == 0;
        return Entity.check[(int) (xUnit - a)][(int) (yUnit + 0.0001d)] == 0 && Entity.check[(int) (xUnit - a)][(int) (yUnit + 0.7499d)] == 0;
    }

    public boolean checkDownIn() {
        if ((int) (y + 0.0001d) + 0.000d <= yUnit + 0.0001d && yUnit <= (int) (yUnit + 0.0001d) + 0.2501d) {
            return Entity.check[(int) ((xUnit + 1.0001d))][(int) (yUnit + 0.0001d)] == 0;

        }
        return Entity.check[(int) ((xUnit + 1.0001d))][(int) (yUnit + 0.0001d)] == 0 && Entity.check[(int) ((xUnit + 1.0001d))][(int) (yUnit + 0.7499d)] == 0;
    }

    public void move(double speedMax) {
        double speedDown;
        if ((int) (((xUnit + 0.0001d)) * 1000) % 1000 == 0) speedDown = speedMax;
        else speedDown = Math.min(speedMax, 1.000 - xUnit + (int) ((xUnit + 0.0001d)));
        double speedLeft;
        if ((int) ((yUnit + 0.0001d) * 1000) % 1000 == 0)
            speedLeft = speedMax;
        else speedLeft = Math.min(speedMax, yUnit - (int) (yUnit + 0.0001d));
        double speedUp;
        if ((int) (xUnit * 1000) % 1000 == 0) speedUp = speedMax;
        else speedUp = Math.min(speedMax, xUnit - (int) ((xUnit + 0.0001d)));
        double speedRight;
        double value;

        if ((int) (yUnit + 0.0001d) + 0.250d >= yUnit + 0.000d) {
            value = (int) (yUnit + 0.0001d);
        } else value = 1.000d + (int) (yUnit + 0.0001d);
        if ((int) ((yUnit + 0.0001d + 0.750d) * 1000) % 1000 == 0) {
            speedRight = speedMax;
        } else {
            speedRight = Math.min(speedMax, 0.250d - yUnit + value);
        }

        if (isGoNorth()) {

            if (checkUpIn(speedUp)) {
                setMoving(true);
                xUnit -= speedUp;
                updateLocationX();
            }
        } else if (isGoSouth()) {

            if (checkDownIn()) {
                setMoving(true);
                xUnit += speedDown;
                updateLocationX();
            }
        } else if (isGoWest()) {


            if (checkLeftIn(speedLeft)) {
                setMoving(true);
                yUnit -= speedLeft;
                updateLocationY();
            }
        } else if (isGoEast()) {

            if (checkRightIn()) {
                setMoving(true);
                yUnit += speedRight;
                updateLocationY();
            }
        }

    }

    public boolean checkDanger() {
        if ((int) (yUnit + 0.0001d) + 0.250 < yUnit)
            return check[(int) xUnit][(int) yUnit] > -1 && check[(int) xUnit][(int) (yUnit + 1)] > -1;
        if ((int) ((xUnit + 0.0001d)) * 1000 % 1000 != 0)
            return check[(int) xUnit][(int) yUnit] > -1 && check[(int) (xUnit + 0.0001d)][(int) (yUnit + 0.0001d)] > -1;
        return check[(int) xUnit][(int) yUnit] > -1;
    }

    public void Wait() {
        if (countToDie > 0) countToDie--;
        else waitToDie = false;
    }

    public void die() {
        setAlive(false);
    }


    public void chooseImg() {
    }

    public abstract void update();

}