import vue.* ;
import controller.* ;
import modele.* ;

public class Lanceur {
    public static void main(String[] args){
	try {
	    if (args.length != 1) {
		System.out.println("\tUsage: java lanceur <path du niveau>");
	    } else {
		Modele modele = new Modele(args[0]);
		Controller controller = new Controller(modele) ;
		Vue vue = new Vue(controller);
		modele.addObserver(vue);
	    }
	} catch (Exception e){
	    e.printStackTrace() ;
	}
    }
}
