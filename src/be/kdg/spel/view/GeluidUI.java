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

    //TODO: kan dit niet beter een model worden, het is niet eecht een ui he!
    public static void playMove(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    InputStream thing = GeluidUI.class.getResourceAsStream("../resources/move2.wav");
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
    public static void playMusic(){

        new Thread(new Runnable() {
            public void run() {
                try {

                   // InputStream audioInput = GeluidUI.class.getResourceAsStream("../resources/music.mp3");
                   // File musicFile = new File(".."+File.separator+"resources"+File.separator+"music.mp3");
                    AudioInputStream inputStream =
                            AudioSystem.getAudioInputStream(
                                    GeluidUI.class.getResourceAsStream(".."+File.separator+"resources"+File.separator+"music.wav"));
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("Error met playMusic()");
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
