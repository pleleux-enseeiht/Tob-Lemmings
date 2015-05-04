package modele.position ;

/** Position est une paire d'entier qui représente la position dans la matrice
 *
 * @author  Philippe Leleux et Jessica Hornik
 * @version 1.1
 */
public class Position {

    /**
     * private static final int X_MAX = 69 ;
     * private static final int Y_MAX = 24 ;
     */

    //@ private invariant x <= xMax ;
    //@ private invariant y <= yMax ;
    //@ private invariant getX() == x ;
    //@ private invariant getY() == y ;

    //Attributs
    private int x;		// abscisse
    private int y;		// ordonnée
    private int xMax; // valeur max que peut prendre l'abscisse
    private int yMax; // valeur max que peut prendre l'ordonee

    //Constructeur
    /** Construire une position à partir de ses coordonnées
     * @param vx première coordonnée
     * @param vy deuxième coordonnée
     * @param xMax_ valeur max que peut prendre x
     * @param yMax_ valeur max que peut prendre y
     */
    public Position(int vx, int vy, int xMax_, int yMax_) { // Add arguments : xMax yMax du niveau
	// System.out.println("CONSTRUCTEUR Point(" + vx + ", " + vy + ")");
	assert (!((vx > xMax_) || (vy > yMax_) || (x < 0) || (y < 0)));
        this.x = vx;
        this.y = vy;
        this.xMax = xMax_ ;
        this.yMax = yMax_ ;
    }

    public Position(Position autre) {
        this(autre.getX(),autre.getY(),autre.getXMax(),autre.getYMax());
    }

    //Gestion de la première coordonnée
    /** Obtenir la première coordonnée
     * @return première coordonnée
     */
    public int getX() {
	return this.x;
    }

    /** Changer la première coordonnée
     * @param vx première coordonnée
     */
    // @ requires (vx <= xMax) && (x >= 0);
    public void setX(int vx) {
	assert (!((vx > xMax) || (x < 0)));
	this.x = vx;
    }

    //Gestion de la deuxième coordonnée
    /** Obtenir la deuxième coordonnée
     * @return deuxième coordonnée
     */
    public int getY() {
	return this.y;
    }

    /** Changer la deuxième coordonnée
     * @param vy deuxième coordonnée
     */
    // @ requires (vy <= yMax) && (y >= 0);
    public void setY(int vy) {
	assert (!((vy > yMax) || (y < 0)));
	    this.y = vy;
    }

    //Gestion de xMax et yMax
    /** Obtenir xMax
     * @return abscisse maximum
     */
    public int getXMax() {
	return this.xMax ;
    }

    /** Obtenir yMax
     * @return ordonnee maximum
     */
    public int getYMax(){
	return this.yMax ;
    }

    /** Transforme la position (x,y) abscisse, ordonnee en position (i,j) de tableau
     * @return i la ligne du tableau
     */
    public int getIArray(){
	return (this.getYMax() - 1 - this.y);
    }
    /** Transforme la position (x,y) abscisse, ordonnee en position (i,j) de tableau
     * @return j la colonne du tableau
     */
    public int getJArray(){
	return this.x ;
    }


    /** Afficher la position. */
    public String toString() {
	return ("(" + this.x + ", " + this.y + ")");
    }

    // Obtenir une position adjacente

    /** Obtenir la position a l'Est (E)
     * @return la position E
     */
    public /**@pure*/ Position getPositionE() {
	return new Position(this.getX()+1,this.getY(),this.xMax,this.yMax);
    }

    /** Obtenir la position W
     * @return la position W
     */
    public /**@pure*/ Position getPositionW() {
	return new Position(this.getX()-1,this.getY(),this.xMax,this.yMax);
    }
     /** Obtenir la position S
     * @return la position S
     */
    public /**@pure*/ Position getPositionS() {
	return new Position(this.getX(),this.getY()-1,this.xMax,this.yMax);
    }
    /** Obtenir la position en haut N
     * @return la position en haut
     */
 public /**@pure*/ Position getPositionN() {
	return new Position(this.getX(),this.getY()+1,this.xMax,this.yMax);
    }
     /** Obtenir la position NE
     * @return la position NE
     */
    public /**@pure*/ Position getPositionNE() {
	return new Position(this.getX()+1,this.getY()+1,this.xMax,this.yMax);
    }
     /** Obtenir la position NW
     * @return la position NW
     */
    public /**@pure*/ Position getPositionNW() {
	return new Position(this.getX()-1,this.getY()+1,this.xMax,this.yMax);
    }
    /** Obtenir la position SE
     * @return la position SE
     */
 public /**@pure*/ Position getPositionSE() {
	return new Position(this.getX()+1,this.getY()-1,this.xMax,this.yMax);
    }
    /** Obtenir la position SW
     * @return la position SW
     */
 public /**@pure*/ Position getPositionSW() {
	return new Position(this.getX()-1,this.getY()-1,this.xMax,this.yMax);
    }


}
