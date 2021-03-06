package modele.competence ;

import modele.position.*;
import modele.level.* ;

/** Competence permanente est une classe abstraite
 * @author Jessica Hornik
 * @version 1.0
 */

public abstract class CompetencePermanente implements Competence {
    
    // Attributs
    protected Position pos ;
    protected Level_itf level ;
    protected boolean dir ;
    protected boolean falling ;
    protected int fallSince ;
    protected int waitBeforeNextAction ;
    protected boolean climbing ;
    protected int timeBeforeDie ;
     
    // Constructeur
    /** 
     * Construire la competence CompetencePermanente
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param falling_ s'il tombe
     * @param fallSince_ le nombre de cases duquel il tombe
     * @param waitBeforeNextAction_ doit-il attendre avant d'effectuer son action
     * @param climbing_ le lemming escalade un mur
     * @param timeBeforeDie_
     */
    public CompetencePermanente(Level_itf level_, Position pos_, Boolean dir_, Boolean falling_, Integer fallSince_, Integer waitBeforeNextAction_, Boolean climbing_, Integer timeBeforeDie_){ 
	this.pos = pos_ ;
	this.dir = dir_ ;
	this.level = level_ ;
	this.falling = falling_ ;
	this.fallSince = fallSince_ ;
	this.waitBeforeNextAction = waitBeforeNextAction_ ;
	this.climbing = climbing_ ;
	this.timeBeforeDie = timeBeforeDie_ ;
    } 
    
    public abstract Action actionToPerform(Level_itf level) ;
    
    public void update(Level_itf level_, Position pos_, boolean dir_, int fallSince_,int waitBeforeNextAction_,boolean climbing_,int timeBeforeDie_){
	level = level_ ;
	pos = pos_ ;
	dir = dir_ ;
	fallSince = fallSince_ ;
	waitBeforeNextAction = waitBeforeNextAction_ ;
	climbing = climbing_ ;
	timeBeforeDie = timeBeforeDie_ ;
    }
 

    public abstract int compareTo(Competence competence_) ;

    public /**@pure*/ Position getPos(){
	return this.pos ;
    }
    
    public /**@pure*/ Level_itf getLevel(){
	return this.level ;
    }
    
    public /**@pure*/ boolean getDir(){
	return this.dir ;
    }
    
    public /**@pure*/ int getFallSince(){
	return this.fallSince ;
    }
    
}
