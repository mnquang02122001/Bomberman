package uet.oop.bomberman.Music;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Music {
    private static MediaPlayer mediaPlayer;
    public static void play(String path) {
        Media h = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

    }
}
