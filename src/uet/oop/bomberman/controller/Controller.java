package uet.oop.bomberman.controller;

import javafx.scene.Scene;
import uet.oop.bomberman.entities.Entity;

public class Controller {
    public static void input(Scene scene, Entity bomberman) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.goNorth = true;
                    break;
                case DOWN:
                    bomberman.goSouth = true;
                    break;
                case LEFT:
                    bomberman.goWest = true;
                    break;
                case RIGHT:
                    bomberman.goEast = true;
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    bomberman.goNorth = false;
                    break;
                case DOWN:
                    bomberman.goSouth = false;
                    break;
                case LEFT:
                    bomberman.goWest = false;
                    break;
                case RIGHT:
                    bomberman.goEast = false;
                    break;
            }
        });
    }
}
