package controller ;

import modele.* ;
import modele.position.*;
import modele.competence.*;
import modele.lemming.*;
import modele.level.*;
import modele.element.*;

import vue.* ;

import javax.swing.* ;
import java.util.* ;

import java.lang.Class ;
import java.lang.reflect.Constructor ;
import java.lang.Math ;

public class Controller{

    // Attributs
    private Modele modele ;

    // Constructeur
    public Controller(Modele modele_) {
        this.modele = modele_ ;
    }

    /** Obtenir la liste des lemmings
     * @return la liste des lemmings
     */
    public List<Lemming> getLemmingsPeople(){
        return modele.getLemmingsPeople() ;
    }

    /** Obtenir le niveau
     * @return le niveau du modele
     */
    public Level_itf getLevel(){
        return modele.getLevel();
    }

    /** Affecte le temps restant au JLabel l'affichant
     * @param labelCurrentTime : la label à mettre à jour
     */
    public void setCurrentTime(JLabel labelCurrentTime) {
        labelCurrentTime.setText(modele.getCurrentTime());
    }

    /** Fonction qui relance ou met en pause le jeu
     * en fonction de s'il est ou non déjà en pause
     * @param vitesse : le label à mettre à jour
     */
    public void startPause(JLabel vitesse) {
        vitesse.setText("x" + ((Integer)modele.startPause()).toString());
    }

    /** Fonction qui accelere ou ralentit le jeu
     * en fonction de s'il est ou non déjà accéléré
     * @param vitesse : le label à mettre à jour
     */
    public void fastForward(JLabel vitesse) {
        if (!(vitesse.getText().equals("x0"))) {
            vitesse.setText("x" + ((Integer)modele.fastForward()).toString());
        }
    }

    /** A partir de la position du lemming et du bouton competence,
     * on affecte la competence au lemming correspondant
     * @param x l'abscisse de la position du lemming
     * @param y l'ordonnee de la position du lemming
     * @param buttonCompetence le bouton de la competence a affecter
     */
    public void affecterCompetence(int x, int y, JToggleButton buttonCompetence){

        // On commence par récupérer l'objet qui représente la classe de la compétence à affecter
        // à partir du bouton dont le nom est justement le nom de cette classe
        try {
            Class classeCompetence = Class.forName("modele.competence."+buttonCompetence.getName());
            // A partir de cette classe, on va aller chercher le bon constructeur
            // grâce au tableau de ses types d'entrée
            @SuppressWarnings("unchecked")
                Constructor<?> constructeurCompetence = classeCompetence.getConstructor(new Class[] {
                        Class.forName("modele.level.Level_itf"),
                        Class.forName("modele.position.Position"),
                        Class.forName("java.lang.Boolean"),
                        Class.forName("java.lang.Boolean"),
                        Class.forName("java.lang.Integer"),
                        Class.forName("java.lang.Integer"),
                        Class.forName("java.lang.Boolean"),
                        Class.forName("java.lang.Integer")
                    });
            modele.affecterCompetence(x,y,constructeurCompetence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void afficherLemmings(JLabel[][] grille){
        int x_lemm, y_lemm ;
        List<Lemming> l = modele.getLemmingsPeople() ;

        // Mettre a jour la carte du jeu
        for (int y = modele.getLevel().getHeight()-1 ; y >= 0 ; --y){
            for (int x = 0 ; x < modele.getLevel().getLength() ; ++x){
                grille[x][y].setIcon(Vue.getImage(modele.getLevel().getMap()[x][y]));
                grille[x][y].setOpaque(true);
            }
        }

        // Mettre à jour les lemmings
        for (Lemming lemmCourant : l){
            if (lemmCourant.getState() == States.ALIVE){
                x_lemm = lemmCourant.getPosition().getX() ;
                y_lemm = lemmCourant.getPosition().getY();
                grille[x_lemm][y_lemm].setOpaque(false);
                grille[x_lemm][y_lemm].setIcon(new ImageIcon("./src/vue/lemming.png"));
            }
        }
    }

    
    public void endGame(){
	String etatJeu ;
	if (modele.isFinished()){
	    if (modele.isWon()) {
		etatJeu = "gagné" ;
	    } else {
		etatJeu = "perdu" ;
	    }

	    //Custom button text
	    Object[] options = {"Recommencer",
				"Passer au niveau suivant",
				"Quitter"};
	    int n = JOptionPane.showOptionDialog(new JFrame(),
						 "Vous avez "+etatJeu +" !\n"
						 + "Que voulez-vous faire ?",
						 "Niveau termine",
						 JOptionPane.YES_NO_CANCEL_OPTION,
						 JOptionPane.QUESTION_MESSAGE,
						 null,
						 options,
						 options[2]);
	    //*
	    switch (n){
	    case JOptionPane.NO_OPTION:
		changeLevel();
		break ;
	    case JOptionPane.YES_OPTION:
		restart();
		break ;
	    default:
		cancel();
	    }//*/
	}
    }

    public void restart(){
	System.out.println("restart");
	modele.restart();
    }
    
    public void changeLevel(){
	JOptionPane.showMessageDialog(new JFrame(), "Vous n'avez qu'un niveau de disponible, pour l'instant. Bientot...");
    }
    
    public void cancel(){
	// modele.removeObserver();
    }
    
    
    public void killThemAll(){
        modele.killThemAll(modele.getLemmingsPeople());
    }
    
    public void moreLemmings(){
	modele.moreLemmings();
    }
    
    public void lessLemmings(){
	modele.lessLemmings();
    }
    
    public void setSave(JLabel labelSave){
	labelSave.setText("Save " + this.modele.getLevel().getRescue() + " out of " + modele.getLevel().getCount());
    }

    public void setHome(JLabel labelHome){
	labelHome.setText("Home : " + this.modele.getGameMaster().getNbSaved()) ;
    }
    
    public void setOut(JLabel labelOut){
	labelOut.setText("Out : " + this.modele.getNbOut());
    }

    
}
