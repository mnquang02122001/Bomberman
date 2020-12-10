package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.powers.PowerUpBombs;
import uet.oop.bomberman.entities.powers.PowerUpFlames;
import uet.oop.bomberman.entities.powers.PowerUpSpeed;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bomber extends Entity {

    private List<Bomb> bombList = new ArrayList<>();
    boolean maxSpeed;
    public double speed;
    public int bombCount;
    public int flameCount;
    public Bomber(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        life = 3;
        waitToDie=true;
        countToDie=200;
        speed=0.125;
        bombCount=1;
        flameCount=1;
        maxSpeed=false;
    }

    public List<Bomb> getBombList() {
        return bombList;
    }

    public void addBomb(Bomb bomb) {
        bombList.add(bomb);
    }

    public void clearBomb() {
        bombList.clear();
    }

    public void placeBomb(double x, double y) {
        if (bombList.isEmpty())
            bombList.add(new Bomb(x, y, Sprite.bomb.getFxImage(), true));
    }

    @Override
    public void chooseImg() {
        if (goNorth) {
            img = Sprite.player_up.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goSouth) {
            img = Sprite.player_down.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goWest) {
            img = Sprite.player_left.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, animate, 10).getFxImage();
            }
            return;
        }
        if (goEast) {
            img = Sprite.player_right.getFxImage();
            if (moving) {
                img = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, animate, 10).getFxImage();
            }
            return;
        }

        img = Sprite.player_down.getFxImage();

    }

    public void render(GraphicsContext gc) {
        if (alive) {
            chooseImg();
        } else {
            img = Sprite.player_dead1.getFxImage();
        }
        super.render(gc);
    }
    boolean checkMeetEnemy(){
        for(int i=0; i< BombermanGame.listMonster.size(); i++){
            double X = xUnit-BombermanGame.listMonster.get(i).xUnit;
            double Y = yUnit-BombermanGame.listMonster.get(i).yUnit;
            if(X*X+Y*Y<=0.500*0.500) return true;
        }
        return false;
    }
    void meetItem(){
        for(int i=0; i< BombermanGame.listItem.size(); i++){
            double X = xUnit-BombermanGame.listItem.get(i).xUnit;
            double Y = yUnit-BombermanGame.listItem.get(i).yUnit;
            if(X*X+Y*Y<=0.500*0.500) {
                if(BombermanGame.listItem.get(i) instanceof PowerUpBombs) {
                    bombCount++;
                    System.out.println(BombermanGame.listItem.get(i) instanceof PowerUpBombs?100:0);
                }
                else if(BombermanGame.listItem.get(i) instanceof PowerUpFlames)
                    flameCount++;
                else if(BombermanGame.listItem.get(i) instanceof PowerUpSpeed&&!maxSpeed) {
                    speed += 0.125;
                    maxSpeed = false;
                }
                BombermanGame.listItem.get(i).setAlive(false);
            }

        }

    }


    public void reset(){
        xUnit=1;
        yUnit=1;
        updateLocationX();
        updateLocationY();
    }
    public void die(){
        if(life>0){

            if(waitToDie==false){
                life--;
                waitToDie=true;
                countToDie=200;
                reset();
            }
            //System.out.println(life+"/");
        }

        else setAlive(false);
    }

    @Override
    public void update() {
        Wait();
        if(danger){
            if(!checkDanger()) die();
        }
        if(checkMeetEnemy()) die();
        meetItem();
        move(speed);
        animate();
    }

}