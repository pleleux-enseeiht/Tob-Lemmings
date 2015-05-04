package vue ;

import javax.swing.* ;
import java.awt.event.* ;
import controller.*;

public class ActionChangeLevel implements ActionListener {
    
    private Controller controller ;
    
    public ActionChangeLevel (Controller controller_) {
	this.controller = controller_ ;
    }
    
    public void actionPerformed(ActionEvent e){
	controller.changeLevel() ;
    }
}
    