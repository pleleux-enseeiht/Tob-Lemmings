package modele.element;

import modele.position.*;

/** La classe Level ElementSortie implante la structure d'un bloc de type sortie.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class ElementSortie extends ElementVide {

//Constructeur
	public ElementSortie(Position position) {
		super(position);
	}

// Affichage du niveau
	/** afficher tous les details du niveau
	 * @return string detail du niveau
	 */
	public String toString() {
		String string;
		string = "Element de type sortie : (" + this.getPosition().getX() + ", " + this.getPosition().getY() +")";
		return string;
	}
}

