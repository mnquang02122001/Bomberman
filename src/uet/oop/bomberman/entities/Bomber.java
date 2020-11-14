package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.control.Controller;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    /*@Override
    public void inputMove() {
        int _x = 0, _y = 0;
        if(input.up){
            _y --;
        }
        else if(input.down){
            _y ++;
        }
        else if(input.right){
            _x ++;
        }
        else if(input.left){
            _x --;
        }
        move(_x, _y);
        //move(1,1);
    }

    @Override
    public void move(double _x, double _y) {
        x += _x;
        y += _y;
    }*/

    @Override
    public void update() {
    }
}
