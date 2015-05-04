import modele.element.* ;
import modele.position.*;
import java.io.* ;
import org.junit.*;
import static org.junit.Assert.*;

/** Programme de test de la classe Action
 * @author	Philippe Leleux
 * @version	1.0
 */
public class ElementTest {

    // Attributs
    private Element_itf element;
    private Position pos;
    
    // Initialisation des differents joueurs
    @Before
    public void setUp() {
	    pos = new Position(2,3,4,4);
    }

    @Test
    public void testElementBloquant() {
	this.element = new ElementBloquant(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(1,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type bloquant : (2,3)"));
    }
    @Test
    public void testElementBroyeur() {
	this.element = new ElementBroyeur(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(1,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type broyeur : (2, 3)"));
    }
    @Test
    public void testElementDestructible() {
	this.element = new ElementDestructible(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(element.estDestructible(new Position(1,3,4,4)));
	assertTrue(!element.estDestructible(new Position(0,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type destructible : (2, 3)"));
    }
    @Test
    public void testElementIndestructible() {
	this.element = new ElementIndestructible(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(1,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type indestructible : (2, 3)"));
    }  
    @Test
    public void testElementLanceFlamme() {
	this.element = new ElementLanceFlamme(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(1,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type lance-flamme : (2, 3)"));
    }
    @Test
    public void testElementSemiDestructibleGauche() {
	this.element = new ElementSemiDestructibleGauche(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(element.estDestructible(new Position(1,3,4,4)));
	assertTrue(!element.estDestructible(new Position(3,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type semi-destructible > : (2, 3)"));
    }
    @Test
    public void testElementSemiDestructibleDessus() {
	this.element = new ElementSemiDestructibleDessus(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(element.estDestructible(new Position(2,4,4,4)));
	assertTrue(!element.estDestructible(new Position(3,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type semi-destructible v : (2, 3)"));
    }
    @Test
    public void testElementSemiDestructibleDroite() {
	this.element = new ElementSemiDestructibleDroite(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(element.estDestructible(new Position(3,3,4,4)));
	assertTrue(!element.estDestructible(new Position(1,3,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type semi-destructible < : (2, 3)"));
    }
    @Test
    public void testElementSortie() {
	this.element = new ElementSortie(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(2,4,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type sortie : (2, 3)"));
    }
    @Test
    public void testElementTrappe() {
	this.element = new ElementTrappe(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(2,4,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type trappe : (2, 3)"));
    }
    @Test
    public void testElementVide() {
	this.element = new ElementVide(pos);
	assertEquals(element.getPosition().getX(), 2);
	assertEquals(element.getPosition().getY(), 3);
	assertTrue(!element.estDestructible(new Position(2,4,4,4)));
	assertTrue(element.toString().equalsIgnoreCase("Element de type vide : (2, 3)"));
    }

    //*/
    // Main
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main(ElementTest.class.getName());
    }	

}
