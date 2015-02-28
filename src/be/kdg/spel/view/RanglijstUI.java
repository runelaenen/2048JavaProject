package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class RanglijstUI extends JFrame {
    private Controller controller;
    private Color achtergrondKleur;

    private JButton btnResetten;
    private JLabel lblTitel;
    private JLabel[] lblNaam;
    private JLabel[] lblScore;


   public RanglijstUI(Controller controller){
       super("Highscores");
       setSize(500, 500);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);

       this.achtergrondKleur = new Color(0xfaf8ef);

       this.controller = controller;

       maakComponenten();
       maakLayout();
       behandelEvents();

   }
    private void maakComponenten() {
        btnResetten = new JButton("Resetten");
        lblTitel= new JLabel();
        lblNaam = new JLabel[10];
        lblScore = new JLabel[10];
    }

    private void maakLayout() {


        JPanel pnlSuper = new JPanel(new BorderLayout(5,5));
        super.add(pnlSuper);
        pnlSuper.add(lblTitel, BorderLayout.NORTH);
        pnlSuper.setBackground(achtergrondKleur);

        JPanel pnlNamen = new JPanel(new GridLayout(10,1));
        JPanel pnlScores = new JPanel(new GridLayout(10,1));
        pnlNamen.setBackground(achtergrondKleur);
        pnlScores.setBackground(achtergrondKleur);

        pnlSuper.setBorder(new EmptyBorder(10, 10, 10, 10));
        pnlSuper.add(pnlNamen,BorderLayout.WEST);
        pnlSuper.add(pnlScores,BorderLayout.EAST);

        Font fntTitel = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
        Font fntScore = new Font(Font.SANS_SERIF,Font.PLAIN,30);

        //lblTitel
        lblTitel.setText("HIGHSCORES");
        lblTitel.setVerticalAlignment(SwingConstants.CENTER);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setFont(fntTitel);

        //lblNaam en lblScore invullen
        int index=0;
        for(String[] regel: controller.getHighscoreList()){
            if(index<10) {
                lblNaam[index] = new JLabel(regel[0]);
                lblScore[index] = new JLabel(regel[1]);

                index++;
            }
        }

        for(int i=0; i<10; i++){
            if(lblNaam[i] != null) { // check of er écht een label in de arrayplaats zit
                lblNaam[i].setFont(fntScore);
                lblScore[i].setFont(fntScore);

                lblNaam[i].setHorizontalTextPosition(SwingConstants.LEFT);
                lblNaam[i].setVerticalAlignment(SwingConstants.CENTER);
                pnlNamen.add(lblNaam[i]);

                lblScore[i].setHorizontalTextPosition(SwingConstants.RIGHT);
                lblScore[i].setVerticalAlignment(SwingConstants.CENTER);
                pnlScores.add(lblScore[i]);
            }
        }

    }
    private void behandelEvents() {

    }


}
