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
    protected final int MAX_ANIMATE = 6300;
    public static boolean danger = false;
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
        if (bomb == true) {
            this.xUnit = new Double(bombLocation(xUnit));
            this.yUnit = new Double(bombLocation(yUnit));
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
        //this.y = yUnit * Sprite.SCALED_SIZE;
        this.value = (int) xUnit * width + (int) yUnit;
    }

    public void updateLocationY() {
        this.y = yUnit * Sprite.SCALED_SIZE;
        //this.y = yUnit * Sprite.SCALED_SIZE;
        this.value = (int) xUnit * width + (int) yUnit;
    }

    public double bombLocation(double x) {
        if (x - (int) x >= 0.5) return (int) x + 1;
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
        if (Math.round((int) xUnit * 1000) / 1000 == xUnit)
            return Entity.check[(int) (xUnit)][(int) (yUnit + 0.75)] == 0;
        return Entity.check[(int) (xUnit)][(int) (yUnit + 0.75)] == 0 && Entity.check[(int) (xUnit + 1)][(int) (yUnit + 0.75)] == 0;
    }
    /*public boolean checkRightOut(){

        if((int)(xUnit*10)%10!=0) return (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit)] == 0) && (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit + 1)] == 0);
        return true;
    }
    */

    public boolean checkLeftIn(double a) {
        if (Math.round((int) xUnit * 1000) / 1000 == xUnit) return Entity.check[(int) (xUnit)][(int) (yUnit - a)] == 0;
        return Entity.check[(int) (xUnit)][(int) (yUnit - a)] == 0 && Entity.check[(int) (xUnit + 1)][(int) (yUnit - a)] == 0;

    }
    /*public boolean checkLeftOut(){

        if((int)(xUnit*10)%10!=0) return Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit)]==0&&Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit+1)]==0;

        return true;
    }
    */

    public boolean checkUpIn(double a) {
        if (Math.round((int) yUnit * 1000) / 1000 <= yUnit && Math.round((int) yUnit * 1000) / 1000 + 0.250 >= yUnit)
            return Entity.check[(int) (xUnit - a)][(int) (yUnit)] == 0;
        return Entity.check[(int) (xUnit - a)][(int) (yUnit)] == 0 && Entity.check[(int) (xUnit - a)][(int) (yUnit + 0.5)] == 0;
    }

    public boolean checkDownIn() {
        if (Math.round((int) yUnit * 1000) / 1000 <= yUnit && Math.round((int) yUnit * 1000) / 1000 + 0.250 >= yUnit)
            return Entity.check[(int) (xUnit + 1)][(int) (yUnit)] == 0;
        return Entity.check[(int) (xUnit + 1)][(int) (yUnit)] == 0 && Entity.check[(int) (xUnit + 1)][(int) (yUnit + 0.5)] == 0;
    }

    public void move(double a) {
        if (isGoNorth()&& checkUpIn(a)) {
            setMoving(true);
            xUnit -= a;
            updateLocationX();
        }
        else if (isGoSouth() && checkDownIn()) {
            setMoving(true);
            xUnit += a;
            updateLocationX();
        }
        else if (isGoWest() && checkLeftIn(a)) {
            setMoving(true);
            yUnit -= a;
            updateLocationY();
        }
        else if (isGoEast() && checkRightIn()) {
            setMoving(true);
            yUnit += a;
            updateLocationY();
        }

    }
    public boolean checkDanger(){
        if(Math.round((int) yUnit * 1000) / 1000 + 0.250 < yUnit)
            return check[(int)xUnit][(int)yUnit]==0&&check[(int)xUnit][(int)(yUnit+1)]==0;
        if(Math.round((int)xUnit*1000)/1000!=xUnit)
            return check[(int)xUnit][(int)yUnit]==0&&check[(int)xUnit+1][(int)(yUnit)]==0;
        return check[(int)xUnit][(int)yUnit]==0;
    }
    public void Wait(){
        if(countToDie>0) countToDie--;
        else waitToDie=false;
    }
    public void die(){
        setAlive(false);
    }


    public void chooseImg() {
    }

    public abstract void update();

}