package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.model.Tegel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
    private JButton btnInstelligen;

    private JPanel pnlSuper;
    private JPanel pnlLeft;
    private JPanel pnlScores;
    private JPanel pnlKnopjes;
    private JPanel pnlSpelbord;

    private Controller controller;

    public SpelUI(Tegel[] tegels, Controller controller) throws HeadlessException{
        super("2048");
        super.setSize(600, 500);
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

    private void maakComponenten() {
        lblTitel = new JLabel("2048");
        lblScore = new JLabel("Score: \n 0");
        lblBest = new JLabel("Best: \n 0");
        //btn maken
        btnMenu = new JButton("Menu");
        btnRanglijst = new JButton("Ranglijst");
        btnInstelligen = new JButton("Instellingen");

    }

    private void maakLayout() {
        /*
        *
        *   Left header
        *
         */

        // Panel super aanmaken
        pnlSuper = new JPanel();
        pnlSuper.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlSuper.setLayout(new BorderLayout(10, 10));
        pnlSuper.setBackground(Color.WHITE);


        // Panel left aanmaken
        pnlLeft = new JPanel();
        pnlLeft.setLayout(new GridLayout(3, 1, 10, 10));
        pnlLeft.setBackground(Color.WHITE);
        pnlLeft.setSize(150, 500);


        //labels

        //fonts voor labels
        Font fTitel = new Font(Font.SANS_SERIF, Font.PLAIN, 40);
        Font fLabel = new Font(Font.SANS_SERIF, Font.PLAIN, 23);
        //lblTitel
        lblTitel.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblTitel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblTitel.setFont(fTitel);
        lblTitel.setVerticalTextPosition(SwingConstants.CENTER);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);


        //lblBest
        lblBest.setFont(fLabel);
        lblBest.setHorizontalAlignment(SwingConstants.CENTER);
        lblBest.setVerticalTextPosition(SwingConstants.CENTER);


        //lblScore
        lblScore.setFont(fLabel);
        lblScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblScore.setVerticalTextPosition(SwingConstants.CENTER);

        //panels

        //pnlScores

        pnlScores = new JPanel();
        pnlScores.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlScores.setLayout(new GridLayout(2, 1, 5, 5));
        pnlScores.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        pnlScores.add(lblScore, BorderLayout.NORTH);
        pnlScores.add(lblBest, BorderLayout.SOUTH);
        pnlScores.setBackground(Color.WHITE);

        //pnlKnopjes
        pnlKnopjes = new JPanel();
        pnlKnopjes.setLayout(new GridLayout(3, 1, 5, 5));

        pnlKnopjes.add(btnMenu, BorderLayout.NORTH);
        pnlKnopjes.add(btnInstelligen, BorderLayout.CENTER);
        pnlKnopjes.add(btnRanglijst, BorderLayout.SOUTH);
        pnlKnopjes.setSize(250, 75);

        //pnlLeft opvullen
        pnlLeft.add(lblTitel);
        pnlLeft.add(pnlScores);
        pnlLeft.add(pnlKnopjes);

        //tijdelijke code om iets te testen


        /*
        *
        *   spelbord
        *
         */

        // Panel spelbord aanmaken en opvullen
        pnlSpelbord = new JPanel();
        pnlSpelbord.setLayout(new GridLayout(Controller.ZIJDEGROOTTE, Controller.ZIJDEGROOTTE, 5, 5));
        pnlSpelbord.setBackground(Color.WHITE);
        for (Tegel tegel : tegels) {
            pnlSpelbord.add(new TegelUI(tegel));
        }


        /*
        *
        *   pnlSuper
        *
         */


        // Alles aan super toevoegen
        pnlSuper.add(pnlLeft, BorderLayout.WEST);
        pnlSuper.add(pnlSpelbord, BorderLayout.CENTER);

        // pnlSuper aan super toevoegen
        super.add(pnlSuper, BorderLayout.CENTER);


    }

    public void updateSpelUI(Tegel[] tegels) {
        this.tegels = tegels;

        pnlSpelbord.removeAll();
        for (Tegel tegel : tegels) {
            pnlSpelbord.add(new TegelUI(tegel));
        }
        pnlSpelbord.updateUI();
    }

    public void setScore(int score) {
        lblScore.setText("Score: " + String.valueOf(score));
    }

    public int getScore() {
        return Integer.valueOf(lblScore.getText());
    }

    private void behandelEvents() {
        super.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("VK_UP");
                    controller.keyUP();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("VK_DOWN");
                    controller.keyDOWN();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("VK_LEFT");
                    controller.keyLEFT();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("VK_RIGHT");
                    controller.keyRIGHT();
                }
            }
        });
        btnInstelligen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.instellingen();
            }
        });
    }
}
