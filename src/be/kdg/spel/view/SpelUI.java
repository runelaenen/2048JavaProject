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
    private JLabel lblScoreTitel;
    private JLabel lblBestTitel;
    private JButton btnHerstart;
    private JButton btnRanglijst;
    private JButton btnInstellingen;
    private JPanel pnlSuper;
    private JPanel pnlLeft;
    private JPanel pnlLeftTop;
    private JPanel pnlScoreBest;
    private JPanel pnlScore;
    private JPanel pnlKnopjes;
    private JPanel pnlSpelbord;
    private JPanel pnlEchtSpelbord;
    private Color achtergrondKleur;
    private Controller controller;

    public SpelUI(Controller controller) throws HeadlessException {
        super("2048");
        super.setSize(700, 500);
        super.setFocusable(true);
        super.setLocationRelativeTo(null);
        this.controller = controller;

        super.setBackground(achtergrondKleur);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.achtergrondKleur = new Color(0xfaf8ef);
        this.tegels = controller.getTegelArray();

        this.maakComponenten();
        this.maakLayout();
        this.behandelEvents();
        super.setVisible(true);
    }

    public Color getAchtergrondsKleur() {
        return achtergrondKleur;
    }

    private void maakComponenten() {
        lblTitel = new JLabel("2048");
        lblScore = new JLabel();
        setScore(0);
        lblBest = new JLabel();

        lblScoreTitel = new JLabel("SCORE");
        lblBestTitel = new JLabel("BESTE");

        btnHerstart = new JButton("HERSTART");
        btnRanglijst = new JButton("RANGLIJST");
        btnInstellingen = new JButton("INSTELLINGEN");
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
        pnlSuper.setBackground(achtergrondKleur);


        // Panel left aanmaken
        pnlLeft = new JPanel();
        pnlLeft.setLayout(new BorderLayout(10, 10));
        pnlLeft.setBackground(achtergrondKleur);
        pnlLeft.setOpaque(true);


        // Fonts
        Font fntTitel = controller.getFont().deriveFont(Font.BOLD).deriveFont(64f);
        Font fntLabel = controller.getFont().deriveFont(Font.BOLD).deriveFont(23f);
        Font fntButton = controller.getFont().deriveFont(Font.BOLD).deriveFont(16f);

        //buttons
        //hierdoor gaat de focus niet naar de knopjes als er op wordt geklikt
        btnHerstart.setRequestFocusEnabled(false);
        btnInstellingen.setRequestFocusEnabled(false);
        btnRanglijst.setRequestFocusEnabled(false);

        btnHerstart.setBorder(new EmptyBorder(5,5,5,5));
        btnHerstart.setBackground(new Color(0xED995B));
        btnHerstart.setForeground(new Color(0xffffff));
        btnHerstart.setFont(fntButton);

        btnInstellingen.setBorder(new EmptyBorder(5,5,5,5));
        btnInstellingen.setBackground(new Color(0xED995B));
        btnInstellingen.setForeground(new Color(0xffffff));
        btnInstellingen.setFont(fntButton);

        btnRanglijst.setBorder(new EmptyBorder(5,5,5,5));
        btnRanglijst.setBackground(new Color(0xED995B));
        btnRanglijst.setForeground(new Color(0xffffff));
        btnRanglijst.setFont(fntButton);
        //labels

        //lblTitel
        lblTitel.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblTitel.setBorder(BorderFactory.createLineBorder(new Color(0x7a8a99)));

        lblTitel.setFont(fntTitel);
        lblTitel.setVerticalTextPosition(SwingConstants.CENTER);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setBackground(new Color(0xedc22e));
        lblTitel.setForeground(Color.WHITE);
        lblTitel.setOpaque(true);
        lblTitel.setMinimumSize(new Dimension(200, 200));
        lblTitel.setMaximumSize(new Dimension(200, 200));
        lblTitel.setPreferredSize(new Dimension(200, 200));

        //lblBest
        lblBest.setFont(fntLabel);
        lblBest.setHorizontalAlignment(SwingConstants.CENTER);
        lblBest.setVerticalTextPosition(SwingConstants.CENTER);
        lblBest.setForeground(Color.WHITE);


        //lblScore
        lblScore.setFont(fntLabel);
        lblScore.setHorizontalAlignment(SwingConstants.CENTER);
        lblScore.setVerticalTextPosition(SwingConstants.CENTER);
        lblScore.setForeground(Color.WHITE);

        // lblScore en lblBest Titels
        lblScoreTitel.setFont(fntLabel.deriveFont(14f));
        lblScoreTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblScoreTitel.setVerticalTextPosition(SwingConstants.CENTER);
        lblScoreTitel.setForeground(new Color(0xF8ECDE));

        lblBestTitel.setFont(fntLabel.deriveFont(14f));
        lblBestTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblBestTitel.setVerticalTextPosition(SwingConstants.CENTER);
        lblBestTitel.setForeground(new Color(0xF8ECDE));

        //panels

        //pnlScoreBest

        pnlScoreBest = new JPanel();
        pnlScoreBest.setLayout(new GridLayout(2, 2,5, 5));
        pnlScoreBest.setBorder(BorderFactory.createLineBorder(new Color(0x7a8a99)));
        pnlScoreBest.setBackground(new Color(0xBBADA0));

        pnlScoreBest.add(lblScoreTitel);
        pnlScoreBest.add(lblBestTitel);
        pnlScoreBest.add(lblScore);
        pnlScoreBest.add(lblBest);


        //pnlKnopjes
        pnlKnopjes = new JPanel();
        pnlKnopjes.setLayout(new GridLayout(3, 1, 5, 5));
        pnlKnopjes.setBackground(achtergrondKleur);

        pnlKnopjes.add(btnHerstart, BorderLayout.NORTH);
        pnlKnopjes.add(btnInstellingen, BorderLayout.CENTER);
        pnlKnopjes.add(btnRanglijst, BorderLayout.SOUTH);

        pnlLeftTop = new JPanel(new BorderLayout(10, 10));
        pnlLeftTop.add(lblTitel, BorderLayout.NORTH);
        pnlLeftTop.add(pnlScoreBest, BorderLayout.SOUTH);
        pnlLeftTop.setBackground(achtergrondKleur);
        pnlLeftTop.setOpaque(true);

        //pnlLeft opvullen
        pnlLeft.add(pnlLeftTop, BorderLayout.NORTH);
        pnlLeft.add(pnlKnopjes, BorderLayout.SOUTH);




        /*
        *
        *   spelbord
        *
         */

        // Panel spelbord aanmaken en opvullen
        pnlSpelbord = new JPanel(new GridBagLayout());
        pnlEchtSpelbord = new SquarePanel();
        pnlEchtSpelbord.setLayout(new GridLayout(Controller.ZIJDEGROOTTE, Controller.ZIJDEGROOTTE, 9, 9));
        pnlEchtSpelbord.setBorder(new EmptyBorder(9,9,9,9));


        pnlSpelbord.setBackground(achtergrondKleur);
        pnlEchtSpelbord.setBackground(new Color(0xBCAEA1));

        pnlSpelbord.setOpaque(true);
        pnlEchtSpelbord.setOpaque(true);
        for (Tegel tegel : tegels) {
            pnlEchtSpelbord.add(new TegelUI(tegel, controller));
        }
        pnlSpelbord.add(pnlEchtSpelbord);

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

        pnlEchtSpelbord.removeAll();
        for (Tegel tegel : tegels) {
            pnlEchtSpelbord.add(new TegelUI(tegel, controller));
        }
        pnlEchtSpelbord.updateUI();
    }

    public void setScore(int score) {
        lblScore.setText(String.valueOf(score));
    }

    public void updateBest() {
        lblBest.setText(String.valueOf(controller.getBest()));
    }

    public void refreshBackground() {
        pnlSuper.setBackground(achtergrondKleur);
        pnlSpelbord.setBackground(achtergrondKleur);
        pnlKnopjes.setBackground(achtergrondKleur);
        pnlLeft.setBackground(achtergrondKleur);
        pnlLeftTop.setBackground(achtergrondKleur);

    }

    public void gewonnen() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                Object[] options1 = {"Verder spelen",
                        "Opnieuw spelen",
                        "Stop spel"};

                int antwoord = JOptionPane.showOptionDialog(null,
                        "U bent gewonnen, wat wilt u doen?",
                        "Gewonnen!",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options1,
                        null);

                if (antwoord == 0) {
                    // Verder spelen, dus niets speciaals doen
                } else if (antwoord == 1) {
                    controller.addToHighscore();
                    controller.opnieuw();
                } else if (antwoord == 2) {
                    controller.addToHighscore();
                    controller.resetGameState();
                    System.exit(0);
                }
            }
        });
        t.start();

    }

    public void verloren() {
        Object[] options1 = {"Opnieuw spelen",
                "Stop spel"};

        int antwoord = JOptionPane.showOptionDialog(null,
                "U bent verloren, wat wilt u doen?",
                "Verloren!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options1,
                null);


        if (antwoord == 0) {
            controller.addToHighscore();
            controller.opnieuw();
        } else if (antwoord == 1 || antwoord == -1) {

           controller.addToHighscore();
            controller.resetGameState();
            System.exit(0);
        }
    }

    private void behandelEvents() {
        super.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("VK_UP");
                    controller.geluid().playMove();
                    controller.keyUP();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("VK_DOWN");
                    controller.geluid().playMove();
                    controller.keyDOWN();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("VK_LEFT");
                    controller.geluid().playMove();
                    controller.keyLEFT();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("VK_RIGHT");
                    controller.geluid().playMove();
                    controller.keyRIGHT();
                }
            }
        });
        btnRanglijst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.ranglijst();
            }
        });
        btnInstellingen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.instellingen();
            }
        });
        btnHerstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.opnieuw();
            }
        });
    }


    public void setAchtergrondsKleur(Color kleur) {
        achtergrondKleur = kleur;
        refreshBackground();
    }


}
