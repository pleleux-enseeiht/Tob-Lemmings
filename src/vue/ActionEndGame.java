package vue ;

import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;

import controller.Controller ;

public class ActionEndGame implements ActionListener {

    private Controller controller ;

    public ActionEndGame(Controller controller_) {
        this.controller = controller_ ;
    }
    
    public void actionPerformed(ActionEvent a) {
        controller.endGame();
    }
}
