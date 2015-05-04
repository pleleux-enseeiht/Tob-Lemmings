package modele.level;

/**
  * Cette exception est levée lorsque l'on essaye de modifier le niveau à partir du lemming
  * @author	Philippe Leleux
  * @version	1.0
  */

public class TentativeDeModificationFrauduleuseException extends Exception {
	static final long serialVersionUID = 4L;

	/** Initialiser TentativeDeModificationFrauduleuseException avec le message 
	 * "On ne peut pas modifier le niveau à partir du lemming"
	 */
	public TentativeDeModificationFrauduleuseException(String methode) {
		super("Exception " + methode + " : On ne peut pas modifier le niveau à partir du lemming");
	}

}
