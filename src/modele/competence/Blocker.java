package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.* ;

/** Blocker est une competence semi-permanente qui implemente l'interface competence
 * @author Jessica Hornik
 * @version 1.0
 */

public class Blocker extends CompetenceSemiPermanente {
    
    // Constructeur
    public Blocker(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_,Integer waitBeforeNextAction_,Boolean climbing_,Integer timeBeforeDie_){
	super(level_,pos_,dir_,falling_,fallSince_,waitBeforeNextAction_,climbing_,timeBeforeDie_);
    }
    
    // Action a effectuer 
    public Action actionToPerform(Level_itf level){
	Action action ;
	Position goTo = null ;
	// Position add ;
	Element_itf elementAAjouter = null ; // Builder : ElementDestructible, Blocker : ElementBloquant(pos)
	Position[] destroy = null ;
	boolean falling = false ;
	boolean die = false ;
	Position posS, posE, posW ;
	boolean powerless = false ;
	boolean blocking = false ;
	boolean lanceFlammeADroite = false;
	boolean lanceFlammeAGauche = false;	
	if (pos.getX()!=pos.getXMax()) {
		posE = pos.getPositionE() ;
		lanceFlammeADroite = level.getMap()[posE.getX()][posE.getY()] instanceof ElementLanceFlamme;
	}
	if (pos.getX()!=0) {
		posW = pos.getPositionW() ;
		lanceFlammeAGauche = level.getMap()[posW.getX()][posW.getY()] instanceof ElementLanceFlamme;
	}
	// Si il se trouve en bas de la carte ou a cote d'un lance-flamme
	if ((pos.getY() == 0) || lanceFlammeADroite || lanceFlammeAGauche){
		powerless = true;
	}
	else { // L'element en dessous existe, il est soit vide, soit autre.
		blocking = true;
		posS = pos.getPositionS();
		if (level.getMap()[posS.getX()][posS.getY()] instanceof ElementVide){
		    falling = true ;
		    goTo = posS ;
		} else { // Sol en dessous de lui
		    if (fallSince >= 5) { // Il s'ecrase sur le sol => meurt
			powerless = true ;
		    } else { // Il est en place, et peut bloquer
			// BLOQUER (pos => ElementBloquant) // Si (lemming.competence instanceof Blocker die && pas autre lemming bloquant ici[pas tres possible]) => detruire ElementBloquant a cette pos.
			elementAAjouter = new ElementBloquant(new Position(pos.getX(),pos.getY(),pos.getXMax(),pos.getYMax())) ;
		    }
		}
	}	
	if (elementAAjouter != null) {
		return new Action(goTo,elementAAjouter.getPosition(),destroy,falling,die,fallSince,powerless,climbing,timeBeforeDie,dir,blocking);
	} else {
		return new Action(goTo,null,destroy,falling,die,fallSince,powerless,climbing,timeBeforeDie,dir, blocking);
	}
    }

    public int compareTo(Competence competence_){
	int res ;
	if (!(competence_ instanceof CompetenceSemiPermanente)){
	    res = 1 ;
	} else if (competence_ instanceof Blocker){
	    res = 0 ;
	} else { // competence_ instanceof Bomber
	    res = -1 ;
	}
	return res ;
    }

}
	    
    
