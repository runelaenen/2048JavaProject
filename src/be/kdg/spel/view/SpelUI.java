package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;
import be.kdg.spel.model.Tegel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

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
    private JPanel pnlKnopjes;
    private JPanel pnlSpelbord;
    private JPanel pnlEchtSpelbord;
    private Color achtergrondKleur;
    private Controller controller;
    private ImageIcon icnSpel;
    private ImageIcon icnGewonnen;
    private ImageIcon icnVerloren;
    private JMenuBar menuBar;
    private JMenu mnuSpel, mnuHelp;
    private JMenuItem mnuiNieuwSpel, mnuiAfsluiten, mnuiInstellingen, mnuiSpelregels, mnuiInfo, mnuiCheats;


    public SpelUI(Controller controller) throws HeadlessException {
        super("2048");
        super.setSize(700, 500);
        super.setMinimumSize(new Dimension(645, 455));
        super.setLocationRelativeTo(null);
        this.controller = controller;
        icnSpel = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/icnSpel.png"));
        super.setIconImage(icnSpel.getImage());
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

    public void setAchtergrondsKleur(Color kleur) {
        achtergrondKleur = kleur;
        refreshBackground();
    }

    private void maakComponenten() {
        lblTitel = new JLabel("2048");
        lblScore = new JLabel("0");
        lblBest = new JLabel("0");

        lblScoreTitel = new JLabel("SCORE");
        lblBestTitel = new JLabel("BESTE");

        btnHerstart = new JButton("HERSTART");
        btnRanglijst = new JButton("RANGLIJST");
        btnInstellingen = new JButton("INSTELLINGEN");

        icnGewonnen = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/icnWin.png"));
        icnVerloren = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/icnVerloren.png"));

        menuBar = new JMenuBar();
        mnuSpel = new JMenu("Spel");
        mnuHelp = new JMenu("Help");

        mnuiNieuwSpel = new JMenuItem("Nieuw Spel");
        mnuiAfsluiten = new JMenuItem("Afsluiten");
        mnuiCheats = new JMenuItem("Cheat venster");
        mnuiInfo = new JMenuItem("Info");
        mnuiInstellingen = new JMenuItem("Instellingen");
        mnuiSpelregels = new JMenuItem("Spelregels");


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
        btnHerstart.setFocusable(false);
        btnInstellingen.setFocusable(false);
        btnRanglijst.setFocusable(false);


        btnHerstart.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnHerstart.setBackground(new Color(0xED995B));
        btnHerstart.setForeground(new Color(0xffffff));
        btnHerstart.setFont(fntButton);

        btnInstellingen.setBorder(new EmptyBorder(5, 5, 5, 5));
        btnInstellingen.setBackground(new Color(0xED995B));
        btnInstellingen.setForeground(new Color(0xffffff));
        btnInstellingen.setFont(fntButton);

        btnRanglijst.setBorder(new EmptyBorder(5, 5, 5, 5));
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

        /*
        *       Menubalk
        *
         */
        //Toevoegen van menu's aan menubalk
        menuBar.add(mnuSpel);
        menuBar.add(mnuHelp);


        //menuItems eigenschappen
        mnuiNieuwSpel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        mnuiNieuwSpel.getAccessibleContext().setAccessibleDescription("Begin opnieuw");

        mnuiCheats.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        mnuiCheats.getAccessibleContext().setAccessibleDescription("Open het cheat venster");

        mnuiInstellingen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnuiInstellingen.getAccessibleContext().setAccessibleDescription("Open het instellingen venster");

        mnuiAfsluiten.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        mnuiAfsluiten.getAccessibleContext().setAccessibleDescription("Sluit het spel");

        mnuiSpelregels.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        mnuiSpelregels.getAccessibleContext().setAccessibleDescription("Bekijk de spelregels");

        mnuiInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        mnuiInfo.getAccessibleContext().setAccessibleDescription("Bekijk de credits");

        //toevoegen van menuItems
        mnuSpel.add(mnuiNieuwSpel);
        mnuSpel.addSeparator();

        mnuSpel.add(mnuiCheats);
        mnuSpel.add(mnuiInstellingen);

        mnuSpel.addSeparator();
        mnuSpel.add(mnuiAfsluiten);

        mnuHelp.add(mnuiSpelregels);
        mnuHelp.add(mnuiInfo);


        super.setJMenuBar(menuBar);


        //panels

        //pnlScoreBest

        pnlScoreBest = new JPanel();
        pnlScoreBest.setLayout(new GridLayout(2, 2, 5, 5));
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
        pnlEchtSpelbord.setBorder(new EmptyBorder(9, 9, 9, 9));


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
        if (Integer.valueOf(lblScore.getText()) > Integer.valueOf(lblBest.getText())) {
            lblBest.setText(lblScore.getText());
        }
    }

    public void refreshBackground() {
        pnlSuper.setBackground(achtergrondKleur);
        pnlSpelbord.setBackground(achtergrondKleur);
        pnlKnopjes.setBackground(achtergrondKleur);
        pnlLeft.setBackground(achtergrondKleur);
        pnlLeftTop.setBackground(achtergrondKleur);

    }

    public void gewonnen() {
        controller.geluid().playFirework();
        Object[] options1 = {"Verder spelen",
                "Opnieuw spelen",
                "Stop spel"};

        int antwoord = JOptionPane.showOptionDialog(null,
                "U bent gewonnen, wat wilt u doen?",
                "Gewonnen!",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icnGewonnen,
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

    public void verloren() {
        Object[] options1 = {"Opnieuw spelen",
                "Stop spel"};

        int antwoord = JOptionPane.showOptionDialog(null,
                "U bent verloren, wat wilt u doen?",
                "Verloren!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icnVerloren,
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
                if (e.getKeyCode() == KeyEvent.VK_UP ||e.getKeyCode() == KeyEvent.VK_Z) {

                    controller.geluid().playMove();
                    controller.keyUP();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN ||e.getKeyCode() == KeyEvent.VK_S) {

                    controller.geluid().playMove();
                    controller.keyDOWN();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT ||e.getKeyCode() == KeyEvent.VK_Q) {

                    controller.geluid().playMove();
                    controller.keyLEFT();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ||e.getKeyCode() == KeyEvent.VK_D) {

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
        mnuiNieuwSpel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.opnieuw();
            }
        });
        mnuiCheats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cheat = "";
                boolean vragen = true;
                while (vragen) {
                    cheat = (String) JOptionPane.showInputDialog(
                            null,
                            "Welkom op het cheat-screen gij deugeniet!\nVul hieronder uw cheat code in",
                            "Cheat FTW",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "Vul u code in of druk nu op ok om te sluiten");

                    if (cheat != null) {
                        if (!cheat.isEmpty()) {
                            vragen = false;
                        }
                    }
                }

                switch (cheat) {
                    case "":
                        break;
                    case "letmewin":
                        gewonnenCheat();
                        break;
                }
            }
        });
        mnuiInstellingen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.instellingen();
            }
        });
        mnuiAfsluiten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuiInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:dit zou het infoSplashScreen moeten tonen , maar het is niet zichtbaar
                new SplashScreenInfo();


            }
        });
        mnuiSpelregels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SpelregelsUI(controller);
            }
        });
    }

    public void gewonnenCheat() {
        controller.setTegelWaardeCheat();
        updateSpelUI(tegels);
        controller.geluid().playFirework();

        Object[] options1 = {
                "Opnieuw spelen",
                "Stop spel"};

        int antwoord = JOptionPane.showOptionDialog(null,
                "U bent gewonnen met behulp van cheat! Wat wilt u nu doen?",
                "Gewonnen!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icnGewonnen,
                options1,
                null);
        if (antwoord == 0) {
            controller.addToHighscore(99999);
            controller.opnieuw();
        } else if (antwoord == 1) {
            controller.addToHighscore(99999);
            controller.resetGameState();
            System.exit(0);
        }
    }


}
