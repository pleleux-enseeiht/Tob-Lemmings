package modele.element;

import modele.position.*;

/** La classe Level ElementBroyeur implante la structure d'un bloc de type broyeur.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class ElementBroyeur extends ElementVide {

    //Constructeur
    public ElementBroyeur(Position position) {
        super(position);
    }

    // Affichage du niveau
    /** afficher tous les details du niveau
     * @return string detail du niveau
     */
    public String toString() {
        String string;
        string = "Element de type broyeur : (" + this.getPosition().getX() + ", " + this.getPosition().getY() +")";
        return string;
    }
}

