package vue ;
import java.util.* ;
import java.awt.event.*;
import javax.swing.* ;
import java.lang.* ;

import controller.Controller ;

public class ActionKillThemAll implements ActionListener {
    private Controller controller ;
    
    public ActionKillThemAll(Controller controller_){
	this.controller = controller_ ;
    }

    public void actionPerformed(ActionEvent e){
	controller.killThemAll();
    }
}