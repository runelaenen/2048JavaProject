package be.kdg.spel.view;

import be.kdg.spel.controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;

/**
 * Created by Rune on 4/02/2015.
 */
public class RanglijstUI extends JFrame {
    private Controller controller;
    private JButton btnResetten;
    private JLabel lblTitel;
    private JLabel lblNaam;
    private JLabel lblScore;


   public RanglijstUI(Controller controller){
       super("Highscores");
       setSize(500, 500);
       setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       setLocationRelativeTo(null);
       setVisible(true);

       this.controller = controller;

       maakComponenten();
       maakLayout();
       behandelEvents();

   }
    private void maakComponenten() {



        btnResetten = new JButton("Resetten");
        lblTitel= new JLabel();
        lblNaam = new JLabel();
        lblScore = new JLabel("");


    }

    private void maakLayout() {


        JPanel pnlSuper = new JPanel(new BorderLayout(5,5));
        super.add(pnlSuper);
        pnlSuper.add(lblTitel, BorderLayout.NORTH);
        pnlSuper.add(lblNaam,BorderLayout.WEST);
        pnlSuper.add(lblScore,BorderLayout.EAST);
        Font fntTitel = new Font(Font.SANS_SERIF, Font.PLAIN, 50);
        Font fntScore = new Font(Font.SANS_SERIF,Font.PLAIN,30);

        //lblTitel
        lblTitel.setText("HIGHSCORES");
        lblTitel.setVerticalAlignment(SwingConstants.CENTER);
        lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitel.setFont(fntTitel);

        //lblNaam
        lblNaam.setText("<html>");
        lblScore.setText("<html>");
        for(String regel: controller.getHighscoreList()){
            String[] regels=  regel.split("[;]");
            lblNaam.setText(lblNaam.getText()+ regels[1] +"<br>");
            lblScore.setText(lblScore.getText()+regels[0]+"<br>");

            }
        lblNaam.setText(lblNaam.getText()+ "</html>");
        lblScore.setText(lblScore.getText() + "</html>");




        lblNaam.setFont(fntScore);
        //lblScore

        lblScore.setFont(fntScore);




    }
    private void behandelEvents() {

    }


}
