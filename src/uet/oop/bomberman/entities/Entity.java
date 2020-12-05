package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

public abstract class Entity implements IRender {
    protected final int MAX_ANIMATE = 6300;
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected double x;
    public static int width= BombermanGame.WIDTH;
    public static int height= BombermanGame.HEIGHT;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected double y;
    public double xUnit;
    public double yUnit;
    protected boolean alive = true;
    protected boolean goNorth, goSouth, goEast, goWest, moving;
    protected Image img;
    public int value;
    public static int [][]check=new int[height][width];
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
    public void updateLocationX(){
        this.x = xUnit * Sprite.SCALED_SIZE;
        //this.y = yUnit * Sprite.SCALED_SIZE;
        this.value=(int)xUnit*width+(int)yUnit;
    }
    public void updateLocationY(){
        this.y = yUnit * Sprite.SCALED_SIZE;
        //this.y = yUnit * Sprite.SCALED_SIZE;
        this.value=(int)xUnit*width+(int)yUnit;
    }
    public Entity(double xUnit, double yUnit, Image img, boolean bomb) {
        if(bomb==true) {
            this.xUnit = new Double(bombLocation(xUnit));
            this.yUnit = new Double(bombLocation(yUnit));
            updateLocationX();
            updateLocationY();
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
    public boolean checkRightIn(){
        if((int)(xUnit*10)%10==0) return Entity.check[(int)(xUnit)][(int)(yUnit+0.75)]==0;
        return Entity.check[(int)(xUnit)][(int)(yUnit+0.75)]==0 && Entity.check[(int)(xUnit+1)][(int)(yUnit+0.75)]==0;
    }
    /*public boolean checkRightOut(){

        if((int)(xUnit*10)%10!=0) return (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit)] == 0) && (Entity.check[(int) (yUnit + 0.75) + Entity.width * (int) (xUnit + 1)] == 0);
        return true;
    }
    */

    public boolean checkLeftIn(){
        if((int)(xUnit*10)%10==0) return Entity.check[(int)(xUnit)][(int)(yUnit-0.25)]==0;
        return Entity.check[(int)(xUnit)][(int)(yUnit-0.25)]==0&&Entity.check[(int)(xUnit+1)][(int)(yUnit-0.25)]==0;

    }
    /*public boolean checkLeftOut(){

        if((int)(xUnit*10)%10!=0) return Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit)]==0&&Entity.check[(int)(yUnit-0.25)+Entity.width*(int)(xUnit+1)]==0;

        return true;
    }
    */

    public boolean checkUpIn(){
        if((int)(yUnit*10)%10==0||(int)((yUnit-0.25)*10)%10==0) return Entity.check[(int)(xUnit-0.25)][(int)(yUnit)]==0;
        return Entity.check[(int)(xUnit)][(int)(yUnit-0.25)]==0&&Entity.check[(int)(xUnit)][(int)(yUnit+0.5)]==0;
    }
    public boolean checkDownIn(){
        if((int)(yUnit*10)%10==0||(int)((yUnit-0.25)*10)%10==0) return Entity.check[(int)(xUnit+1)][(int)(yUnit)]==0;
        return Entity.check[(int)(xUnit+1)][(int)(yUnit)]==0&&Entity.check[(int)(xUnit+1)][(int)(yUnit+5)]==0;
    }
    public void move() {
        if (isGoNorth()/*&&checkUpOut()*/&&checkUpIn()) {
            setMoving(true);
            xUnit-=0.25;
            updateLocationX();
            return ;
        }
        if (isGoSouth()&&checkDownIn()/*&&checkDownOut()*/) {
            setMoving(true);
            xUnit+=0.25;
            updateLocationX();
            return ;
        }
        if (isGoWest()&&checkLeftIn()/*&&checkLeftOut()*/) {
            setMoving(true);
            yUnit-=0.25;
            updateLocationY();
            return ;
        }
        if (isGoEast()&&checkRightIn()/*&&checkRightOut()*/) {
            setMoving(true);
            yUnit+=0.25;
            updateLocationY();
            return ;
        }
        setMoving(false);
    }


    public void chooseImg(){}
    public abstract void update();

}