package modele.competence ;

import modele.position.*;
import modele.level.* ;

/** Competence est une interface
 * @author Jessica Hornik
 * @version 1.0
 */

public interface Competence {
    /** Action a effectuer.
     * @param level le niveau disponible
     * @return l'action a effectuer
     */
    public Action actionToPerform(Level_itf level) ;
    
    /** Actualise la competence avec les elements courants du niveau et du lemming
     * @param level_ le niveau et ses informations
     * @param pos_ la position du lemming
     * @param dir_ la direction du lemming
     * @param fallSince_ le nombre de cases duquel il tombe
     * @param waitBeforeNextAction_
     * @param climbing_ le lemming escalade un mur
     * @param timeBeforeDie_ 
     */
    
    public void update(Level_itf level_,Position pos_, boolean dir_, int fallSince_,int waitBeforeNextAction_,boolean climbing_,int timeBeforeDie_) ;
    
    
    // Comparaison de competences par ordre de priorite de traitement dans la liste
    public int compareTo(Competence competence_) ;
}
