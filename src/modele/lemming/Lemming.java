package modele.lemming ;

import java.util.* ;
import modele.position.* ;
import modele.level.* ;
import modele.competence.* ;
import modele.element.*;
/**
 * Classe Lemmings
 * Un lemming est defini par son etat, sa direction, et sa position.
 * Il a une liste de competences (4 au maximum)
 * Il possede les informations du niveau
 * @author Jessica Hornik
 * @version 1.0
 */

public class Lemming {
    private int waitBeforeNextAction ;
    private List<Competence> competences ;
    private States state ;
    private boolean direction ; // True si on va de gauche a droite, false si de droite a gauche
    private Position position ;
    private Level_itf level ;
    private boolean falling ;
    private int fallSince ;
    private boolean climbing;
    private int timeBeforeDie;
    private boolean blocking;

    // Constructeur
    public Lemming(Level_itf level) {
	this.level = level ;
	this.waitBeforeNextAction = 0 ;
	this.position = new Position(level.getPositionTrappe());
	this.competences = new ArrayList<Competence>(5);
	this.competences.add(new Walker(level,new Position(position),true,true,0,0,false,0));
	this.state = States.NOT_YET_THERE ;
	this.direction = true ; // toujours vers la droite en premier
	this.falling = false ;
	this.fallSince = 0 ;
	this.climbing = false;
	this.timeBeforeDie = 0;
	this.blocking = false;
	
    }

    public void update (Level_itf level) {
	this.level = level ;
    }

    // Gestion de waitBeforeNextAction
    public /**@pure*/int getWaitBeforeNextAction(){
	return this.waitBeforeNextAction ;
    }

    public void setWaitBeforeNextAction(int waitBeforeNextAction_) {
	this.waitBeforeNextAction = waitBeforeNextAction_;
    }

    // Gestion de la direction
    public /**@pure*/ boolean getDirection() {
	return this.direction ;
    }

    public void setDirection(boolean direction_) {
	this.direction = direction_ ;
    }

    // Gestion de la position
    public /**@pure*/ Position getPosition() {
	return this.position ;
    }

    public void setPosition(Position position_) {
	this.position = new Position(position_) ;
    }

    // Gestion de falling
    public /**@pure*/ boolean getFalling() {
	return this.falling ;
    }

    public void setFalling(boolean falling_) {
	this.falling = falling_ ;
    }

    // Gestion de fallSince
    public /**@pure*/ int getFallSince() {
	return this.fallSince ;
    }

    public void setFallSince(int fallSince_) {
	this.fallSince = fallSince_ ;
    }

    // Gestion de climbing
    public /**@pure*/ boolean getClimbing() {
	return this.climbing ;
    }

    public void setClimbing(boolean climbing_) {
	this.climbing = climbing_ ;
    }

    // Gestion de timeBeforeDie
    public /**@pure*/ int getTimeBeforeDie() {
	return this.timeBeforeDie ;
    }

    public void setTimeBeforeDie(int timeBeforeDie_) {
	this.timeBeforeDie = timeBeforeDie_ ;
    }

    // Gestion de State
    public /**@pure*/ States getState(){
	return this.state ;
    }

    public void setState(States state_){
	this.state = state_;
    }

    // Gestion de Blocking
    public /**@pure*/ boolean getBlocking(){
	return this.blocking ;
    }
    
    public void setBlocking(boolean blocking_){
	this.blocking = blocking_;
    }

    // Gestion des competences
    public /**@pure*/ List getCompetence(){
	return this.competences ;
    }

    public void setCompetence(Competence competence_){
	boolean place = false;
	int i = 0;
	while (!place && (i < this.competences.size())) {
		if (competence_.compareTo(this.competences.get(i)) == -1) {
			++i;
		} else if (competence_.compareTo(this.competences.get(i)) == 1) {
			this.competences.add(i, competence_);
			place = true;
		} else {
			if (competence_ instanceof CompetenceTemporaire) {
				this.competences.set(i, competence_);
			} else {
				this.competences.add(i, competence_);
			}
			place = true;
		}
	}
    }

    // Actions
    public ArrayList<Action> getActionToPerform() {
	ArrayList<Action> res ;
	Position goTo = null;
	Position add = null;
	Position[] destroy = new Position[8];
	boolean falling = this.falling ;
	boolean die = false ;
    	int fallSince = this.fallSince ;
	boolean climberCondition;
	boolean floaterCondition;
	int i;
	boolean actionGotten = false;
	res = new ArrayList<Action>() ;
    	if (waitBeforeNextAction > 0 ){ // s'il est bomber [constructeur est a part, car n'a pas encore fini de construire] et doit tomber
	    waitBeforeNextAction-- ;
	    return null ;
    	} else {
	    i = 0;
	    climberCondition = (competences.get(i) instanceof Climber) &&
		((this.direction&&!(this.level.getMap()[this.position.getPositionNE().getX()][this.position.getPositionNE().getY()] instanceof ElementVide) &&
		!(this.level.getMap()[this.position.getPositionE().getX()][this.position.getPositionE().getY()] instanceof ElementVide)) ||
		(!this.direction&&!(this.level.getMap()[this.position.getPositionNW().getX()][this.position.getPositionNW().getY()] instanceof ElementVide) &&
		!(this.level.getMap()[this.position.getPositionW().getX()][this.position.getPositionW().getY()] instanceof ElementVide)));
	    floaterCondition = (competences.get(i) instanceof Floater) &&
		(this.level.getMap()[this.position.getPositionS().getX()][this.position.getPositionS().getY()] instanceof ElementVide);
	    competences.get(i).update(level,position,direction,fallSince,waitBeforeNextAction,climbing,timeBeforeDie);
	    while (!actionGotten) {
			if (competences.get(i) instanceof CompetencePermanente) {
				if (climbing||(climberCondition&&((this.direction&&!(this.level.getMap()[this.position.getPositionNE().getX()][this.position.getPositionNE().getY()] instanceof ElementVide))||(!this.direction&&!(this.level.getMap()[this.position.getPositionNW().getX()][this.position.getPositionNW().getY()] instanceof ElementVide))))) {
					res.add(competences.get(i).actionToPerform(level));
					actionGotten = true;
				} else if (floaterCondition&&(competences.get(i) instanceof Floater)) {
					res.add(competences.get(i).actionToPerform(level));
					actionGotten = true;
				} else if (competences.get(i) instanceof Walker) {
					res.add(competences.get(i).actionToPerform(level));
					actionGotten = true;
				} else {
					++i;
				}
			} else if (competences.get(i) instanceof Bomber) {
				if (timeBeforeDie!=0) {
					timeBeforeDie = 5;
				}
				res.add(competences.get(i).actionToPerform(level));
				++i;
			} else {
				res.add(competences.get(i).actionToPerform(level));
				if (res.get(i).getPowerless()) {
					res.remove(i);
					competences.remove(i);
				} else {
					actionGotten = true;
				}
			}
	    }
	}
	return res ;
    }
}
