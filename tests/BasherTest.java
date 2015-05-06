import modele.position.* ;
import modele.competence.*;
import modele.level.*;
import modele.parser.*;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Basher
 * @author	Philippe Leleux
 * @version	1.0
 */
public class BasherTest {

    // Attributs

    private Level level;
    private Position pos;
    private boolean dir;
    private boolean falling;
    private int fallSince;
    private int waitBeforeNextAction;
    private boolean climbing;
    private boolean blocking;
    private Basher bibi;
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
	    bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0); 
    }
    
    @Test
    public void testBasherBasDeCarte() {
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherPresLanceFlamme() {
	pos = new Position(45,16,69,24);
	bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherVideSansClimbing() {
	pos = new Position(6,2,69,24);
	bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherSEcrase() {
	pos = new Position(6,1,69,24);
	bibi = new Basher(level, pos, dir, true, 5, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 5);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherDroiteDeMap() {
	pos = new Position(69,16,69,24);
	bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherRienADroite() {
	pos = new Position(68,16,69,24);
	bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testBasherCreuse() {
	pos = new Position(13,1,69,24);
	bibi = new Basher(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), 14);
	assertEquals(action.getDestroy()[0].getY(), 1);
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }

    //*/
    // Main
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(BasherTest.class.getName());
    }	

}