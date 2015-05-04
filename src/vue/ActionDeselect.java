package vue ;

import javax.swing.* ;
import java.awt.event.* ;
import java.awt.Component ;

public class ActionDeselect implements ActionListener {

    private JToggleButton[] mesAmis ;

    public ActionDeselect(JPanel pane) {
        Component[] c = pane.getComponents();
        this.mesAmis = new JToggleButton[8] ;
        for (int k = 0 ; k < 8 ; k++) {
            this.mesAmis[k] = (JToggleButton)c[k];
        }
    }

    public void actionPerformed(ActionEvent e) {
        JToggleButton moi = (JToggleButton)e.getSource();
        if (moi.isSelected()) {
            for (int k = 0 ; k < mesAmis.length ; k++) {
                if (mesAmis[k] != moi) {
                    mesAmis[k].setSelected(false);
                }
            }
        }
    }
}

