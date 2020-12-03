package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Music.Music;
import uet.oop.bomberman.controller.Controller;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.enemy.Balloon;
import uet.oop.bomberman.entities.tiles.Brick;
import uet.oop.bomberman.entities.tiles.Grass;
import uet.oop.bomberman.entities.tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final String MAP_LV1 = "res/levels/Level1.txt";
    public static final String THEME_MUSIC_PATH = "res/music/theme.mp3";
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
        createMap(MAP_LV1);
        Bomber bomberman = new Bomber(1.00, 1.00, Sprite.player_left.getFxImage());
        entities.add(bomberman);
        entities.add(new Balloon(3, 3, Sprite.balloom_left1.getFxImage()));
        Controller.input(scene, bomberman);
        Music.play(THEME_MUSIC_PATH);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //stillObjects.addAll(bomberman.getBombList());
                render();
                update();
            }
        };
        timer.start();
    }

    public void createMap(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(path));
        for (int i = 0; i < HEIGHT; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (s.charAt(j) == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    Entity.check[i*WIDTH+j]=2;

                } else if (s.charAt(j) == '*') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    Entity.check[i*WIDTH+j]=1;
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    Entity.check[i*WIDTH+j]=0;
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        for (Entity stillObject : stillObjects) {
            stillObject.update();
            //if(!stillObject.isAlive()) stillObjects.remove(stillObject);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }


}
