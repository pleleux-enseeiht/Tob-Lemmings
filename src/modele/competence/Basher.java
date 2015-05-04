package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.*;

/** 
 * Basher est une competence qui herite de CompetencePermanente
 * @author Jessica Hornik et philippe leleux
 * @version 1.0
 * L'action qu'il renvoie revient a :
 ** Si [en bas de map] => 
 ** sinonSi [case(x,y-1)==vide && !climbing] => enlever competence
 ** sinon{ // Sol present ou escalade
 *** Si [fallSince>=5(pas possible en escalade)] => enlever competence
 *** sinonSi [dir]{
 **** Si [droite de map] => enlever competence;
 **** sinonSi [case(x+1,y)==vide] => enlever competence ;
 **** sinon { // case pleine devant
 ***** destroy(posE);
 **** }
 *** } sinon { // !dir
 **** Si [gauche de map] => enlever competence;
 **** sinonSi [case(x-1,y) ==vide] => enlever competence;
 **** sinon { // case pleine devant
 ***** destroy(posW);
 **** }
 *** }
 ** }
 */
public class Basher extends CompetenceTemporaire {
    
    public Basher(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_, Integer waitBeforeNextAction_, Boolean climbing_, Integer timeBeforeDie_){
	super(level_,pos_,dir_,falling_,fallSince_,waitBeforeNextAction_,climbing_,timeBeforeDie_);
    }

    // Methodes
    public Action actionToPerform(Level_itf level){
	Position goTo = null ;
	Position add = null ;
	Position[] destroy = null ;
	Position posS, posNE, posNW, posE, posW, posN, posSE, posSW; // Positions adjacentes de pos 
	boolean falling = false ;
	boolean die = false ;
	boolean powerless = false;
	boolean blocking = false ;
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
		powerless = true;
	} 
	else { // L'element en dessous existe, il est soit vide, soit autre.
		posS = pos.getPositionS();
		if ((level.getMap()[posS.getX()][posS.getY()] instanceof ElementVide)&&!climbing){
		    powerless = true;
		} 
		else { // Il y a du sol en dessous de lui
		    if (fallSince >= 5) { // il y a un sol en dessous de lui et il tombe depuis > 5 cases (donc il meurt), on lui enleve la competence
			powerless = true;
		    } 
		    else if (dir){ // Il va vers la droite
			posE = pos.getPositionE() ;
			if ((pos.getX() == pos.getXMax()) || (level.getMap()[posE.getX()][posE.getY()] instanceof ElementBloquant)){ // Il est au bout a droite (donc il change de direction)
			    powerless = true;
			} 
			else { // la case a droite existe, elle vaut soit vide soit autre.
			    if (level.getMap()[posE.getX()][posE.getY()] instanceof ElementVide) {
				powerless = true;
			    } 
			    else {
				destroy = new Position[1];
				destroy[0] = posE;
			    }
			}
		    } 
		    else { // !dir (Il va vers la gauche)
			posW = pos.getPositionW() ;
			if ((pos.getX() == 0) || (level.getMap()[posW.getX()][posW.getY()] instanceof ElementBloquant)){ // Il est au bout a droite (donc il change de direction)
			    powerless = true;
			} 
			else { // la case a gauche existe, elle vaut soit vide soit autre.
			    if (level.getMap()[posW.getX()][posW.getY()] instanceof ElementVide) {
				powerless = true;
			    } 
			    else {
				destroy = new Position[1];
				destroy[0] = posW;
			    }
			}
		    }
		}
	}
	action = new Action(goTo,add,destroy,falling,die,fallSince,powerless, climbing, timeBeforeDie, dir, blocking) ;
	action.setWaitBeforeNextAction(5);
	return action;
    }
}
