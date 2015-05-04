package vue ;

import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;

import javax.swing.JLabel ;

import controller.Controller ;

public class ActionFastForward implements ActionListener {

    private Controller controller ;
    private JLabel vitesse ;

    public ActionFastForward(Controller controller_, JLabel vitesse_) {
        this.controller = controller_ ;
        this.vitesse = vitesse_ ;
    }

    public void actionPerformed(ActionEvent a) {
        controller.fastForward(vitesse);
    }
}
