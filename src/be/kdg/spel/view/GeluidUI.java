package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.InputStream;

/**
 * Created by Rune on 28/02/2015.
 */
public class GeluidUI {
    private Controller controller;
    private boolean geluid = true;
    private boolean muziek = true;
    private Clip achtergrondMuziek = null;

    public GeluidUI(Controller controller) {
        this.controller = controller;
    }

    //TODO: kan dit niet beter een model worden, het is niet eecht een ui he!
    //Antwoord: nietecht, het hoort eerder bij User Interface dan bij 'het berekenen van game logica'
    public void playMove(){
        if(geluid) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        InputStream thing = GeluidUI.class.getResourceAsStream(".." + File.separator + "resources" + File.separator + "move2.wav");
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
    public void playMusic(){
        if(muziek) {
            System.out.println("muziek speelt");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        AudioInputStream inputStream =
                                AudioSystem.getAudioInputStream(
                                        GeluidUI.class.getResourceAsStream(".." + File.separator + "resources" + File.separator + "music.wav"));
                        achtergrondMuziek = AudioSystem.getClip();
                        achtergrondMuziek.open(inputStream);
                        achtergrondMuziek.start();
                        achtergrondMuziek.loop(Clip.LOOP_CONTINUOUSLY);
                        while (achtergrondMuziek.isRunning()) {
                            Thread.sleep(100);
                        }
                        //achtergrondMuziek = null;
                    } catch (Exception e) {
                        System.out.println("Error met playMusic()");
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void stopAchtergrondMuziek(){
        if(achtergrondMuziek != null){
            achtergrondMuziek.stop();
        }
    }


    public boolean isGeluid() {
        return geluid;
    }

    public boolean isMuziek() {
        return muziek;
    }

    public void setGeluid(boolean geluid){
        this.geluid = geluid;
    }

    public void setMuziek(boolean muziek) {
        this.muziek = muziek;
    }
}
