package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rune on 4/02/2015.
 */
public class RanglijstUI extends JDialog {
    private Controller controller;
    private Color achtergrondKleur;

    private JButton btnResetten;
    private JLabel lblTitel;
    private JLabel[] lblNaam;
    private JLabel[] lblScore;


    public RanglijstUI(Controller controller) {
        setModal(true);
        setSize(500, 500);
        setTitle("Ranglijst");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.achtergrondKleur = controller.getAchtergrondsKleur();

        this.controller = controller;

        maakComponenten();
        maakLayout();
        behandelEvents();


        setVisible(true);

    }

    private void maakComponenten() {
        btnResetten = new JButton("Resetten");
        lblTitel = new JLabel();
        lblNaam = new JLabel[10];
        lblScore = new JLabel[10];
    }

    private void maakLayout() {

        // Panels
        JPanel pnlSuper = new JPanel(new BorderLayout(5, 5));
        JPanel pnlNaamScores = new JPanel(new BorderLayout(5, 5));
        JPanel pnlNamen = new JPanel(new GridLayout(10, 1));
        JPanel pnlScores = new JPanel(new GridLayout(10, 1));

        pnlSuper.setBackground(achtergrondKleur);
        pnlNamen.setBackground(achtergrondKleur);
        pnlScores.setBackground(achtergrondKleur);
        pnlNaamScores.setBackground(achtergrondKleur);
        pnlNaamScores.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlNaamScores.add(pnlNamen, BorderLayout.WEST);
        pnlNaamScores.add(pnlScores, BorderLayout.EAST);
        pnlSuper.add(pnlNaamScores, BorderLayout.CENTER);

        super.add(pnlSuper);

        // Fonts
        Font fntTitel = controller.getFont().deriveFont(52f);
        Font fntScore = controller.getFont().deriveFont(23f);

        // Titel
        lblTitel.setText("RANGLIJST");
        lblTitel.setVerticalAlignment(SwingConstants.CENTER);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setFont(fntTitel);
        lblTitel.setBackground(new Color(0xedc22e));
        lblTitel.setOpaque(true);
        pnlSuper.add(lblTitel, BorderLayout.NORTH);

        // resetknop
        pnlSuper.add(btnResetten, BorderLayout.SOUTH);

        //lblNaam en lblScore invullen
        int index = 0;
        for (String[] regel : controller.getHighscoreList()) {
            if (index < 10) {
                lblNaam[index] = new JLabel(regel[0]);
                lblScore[index] = new JLabel(regel[1], SwingConstants.RIGHT);

                index++;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (lblNaam[i] != null) { // check of er écht een label in de arrayplaats zit
                lblNaam[i].setFont(fntScore);
                lblScore[i].setFont(fntScore);

                pnlNamen.add(lblNaam[i]);
                pnlScores.add(lblScore[i]);
            }
        }

    }

    private void behandelEvents() {
        JDialog ranglijstDialoog = this;
        btnResetten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options1 = {"Ja",
                        "Nee"};

                int antwoord = JOptionPane.showOptionDialog(null,
                        "Wilt u zeker de ranglijst resetten?",
                        "Reset highscore!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options1,
                        null);

                if (antwoord == 0) { // Ja
                    controller.resetHighscore();
                    ranglijstDialoog.dispose();
                }
            }
        });
    }


}
