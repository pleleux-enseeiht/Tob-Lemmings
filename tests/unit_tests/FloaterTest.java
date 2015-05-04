import modele.position.* ;
import modele.competence.*;
import modele.level.*;
import modele.parser.*;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Floater
 * @author	Philippe Leleux
 * @version	1.0
 */
public class FloaterTest {

    // Attributs

    private Level level;
    private Position pos;
    private boolean dir;
    private boolean falling;
    private int fallSince;
    private int waitBeforeNextAction;
    private boolean climbing;
    private boolean blocking;
    private Floater bibi;
    private Action action;
    
    
    // Initialisation des differents joueurs
    @Before public void setUp() throws NiveauNonConformeException {
	    this.level = new Level(new Parser(new File("exemple.lemmings3")));
	    pos = new Position(35,0,69,24);
	    dir = true;
	    falling = false;
	    fallSince = 0;
	    waitBeforeNextAction = 0;
	    climbing = false;
	    blocking = false;
	    bibi = new Floater(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0); 
    }

    @Test
    public void testFloaterBasDeCarte() {
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 1);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testFloaterPresLanceFlamme() {
	pos = new Position(47,16,69,24);
	bibi = new Floater(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 1);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testFloaterVide() {
	pos = new Position(6,2,69,24);
	bibi = new Floater(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertEquals(action.getGoTo().getX(), 6);
	assertEquals(action.getGoTo().getY(), 1);
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 1);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }

    //*/
    // Main
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(FloaterTest.class.getName());
    }	

}
