package modele.competence ;

import modele.position.* ;
/** Classe représentant une action qu'un lemming requiert d'effectuer
 * 
 */
public class Action {
    private Position goTo ;
    private Position add ;
    private Position[] destroy ;
    private boolean die ;
    private boolean falling ;
    private int fallSince ;
    private int waitBeforeNextAction ;
    private boolean powerless ; // Il ne peut pas effectuer son action
    private boolean climbing ;
    private int timeBeforeDie;
    private boolean dir;
    private boolean blocking;

    // @ private invariant getGoTo()=goTo ;
    // @ private invariant getAdd() = add ;
    // @ private invariant getDestroy() = destroy ;
    // @ private invariant getFalling() = falling ;
    // @ private invariant getFallSince() = fallSince ;
    // @ private invariant falling => ((goTo == null) && (add == null) && (destroy == null) && (fallSince > 0) ;
    // @ private invariant waitBeforeNextAction >= 0;
    // @ private invariant fallSince >= 0 ;
    // @ private invariant (fallSince >= 5) => die ;

    /** Obtenir la position goTo
     * @return requete de position suivante
     */
    public Position getGoTo(){
	return this.goTo ;
    }
    /** Obtenir la position add
     * @return requete de construction
     */
    public Position getAdd(){
	return this.add ;
    }
    /** Obtenir la liste de position destroy
     * @return requete de destruction
     */
    public Position[] getDestroy(){
	return this.destroy ;
    }
    /** Obtenir le statut de die
     * @return requete de mort
     */
    public boolean getDie(){
	return this.die ;
    }
    /** Obtenir le statut de tomber
     * @return requete de tomber
     */
    public boolean getFalling(){
	return this.falling ;
    }
    /** Obtenir le temps depuis lequel le lemming tombe
     * @return requete de construction
     */
    public int getFallSince(){
	return this.fallSince ;
    }

    /** Obtenir si l'action est possible ou non
     * @return requete d'enlever la competence en question
     */
    public boolean getPowerless(){
	return this.powerless ;
    }

    /** Vrai si le lemming est en train d'escalader un mur
     * @return climbing
     */
    public boolean getClimbing(){
	return this.climbing ;
    }

    /** Obtenir le temps restant avant explosion
     * @return timeBeforeDie 
     */
    public int getTimeBeforeDie() {
	return this.timeBeforeDie ;
    }

    /** Obtenir le booleen dir
     * @return dir
     */
    public boolean getDir() {
	return this.dir;
    }

    /** Obtenir le booleen blocking
     * @return blocking
     */
    public boolean getBlocking() {
	return this.blocking;
    }

    /** Obtenir waitBeforeNextAction
     * @return waitBeforeNextAction
     */
    public int getWaitBeforeNextAction() {
	return this.waitBeforeNextAction;
    }

    /** Changer waitBeforeNextAction
     * @param waitBeforeNextAction_
     */
    public void setWaitBeforeNextAction(int waitBeforeNextAction_) {
	this.waitBeforeNextAction = waitBeforeNextAction_;
    }
    
    // Constructeur
    /** Construire une action 
     * @param goTo_ position ou l'on veut aller
     * @param add_ position ou l'on veut construire
     * @param destroy_ positions ou l'on veut detruire
     * @param falling_ si on va tomber 
     * @param die_ si on va mourir
     * @param fallSince_ depuis combien de cases tombe-t-on ?
     * @param powerless_ l'action est impossible
     * @param timeBeforeDie_
     * @param climbing_ le lemming escalade un mur
     */
    public Action(Position goTo_, Position add_, Position[] destroy_, boolean falling_, boolean die_, int fallSince_, boolean powerless_, boolean climbing_, int timeBeforeDie_, boolean dir_, boolean blocking_){
	goTo = goTo_ ;
	add = add_ ;
	destroy = destroy_ ;
	falling = falling_;
	die = die_ ;
	fallSince = fallSince_ ;
	powerless = powerless_ ;
	climbing = climbing_;
	timeBeforeDie = timeBeforeDie_;
	dir = dir_ ;
	blocking = blocking_ ;
	if (fallSince >= 5) {
	    die = true ;
	} else if (falling_){
	    fallSince++;
	}
    }
    
    public String toString(){
	return "Go to : " + goTo + "\nBuild : " + add + "\nDestroy : "+ destroy + "\nTombe ? " +falling + "\nMeurt ? " +die + "\nTombe Depuis : " + fallSince + "\nAction possible : " + powerless + "\nEscalade : " + climbing + "\ncompte a rebour avant mort : " + timeBeforeDie + "\ndirection : " + dir + "\nbloque : " + blocking;
    }
}
