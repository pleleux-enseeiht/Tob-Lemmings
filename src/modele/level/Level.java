package modele.level;
import modele.parser.*;
import modele.position.*;
import modele.element.*;

/** La classe Level appelle le parser pour créer le niveau du jeu. C'est aussi la classe qui modifie la map en pouvant ajouter ou supprimer un bloc.
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class Level implements Level_itf {

    //Attributs
    private int speed;	// la vitesse du jeu (en millisecondes)
    private int time;	// le temps maximum (en secondes)
    private int count;	// le nombre total de Lemmings
    private int rescue;	// le nombre de Lemmings qu’il faut sauver
    private int climber;	// nombre d’aptitudes disponibles
    private int floater;	// idem
    private int blocker;	// idem
    private int bomber;	// idem
    private int builder;	// idem
    private int basher;	// idem
    private int digger;	// idem
    private int miner;	// idem
    private int length;	// longueur de la carte
    private int height;	// hauteur de la carte
    private Position positionTrappe;	// position de la trappe
    private Position positionSortie;	// position de la sortie
    private Element_itf[][] map;	// carte du niveau

    //Constructeur
    public Level(Parser_itf parser) {
	    this.speed = parser.getSpeed();
	    this.time = parser.getTime();
	    this.count = parser.getCount();
	    this.rescue = parser.getRescue();
	    this.climber = parser.getClimber();
	    this.floater = parser.getFloater();
	    this.blocker = parser.getFloater();
	    this.bomber = parser.getBomber();
	    this.builder = parser.getBuilder();
	    this.basher = parser.getBasher();
	    this.digger = parser.getDigger();
	    this.miner = parser.getMiner();
	    this.length = parser.getLength();
	    this.height = parser.getHeight();
	    this.positionTrappe = parser.getPositionTrappe();
	    this.positionSortie = parser.getPositionSortie();
	    this.map = parser.getMap();
    }
    
    // Methodes 
    public void update(Level_itf level) {
	this.speed = level.getSpeed();
	this.time = level.getTime();
	this.count = level.getCount();
	this.rescue = level.getRescue();
	this.climber = level.getClimber();
	this.floater = level.getFloater();
	this.blocker = level.getFloater();
	this.bomber = level.getBomber();
	this.builder = level.getBuilder();
	this.basher = level.getBasher();
	this.digger = level.getDigger();
	this.miner = level.getMiner();
	this.length = level.getLength();
	this.height = level.getHeight();
	this.positionTrappe = level.getPositionTrappe();
	this.positionSortie = level.getPositionSortie();
	this.map = level.getMap();
    }
    
    // Gestion de speed
    /** obtenir le parametre speed du niveau : la vitesse du jeu (en millisecondes)
     * @return speed le parametre speed
     */ 
    public int getSpeed() {
	return this.speed;
    }

    /** Changer le parametre speed
     * @param speed le nouveau parametre
     */
    public void setSpeed(int speed) {
	this.speed = speed;
    }

    // Gestion de time
    /** obtenir le parametre time du niveau : le temps maximum (en secondes)
     * @return time le parametre time
     */ 
    public int getTime() {
	return this.time;
    }

    /** Changer le parametre time
     * @param time le nouveau parametre
     */
    public void setTime(int time) {
	this.time = time;
    }

    // Gestion de count
    /** obtenir le parametre count du niveau : nombre total de Lemmings
     * @return count le parametre count
     */ 
    public int getCount() {
	return this.count;
    }

    /** Changer le parametre count
     * @param count le nouveau parametre
     */
    public void setCount(int count) {
	this.count = count;
    }

    // Gestion de rescue
    /** obtenir le parametre rescue du niveau : nombre de Lemmings qu’il faut sauver
     * @return rescue le parametre rescue
     */ 

    public int getRescue() {
	return this.rescue;
    }

    /** Changer le parametre rescue
     * @param rescue le nouveau parametre
     */

    public void setRescue(int rescue) {
	this.rescue = rescue;
    }

    // Gestion de climber
    /** obtenir le parametre climber du niveau : nombre d’aptitudes climber disponibles
     * @return climber le parametre climber
     */
    public int getClimber() {
	return this.climber;
    }

    /** Changer le parametre climber
     * @param climber le nouveau parametre
     */
    public void setClimber(int climber) {
	this.climber = climber;
    }

    // Gestion de floater
    /** obtenir le parametre floater du niveau : nombre d’aptitudes floater disponibles
     * @return floater le parametre floater
     */
    public int getFloater() {
	return this.floater;
    }

    /** Changer le parametre floater
     * @param floater le nouveau parametre
     */
    public void setFloater(int floater) {
	this.floater = floater;
    }

    // Gestion de bomber
    /** obtenir le parametre bomber du niveau : nombre d’aptitudes bomber disponibles
     * @return bomber le parametre bomber
     */
    public int getBomber() {
	return this.bomber;
    }

    /** Changer le parametre bomber
     * @param bomber le nouveau parametre
     */
    public void setBomber(int bomber) {
	this.bomber = bomber;
    }

    // Gestion de builder
    /** obtenir le parametre builder du niveau : nombre d’aptitudes builder disponibles
     * @return builder le parametre builder
     */
    public int getBuilder() {
	return this.builder;
    }

    /** Changer le parametre builder
     * @param builder le nouveau parametre
     */
    public void setBuilder(int builder) {
	this.builder = builder;
    }

    // Gestion de basher
    /** obtenir le parametre basher du niveau : nombre d’aptitudes basher disponibles
     * @return basher le parametre basher
     */
    public int getBasher() {
	return this.basher;
    }

    /** Changer le parametre basher
     * @param basher le nouveau parametre
     */
    public void setBasher(int basher) {
	this.basher = basher;
    }

    // Gestion de digger
    /** obtenir le parametre digger du niveau : nombre d’aptitudes digger disponibles
     * @return digger le parametre digger
     */
    public int getDigger() {
	return this.digger;
    }

    /** Changer le parametre digger
     * @param digger le nouveau parametre
     */
    public void setDigger(int digger) {
	this.digger = digger;
    }

    // Gestion de miner
    /** obtenir le parametre miner du niveau : nombre d’aptitudes miner disponibles
     * @return miner le parametre miner
     */
    public int getMiner() {
	return this.miner;
    }

    /** Changer le parametre miner
     * @param miner le nouveau parametre
     */
    public void setMiner(int miner) {
	this.miner = miner;
    }

    // Gestion de length
    /** obtenir le parametre length du niveau : longueur de la carte
     * @return length le parametre length
     */
    public int getLength() {
	return this.length;
    }

    // Gestion de height
    /** obtenir le parametre height du niveau : hauteur de la carte
     * @return height le parametre height
     */
    public int getHeight() {
	return this.height;
    }

    // Gestion de positionTrappe
    /** obtenir la position de la trappe dans la carte
     * @return positionTrappe la position de la trappe
     */
    public Position getPositionTrappe() {
	return this.positionTrappe;
    }

     // Gestion de positionSortie
    /** obtenir la position de la sortie dans la carte
     * @return positionSortie la position de la sortie
     */
    public Position getPositionSortie() {
	return this.positionSortie;
    }

    // Gestion de map
    /** obtenir la carte du niveau sous la forme d'une matrice de caractères avec
     * solide indestructible:*, destructible:+, semi-destructibles:< > V, sortie:@,
     * broyeur:%, lance-flamme:!, trappe:0
     * @return map la carte du niveau
     */
    public Element_itf[][] getMap() {
	return this.map;
    }

    /** Enlever un bloc en mettant le caractere ' ' a sa place
     * @param positionDuLemming la position du lemming
     * @param positionDesBlocs la position du bloc a detruire
     * @throws ElementNonDestructibleException quand on essaie de detruire un bloc indestructible.
     */
    public void destroy(Position positionDuLemming, Position[] positionDesBlocs) throws ElementNonDestructibleException {
	if (positionDesBlocs.length == 1 ) {
		if (map[positionDesBlocs[0].getX()][positionDesBlocs[0].getY()].estDestructible(positionDuLemming)) {
		    map[positionDesBlocs[0].getX()][positionDesBlocs[0].getY()] = new ElementVide(positionDesBlocs[0]);
		} else {
		    throw new ElementNonDestructibleException();
		}
	} else if (positionDesBlocs.length == 2 ) {
		if (map[positionDesBlocs[0].getX()][positionDesBlocs[0].getY()].estDestructible(positionDuLemming)) {
		    map[positionDesBlocs[0].getX()][positionDesBlocs[0].getY()] = new ElementVide(positionDesBlocs[0]);
		} else {
		    throw new ElementNonDestructibleException();
		}
		if (map[positionDesBlocs[1].getX()][positionDesBlocs[1].getY()].estDestructible(positionDuLemming)) {
		    map[positionDesBlocs[1].getX()][positionDesBlocs[1].getY()] = new ElementVide(positionDesBlocs[1]);
		} else {
		    throw new ElementNonDestructibleException();
		} 
	} else {
		for (int i = 0; i < positionDesBlocs.length; ++i) {
			if (positionDesBlocs[i]!=null) {
				if (map[positionDesBlocs[i].getX()][positionDesBlocs[i].getY()].estDestructible(positionDuLemming)) {
				    map[positionDesBlocs[i].getX()][positionDesBlocs[i].getY()] = new ElementVide(positionDesBlocs[i]);
				}
			}
		}
	}			
    }

    /** Creer des blocs
     * @param block le bloc à ajouter
     * @throws BlockAlreadyThereException quand on essaie de créer un bloc qui n'est pas vide au
     * depart.
     */
    public void addBlocks(Element_itf block) throws BlockAlreadyThereException {
	if (map[block.getPosition().getX()][block.getPosition().getY()] instanceof ElementVide) {
		map[block.getPosition().getX()][block.getPosition().getY()] = block;
	}	
	else {
		throw new BlockAlreadyThereException();
	}
    }

    /** Enlever un ElementBloquant
     * @param position la position du lemming bloquant
     */
    public void deleteBlockingLemming(Position pos) {
	if (map[pos.getX()][pos.getY()] instanceof ElementBloquant) {
		map[pos.getX()][pos.getY()] = new ElementVide(pos);
	}
    }

    // Affichage du niveau
    /** afficher tous les détails du niveau
     * @return string détail du niveau
     */
    public String toString() {
	String string;	// Le contenu du fichier de carte
	string = "speed "+this.speed+"\ntime "+this.time+"\ncount "+this.count+"\nrescue "+this.rescue+"\nclimber "+this.climber;
	string += "\nfloater "+this.floater+"\nblocker "+this.blocker+"\nbomber "+this.bomber+"\nbuilder "+this.builder;
	string += "\nbasher "+this.basher+"\ndigger "+this.digger+"\nminer "+this.miner+"\n";
	string += "hauteur de la carte : " + this.getHeight() + "\nlargeur de la carte : " + this.getLength() + "\n";
	string += "position initiale : " + this.getPositionTrappe() + "\n";
	string += "position finale : " + this.getPositionSortie() + "\n";
	for (int y  = this.getHeight()-1; y >=0; y--) {
	    for (int x = 0; x < this.getLength(); x++) {
		if (map[x][y] instanceof ElementDestructible) {
			string += "+";
		} 
		else if (map[x][y] instanceof ElementIndestructible) {
			string += "*";
		}
		else if (map[x][y] instanceof ElementSemiDestructibleDroite) {
			string += "<";
		}
		else if (map[x][y] instanceof ElementSemiDestructibleGauche) {
			string += ">";
		}
		else if (map[x][y] instanceof ElementSemiDestructibleDessus) {
			string += "v";
		}
		else if (map[x][y] instanceof ElementSortie) {
			string += "@";
		}
		else if (map[x][y] instanceof ElementBroyeur) {
			string += "%";
		}
		else if (map[x][y] instanceof ElementLanceFlamme) {
			string += "!";
		}
		else if (map[x][y] instanceof ElementTrappe) {
			string += "O";
		}
		else if (map[x][y] instanceof ElementVide) {
			string += " ";
		}
	    }
	    string += "\n";
	}
	return string;
    }
}
