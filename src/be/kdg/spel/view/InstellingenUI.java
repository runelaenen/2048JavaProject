package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rune on 4/02/2015.
 */
public class InstellingenUI extends JDialog {
    private Controller controller;
    private JButton btnOpslaan;
    private JButton btnReset;
    private JButton btnAnnuleren;
    private JButton btnKleur;
    private JLabel lblAchtergrondKleur;
    private Color kleur;


    public InstellingenUI(Controller controller) throws HeadlessException {
        setTitle("Instellingen");
        setModal(true);
        setSize(350, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        this.controller = controller;
        this.kleur = controller.getAchtergrondsKleur();

        maakComponenten();
        maakLayout();
        behandelEvents();

        setVisible(true);
    }

    private void maakComponenten() {
        //buttons
        btnOpslaan = new JButton("Opslaan");
        btnAnnuleren = new JButton("Annuleren");
        btnReset = new JButton("Reset");
        btnKleur = new JButton("Kies een kleur...");
        //labels
        lblAchtergrondKleur = new JLabel("Achergrondskleur: ");
    }

    private void maakLayout() {
        JPanel pnlSuper = new JPanel(new BorderLayout());
        JPanel pnlInstellingen = new JPanel(new GridLayout(1, 2, 5, 5));
        JPanel pnlKnoppen = new JPanel(new FlowLayout());






        //pnlInstellingen
        pnlInstellingen.setBackground(controller.getAchtergrondsKleur());
        pnlInstellingen.add(lblAchtergrondKleur);
        pnlInstellingen.add(btnKleur);

        //pnlKnoppen
        pnlKnoppen.setBackground(controller.getAchtergrondsKleur());
        pnlKnoppen.add(btnOpslaan);
        pnlKnoppen.add(btnAnnuleren);
        pnlKnoppen.add(btnReset);

        //pnlSuper
        pnlSuper.setBorder(new EmptyBorder(10,10,10,10));
        pnlSuper.setBackground(controller.getAchtergrondsKleur());

        pnlSuper.add(pnlInstellingen, BorderLayout.NORTH);
        pnlSuper.add(pnlKnoppen, BorderLayout.SOUTH);


        add(pnlSuper, BorderLayout.CENTER);

    }

    private void behandelEvents() {

        btnOpslaan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.instellingenOpslaan(kleur);
                dispose();
            }
        });
        btnAnnuleren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnKleur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 kleur = JColorChooser.showDialog(null, "Kies een kleur voor de achtergrond", controller.getAchtergrondsKleur());
            }
        });
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options1 = {"Ja",
                        "Nee"};

                int antwoord = JOptionPane.showOptionDialog(null,
                        "Wilt u de instellingen resetten?",
                        "Reset instellingen!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options1,
                        null);

                if (antwoord == 0) { // Ja
                    controller.instellingenDefault();
                    dispose();
                }
            }
        });

    }


}
