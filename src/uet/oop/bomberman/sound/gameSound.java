package uet.oop.bomberman.sound;

import java.util.HashMap;
import java.applet.Applet;
import java.applet.AudioClip;

public class gameSound {
	public static gameSound instance;

	public static final String menu = "menu.mp3";
	public static final String playgame = "playgame.mp3";
	public static final String item = "item.mp3";
	public static final String win = "win.mp3";
	public static final String lose = "lose.mp3";
	public static final String bomb = "bomb.mp3";
	public static final String bomberman_die = "bomberman_die.mp3";
	public static final String monster_die = "monster_die.mp3";
	public static final String bomb_bang = "bomb_bang.mp3";
	

	private HashMap<String, AudioClip> audioMap;

	public gameSound() {
		audioMap = new HashMap<>();
		loadAllAudio();
	}

	public static gameSound getIstance() {
		if (instance == null) {
			instance = new gameSound();
		}
		return instance;
	}

	public void loadAllAudio() {
		putAudio(menu);;
		putAudio(playgame);
		putAudio(item);
		putAudio(win);
		putAudio(lose);
		putAudio(bomb);
		putAudio(monster_die);
		putAudio(bomberman_die);
		putAudio(bomb_bang);
		
	}

	public void stop() {
		getAudio(menu).stop();;
		getAudio(playgame).stop();
		getAudio(win).stop();
		getAudio(lose).stop();
		getAudio(bomb).stop();
		getAudio(bomb_bang).stop();
	}
		

	public void putAudio(String idgame) {
		AudioClip auClip = Applet.newAudioClip(gameSound.class.getResource(idgame));
		audioMap.put(idgame, auClip);
	}

	public AudioClip getAudio(String idgame) {
		return audioMap.get(idgame);
	}
}
