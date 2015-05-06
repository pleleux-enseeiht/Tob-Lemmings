import modele.parser.* ;
import modele.position.* ;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Parser.
 * @author	Jessica Hornik
 * @version	1.0
 */
public class ParserTest {

    // précision pour la comparaison entre réels.
    public final static double EPSILON = 1e-6;

    private Parser p;
    private Parser p1;
    
    @Before
	public void setUp() {
	try {
	    p = new Parser(new File("./exemple.lemmings")) ;
	} catch (modele.parser.NiveauNonConformeException e) {
	}
    }

    /** Vérifier si deux positions ont mêmes coordonnées.
     * @param p1 la premiere position
     * @param p2 la deuxième position
     */
    static void memesCoordonnees(String message, Position p1, Position p2) {
	assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
	assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
    }

    @Test
	public void testInitialisation() {
	assertNotNull(p);
	assertEquals("getSpeed incorrect ", p.getSpeed(), 200, EPSILON);
	assertTrue(p.getTime() == 120);
	assertTrue(p.getCount() == 114);
	assertTrue(p.getRescue() == 60) ;
	assertTrue(p.getClimber() == 10) ;
	assertTrue(p.getFloater() == 10) ;
	assertTrue(p.getBomber() == 10) ;
	assertTrue(p.getBuilder() == 10) ;
	assertTrue(p.getBasher() == 10) ;
	assertTrue(p.getDigger() == 10) ;
	assertTrue(p.getMiner() == 10) ;
	assertTrue(p.getLength() == 70) ;
	assertTrue(p.getHeight() == 25) ;
	    memesCoordonnees("Position Trappe", new Position(3,19,69,24),p.getPositionTrappe());
	    memesCoordonnees("Position Sortie", new Position(67,10,69,24),p.getPositionSortie());
    }

    @Test
	public void testGetMap() {
	assertTrue(p != null);
    }
    
	
    
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(TestParser.class.getName());
    }
}
