package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by Rune on 28/02/2015.
 */
public class GeluidUI {

    private boolean geluid = true;
    private boolean muziek = true;
    private Clip achtergrondMuziek = null;

    public GeluidUI(Controller controller) {
    }

    public void playMove() {
        if (geluid) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        InputStream geluid = getClass().getResourceAsStream("/be/kdg/spel/resources/move.wav");
                        //buffer toegevoegd voor mark/reset support
                        InputStream bufferedGeluid = new BufferedInputStream(geluid);
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedGeluid);

                        clip.open(inputStream);
                        clip.start();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Er is een fout opgetreden bij het inladen van een geluidsfragment! Gelieve het spel opnieuw op te starten", "Fout bij geluidsfragment", JOptionPane.ERROR_MESSAGE, null);
                    }
                }
            }).start();
        }
    }

    public void playMusic() {
        if (muziek) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        InputStream muziek = getClass().getResourceAsStream("/be/kdg/spel/resources/music.wav");
                        InputStream bufferedMuziek = new BufferedInputStream(muziek);
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedMuziek);
                        achtergrondMuziek = AudioSystem.getClip();
                        achtergrondMuziek.open(inputStream);
                        achtergrondMuziek.start();
                        achtergrondMuziek.loop(Clip.LOOP_CONTINUOUSLY);
                        while (achtergrondMuziek.isRunning()) {
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Er is een fout opgetreden bij het inladen van het achtergrondmuziek! Gelieve het spel opnieuw op te starten", "Fout bij achtergrondmuziek", JOptionPane.ERROR_MESSAGE, null);
                    }

                }
            }).start();
        }
    }

    public void stopAchtergrondMuziek() {
        if (achtergrondMuziek != null) {
            achtergrondMuziek.stop();
        }
    }


    public boolean isGeluid() {
        return geluid;
    }

    public boolean isMuziek() {
        return muziek;
    }

    public void setGeluid(boolean geluid) {
        this.geluid = geluid;
    }

    public void setMuziek(boolean muziek) {
        this.muziek = muziek;
    }
}
