package modele ;

import java.util.* ;
import java.io.* ;
import modele.lemming.* ;
import modele.level.* ;
import modele.parser.*;
import modele.gamemaster.* ;
import modele.position.Position ;
import modele.competence.Competence ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import javax.swing.Timer;

import java.lang.reflect.Constructor ;

/**
 * Modele du jeu des Lemmings
 * @author Jessica Hornik
 * @version 1.0
 *
 */

public class Modele extends Observable {
    // Attributs
    Level_itf level ;
    Level_itf level_proxy ;
    Level_itf level_initial ;
    List<Lemming> lemmingsPeople ;
    Timer timer ;
    // Le maitre du jeu à qui on demande les configurations
    // successives du jeu
    GameMaster gameMaster ;
    // Le temps restant avant que le level ne se termine
    private int currentTime ;
    // La vitesse courante (x1, x2, x4 ou x0)
    private int currentSpeed ;
    // La vitesse d'apparition des lemmings
    private int vitesseApparitionLemming ;
    // La frequence minimum d'apparition des lemmings (vitesse min)
    private static int vitesseMax ;

    // Constructeur
    public Modele(String file) throws modele.parser.NiveauNonConformeException{
        this.level = new Level(new Parser(new File(file)));
	this.level_initial = new Level_proxy(level);
        this.level_proxy = new Level_proxy(this.level) ;
        lemmingsPeople = new ArrayList<Lemming>();
        for (int i = 0; i < level.getCount(); ++i) {
            this.lemmingsPeople.add(new Lemming(level_proxy));
        }
        // Création d'un timer qui, toutes les 200 ms,
        // Exécute la methode actionPerformed de la classe
        // ActionNextStep
        timer = new Timer(level.getSpeed(),new ActionNextStep());
        timer.start();
        gameMaster = new GameMaster(level);
        currentTime = level.getTime() ;
        currentSpeed = 1 ;
	vitesseMax = 10 ;
	vitesseApparitionLemming = vitesseMax ;
    }

    /** Faire apparaitre les lemmings NOT_YET_THERE
     *
     */
    // Methodes
    public List<Lemming> getLemmingsPeople(){
        return this.lemmingsPeople ;
    }

    public GameMaster getGameMaster(){
	return this.gameMaster ;
    }

    public Level_itf getLevel(){
        return this.level_proxy ;
    }
    
    public Level_itf getLevelInitial(){
	return this.level_initial ;
    }
    
    public /**@pure*/int getNbOut(){	
	return (this.gameMaster.getNbApparus() - this.gameMaster.getNbSaved() - this.gameMaster.getNbDead()) ;
    }
    
    public boolean isWon(){
	return this.gameMaster.isWon();
    }

    /** Si le jeu est termine ou non
     * @return true s'il est termine
     */
    public boolean isFinished(){
	return (this.gameMaster.isFinished()||this.currentTime==0);
    }

    public void affecterCompetence(int x, int y, Constructor<?> constructeurCompetence) {
        Lemming l = GameMaster.findLemming(x,y,lemmingsPeople) ;
        if (l != null){
            try {
                l.setCompetence((Competence)constructeurCompetence.newInstance(new Object[] {
                            level,
                            new Position(x,y,level.getLength(),level.getHeight()),
                            Boolean.valueOf(true),
                            Boolean.valueOf(false),
                            Integer.valueOf(0),
                            Integer.valueOf(0),
                            Boolean.valueOf(false),
                            Integer.valueOf(5)
                        }));
            } catch (Exception e) {
                e.printStackTrace() ;
            }
        }
    }
    // System.out.println(c==null ? "NULL" : "NOTNULL");

    public String getCurrentTime() {
        return (((Integer)currentTime).toString() + "s restantes");
    }

    /** On met en pause ou relance le timer dans cette fonction
     * @return le pourcentage de vitesse après le changement
     */
    public int startPause() {
        // Si le timer tourne on le met sur pause
        if (timer.isRunning()) {
            timer.stop();
            return 0 ;
        } else { // Sinon, on le relance
            timer.start();
            return this.currentSpeed ;
        }
    }

