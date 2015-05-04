package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.*;
/** 
 * Floater est une competence qui herite de CompetencePermanente
 * @author Jessica Hornik et Philippe Leleux
 * @version 1.0
 * Pour utiliser cette compétence, le lemming doit être au dessus du vide
 * L'action qu'il renvoie revient a :
 ** fallSince = 0
 */


public class Floater extends CompetencePermanente {
    // Attributs
    // Ceux de Competence Permanente
    // Constructeur
    /** 
     * Construire la competence walker
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param fallSince_ le nombre de cases duquel il tombe
     */
    public Floater(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_,Integer waitBeforeNextAction_,Boolean climbing_,Integer timeBeforeDie_){
	super(level_,pos_,dir_,falling_,fallSince_,waitBeforeNextAction_,climbing_,timeBeforeDie_);
    }

    // Methodes
    public int compareTo(Competence competence_){
	int res ;
	if (competence_ instanceof Walker){
	    res = 0 ;
	} else {
	    res = -1;
	}
	return res ;
    }
    
    public Action actionToPerform(Level_itf level){
	Position goTo = null ;
	Position add = null ;
	Position[] destroy = null ;
	boolean falling = true ;
	boolean die = false ;
	boolean powerless = false ;
	boolean climbing = false ;
	int fallSince = 0 ;
	boolean blocking = false ;
	Position posW, posE;
	Action action;
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
		die = true;
	} 
	else { // L'element en dessous existe, il est soit vide, soit autre.
		goTo = pos.getPositionS() ;
	}
	action = new Action(goTo,add,destroy,falling,die,fallSince,powerless,climbing,timeBeforeDie,dir,blocking) ;
	action.setWaitBeforeNextAction(5);
	return action;
    }
}
