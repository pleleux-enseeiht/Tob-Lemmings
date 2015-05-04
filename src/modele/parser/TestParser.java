package modele.parser;

import java.io.*;
import modele.level.*;

public class TestParser {

	private static Parser parser;

	public static void main(String[] args) {
		try {
			File f = new File(args[0]);
			parser = new Parser(f);
			System.out.println(parser);
			Level level = new Level(parser);
			System.out.println(level);
		}
		catch (NiveauNonConformeException e) {
			System.out.println("Exception!");
		}
	}

}
