package modele.level;

/**
  * Cette exception est levée lorsque l'on essaye d'ajouter un bloc déjà existant à la map
  * @author	Philippe Leleux
  * @version	1.0
  */

public class BlockAlreadyThereException extends Exception {
	static final long serialVersionUID = 4L;

	/** Initialiser BlockAlreadyThereException avec le message 
	 * "Exception addBlocks : element deja existant"
	 */
	public BlockAlreadyThereException() {
		super("Exception addBlocks : element deja existant");
	}

}
