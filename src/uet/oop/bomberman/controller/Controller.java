package uet.oop.bomberman.controller;

import javafx.scene.Scene;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Controller {
    public static void input(Scene scene, Bomber bomberman) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.setGoNorth(true);

                    break;
                case DOWN:
                    bomberman.setGoSouth(true);

                    break;
                case LEFT:
                    bomberman.setGoWest(true);

                    break;
                case RIGHT:
                    bomberman.setGoEast(true);

                    break;
                case SPACE:
                    if (bomberman.bombCount > 0) {
                        BombermanGame.entities.add(new Bomb(bomberman.xUnit, bomberman.yUnit, Sprite.bomb.getFxImage(), true));
                        bomberman.bombCount--;
                    }
                    break;

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