     /** On accélère ou ralentit le timer dans cette fonction
     * En pratique un roulement s'effectue comme suit :
     * 200 ms -> 100 ms -> 50 ms -> 200 ms ...
     *@return La vitesse après le changement
     */
    public int fastForward() {
        int speed = level.getSpeed();
        int delay = timer.getDelay();
        if (delay == speed) {
            timer.setDelay(speed/2);
            this.currentSpeed = 2;
        } else if (delay == speed/2) {
            timer.setDelay(speed/4);
            this.currentSpeed = 4;
        } else {
            timer.setDelay(speed);
            this.currentSpeed = 1 ;
        }
        return this.currentSpeed ;
    }

    public void killThemAll(List<Lemming> lemmingsPeople){
	gameMaster.killThemAll(lemmingsPeople);
	notifyVue() ;
    }
    
    public void moreLemmings(){
	if (vitesseApparitionLemming > 0){
	    this.vitesseApparitionLemming-- ;
	}
    }
    
    public void lessLemmings(){
	if (vitesseApparitionLemming < vitesseMax){
	    this.vitesseApparitionLemming++ ;
	}
    }
    
    public void restart(){
	this.level.update(getLevelInitial());
        this.level_proxy = new Level_proxy(this.level) ;
        lemmingsPeople = new ArrayList<Lemming>();
        for (int i = 0; i < level.getCount(); ++i) {
            this.lemmingsPeople.add(new Lemming(level_proxy));
        }
        timer = new Timer(level.getSpeed(),new ActionNextStep());
        timer.start();
        gameMaster = new GameMaster(level);
        currentTime = level.getTime() ;
        currentSpeed = 1 ;
	vitesseMax = 10 ;
	vitesseApparitionLemming = vitesseMax ;
	notifyVue();

    }
    
    // Fonction qui "commit / push" des changements à la vue
    // A APPELER A CHAQUE FOIS QUE LA VUE DOIT CHANGER
    // EN FONCTION DU MODELE
    private void notifyVue() {
	setChanged();
	notifyObservers();
    }

    private class ActionNextStep implements ActionListener {

        private static final long serialVersionUID = 1L;
        // Entier qui va cadencer l'évolution du temps
        // en permettant l'incrémentation de currentTime
        // toutes les secondes
        private int hashTimeSeconde ;
	private int hashTimeLemming ;

        public ActionNextStep() {
            this.hashTimeSeconde = 0 ;
	    this.hashTimeLemming = 0 ;
        }

        public void actionPerformed(ActionEvent e) {
	    // On regarde si on doit faire apparaitre un lemming
	    boolean faireApparaitre = false ;
	    hashTimeLemming++ ;
	    if (hashTimeLemming >= vitesseApparitionLemming){
		faireApparaitre = true ;
		hashTimeLemming = 0 ;
	    }

            try {
                // On demande au gameMaster d'exécuter un pas du jeu
                // (pour l'instant on ne le fait pas car ca fait n'imp
		if (!(gameMaster.isFinished())) {
		    gameMaster.nextStep(level,level_proxy,lemmingsPeople,faireApparaitre);
		} else {
		    // JEU TERMINE !!!!!!
		}
            } catch (Exception exc) {
                System.out.println("Le maitre du jeu a levé l'exception suivante : " + exc);
		exc.printStackTrace();
            }
            // On regarde ensuite s'il faut ou non mettre à jour le temps restant
            hashTimeSeconde ++ ;
            if (hashTimeSeconde == 5) {
                currentTime -- ;
                hashTimeSeconde = 0 ;
            }


            // Enfin, on regarde si on est arrivé à la fin du temps imparti :
            if (currentTime == 0) {
                // TODO : ARRETER LE NIVEAU
                timer.stop();
            }
            notifyVue() ;
        }
    }
}
