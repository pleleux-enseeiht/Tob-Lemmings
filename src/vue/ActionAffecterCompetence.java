package vue ;

//import java.lang.Class ;
//import java.lang.Constructor ;
import java.util.* ;
import java.awt.event.*;
import javax.swing.* ;
import java.lang.* ;

import controller.Controller ;

public class ActionAffecterCompetence extends MouseAdapter {

    private JPanel zone ;
    private int x ;
    private int y ;
    private Controller controller ;

    public ActionAffecterCompetence(JPanel zone_, int x_, int y_, Controller controller_){
        this.zone = zone_ ;
        this.x = x_ ;
        this.y = y_ ;
        this.controller = controller_ ;
    }

    public void mouseClicked(MouseEvent a){

        JToggleButton buttonCompetence = (JToggleButton)zone.getComponent(0) ;
        int i = 0 ;
        while ((i<zone.getComponentCount()-5) && (!buttonCompetence.isSelected())){
            buttonCompetence = (JToggleButton)zone.getComponent(i) ;
            ++i ;
        }
        controller.affecterCompetence(x,y,buttonCompetence);
    }
}


