package modele.parser;

/**
  * Cette exception est levée lorsque le fichier du niveau n'est pas à un format conforme 
  * avec le parser associé.
  * @author	Philippe Leleux
  * @version	1.0
  */

public class NiveauNonConformeException extends Exception {
	static final long serialVersionUID = 4L;

	/** Initialiser NiveauNonConformeException avec le message 
	 * "Exception Parser : format du niveau non conforme"
	 */
	public NiveauNonConformeException() {
		super("Exception Parser : format du niveau non conforme");
	}

}
