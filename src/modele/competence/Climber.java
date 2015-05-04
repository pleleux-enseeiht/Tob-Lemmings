package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.*;
/** 
 * Climber est une competence qui herite de CompetencePermanente
 * @author Jessica Hornik et Philippe Leleux
 * @version 1.0
 * L'action qu'il renvoie revient a :
 ** Si [en bas de map] => die
 ** Sinon si [case(x,y+1)==vide] {
 *** Si [case(x+1,y+1)==vide] => goTo = new Position(x+1,y+1,xMax,yMax) ;
 *** Sinon goTo = new Position(x,y+1,xMax,yMax) ;
 ** } Sinon => dir=!dir ou rien, pour l'instant rien
 */


public class Climber extends CompetencePermanente {
    // Attributs
    // Ceux de Competence Permanente
    // Constructeur
    /** 
     * Construire la competence Climber
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param falling_ s'il tombe
     * @param fallSince_ le nombre de cases duquel il tombe
     * @param waitBeforeNextAction_ doit-il attendre avant d'effectuer son action
     * @param climbing_ le lemming escalade un mur
     * @param timeBeforeDie_
     */
    public Climber(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_,Integer waitBeforeNextAction_,Boolean climbing_,Integer timeBeforeDie_){
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
	Position posS, posNE, posN, posE, posW, posNW ; // Positions adjacentes de pos 
	boolean falling = false ;
	boolean die = false ;
	boolean powerless = false ;
	boolean climbing = true ;
	boolean blocking = false;
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
	// Si il se trouve en bas de la carte ou a cote d'un lance-flamme ou au dessus d'un broyeur
	if ((pos.getY() == 0) || lanceFlammeADroite || lanceFlammeAGauche){
		die = true;
	} 
	else { // L'element en dessous existe, il est soit vide, soit autre.
		posN = pos.getPositionN();
		if (level.getMap()[posN.getX()][posN.getY()] instanceof ElementVide) {
			if (dir) {
				posNE = pos.getPositionNE();
				if (level.getMap()[posNE.getX()][posNE.getY()] instanceof ElementVide) {
					goTo = new Position(posNE.getX(), posNE.getY(), pos.getXMax(), pos.getYMax());
					climbing = !climbing;
				} else {
					goTo = new Position(posN.getX(), posN.getY(), pos.getXMax(), pos.getYMax());
			System.out.println("youhou");
				}
			} else {
				posNW = pos.getPositionNW();
				if (level.getMap()[posNW.getX()][posNW.getY()] instanceof ElementVide) {
					goTo = new Position(posNW.getX(), posNW.getY(), pos.getXMax(), pos.getYMax());
					climbing = !climbing;
				} else {
					goTo = new Position(posN.getX(), posN.getY(), pos.getXMax(), pos.getYMax());
				}
			}			
		} // else { on ne fait rien mais ca serait pas mal de changer de direction plutot 		
	}
	action = new Action(goTo,add,destroy,falling,die,fallSince,powerless,climbing,timeBeforeDie,dir,blocking) ;
	action.setWaitBeforeNextAction(5);
	return action;
    }
}
