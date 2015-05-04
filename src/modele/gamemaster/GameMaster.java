package modele.gamemaster ;

import modele.lemming.*;
import modele.competence.*;
import modele.level.*;
import modele.parser.*;
import modele.position.*;
import modele.element.*;

import java.util.*;

public class GameMaster {
    // Attributs
    private int nbSaved ; // Nombre de lemmings sauves
    private int nbDead ; // Nombre de lemmings morts
    private final int nbTotal ; // Nombre de lemmings total
    private int nbApparus ; // Nombre de lemmings deja apparus
    private final int nbToBeSaved ; // Nombre de lemmings a sauver
    

    // Constructeur
    public GameMaster(Level_itf level) {
        this.nbSaved = 0 ;
        this.nbDead = 0 ;
        this.nbTotal = level.getCount() ;
        this.nbApparus = 0 ;
	this.nbToBeSaved = level.getRescue() ;
    }

    /** Obtenir le nombre de lemmings sauves
     * @return nombre de lemmings sauves
     */
    public /**@pure*/ int getNbSaved(){
        return this.nbSaved ;
    }

    /** Obtenir le nombre de lemmings morts
     * @return nombre de lemmings morts
     */
    public /**@pure*/ int getNbDead(){
        return this.nbDead ;
    }

    /** Savoir si le jeu est gagne
     * @return true si c'est gagne, false sinon
     */
    public boolean isWon(){
	return (nbSaved >= nbToBeSaved) ;
    }

    /** Incrementer le nombre de lemmings sauves
     */
    public void incSaved(){
        this.nbSaved++;
    }

    /** Incrementer le nombre de lemmings morts */
    public void incDead(){
        this.nbDead++ ;
    }

    /** Savoir si le jeu est termine ou non
     * @return true si le jeu est termine, false sinon
     */
    public boolean isFinished(){
        return (nbDead + nbSaved == nbTotal) ;
    }

    public int getNbApparus(){
	return this.nbApparus ;
    }

    public boolean tousApparus(){
        return (nbTotal == nbApparus) ;
    }

    /** On cherche le lemming ayant une certaine position (x,y) dans une liste de lemming
     * @param x l'abscisse de la position du lemming
     * @param y l'ordonnee de la position du lemming
     * @param lemmingsPeople la liste des lemmings dans laquelle chercher le lemming
     * @return le lemming correspondant (ou null, s'il n'y a pas de lemming a la position (x,y))
     */
    public static final Lemming findLemming(int x, int y, List<Lemming> lemmingsPeople){
        int i = 0 ;
        boolean trouve = false ;
        Lemming res = null ;
        Lemming temp ;
        while (!trouve && i<lemmingsPeople.size()){
            temp = lemmingsPeople.get(i) ;
            if ((temp.getPosition().getX() == x) && (temp.getPosition().getY() == y)){
                res = temp ;
                trouve = true ;
            } else {
                ++i ;
            }
        }
        return res ;
    }

