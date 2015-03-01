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
    private JLabel lblGeluid;
    private JCheckBox cboGeluid;
    private JLabel lblMuziek;
    private JCheckBox cboMuziek;



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
    public boolean geluidAan(){
        if(cboGeluid.isSelected()){
            return true;
        }
        return false;
    }

    private void maakComponenten() {
        //buttons
        btnOpslaan = new JButton("Opslaan");
        btnAnnuleren = new JButton("Annuleren");
        btnReset = new JButton("Reset");
        btnKleur = new JButton("Kies een kleur...");
        //labels
        lblAchtergrondKleur = new JLabel("Achergrondskleur: ");
        lblGeluid = new JLabel("Geluid");
        lblMuziek = new JLabel("Muziek");

        //checkboxes
        cboGeluid = new JCheckBox();
        cboMuziek = new JCheckBox();

    }

    private void maakLayout() {
        JPanel pnlSuper = new JPanel(new BorderLayout());
        JPanel pnlInstellingen = new JPanel(new GridLayout(3, 2, 5, 5));
        JPanel pnlKnoppen = new JPanel(new FlowLayout());






        //pnlInstellingen
        pnlInstellingen.setBackground(controller.getAchtergrondsKleur());
        pnlInstellingen.add(lblAchtergrondKleur);
        pnlInstellingen.add(btnKleur);
        pnlInstellingen.add(lblGeluid);
        pnlInstellingen.add(cboGeluid);
        pnlInstellingen.add(lblMuziek);
        pnlInstellingen.add(cboMuziek);

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
        //checkboxen
        cboGeluid.setSelected(true);
        cboMuziek.setSelected(true);


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
