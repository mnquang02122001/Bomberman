package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

public abstract class Entity implements IRender {
    protected final int MAX_ANIMATE = 6300;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    public static int width=31;
    public static int height=13;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    public double xUnit;
    public double yUnit;
    protected boolean alive = true;
    protected boolean goNorth, goSouth, goEast, goWest, moving;
    protected Image img;
    public int value;
    public static int []check=new int[width*height];
    protected int animate = 0;

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

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(double xUnit, double yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.xUnit=xUnit;
        this.yUnit=yUnit;
        this.img = img;
        this.value=(int)xUnit*width+(int)yUnit;
        setAlive(true);
    }
    public void updateLocation(){
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.value=(int)xUnit*width+(int)yUnit;
    }
    public Entity(double xUnit, double yUnit, Image img, boolean bomb) {
        if(bomb==true) {
            this.xUnit = new Double(bombLocation(xUnit));
            this.yUnit = new Double(bombLocation(yUnit));
            updateLocation();
        }

    }
    public double bombLocation(double x){
        if(x-(int)x>=0.5) return (int)x+1;
        return (int)x;
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

    public void move() {
    }
    public boolean checkRightIn(){
        if((int)(xUnit*10)%10==0) return Entity.check[(int)(yUnit+0.75)+Entity.width*(int)(xUnit)]==0;
        return true;
    }
    public boolean checkRightOut(){

        if((int)(xUnit*10)%10!=0) return (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit)] == 0) && (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit + 1)] == 0);
        return true;
    }
    public boolean checkLeftIn(){
        if((int)(xUnit*10)%10==0) return Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit)]==0;
        return true;
    }
    public boolean checkLeftOut(){

        if((int)(xUnit*10)%10!=0) return Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit)]==0&&Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit+1)]==0;

        return true;
    }
    public boolean checkUpIn(){
        if((int)(yUnit*10)%10==0||(int)((yUnit-0.25)*10)%10==0) return Entity.check[(int)(xUnit-0.25)*Entity.width+(int)(yUnit)]==0;
        return true;
    }
    public boolean checkUpOut(){
        if((int)(yUnit*10)%10!=0&&(int)((yUnit-0.25)*10)%10!=0) return Entity.check[(int)(xUnit-0.25)*Entity.width+(int)(yUnit)]==0&&Entity.check[(int)(xUnit-0.25)*Entity.width+(int)(yUnit+0.5)]==0;;
        return true;
    }
    public boolean checkDownIn(){
        if((int)(yUnit*10)%10==0||(int)((yUnit-0.25)*10)%10==0) return Entity.check[(int)(xUnit+1)*Entity.width+(int)(yUnit)]==0;
        return true;
    }
    public boolean checkDownOut(){
        if((int)(yUnit*10)%10!=0||(int)((yUnit-0.25)*10)%10!=0) return Entity.check[(int)(xUnit+1)*Entity.width+(int)(yUnit)]==0&&Entity.check[(int)(xUnit+1)*Entity.width+(int)(yUnit+0.5)]==0;
        return true;
    }

    public abstract void update();

}