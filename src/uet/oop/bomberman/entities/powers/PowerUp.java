package uet.oop.bomberman.entities.powers;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;


public abstract class PowerUp extends Entity {
    public PowerUp(double xUnit, double yUnit, Image image){
        super(xUnit, yUnit, image);
    }
}
