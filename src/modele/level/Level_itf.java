package modele.level;

import modele.element.*;
import modele.position.*;

/** La classe Level_itf est l'interface que les classes Level et Level_proxy vont implanter.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public interface Level_itf {
    /** Mettre a jour le level avec les infos correspondantes 
     * @param level
     */
    public void update(Level_itf level) ;

// Gestion de speed
	/** obtenir le parametre speed du niveau : la vitesse du jeu (en millisecondes)
	 * @return speed le parametre speed
	 */ 
	public int getSpeed();

	/** Changer le parametre speed
	  * @param speed le nouveau parametre
	  */
	public void setSpeed(int speed);

// Gestion de time
	/** obtenir le parametre time du niveau : le temps maximum (en secondes)
	 * @return time le parametre time
	 */ 
	public int getTime();

	/** Changer le parametre time
	  * @param time le nouveau parametre
	  */
	public void setTime(int time);

// Gestion de count
	/** obtenir le parametre count du niveau : nombre total de Lemmings
	 * @return count le parametre count
	 */ 
	public int getCount();

	/** Changer le parametre count
	  * @param count le nouveau parametre
	  */
	public void setCount(int count);

// Gestion de rescue
	/** obtenir le parametre rescue du niveau : nombre de Lemmings qu’il faut sauver
	 * @return rescue le parametre rescue
	 */ 
	public int getRescue();

	/** Changer le parametre rescue
	  * @param rescue le nouveau parametre
	  */
	public void setRescue(int rescue);

// Gestion de climber
	/** obtenir le parametre climber du niveau : nombre d’aptitudes climber disponibles
	 * @return climber le parametre climber
	 */
	public int getClimber();

	/** Changer le parametre climber
	  * @param climber le nouveau parametre
	  */
	public void setClimber(int climber);

// Gestion de floater
	/** obtenir le parametre floater du niveau : nombre d’aptitudes floater disponibles
	 * @return floater le parametre floater
	 */
	public int getFloater();

	/** Changer le parametre floater
	  * @param floater le nouveau parametre
	  */
	public void setFloater(int floater);

// Gestion de bomber
	/** obtenir le parametre bomber du niveau : nombre d’aptitudes bomber disponibles
	 * @return bomber le parametre bomber
	 */
	public int getBomber();

	/** Changer le parametre bomber
	  * @param bomber le nouveau parametre
	  */
	public void setBomber(int bomber);

// Gestion de builder
	/** obtenir le parametre builder du niveau : nombre d’aptitudes builder disponibles
	 * @return builder le parametre builder
	 */
	public int getBuilder();

	/** Changer le parametre builder
	  * @param builder le nouveau parametre
	  */
	public void setBuilder(int builder);

// Gestion de basher
	/** obtenir le parametre basher du niveau : nombre d’aptitudes basher disponibles
	 * @return basher le parametre basher
	 */
	public int getBasher();

	/** Changer le parametre basher
	  * @param basher le nouveau parametre
	  */
	public void setBasher(int basher);

// Gestion de digger
	/** obtenir le parametre digger du niveau : nombre d’aptitudes digger disponibles
	 * @return digger le parametre digger
	 */
	public int getDigger();

	/** Changer le parametre digger
	  * @param digger le nouveau parametre
	  */
	public void setDigger(int digger);

// Gestion de miner
	/** obtenir le parametre miner du niveau : nombre d’aptitudes miner disponibles
	 * @return miner le parametre miner
	 */
	public int getMiner();

	/** Changer le parametre miner
	  * @param miner le nouveau parametre
	  */
	public void setMiner(int miner);

// Gestion de length
	/** obteenir le parametre length du niveau : longueur de la carte
	* @return length le parametre length
	*/
	public int getLength();

// Gestion de height
	/** obtenir le parametre height du niveau : hauteur de la carte
	* @return height le parametre height
	*/
	public int getHeight() ;

// Gestion de positionTrappe
	/* obtenir la position de la trappe dans la carte
	* @return positionTrappe la position de la trappe
	*/
	public Position getPositionTrappe();

// Gestion de positionSortie
	/* obtenir la position de la sortie dans la carte
	* @return positionSortie la position de la sortie
	*/
	public Position getPositionSortie();
    

// Gestion de map
	/** obtenir la carte du niveau sous la forme d'une matrice de caractères avec
	 * solide indestructible:*, destructible:+, semi-destructibles:< > V, sortie:@,
	 * broyeur:%, lance-flamme:!, trappe:0
	 * @return map la carte du niveau
	 */
	public Element_itf[][] getMap();

	/** Enlever un bloc en mettant le caractere ' ' a sa place
	 * @param positionDuLemming la position du lemming
	 * @param positionDesBlocs la position du bloc a detruire
	 * @throws ElementNonDestructibleException quand on essaie de detruire un bloc indestructible.
	 */ 
	public void destroy(Position positionDuLemming, Position[] positionDesBlocs) throws ElementNonDestructibleException, TentativeDeModificationFrauduleuseException;

	/** Creer des blocs
	  * @param block le bloc à ajouter
	  * @throws BlockAlreadyThereException quand on essaie de créer un bloc qui n'est pas vide au
	  * depart.
	  */
    public void addBlocks(Element_itf block) throws BlockAlreadyThereException, TentativeDeModificationFrauduleuseException;

	/** Enlever un ElementBloquant
	 * @param position la position du lemming bloquant
	 */
	public void deleteBlockingLemming(Position pos) throws TentativeDeModificationFrauduleuseException;	

// Affichage du niveau
	/** afficher tous les détails du niveau
	 * @return string détail du niveau
	 */
	public String toString();

}

