package vue ;

import java.awt.* ; 
import java.awt.event.*;
import javax.swing.* ;

public class ActionQuitter implements ActionListener {
    public ActionQuitter (){
    }
    public void actionPerformed(ActionEvent a){
	System.exit(1) ;
    }
}