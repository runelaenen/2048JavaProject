package be.kdg.spel.view;

import be.kdg.spel.controller.SpelController;
import be.kdg.spel.model.Tegel;
import javax.swing.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class SpelScherm extends JFrame {

    private Tegel[] tegels = new Tegel[SpelController.BORDGROOTTE];

    public SpelScherm() {
        super("2048");
        super.setSize(400,500);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.maakComponenten();
        this.maakLayout();
        this.behandelEvents();
        super.setVisible(true);
    }

    private void maakComponenten(){

    }

    private void maakLayout() {

    }

    private void behandelEvents()
    {

    }
}
