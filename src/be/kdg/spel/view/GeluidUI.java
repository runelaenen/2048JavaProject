package be.kdg.spel.view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Rune on 28/02/2015.
 */
public class GeluidUI {


    public static void playMove(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();

                    InputStream thing = GeluidUI.class.getResourceAsStream("../resources/move.wav");

                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(thing);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("Error met playMove()");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}