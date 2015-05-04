package modele.element;

import modele.position.*;

/** La classe Level ElementSemiDestructibleGauche implante la structure d'un bloc de type element semi-destructible >.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class ElementSemiDestructibleGauche implements Element_itf {

//Attributs
	private Position position;	// Position de l'element dans la grille

//Constructeur
	public ElementSemiDestructibleGauche(Position position) {
		this.position = position;
	}

//Gestion de la position
	/** Obtenir la position
	 * @return position la position du bloc
	 */
	public Position getPosition() {
		return this.position;
	}

// Methode estDestructible
	/** Renvoie vrai si l'element est destructible a la position
	 * @param position la position depuis laquelle on tente de détruire l'élément
	 * @return true si on peut le detruire, false sinon
	 */
	public boolean estDestructible (Position position) {
		return (position.getX() == this.position.getX()-1);
	}

// Affichage du niveau
	/** afficher tous les details du niveau
	 * @return string detail du niveau
	 */
	public String toString() {
		String string;
		string = "Element de type semi-destructible > : (" + this.position.getX() + ", " + this.position.getY() +")";
		return string;
	}

}

