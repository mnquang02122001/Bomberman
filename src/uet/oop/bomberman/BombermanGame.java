package uet.oop.bomberman;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import uet.oop.bomberman.controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = Entity.width;
    public static final int HEIGHT = Entity.height;
    public static final String MAP_LV1 = "res/levels/Level1.txt";
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

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        createMap(MAP_LV1);
        Entity bomberman = new Bomber(1, 1, Sprite.player_right);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        entities.add(bomberman);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        bomberman.setDirection(0);
                        if (bomberman.getMoving()==0&&Entity.check[(int)(bomberman.xUnit-0.25)*Entity.width+(int)(bomberman.yUnit)]==0) {
                            bomberman.xUnit-=0.25;
                            bomberman.updateLocation();

                        }
                        break;
                    case DOWN:
                        bomberman.setDirection(1);
                        if (bomberman.getMoving()==1&&Entity.check[(int)(bomberman.xUnit+1)*Entity.width+(int)(bomberman.yUnit)]==0) {
                            bomberman.xUnit+=0.25;
                            bomberman.updateLocation();

                        }
                        break;
                    case LEFT:
                        bomberman.setDirection(2);
                        if (bomberman.getMoving()==2&&Entity.check[(int)(bomberman.yUnit-0.25)+Entity.width*(int)(bomberman.xUnit)]==0) {
                            bomberman.yUnit-=0.25;
                            bomberman.updateLocation();
                            System.out.print(Entity.check[bomberman.getValue()]);
                        }
                        break;
                    case RIGHT:
                        bomberman.setDirection(3);
                        if (bomberman.getMoving()==3&&Entity.check[bomberman.getValue()]==0) {
                            bomberman.yUnit+=0.25;
                            bomberman.updateLocation();
                            System.out.print(Entity.check[bomberman.getValue()]);

                        }

                        break;
                    case SHIFT:  break;
                }
            }
        });
    }


    public void createMap(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(path));
        for (int i = 0; i < HEIGHT; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (s.charAt(j) == '#') {
                    object = new Wall(i, j, Sprite.wall);
                    Entity.check[object.getValue()]=2;

                } else if (s.charAt(j) == '*') {
                    object = new Brick(i, j, Sprite.brick);
                    Entity.check[object.getValue()]=1;

                } else {
                    object = new Grass(i, j, Sprite.grass);
                    Entity.check[object.getValue()]=0;
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
        ;
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }




}
