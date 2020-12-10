package uet.oop.bomberman.entities.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.powers.PowerUpBombs;
import uet.oop.bomberman.entities.powers.PowerUpFlames;
import uet.oop.bomberman.entities.powers.PowerUpSpeed;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Brick extends Entity {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        changement=false;
        random=rd.nextInt()%8;
    }
    private int random;
    public boolean changement;
    Random rd=new Random();
    public void update() {

        if(check[value/width][value%width]==-1) {
            img = Sprite.bomb_exploded2.getFxImage();

        }
        else{
            if(check[value/width][value%width]==1)
                img = Sprite.brick.getFxImage();
            else {
                if(random==0) changement=false;
                img=Sprite.grass.getFxImage();
            }
            setAlive(false);

        }
    }

}
