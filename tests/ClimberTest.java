import modele.position.* ;
import modele.competence.*;
import modele.level.*;
import modele.parser.*;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Climber
 * @author	Philippe Leleux
 * @version	1.0
 */
public class ClimberTest {

    // Attributs

    private Level level;
    private Position pos;
    private boolean dir;
    private boolean falling;
    private int fallSince;
    private int waitBeforeNextAction;
    private boolean climbing;
    private boolean blocking;
    private Climber bibi;
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
	    bibi = new Climber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0); 
    }
    
    @Test
    public void testClimberBasDeCarte() {
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testClimberPresLanceFlamme() {
	pos = new Position(45,16,69,24);
	bibi = new Climber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testClimberPlafond() {
	pos = new Position(68,14,69,24);
	bibi = new Climber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testClimberMur() {    
	pos = new Position(68,12,69,24);
	bibi = new Climber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertEquals(action.getGoTo().getX(), 68);
	assertEquals(action.getGoTo().getY(), 13);
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testClimberHautDuMur() {    
	pos = new Position(67,15,69,24);
	bibi = new Climber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);
	action = bibi.actionToPerform(level);
	assertEquals(action.getGoTo().getX(), 68);
	assertEquals(action.getGoTo().getY(), 16);
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
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
	org.junit.runner.JUnitCore.main(ClimberTest.class.getName());
    }	

}
