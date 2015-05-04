package modele.level ;
/** 
 * Cette exception est levee lorsque le niveau reel tente d'effectuer un update
 * @author Jessica Hornik
 * @version 1.0
 */

public class LevelNonUpdatableException extends Exception {
    static final long serialVersionUID = 4L;
    /** Initialiser LevelNonUpdatableException */
    public LevelNonUpdatableException(){
	super();
    }
}
