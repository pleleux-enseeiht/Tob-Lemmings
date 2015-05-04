package modele.competence ;
import modele.level.* ;
import modele.parser.* ;
import modele.position.* ;
import java.io.* ;

public class TestWalker {
    public static void main(String args[]){
	Level_itf level ;
	Walker walk ;
	Action action ;
	try {
	    Parser parser = new Parser(new File("./exemple.lemmings")); 
	    level = new Level(parser);
	    System.out.println(level);
	    System.out.println(parser);
	    Position pos = new Position(3,4,level.getLength()-1,level.getHeight()-1);
	    walk = new Walker(level, pos, true, false, 1,0,false,0);
	    action = walk.actionToPerform(level);
	    System.out.println("l'action a faire est : \n" + action);
	} catch (NiveauNonConformeException e) {
	    System.out.println("Niveau non conforme !");
	}
    }
}
