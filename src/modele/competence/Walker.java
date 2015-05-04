package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.*;
/** 
 * Walker est une competence qui herite de CompetencePermanente
 * @author Jessica Hornik
 * @version 1.2
 * L'action qu'il renvoie revient a :
 ** Si [en bas de map] => die
 ** sinonSi [case(x,y-1)==vide] => falling = true
 ** sinon{ // Sol present 
 *** Si [fallSince>=5] => die = true;
 *** sinonSi [dir]{
 **** Si [droite de map] => dir = !dir
 **** sinonSi [case(x+1,y)==vide] => goTo = new Position(x+1,y,xMax,yMax) ;
 **** sinon { // case pleine devant
 ***** Si [en haut de map] => dir = !dir
 ***** sinonSi [case(x+1,y+1)==vide] => goTo = new Position(x+1,y+1,xMax,yMax);
 ***** sinon => dir = !dir // mur devant
 **** }
 *** } sinon { // !dir
 **** Si [gauche de map] => dir = !dir
 **** sinonSi [case(x-1,y) ==vide] => goTo = new Position(x-1,y,xMax,yMax);
 **** sinon { // case pleine devant
 ***** Si [haut de map] => dir = !dir
 ***** sinonSi [case(x-1,y+1)==vide] => goTo = new Position(x-1,y+1,xMax,yMax);
 ***** sinon => dir = !dir // mur devant
 **** }
 *** }
 ** }
 */


public class Walker extends CompetencePermanente {
    // Attributs
    // Ceux de Competence Permanente
    // Constructeur
    /** 
     * Construire la competence walker
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param falling_ s'il tombe
     * @param fallSince_ le nombre de cases duquel il tombe
     * @param waitBeforeNextAction_ doit-il attendre avant d'effectuer son action
     * @param climbing_ le lemming escalade un mur
     * @param timeBeforeDie_
     */
    public Walker(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_,Integer waitBeforeNextAction_,Boolean climbing_,Integer timeBeforeDie_){
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
	Position posS, posNE, posNW, posE, posW, posN ; // Positions adjacentes de pos 
	boolean die = false ;
	boolean powerless = false ;
	boolean blocking = false ;
	boolean climbing = false;
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
		posS = pos.getPositionS() ;
		if ((level.getMap()[posS.getX()][posS.getY()] instanceof ElementVide)/* || (level.getMap()[posS.getX()][posS.getY()] instanceof ElementBloquant)*/&&!climbing){ // Il n'y a rien en dessous de lui (donc il tombe) ////////////// OU BIEN ELEMENT BLOQUANT ??>????>>>???
		    falling = true ;
		    goTo = posS;
		    //fallSince++ ;
		} else { // Il y a du sol en dessous de lui
		    posN = pos.getPositionN();
		    if (fallSince >= 5) { // il y a un sol en dessous de lui et il tombe depuis > 5 cases (donc il meurt)
			die = true ;
		    } else if (dir){ // Il va vers la droite
			
			if (pos.getX() == pos.getXMax()){ // Il est au bout a droite (donc il meurt)
			    die = true ;
			} else { // la case a droite existe, elle vaut soit ELEMENTBLOQUANT, soit vide soit autre.
			    posE = pos.getPositionE();
			    
			    if (level.getMap()[posE.getX()][posE.getY()] instanceof ElementBloquant) {
				dir = !dir ;
			    } else if (level.getMap()[posE.getX()][posE.getY()] instanceof ElementVide){ // Il n'y a rien a droite (donc il avance a droite)
				goTo = new Position(pos.getX()+1,pos.getY(),pos.getXMax(),pos.getYMax());
			    } else { // Il y a une case non vide a droite 
				if ((pos.getY() == pos.getYMax())||!(level.getMap()[posN.getX()][posN.getY()] instanceof ElementVide)){ // Le plafond est juste au-dessus (donc on change de dir)
				    dir = !dir ;
				} else { // La case au N-E existe, et est egale au VIDE ou autre
				    posNE = pos.getPositionNE();
				    if (level.getMap()[posNE.getX()][posNE.getY()] instanceof ElementVide){ // Rien en haut a droite (donc on monte la marche)
					goTo = new Position(posNE.getX(),posNE.getY(),pos.getXMax(),pos.getYMax());
				    } else { // Il y a un mur devant (donc on change de dir)
					dir = !dir ;
				    }
				}
			    }
			}
		    } else { // !dir (Il va vers la gauche)
			if (pos.getX() == 0){ // On est tout a gauche de la map (donc on meurt)
			    die = true ;
			} else { // La case a gauche existe = bloquant ou ' ' ou autre.
			    posW = pos.getPositionW();
			    if (level.getMap()[posW.getX()][posW.getY()] instanceof ElementBloquant){
				dir = !dir ;
			    } else if (level.getMap()[posW.getX()][posW.getY()] instanceof ElementVide){ // Rien a gauche (donc on va a gauche)
				goTo = new Position(posW.getX(),posW.getY(),posW.getXMax(),posW.getYMax());
			    } else { // Il y a un bloc non vide a gauche
				
				if ((pos.getY() == pos.getYMax())||!(level.getMap()[posN.getX()][posN.getY()] instanceof ElementVide)){ // Plafond (donc on change de dir)
				    dir = !dir ;
				} else {
				    posNW = pos.getPositionNW();
				    if (level.getMap()[posNW.getX()][posNW.getY()] instanceof ElementVide){ // Rien en haut a gauche (donc on monte la marche)
					goTo = new Position(posNW.getX(),posNW.getY(),pos.getXMax(),pos.getYMax());					
				    } else { // Il y a un mur devant (donc on change de dir)
					dir = !dir ;
				    }
				}
			    }
			}
		    }
		}
	    }
	    return new Action(goTo,add,destroy,falling,die,fallSince,powerless,climbing,timeBeforeDie,dir, blocking) ;
    }
}
