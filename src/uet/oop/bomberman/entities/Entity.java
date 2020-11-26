package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.render.IRender;

public abstract class Entity implements IRender {
    protected final int MAX_ANIMATE = 6300;
    //Tọa độ X tính từ góc trái trên trong Canvas
    public int x;
    //Tọa độ Y tính từ góc trái trên trong Canvas
    public int y;
    public boolean running, goNorth, goSouth, goEast, goWest, moving;
    protected Image img;
    protected int animate = 0;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
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

    public abstract void update();
}