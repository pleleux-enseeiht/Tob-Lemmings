package vue ;

import javax.swing.* ;
import java.awt.event.* ;
import controller.*;

public class ActionRestart implements ActionListener {
    
    private Controller controller ;
    
    public ActionRestart (Controller controller_) {
	this.controller = controller_ ;
    }
    
    public void actionPerformed(ActionEvent e){
	controller.restart() ;
    }
}
       