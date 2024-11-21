package main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for the {@code Sound} class.
 */
public class SoundTest {

    private Sound sound;

    /**
     * Setup method to initialize the Sound object before each test.
     */
    @BeforeEach
    public void setUp() {
        sound = new Sound();
    }

    /**
     * Test to verify that the sound URLs are properly initialized.
     */
    @Test
    public void testSoundURLInitialization() {
        for (int i = 0; i <= 6; i++) {
            assertNotNull(sound.soundURL[i], "Sound URL at index " + i + " should not be null");
        }
    }

    /**
     * Test to ensure that setting a file doesn't throw any exceptions.
     */
    @Test
    public void testSetFile() {
        for (int i = 0; i <= 6; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> sound.setFile(finalI), "Setting file at index " + i + " should not throw an exception");
        }
    }

    /**
     * Test to ensure that playing and stopping a sound doesn't throw any exceptions.
     */
    @Test
    public void testPlayAndStop() {
        // Set a valid sound file before testing play and stop
        sound.setFile(0);

        // Test play method
        assertDoesNotThrow(() -> sound.play(), "Playing sound should not throw an exception");

        // Test stop method
        assertDoesNotThrow(() -> sound.stop(0), "Stopping sound should not throw an exception");
    }

    /**
     * Test to ensure that looping a sound doesn't throw any exceptions.
     */
    @Test
    public void testLoop() {
        // Set a valid sound file before testing loop
        sound.setFile(0);

        // Test loop method
        assertDoesNotThrow(() -> sound.loop(), "Looping sound should not throw an exception");

        // Stop sound after test to clean up
        sound.stop(0);
    }

    /**
     * Test to ensure that playing sound effects doesn't throw any exceptions.
     */
    @Test
    public void testPlaySoundEffect() {
        for (int i = 0; i <= 6; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> sound.playSE(finalI), "Playing sound effect at index " + i + " should not throw an exception");
        }
    }

    /**
     * Test to ensure that playing and stopping music doesn't throw any exceptions.
     */
    @Test
    public void testPlayAndStopMusic() {
        for (int i = 0; i <= 6; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> sound.playMusic(finalI), "Playing music at index " + i + " should not throw an exception");
            int finalI1 = i;
            assertDoesNotThrow(() -> sound.stopMusic(finalI1), "Stopping music at index " + i + " should not throw an exception");
        }
    }
}
