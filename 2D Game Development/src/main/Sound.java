package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip; // use to open audio files
	URL soundURL[] = new URL[30]; // use to store the file path
	
	public Sound() {
		// accessing the wav / audio files
		soundURL[0] = getClass().getResource("/Sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/Sound/coin.wav");
		soundURL[2] = getClass().getResource("/Sound/powerup.wav");
		soundURL[3] = getClass().getResource("/Sound/unlock.wav");
		soundURL[4] = getClass().getResource("/Sound/fanfare.wav");
	}
	
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); // getting what audio file will be use
			clip = AudioSystem.getClip(); // gets the audio file within the stream or buffer or the waiting area, just my explanation
			clip.open(ais); // then opens it
				
		} catch(Exception e) {
			
		}
	}
	
	public void play() {
		
		clip.start();
	}
	
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		
		clip.stop();
		
	}
	
}
