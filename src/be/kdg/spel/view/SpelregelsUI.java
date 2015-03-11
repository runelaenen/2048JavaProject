package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alex on 11/03/2015.
 */
public class SpelregelsUI extends JDialog {
    private final Color achtergrondKleur;
    private Controller controller;
    private JButton btnBegrepen;
    private JTextArea txtUitleg;
    private JLabel lblTip;
    private JLabel lblTitel;
    private JLabel lblPijltjes;
    private JLabel lblWasd;
    private ImageIcon icnPijltjes;
    private ImageIcon icnWasd;
    private ImageIcon icnSpelregels;


    public SpelregelsUI(Controller controller) {
        setTitle("Spelregels");
        setModal(true);
        setSize(700, 500);
        setMinimumSize(new Dimension(700,500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        icnPijltjes = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/pijltjes.png"));
        icnWasd = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/wasd.png"));
        icnSpelregels = new ImageIcon(getClass().getResource("/be/kdg/spel/resources/icnSpelregels.png"));
        setIconImage(icnSpelregels.getImage());
        this.controller = controller;
        this.achtergrondKleur = controller.getAchtergrondsKleur();

        maakComponenten();
        maakLayout();
        behandelEvents();
        setVisible(true);

    }

    private void maakComponenten() {
        btnBegrepen = new JButton("Begrepen!");
        lblTitel = new JLabel("SPELREGELS");
        txtUitleg = new JTextArea("De 2048 spelregels zijn simpel. Je begint het spel met een raster met daarin vakjes. Je begint met 2 vakjes met het nummer 2.\n Je kan deze nummers combineren door de vakjes over elkaar heen te schuiven. Wanneer je 2 dezelfde getallen over elkaar heen schuift, dan verdubbelen ze.\nHet einddoel is dus om zoveel mogelijk vakjes te verdubbelen om uiteindelijk het getal 2048 te krijgen.\nJe kan de vakjes verplaatsen met de volgende toetsen:");
        lblTip = new JLabel("Een belangrijke tip! Schuif NOOIT naar beneden!");
        lblPijltjes = new JLabel(icnPijltjes);
        lblWasd = new JLabel(icnWasd);

    }

    private void maakLayout() {
        JPanel pnlSuper = new JPanel(new BorderLayout());
        JPanel pnlUitleg = new JPanel(new BorderLayout());
        JPanel pnlPrenten = new JPanel(new GridLayout(1, 2));

        // Fonts
        Font fntTitel = controller.getFont().deriveFont(52f);
        Font fntUitleg = controller.getFont().deriveFont(23f);

        lblTitel.setFont(fntTitel);
        lblTitel.setBackground(new Color(0xedc22e));
        lblTitel.setOpaque(true);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);

        txtUitleg.setFont(fntUitleg);
        txtUitleg.setEditable(false);
        txtUitleg.setBackground(achtergrondKleur);
        txtUitleg.setLineWrap(true);
        txtUitleg.setWrapStyleWord(true);

        lblTip.setFont(fntUitleg);
        lblTip.setOpaque(true);
        lblTip.setBackground(achtergrondKleur);
        lblTitel.setMaximumSize(new Dimension(1280, 20));

        lblPijltjes.setBackground(achtergrondKleur);
        lblPijltjes.setOpaque(true);

        lblWasd.setBackground(achtergrondKleur);
        lblWasd.setOpaque(true);


        pnlPrenten.add(lblPijltjes);
        pnlPrenten.add(lblWasd);

        pnlUitleg.add(txtUitleg, BorderLayout.NORTH);
        pnlUitleg.add(pnlPrenten,BorderLayout.CENTER);
        pnlUitleg.add(lblTip, BorderLayout.SOUTH);

        pnlSuper.add(lblTitel, BorderLayout.NORTH);
        pnlSuper.add(btnBegrepen, BorderLayout.SOUTH);
        pnlSuper.add(pnlUitleg, BorderLayout.CENTER);
        super.add(pnlSuper);
    }

    private void behandelEvents() {
        btnBegrepen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
