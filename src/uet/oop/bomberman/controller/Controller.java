package uet.oop.bomberman.controller;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Controller {
    public static void input(Scene scene, Bomber bomberman) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.setGoNorth(true);
                    if (bomberman.checkUpOut()&&bomberman.checkUpIn()) {
                        bomberman.xUnit-=0.25;
                        bomberman.setMoving(true);
                        bomberman.updateLocation();

                    }
                    break;
                case DOWN:
                    bomberman.setGoSouth(true);
                    if (bomberman.checkDownIn()&&bomberman.checkDownOut()) {
                        bomberman.xUnit+=0.25;
                        bomberman.setMoving(true);
                        bomberman.updateLocation();
                    }
                    break;
                case LEFT:
                    bomberman.setGoWest(true);
                    if (bomberman.checkLeftIn()&&bomberman.checkLeftOut()) {
                        bomberman.yUnit-=0.25;
                        bomberman.setMoving(true);
                        bomberman.updateLocation();
                    }
                    break;
                case RIGHT:
                    bomberman.setGoEast(true);
                    if (bomberman.checkRightIn()&&bomberman.checkRightOut()) {
                        bomberman.yUnit+=0.25;
                        bomberman.setMoving(true);
                        bomberman.updateLocation();
                    }
                    break;
                case SPACE:
                    bomberman.placeBomb(bomberman.getX()/Sprite.SCALED_SIZE, bomberman.getY()/Sprite.SCALED_SIZE );
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.setGoNorth(false);
                    break;
                case DOWN:
                    bomberman.setGoSouth(false);
                    break;
                case LEFT:
                    bomberman.setGoWest(false);
                    break;
                case RIGHT:
                    bomberman.setGoEast(false);
                    break;
            }
        });
    }
}
