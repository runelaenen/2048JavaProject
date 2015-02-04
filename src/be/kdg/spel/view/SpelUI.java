package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.model.Tegel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Rune on 4/02/2015.
 */
public class SpelUI extends JFrame {

    private Tegel[] tegels;
    private JLabel lblTitel;
    private JLabel lblScore;
    private JLabel lblBest;

    private JButton btnMenu;
    private JButton btnRanglijst;

    private JPanel pnlSuper;
    private JPanel pnlHeader;
    private JPanel pnlHeaderScore;
    private JPanel pnlHeaderBest;
    private JPanel pnlSpelbord;

    private Controller controller;

    public SpelUI(Tegel[] tegels, Controller controller) {
        super("2048");
        super.setSize(400, 500);
        super.setFocusable(true);
        super.setLocationRelativeTo(null);
        super.setBackground(new Color(0xfaf8ef));
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.tegels = tegels;
        this.controller = controller;

        this.maakComponenten();
        this.maakLayout();
        this.behandelEvents();
        super.setVisible(true);
    }

    private void maakComponenten(){
        lblTitel = new JLabel("2048");
        lblScore = new JLabel("Score: \n 0");
        lblBest = new JLabel("Best: \n 0");

        btnMenu = new JButton("MENU");
        btnRanglijst = new JButton("RANGLIJST");
    }

    private void maakLayout() {
        /*
        *
        *   header
        *
         */

        // Panel super aanmaken
        pnlSuper = new JPanel();
        pnlSuper.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlSuper.setLayout(new BorderLayout(10,10));

        // Panel header aanmaken en opvullen
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new GridLayout(1,3,10,10));


        // Panels in header aanmaken
        pnlHeaderScore = new JPanel();
        pnlHeaderScore.setLayout(new BorderLayout(0,5));
        pnlHeaderBest = new JPanel();
        pnlHeaderBest.setLayout(new BorderLayout(0,5));

        pnlHeaderScore.add(lblScore, BorderLayout.CENTER);
        pnlHeaderScore.add(btnMenu, BorderLayout.SOUTH);

        pnlHeaderBest.add(lblBest, BorderLayout.CENTER);
        pnlHeaderBest.add(btnRanglijst, BorderLayout.SOUTH);


        pnlHeader.add(lblTitel);
        pnlHeader.add(pnlHeaderScore);
        pnlHeader.add(pnlHeaderBest);




        /*
        *
        *   spelbord
        *
         */

        // Panel spelbord aanmaken en opvullen
        pnlSpelbord = new JPanel();
        pnlSpelbord.setLayout(new GridLayout(Controller.ZIJDEGROOTTE, Controller.ZIJDEGROOTTE,5,5));

        for(Tegel tegel : tegels){
            pnlSpelbord.add(new TegelUI(tegel));
        }


        /*
        *
        *   pnlSuper
        *
         */


        // Alles aan super toevoegen
        pnlSuper.add(pnlHeader, BorderLayout.NORTH);
        pnlSuper.add(pnlSpelbord, BorderLayout.CENTER);

        // pnlSuper aan super toevoegen
        super.add(pnlSuper, BorderLayout.CENTER);
    }

    private void behandelEvents()
    {
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                    controller.keyUP();
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    controller.keyDOWN();
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    controller.keyLEFT();
                } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    controller.keyRIGHT();
                }
            }
        });
    }
}
