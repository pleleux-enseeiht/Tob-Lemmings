package vue ;

import controller.* ;
import modele.* ;

public class TestVue {
    public static void main(String[] args){
	try {
	    Modele modele = new Modele("./exemple.lemmings");
	    Controller controller = new Controller(modele) ;
	    Vue vue = new Vue(controller);
            // TRES TRES IMPORTANTTTTT
            modele.addObserver(vue);
	} catch (Exception e){
	    e.printStackTrace() ;
	}
    }
}
