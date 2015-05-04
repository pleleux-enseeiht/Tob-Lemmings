package modele.element;

import modele.position.*;

/** La classe Level ElementBloquant implante la structure d'un bloc de type element bloquant.
 *
 * @author  Jessica Hornik
 * @version 1.0
 */

public class ElementBloquant extends ElementIndestructible {
    // Constructeur
    public ElementBloquant(Position position){
        super(position);
    }

    // Affichage du niveau
    /** afficher tous les details du niveau
     * @return string detail du niveau
     */
    public String toString(){
        String res ;
        res = "Element de type bloquant : (" + this.position.getX() + "," + this.position.getY() + ")" ;
        return res ;
    }
}