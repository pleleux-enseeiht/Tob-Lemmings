import modele.position.* ;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Position
 * @author	Philippe Leleux
 * @version	1.0
 */
public class PositionTest {

    // Attributs

    private Position position, positionSW, positionSE, positionNW, positionNE, positionNotInitialised;
    
    // Initialisation des differents joueurs
    @Before public void setUp() {
	    position = new Position(2,4,10,10);
	    positionSW = new Position(0,0,3,15);
	    positionSE = new Position(21,0,21,34);
	    positionNW = new Position(0,13,12,13);
	    positionNE = new Position(3,3,3,3);
    }
    
    // Test get/setX(), get/setY()
    @Test
	public void testGetSet() {
	    // X
	    assertEquals(position.getX(), 2);
	    position.setX(9);
	    assertEquals(position.getX(), 9);
	    // Y
	    assertEquals(position.getY(), 4);
	    position.setY(9);
	    assertEquals(position.getY(), 9);		
    }
    
    // Test getXMax(), getYMax(), getIArray(), getJArray()
    @Test
	public void testXYMaxIJArray() {
	// XMax
	assertEquals(position.getXMax(), 10);
	// YMax
	assertEquals(position.getYMax(), 10);
	// IArray
	assertEquals(position.getIArray(), 5);
	// JArray
	assertEquals(position.getJArray(), 2);
    }

    // Test getPositionE/W/N/S/SE/SW/NE/NW
    @Test
	public void testPositionsAdjacentes() {
	    // E
	    assertEquals(position.getPositionE().getX(), 3);
	    assertEquals(position.getPositionE().getY(), 4);			
	    // W
	    assertEquals(position.getPositionW().getX(), 1);
	    assertEquals(position.getPositionW().getY(), 4);			
	    // N
	    assertEquals(position.getPositionN().getX(), 2);
	    assertEquals(position.getPositionN().getY(), 5);			
	    // S
	    assertEquals(position.getPositionS().getX(), 2);
	    assertEquals(position.getPositionS().getY(), 3);			
	    // SE
	    assertEquals(position.getPositionSE().getX(), 3);
	    assertEquals(position.getPositionSE().getY(), 3);			
	    // SW
	    assertEquals(position.getPositionSW().getX(), 1);
	    assertEquals(position.getPositionSW().getY(), 3);			
	    // NE
	    assertEquals(position.getPositionNE().getX(), 3);
	    assertEquals(position.getPositionNE().getY(), 5);			
	    // NW
	    assertEquals(position.getPositionNW().getX(), 1);
	    assertEquals(position.getPositionNW().getY(), 5);
    }				
    //*/
    // Main
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(PositionTest.class.getName());
    }	

}
