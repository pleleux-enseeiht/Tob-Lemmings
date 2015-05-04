package modele.element;

import modele.position.*;

/** La classe Level_itf est l'interface que les differentes classes element vont implanter.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public interface Element_itf {

// Gestion de la position
	/** Obtenir la position
	 * @return position la position
	 */
	public Position getPosition();

// Méthode estDestructible
	/** Renvoie vrai si l'élément est destructible a la position
	 * @param position la position depuis laquelle on tente de détruire l'élément
	 * @return true si on peut le detruire, false sinon
	 */
	public boolean estDestructible (Position position);

// Affichage du niveau
	/** afficher tous les détails de l'element
	 * @return string détail de l'element
	 */
	public String toString();

}

