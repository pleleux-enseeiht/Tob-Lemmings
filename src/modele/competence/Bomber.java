package modele.competence ;

import modele.position.* ;
import modele.level.* ;
import modele.element.*;

/**  
 * Bomber est une competence qui herite de CompetenceSemiPermanente
 * @author Jessica Hornik et Philippe Leleux
 * @version 1.1
 * L'action qu'il renvoie revient a :
 ** Si [timeBeforeDie > 0] => timeBeforeDie-- ;
 ** Sinon => die = true;
 */
public class Bomber extends CompetenceSemiPermanente {
    // Attributs : ceux de Competence Semi Permanente
    
    // Constructeur 
    /**
     * Construire la competence Bomber
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param fallSince_ le nombre de cases duquel il tombe
     */
    public Bomber(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_,Integer waitBeforeNextAction_,Boolean climbing_,Integer timeBeforeDie_){
	super(level_,pos_,dir_,falling_,fallSince_,waitBeforeNextAction_,climbing_,timeBeforeDie_);
    }
    
    // Methodes
    public Action actionToPerform(Level_itf level) {
	Position[] destroy = null;
	boolean die = false;	
	if (timeBeforeDie > 0) {
		timeBeforeDie--;
	} else {
		destroy = new Position[8];
		for (int i = 0; i < 8; ++i) {
			destroy[i] = null;
		}
		die = true;
		if (pos.getY() == 0) {
			destroy[0] = pos.getPositionN();
			if (pos.getX() == 0) {
				destroy[1] = pos.getPositionE();
				destroy[2] = pos.getPositionNE();
			} else {
				destroy[1] = pos.getPositionW();
				destroy[2] = pos.getPositionNW();
 				if (pos.getX() != pos.getXMax()) {
					destroy[3] = pos.getPositionE();
					destroy[4] = pos.getPositionNE();
				}
			}
		} else if (pos.getY() == pos.getYMax()) {					
			destroy[0] = pos.getPositionS();
			if (pos.getX() == 0) {
				destroy[1] = pos.getPositionE();
				destroy[2] = pos.getPositionSE();
			} else {
				destroy[1] = pos.getPositionW();
				destroy[2] = pos.getPositionSW();
 				if (pos.getX() != pos.getXMax()) {
					destroy[3] = pos.getPositionE();
					destroy[4] = pos.getPositionSE();
				}
			}
		} else {
			destroy[0] = pos.getPositionN();
			destroy[1] = pos.getPositionS();
			if (pos.getX() == 0) {
				destroy[2] = pos.getPositionE();
				destroy[3] = pos.getPositionNE();
				destroy[4] = pos.getPositionSE();
			} else {
				destroy[2] = pos.getPositionW();
				destroy[3] = pos.getPositionNW();
				destroy[4] = pos.getPositionSW();
				if (pos.getX() != pos.getXMax()) {
					destroy[5] = pos.getPositionE();
					destroy[6] = pos.getPositionNE();
					destroy[7] = pos.getPositionSE();
				}
			}
		}
	}
	return new Action(null,null,destroy,false,die,0,false,false,timeBeforeDie,dir,false) ;
    }
    
    public int compareTo(Competence competence_){
	int res ;
	if (competence_ instanceof Bomber){
	    res = 0 ;
	} else {
	    res = 1 ;
	}
	return res ;
    }
    

}