    /** Effectue l'etape suivante du jeu a partir des donnees actuelles du jeu
     * @param level le niveau reel
     * @param level_proxy le niveau donne aux lemmings
     * @param lemmingsPeople la liste de lemmings
     * @param faireApparaitre si on doit faire apparaitre un lemming ou non
     * @throws modele.level.TentativeDeModificationFrauduleuseException
     * @throws ElementNonDestructibleException,
     * @throws BlockAlreadyThereException
     */
    public void nextStep(Level_itf level, Level_itf level_proxy, List<Lemming> lemmingsPeople, boolean faireApparaitre)
        throws modele.level.TentativeDeModificationFrauduleuseException,
               ElementNonDestructibleException,
               BlockAlreadyThereException {

        List<Action> actions ;
        Action aux;
        int indexRealAction;
        boolean dieFromSuicideBombing = false;
        Lemming lemmCourant ;
        boolean unLemmingAEteLibere = false ;
	level_proxy.update(level) ;
	for(int i = 0; i < lemmingsPeople.size(); i++) {
	    lemmCourant = lemmingsPeople.get(i) ;
            lemmCourant.update(level_proxy);
	    
            // On commence par regarder si le lemming doit apparaître
            // et si oui, on le fait apparaître
            if ((lemmCourant.getState().equals(States.NOT_YET_THERE))
                && faireApparaitre
                && (!unLemmingAEteLibere)){

                lemmCourant.setState(States.ALIVE);
                nbApparus++ ;
                unLemmingAEteLibere = true ;
            }

            // On lui fait exécuter ses actions s'il est en vie
            if (lemmCourant.getState().equals(States.ALIVE)) {
                // On regarde ensuite si le lemming est sauvé
                if ((lemmCourant.getPosition().getX() == level.getPositionSortie().getX())
                    && (lemmCourant.getPosition().getY() == level.getPositionSortie().getY())) {

                    lemmingsPeople.remove(i);
                    incSaved();
                    i-- ;
                } else {
                    // S'il n'est pas sauvé, on récupère les actions qu'il doit effectuer
                    actions = lemmCourant.getActionToPerform();
		    if (actions != null) {
		            if (lemmCourant.getWaitBeforeNextAction() == 0) {
		                indexRealAction = 0;
		                if (actions.size() == 2) {
		                    // Gestion de l'action du bomber
		                    aux = actions.get(0);
		                    lemmCourant.setTimeBeforeDie(aux.getTimeBeforeDie());
		                    if (aux.getDestroy()!=null) {
		                        level.destroy(lemmCourant.getPosition(), aux.getDestroy());
		                    }
		                    dieFromSuicideBombing = aux.getDie();
		                    // Gestion de l'autre action
		                    indexRealAction = 1;
		                } else {
		                    aux = actions.get(indexRealAction);
				    if (aux.getBlocking()){
					lemmCourant.setBlocking(true);
				    }
				    if (aux.getAdd()!=null) {
					try {
					    if (lemmCourant.getBlocking()){
						level.addBlocks(new ElementBloquant(aux.getAdd()));
					    } else {
		                       		level.addBlocks(new ElementDestructible(aux.getAdd()));
					    }
					} catch (BlockAlreadyThereException e) {}
		                    }
				    lemmCourant.setWaitBeforeNextAction(aux.getWaitBeforeNextAction());
		                    if (aux.getDestroy()!=null) {
		                        try {
		                            level.destroy(lemmCourant.getPosition(), aux.getDestroy());
					    lemmCourant.setPosition(aux.getDestroy()[0]);					    
					    lemmCourant.setPosition(aux.getDestroy()[1]);
		                        } catch (ElementNonDestructibleException e) {
		                            lemmCourant.setDirection(!lemmCourant.getDirection());
		                        } catch (Exception e) {}
					
		                    }
		                    if (aux.getGoTo()!=null) {
		                        lemmCourant.setPosition(aux.getGoTo());
		                    }
		                    if ((aux.getFalling())&&(aux.getBlocking())) {
		                        level.deleteBlockingLemming(lemmCourant.getPosition().getPositionN());
					if (lemmCourant.getPosition().getY()!=0) {
						level.addBlocks(new ElementBloquant(lemmCourant.getPosition()));
					}
		                    }
		                    if (aux.getDie()||dieFromSuicideBombing||(level.getMap()[lemmCourant.getPosition().getX()][lemmCourant.getPosition().getY()] instanceof ElementBroyeur)) {
					if (lemmCourant.getBlocking()) {
						level.deleteBlockingLemming(lemmCourant.getPosition());
					}
		                        lemmingsPeople.remove(i);
		                        incDead();
		                        i-- ;
		                    }
		                    lemmCourant.setDirection(aux.getDir());
					if (aux.getBlocking()) {
						lemmCourant.setBlocking(aux.getBlocking());
					}
		                }
		            } else {
		                lemmCourant.setWaitBeforeNextAction(lemmCourant.getWaitBeforeNextAction()-1);
		            }
		    }
                }
            }
        }
    }

    /** On tue instantanement tous les lemmings restants */
    // IL FAUDRAIT PEUT-ETRE LEUR AFFECTER LA COMPETENCE BOMBER A TOUS
    public void killThemAll(List<Lemming> lemmingsPeople){
        while (lemmingsPeople.size()!=0) {
            lemmingsPeople.remove(0);
            incDead();
        }
    }
}
