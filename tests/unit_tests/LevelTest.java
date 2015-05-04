import modele.parser.* ;
import modele.level.* ;
import modele.position.* ;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Level
 * @author	Jessica Hornik et Philippe Leleux
 * @version	1.0
 */
public class LevelTest {

    // précision pour la comparaison entre réels.
    public final static double EPSILON = 1e-6;

    private Parser parser;
    private Level level;
    private Level level1;
    
    @Before
	public void setUp() {
	try {
	    parser = new Parser(new File("./exemple.lemmings")) ;
	    level = new Level(parser);
	} catch (modele.parser.NiveauNonConformeException e) {
	    e.printStackTrace();
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
	assertNotNull(level);
	assertEquals("getSpeed incorrect ", level.getSpeed(), 200, EPSILON);
	assertTrue(level.getTime() == 120);
	assertTrue(level.getCount() == 114);
	assertTrue(level.getRescue() == 60) ;
	assertTrue(level.getClimber() == 10) ;
	assertTrue(level.getFloater() == 10) ;
	assertTrue(level.getBomber() == 10) ;
	assertTrue(level.getBuilder() == 10) ;
	assertTrue(level.getBasher() == 10) ;
	assertTrue(level.getDigger() == 10) ;
	assertTrue(level.getMiner() == 10) ;
	assertTrue(level.getLength() == 70) ;
	assertTrue(level.getHeight() == 25) ;
	    memesCoordonnees("Position Trappe", new Position(3,19,69,24),level.getPositionTrappe());
	    memesCoordonnees("Position Sortie", new Position(67,10,69,24),level.getPositionSortie());
    }

    @Test
	public void testGetMap() {
	assertTrue(level != null);
    }
    
	
    
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(TestParser.class.getName());
    }
}
