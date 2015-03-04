package be.kdg.spel.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rune on 4/03/2015.
 */
class SquarePanel extends JPanel {

    @Override
    public Dimension getPreferredSize() {
        Dimension grootte = super.getPreferredSize();
        Container container = getParent();
        if (container != null) {
            grootte = container.getSize();
        } else {
            return new Dimension(10, 10);
        }
        int w = (int) grootte.getWidth();
        int h = (int) grootte.getHeight();
        int s = (w < h ? w : h);
        return new Dimension(s, s);
    }
}