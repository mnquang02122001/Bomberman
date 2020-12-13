package uet.oop.bomberman.entities.tiles;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Brick extends Entity {
    private int random;
    public boolean change;
    Random rd = new Random();
    private int countPresent;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        change = false;
        random = rd.nextInt() % 3;
        countPresent = 1;
    }

    public void update() {

        if (check[value / width][value % width] == -1) {
            img = Sprite.bomb_exploded2.getFxImage();

        } else {
            if (check[value / width][value % width] == 1)
                img = Sprite.brick.getFxImage();
            else {
                if (random == 0) {
                    if (countPresent > 0) {
                        change = true;
                        countPresent--;
                    } else change = false;
                }
                img = Sprite.grass.getFxImage();
            }
            setAlive(false);

        }
    }

}
