import modele.position.* ;
import modele.competence.*;
import modele.level.*;
import modele.parser.*;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Bomber
 * @author	Philippe Leleux
 * @version	1.0
 */
public class BomberTest {

    // Attributs

    private Position[] destroy;
    private Level level;
    private Position pos;
    private boolean dir;
    private boolean falling;
    private int fallSince;
    private int waitBeforeNextAction;
    private boolean climbing;
    private boolean blocking;
    private Bomber bibi;
    private Action action;
    
    
    // Initialisation des differents joueurs
    @Before public void setUp() throws NiveauNonConformeException {
	    this.level = new Level(new Parser(new File("exemple.lemmings3")));
	    pos = new Position(15,15,69,24);
	    dir = true;
	    falling = false;
	    fallSince = 0;
	    waitBeforeNextAction = 0;
	    climbing = false;
	    blocking = false;
	    bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 2); 
    }
    
    @Test
    public void testTempsPasEcoule() {
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertNull(action.getDestroy());
	assertTrue(!action.getFalling());
	assertTrue(!action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 1);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }	
    @Test
    public void testTempsEcouleMilieuDeMap() {
	destroy = new Position[8];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionS();
	destroy[2] = pos.getPositionW();
	destroy[3] = pos.getPositionNW();
	destroy[4] = pos.getPositionSW();
	destroy[5] = pos.getPositionE();
	destroy[6] = pos.getPositionNE();
	destroy[7] = pos.getPositionSE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertEquals(action.getDestroy()[3].getX(), destroy[3].getX());
	assertEquals(action.getDestroy()[3].getY(), destroy[3].getY());
	assertEquals(action.getDestroy()[4].getX(), destroy[4].getX());
	assertEquals(action.getDestroy()[4].getY(), destroy[4].getY());
	assertEquals(action.getDestroy()[5].getX(), destroy[5].getX());
	assertEquals(action.getDestroy()[5].getY(), destroy[5].getY());
	assertEquals(action.getDestroy()[6].getX(), destroy[6].getX());
	assertEquals(action.getDestroy()[6].getY(), destroy[6].getY());
	assertEquals(action.getDestroy()[7].getX(), destroy[7].getX());
	assertEquals(action.getDestroy()[7].getY(), destroy[7].getY());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
   @Test
    public void testTempsEcouleDroiteDeMap() {
	pos = new Position(69, 16, 69, 24);
	destroy = new Position[5];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionS();
	destroy[2] = pos.getPositionW();
	destroy[3] = pos.getPositionNW();
	destroy[4] = pos.getPositionSW();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertEquals(action.getDestroy()[3].getX(), destroy[3].getX());
	assertEquals(action.getDestroy()[3].getY(), destroy[3].getY());
	assertEquals(action.getDestroy()[4].getX(), destroy[4].getX());
	assertEquals(action.getDestroy()[4].getY(), destroy[4].getY());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleGaucheDeMap() {
	pos = new Position(0, 16, 69, 24);
	destroy = new Position[5];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionS();
	destroy[2] = pos.getPositionE();
	destroy[3] = pos.getPositionNE();
	destroy[4] = pos.getPositionSE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertEquals(action.getDestroy()[3].getX(), destroy[3].getX());
	assertEquals(action.getDestroy()[3].getY(), destroy[3].getY());
	assertEquals(action.getDestroy()[4].getX(), destroy[4].getX());
	assertEquals(action.getDestroy()[4].getY(), destroy[4].getY());
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleHautDeMap() {
	pos = new Position(1, 24, 69, 24);
	destroy = new Position[5];
	destroy[0] = pos.getPositionS();
	destroy[1] = pos.getPositionW();
	destroy[2] = pos.getPositionSW();
	destroy[3] = pos.getPositionE();
	destroy[4] = pos.getPositionSE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertEquals(action.getDestroy()[3].getX(), destroy[3].getX());
	assertEquals(action.getDestroy()[3].getY(), destroy[3].getY());
	assertEquals(action.getDestroy()[4].getX(), destroy[4].getX());
	assertEquals(action.getDestroy()[4].getY(), destroy[4].getY());
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleBasDeMap() {
	pos = new Position(35, 0, 69, 24);
	destroy = new Position[5];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionW();
	destroy[2] = pos.getPositionNW();
	destroy[3] = pos.getPositionE();
	destroy[4] = pos.getPositionNE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertEquals(action.getDestroy()[3].getX(), destroy[3].getX());
	assertEquals(action.getDestroy()[3].getY(), destroy[3].getY());
	assertEquals(action.getDestroy()[4].getX(), destroy[4].getX());
	assertEquals(action.getDestroy()[4].getY(), destroy[4].getY());
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleHautDroiteDeMap() {
	pos = new Position(69, 24, 69, 24);
	destroy = new Position[3];
	destroy[0] = pos.getPositionS();
	destroy[1] = pos.getPositionW();
	destroy[2] = pos.getPositionSW();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertNull(action.getDestroy()[3]);
	assertNull(action.getDestroy()[4]);
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleHautGaucheDeMap() {
	pos = new Position(0, 24, 69, 24);
	destroy = new Position[3];
	destroy[0] = pos.getPositionS();
	destroy[1] = pos.getPositionE();
	destroy[2] = pos.getPositionSE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertNull(action.getDestroy()[3]);
	assertNull(action.getDestroy()[4]);
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleBasDroiteDeMap() {
	pos = new Position(69, 0, 69, 24);
	destroy = new Position[3];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionW();
	destroy[2] = pos.getPositionNW();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertNull(action.getDestroy()[3]);
	assertNull(action.getDestroy()[4]);
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
	assertEquals(action.getFallSince(), 0);
	assertTrue(!action.getPowerless());
	assertTrue(!action.getClimbing());
	assertEquals(action.getTimeBeforeDie(), 0);
	assertTrue(action.getDir());
	assertTrue(!action.getBlocking());
    }
    @Test
    public void testTempsEcouleBasGaucheDeMap() {
	pos = new Position(0, 0, 69, 24);
	destroy = new Position[3];
	destroy[0] = pos.getPositionN();
	destroy[1] = pos.getPositionE();
	destroy[2] = pos.getPositionNE();
	bibi = new Bomber(level, pos, dir, falling, fallSince, waitBeforeNextAction, climbing, 0);	
	action = bibi.actionToPerform(level);
	assertNull(action.getGoTo());
	assertNull(action.getAdd());
	assertEquals(action.getDestroy()[0].getX(), destroy[0].getX());
	assertEquals(action.getDestroy()[0].getY(), destroy[0].getY());
	assertEquals(action.getDestroy()[1].getX(), destroy[1].getX());
	assertEquals(action.getDestroy()[1].getY(), destroy[1].getY());
	assertEquals(action.getDestroy()[2].getX(), destroy[2].getX());
	assertEquals(action.getDestroy()[2].getY(), destroy[2].getY());
	assertNull(action.getDestroy()[3]);
	assertNull(action.getDestroy()[4]);
	assertNull(action.getDestroy()[5]);
	assertNull(action.getDestroy()[6]);
	assertNull(action.getDestroy()[7]);
	assertTrue(!action.getFalling());
	assertTrue(action.getDie());
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
	org.junit.runner.JUnitCore.main(BomberTest.class.getName());
    }	

}
