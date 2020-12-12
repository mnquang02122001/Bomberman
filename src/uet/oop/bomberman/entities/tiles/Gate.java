package uet.oop.bomberman.entities.tiles;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Gate extends Entity {
    public Gate(){
        super(1.000, 1.000, Sprite.portal.getFxImage());

    }
    @Override
    public void update() {

    }
}
