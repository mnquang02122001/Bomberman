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
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemy.*;
import uet.oop.bomberman.entities.powers.PowerUpBombs;
import uet.oop.bomberman.entities.powers.PowerUpFlames;
import uet.oop.bomberman.entities.powers.PowerUpSpeed;
import uet.oop.bomberman.entities.tiles.Brick;
import uet.oop.bomberman.entities.tiles.Gate;
import uet.oop.bomberman.entities.tiles.Grass;
import uet.oop.bomberman.entities.tiles.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static uet.oop.bomberman.entities.Bomber.changeScreen;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static final String MAP_LV1 = "res/levels/Level2.txt";
    public static final String THEME_MUSIC_PATH = "res/music/theme.mp3";
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> listMonster=new ArrayList<>();
    public static List<Entity> listItem=new ArrayList<>();
    private Bomber bomberman;
    private int countGate=1;
    private static boolean isFirst = true;
    private GraphicsContext gc;
    private Canvas canvas;

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
        bomberman = new Bomber(1, 1, Sprite.player_left.getFxImage());
        entities.add(bomberman);
        createMonster();
        createItem();
        Music.play(THEME_MUSIC_PATH);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                stillObjects.addAll(bomberman.getBombList());
                render();
                update();
            }
        };
        timer.start();
        Controller.input(scene, bomberman);


    }
    public void createMonster(){
        Entity first = new Balloon(3, 3, Sprite.balloom_left1.getFxImage());
        Entity second = new Doll(1,1, Sprite.doll_left1.getFxImage());
        Entity third = new Kondoria(5, 5, Sprite.kondoria_left1.getFxImage());
        Entity fourth = new Minvo(7, 7, Sprite.minvo_left1.getFxImage());
        Entity fifth = new Oneal(8, 8, Sprite.oneal_left1.getFxImage());
        entities.add(first);
        entities.add(second);
        entities.add(third);
        entities.add(fourth);
        entities.add(fifth);

        listMonster.add(first);
        listMonster.add(second);
        listMonster.add(third);
        listMonster.add(fourth);
        listMonster.add(fifth);
    }
    public void createItem(){
        Entity Item=new PowerUpBombs(1.000, 2.000, Sprite.powerup_bombs.getFxImage());
        listItem.add(Item);
        entities.add(Item);
    }
    public void createMap(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader(path));
        for (int i = 0; i < HEIGHT; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (s.charAt(j) == '#') {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                    Entity.check[i][j] = 2;


                } else if (s.charAt(j) == '*') {
                    object = new Brick(i, j, Sprite.brick.getFxImage());
                    Entity.check[i][j] = 1;
                } else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                    Entity.check[i][j] = 0;
                }
                Entity.link.put(object.value, object);
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        if(listMonster.isEmpty()){
            if(countGate>0) {
                Entity gate = new Gate();
                listMonster.add(gate);
                entities.add(gate);
                countGate--;
            }
        }
        for(int i=0; i<entities.size(); i++) {
            if (!entities.get(i).isAlive()) {
                if(entities.get(i) instanceof Bomb) {
                    bomberman.bombCount++;
                    entities.remove(entities.get(i));
                }
                else if(entities.get(i) instanceof Brick && ((Brick) entities.get(i)).changement==true) {
                    Random rd = new Random();
                    int random = rd.nextInt() % 3;
                    Entity item;
                    switch (random) {
                        case 0:
                            item=new PowerUpBombs(entities.get(i).xUnit, entities.get(i).yUnit, Sprite.powerup_bombs.getFxImage());
                            break;
                        case 1:
                            item=new PowerUpFlames(entities.get(i).xUnit, entities.get(i).yUnit, Sprite.powerup_flamepass.getFxImage());
                            break;
                        default:
                            item=new PowerUpSpeed(entities.get(i).xUnit, entities.get(i).yUnit, Sprite.powerup_speed.getFxImage());
                            break;


                    }
                    listItem.add(item);
                    entities.remove(i);
                    entities.add(0, item);


                }
                else entities.remove(entities.get(i));

            }

            else entities.get(i).update();
            
        }
        listMonster.removeIf(monster -> !monster.isAlive());

        listItem.removeIf(item -> !item.isAlive());

    }


    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        if(changeScreen==true)
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


}
