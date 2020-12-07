package bomberman;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

    static Sounds sound;
    Clip bg=null;
    Clip explosion=null;

    public Sounds() {
        try {
            AudioInputStream bgAudio = AudioSystem.getAudioInputStream(new File("sounds/Battle.mp3"));
            bg = AudioSystem.getClip();
            bg.open(bgAudio);
            AudioInputStream explosionAudio = AudioSystem.getAudioInputStream(new File("sounds/Explosion.mp3"));
            explosion = AudioSystem.getClip();
            explosion.open(explosionAudio);
        } catch (Exception e) {
        }
    }

    public static Sounds getInstance() {
        if (sound == null) {
            sound = new Sounds();
        }
        return sound;
    }

    public void Explosion() {
        explosion.start();
        explosion.close();
    }
}
