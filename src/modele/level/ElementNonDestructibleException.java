package modele.level;

/**
  * Cette exception est levée lors d'une tentative d'utilisation de la methode destroy 
  * sur un element non destructible.
  * @author	Philippe Leleux
  * @version	1.0
  */

public class ElementNonDestructibleException extends Exception {
	static final long serialVersionUID = 4L;

	/** Initialiser ElementNonDestructibleException avec le message 
	 * "Exception destroy : element indestructible"
	 */
	public ElementNonDestructibleException() {
		super("Exception destroy : element indestructible");
	}

}
