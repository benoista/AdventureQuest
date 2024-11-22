package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * {@code Sound} is a class that handles audio playback for the game, supporting background music
 * and sound effects. It allows playing, looping, and stopping audio clips, as well as setting
 * which audio file to use.
 */
public class Sound extends UI {

    // The Clip object used for playing the audio
    Clip clip;

    // Array holding URLs for various sound files
    URL soundURL[] = new URL[30];

    /**
     * Constructor that initializes the sound file URLs.
     * The constructor loads the paths to several sound files, including music, sound effects, and more.
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/sound/music.wav");
        soundURL[1] = getClass().getResource("/sound/coin.wav");
        soundURL[2] = getClass().getResource("/sound/powerup.wav");
        soundURL[3] = getClass().getResource("/sound/unlock.wav");
        soundURL[4] = getClass().getResource("/sound/fanfare.wav");
        soundURL[5] = getClass().getResource("/sound/tp.wav");
        soundURL[6] = getClass().getResource("/sound/dungeon.wav");
        soundURL[7] = getClass().getResource("/sound/merde.wav");
        soundURL[8] = getClass().getResource("/sound/dmgmonster.wav");
        soundURL[13] = getClass().getResource("/sound/loose.wav");
    }

    /**
     * Starts playing the current audio clip.
     */
    public void play() {
        clip.start();
    }

    /**
     * Loops the current audio clip continuously.
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the currently playing audio clip.
     *
     * @param i The index of the sound file (not used for stopping, but part of the method signature).
     */
    public void stop(int i) {
        clip.stop();
    }

    /**
     * Sets the audio file to be played based on the provided index.
     * The index corresponds to a sound file URL in the {@code soundURL} array.
     *
     * @param i The index of the sound file to be set (0 to 6).
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            // Handle exception (e.g., if the file is not found)
        }
    }

    /**
     * Plays the music at the given index and loops it indefinitely.
     *
     * @param i The index of the music to be played (0 to 6).
     */
    public void playMusic(int i) {
        setFile(i);
        play();
        loop();
    }

    /**
     * Stops the music at the given index.
     *
     * @param i The index of the music to stop (0 to 6).
     */
    public void stopMusic(int i) {
        setFile(i);
        stop(i);
    }

    /**
     * Plays a sound effect at the given index.
     *
     * @param i The index of the sound effect to be played (0 to 6).
     */
    public void playSE(int i) {
        setFile(i);
        play();
    }
}
