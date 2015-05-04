package modele.parser;

import modele.position.*;
import modele.element.*;

/** La classe Parser_itf est l'interface que vont implanter les différentes classes Parser
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public interface Parser_itf {

	/** obtenir le parametre speed du niveau : la vitesse du jeu (en millisecondes)
	 * @return speed le parametre speed
	 */ 
	public int getSpeed();

	/** obtenir le parametre time du niveau : le temps maximum (en secondes)
	 * @return time le parametre time
	 */ 
	public int getTime();

	/** obtenir le parametre count du niveau : nombre total de Lemmings
	 * @return count le parametre count
	 */ 
	public int getCount();

	/** obtenir le parametre rescue du niveau : nombre de Lemmings qu’il faut sauver
	 * @return rescue le parametre rescue
	 */ 
	public int getRescue();

	/** obtenir le parametre climber du niveau : nombre d’aptitudes climber disponibles
	 * @return climber le parametre climber
	 */
	public int getClimber();

	/** obtenir le parametre floater du niveau : nombre d’aptitudes floater disponibles
	 * @return floater le parametre floater
	 */
	public int getFloater();

	/** obtenir le parametre bomber du niveau : nombre d’aptitudes bomber disponibles
	 * @return bomber le parametre bomber
	 */
	public int getBomber();

	/** obtenir le parametre builder du niveau : nombre d’aptitudes builder disponibles
	 * @return builder le parametre builder
	 */
	public int getBuilder();

	/** obtenir le parametre basher du niveau : nombre d’aptitudes basher disponibles
	 * @return basher le parametre basher
	 */
	public int getBasher();

	/** obtenir le parametre digger du niveau : nombre d’aptitudes digger disponibles
	 * @return digger le parametre digger
	 */
	public int getDigger();

	/** obtenir le parametre miner du niveau : nombre d’aptitudes miner disponibles
	 * @return miner le parametre miner
	 */
	public int getMiner();

	/** obtenir la longueur de la carte
	 * @return length le parametre length
	 */
	public int getLength();

	/** obtenir la hauteur de la carte
	 * @return height le parametre height
	 */
	public int getHeight();

    /** obtenir la position de la trappe
     * @return positionTrappe la position de la trappe
     */
    public Position getPositionTrappe();

    /** obtenir la position de la sortie
     * @return positionSortie la position de la sortie
     */
    public Position getPositionSortie();

	/** obtenir la carte du niveau sous la forme d'une matrice de caractères avec
	 * solide indestructible:*, destructible:+, semi-destructibles:< > V, sortie:@,
	 * broyeur:%, lance-flamme:!, trappe:0
	 * @return map la carte du niveau
	 */
	public Element_itf[][] getMap();

	/** afficher tous les détails du niveau
	 * @return string détail du niveau
	 */
	public String toString();

}

