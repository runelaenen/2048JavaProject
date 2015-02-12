package be.kdg.spel.view;

import java.awt.*;

/**
 * @author Alex Buze
 * @version 1.0 12/02/2015 11:27
 */
public class ThemasUI {
    private ThemaUI[] themasArray;

    public ThemasUI() {
        this.themasArray = new ThemaUI[2];
        themasArray[0]= new ThemaUI("Default",new Color(0xfaf8ef),Color.BLACK,new Color(0xf19b5e));
        themasArray[1]= new ThemaUI("Zwart",new Color(0),Color.BLACK,new Color(0));



    }

    public ThemaUI[] getThemasArray() {
        return themasArray;
    }
    public ThemaUI get(int index){
        return themasArray[index];
    }
}
