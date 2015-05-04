package modele.element;

import modele.position.*;

/** La classe Level ElementIndestructible implante la structure d'un bloc de type element indestructible.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class ElementIndestructible implements Element_itf {

//Attributs
	protected Position position;	// Position de l'element dans la grille

//Constructeur
	public ElementIndestructible(Position position) {
		this.position = position;
	}

//Gestion de la position
	/** Obtenir la position
	 * @return position la position
	 */
	public Position getPosition() {
		return this.position;
	}

// Méthode estDestructible
	/** Renvoie vrai si l'élément est destructible a la position
	 * @param position la position depuis laquelle on tente de détruire l'élément
	 * @return true si on peut le detruire, false sinon
	 */
	public boolean estDestructible (Position position) {
		return false;
	}

// Affichage du niveau
	/** afficher tous les détails du niveau
	 * @return string détail du niveau
	 */
	public String toString() {
		String string;
		string = "Element de type indestructible : (" + this.position.getX() + ", " + this.position.getY() +")";
		return string;
	}

}

