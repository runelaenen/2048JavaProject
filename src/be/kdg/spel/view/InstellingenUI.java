package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rune on 4/02/2015.
 */
public class InstellingenUI extends JFrame {
    private Controller controller;
    private JButton btnOpslaan;
    private JButton btnAnnuleren;
    private JButton btnKleur;
    private JLabel lblThema;
    private JComboBox cboThema;
    private JLabel lblAchtergrondKleur;
    private Color kleur;


    public InstellingenUI(Controller controller) throws HeadlessException {
        super("Instellingen");
        setSize(350, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.controller = controller;
        this.kleur = controller.getAchtergrondsKleur();

        maakComponenten();
        maakLayout();
        behandelEvents();


    }

    private void maakComponenten() {
        //array


        //buttons
        btnOpslaan = new JButton("Opslaan");
        btnAnnuleren = new JButton("Annuleren");
        btnKleur = new JButton("Kies een kleur...");
        //labels
        lblThema = new JLabel("Thema: ");
        lblAchtergrondKleur = new JLabel("Achergrondskleur: ");
        //combobox
        cboThema = new JComboBox();


    }

    private void maakLayout() {
        JPanel pnlSuper = new JPanel();
        JPanel pnlKnoppen = new JPanel(new FlowLayout());
        JPanel pnlInstellingen = new JPanel(new GridLayout(2, 2, 5, 5));


        //pnlInstellingen
        pnlInstellingen.add(lblThema);
        pnlInstellingen.add(cboThema);
        pnlInstellingen.add(lblAchtergrondKleur);
        pnlInstellingen.add(btnKleur);

        //pnlKnoppen
        pnlKnoppen.add(btnOpslaan);
        pnlKnoppen.add(btnAnnuleren);

        //pnlSuper
        add(pnlInstellingen, BorderLayout.NORTH);
        add(pnlKnoppen, BorderLayout.SOUTH);

        //add(pnlSuper,BorderLayout.CENTER);

    }

    private void behandelEvents() {

        btnOpslaan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.opslaan(kleur);
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


    }


}
