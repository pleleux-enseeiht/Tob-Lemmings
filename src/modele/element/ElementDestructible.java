package modele.element;

import modele.position.*;

/** La classe Level ElementDestructible implante la structure d'un bloc de type element destructible.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class ElementDestructible implements Element_itf {

//Attributs
	private Position position;	// Position de l'element dans la grille

//Constructeur
	public ElementDestructible(Position position) {
		this.position = position;
	}

//Gestion de la position
	/** Obtenir la position
	 * @return position la position du bloc
	 */
	public Position getPosition() {
		return this.position;
	}

// Méthode estDestructible
	/** Renvoie vrai si l'élément est destructible a la position
	 * @param position la position depuis laquelle on tente de détruire l'élément
	 * @return true si on peut le detruire, false sinon
	 */
	public boolean estDestructible (Position pos) {
		boolean nearX = (((pos.getX()-this.position.getX())<=1)&&((pos.getX()-this.position.getX())>=-1));
		boolean nearY = (((pos.getY()-this.position.getY())<=1)&&((pos.getY()-this.position.getY())>=-1));
		return nearX&&nearY;
	}

// Affichage du niveau
	/** afficher tous les détails du niveau
	 * @return string détail du niveau
	 */
	public String toString() {
		String string;
		string = "Element de type destructible : (" + this.position.getX() + ", " + this.position.getY() +")";
		return string;
	}

}

