package be.kdg.spel.model;

/**
 * Created by alex on 11/02/2015.
 */
public class Spelregels {
    boolean gewonnen;
    //TODO: Alle spelregels zouden hier moeten komen
    public Spelregels() {
        this.gewonnen = false;
    }


    public static boolean isVerloren(Tegels tegels) {
        for(Tegel tegel : tegels.getTegelArray()){
            if(tegel.isLeeg()){
                return false;
            }
        }
        return true;
    }
}
