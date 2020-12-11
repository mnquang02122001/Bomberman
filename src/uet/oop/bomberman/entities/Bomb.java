package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Entity {
    int once1=0;
    int once2=0;
    int once3=0;
    int once4=0;
    private int explodeTime = 100;
    public int explodeExistTime = 20;
    private boolean isExplode = false;
    public Bomb(double xUnit, double yUnit, Image img, boolean bomb){
        super(xUnit, yUnit, img, bomb);
    }

    @Override
    public void render(GraphicsContext gc){
        if(isExplode) {
            if (explodeExistTime>0) {
                Entity.danger=true;
                explodeExistTime--;
                img = Sprite.bomb_exploded2.getFxImage();
                //System.out.println(Entity.check[value/width][value%width]);
            } else {
                Bomber.danger=false;
                setAlive(false);
                img=Sprite.grass.getFxImage();
            }
        }
        else{
            img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 20).getFxImage();
        }
        super.render(gc);
    }
    @Override
    public void update() {
        int x1 = (int) (xUnit + 1);
        int y = (int) yUnit;
        int x2 = (int) (xUnit - 1);
        int x = (int) xUnit;
        int y1 = (int) yUnit + 1;
        int y2 = (int) yUnit - 1;
        if(explodeTime > 0){
            explodeTime--;
        }
        else{
            isExplode = true;
            if(explodeExistTime>0) {
                if (Entity.check[x1][y] <= 1) {
                    Entity.check[x1][y] = -1;
                    if(once1==0) {
                        once1--;
                        link.get(x1*width+y).setAlive(true);
                        BombermanGame.entities.add(0, link.get(x1 * width + y));
                    }
                }
                if (Entity.check[x2][y] <= 1) {
                    Entity.check[x2][y] = -1;
                    if(once2==0) {
                        once2--;
                        link.get(x2*width+y).setAlive(true);
                        BombermanGame.entities.add(0, link.get(x2 * width + y));
                    }

                }
                if (Entity.check[x][y1] <= 1) {
                    Entity.check[x][y1] = -1;
                    if (once3 == 0) {
                        once3--;
                        link.get(x*width+y1).setAlive(true);
                        BombermanGame.entities.add(0, link.get(x * width + y1));
                    }
                }
                if (Entity.check[x][y2] <= 1){
                    Entity.check[x][y2] = -1;
                    if (once4 == 0) {
                        once4--;
                        link.get(x*width+y2).setAlive(true);
                        BombermanGame.entities.add(0, link.get(x * width + y2));
                    }
                }
                Entity.check[x][y]=-1;
            }
            else{
                System.out.println("hello");
                if (Entity.check[x1][y] <= 1) Entity.check[x1][y] = 0;
                if (Entity.check[x2][y] <= 1) Entity.check[x2][y] = 0;
                if (Entity.check[x][y1] <= 1) Entity.check[x][y1] = 0;
                if (Entity.check[x][y2] <= 1) Entity.check[x][y2] = 0;
                Entity.check[x][y] = 0;


            }

        }
        animate();
    }
}
