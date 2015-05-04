package vue ;

import javax.swing.* ;
import java.awt.* ;

import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
//import javafx.scene.control.ToggleGroup ;

import modele.position.* ;
import modele.competence.* ;
import modele.lemming.* ;
import modele.level.* ;
import modele.element.* ;
import modele.parser.*;
import modele.gamemaster.* ;

import controller.*;

/**
 * Vue du jeu des Lemmings
 * @author Jessica Hornik
 * @version 1.0
 *
 */

public class Vue implements Observer {

    private Controller controller ;

    // Fenetre
    private JFrame fenetre;
    // Contenu
    private Container contenu ;

    // Panel
    private JPanel zoneJeu ;

    // Menu
    private JMenuItem itemRestart ;
    private JMenuItem itemChangeLevel ;
    private JMenuItem itemQuit;
    private JMenuItem itemChangeColor;

    private JMenu menuGame ;
    private JMenu menuSettings ;
    private JMenu menuHelp ;
    private JMenuBar menu ;

    // Boutons de capacites
    private JToggleButton buttonClimber ;
    private JToggleButton buttonFloater ;
    private JToggleButton buttonBlocker ;
    private JToggleButton buttonBomber ;
    private JToggleButton buttonBuilder ;
    private JToggleButton buttonBasher ;
    private JToggleButton buttonDigger ;
    private JToggleButton buttonMiner ;

    // Boutons de jeu
    private JButton buttonPlus ;
    private JButton buttonMoins ;
    private JButton buttonPause ;
    private JButton buttonFastForward ;
    private JButton buttonKillThemAll ;

    private JLabel labelTime ;
    private JLabel labelCurrentTime ;
    private JLabel labelSave ;
    private JLabel labelSpeed ;
    private JLabel labelCurrentSpeed ;
    private JLabel labelHome ;
    private JLabel labelOut ;

    private static ImageIcon imageDestructible ;
    private static ImageIcon imageBloquant ;
    private static ImageIcon imageBroyeur ;
    private static ImageIcon imageIndestructible ;
    private static ImageIcon imageLanceFlamme ;
    private static ImageIcon imageSemiDestructibleDessus ;
    private static ImageIcon imageSemiDestructibleDroite ;
    private static ImageIcon imageSemiDestructibleGauche ;
    private static ImageIcon imageSortie ;
    private static ImageIcon imageTrappe ;
    private static ImageIcon imageVide ;


    private Controller Controller ;
    private Level_itf level ;
    private java.util.List<Lemming> lemmingsPeople ;
    private JLabel[][] grille ;

    // Le constructeur
    public Vue(Controller controller_) throws modele.parser.NiveauNonConformeException{

	this.controller = controller_ ;
	this.level = controller.getLevel() ;

	grille = new JLabel[level.getLength()][level.getHeight()];

	// Images des boutons
	ImageIcon imageClimber = new ImageIcon("./src/vue/climber.GIF");
	ImageIcon imageFloater = new ImageIcon("./src/vue/floater.png");
	ImageIcon imageBlocker = new ImageIcon("./src/vue/blocker.png");
	ImageIcon imageBomber = new ImageIcon("./src/vue/bomber.png");
	ImageIcon imageBuilder = new ImageIcon("./src/vue/builder.png");
	ImageIcon imageBasher = new ImageIcon("./src/vue/basher.gif");
	ImageIcon imageDigger = new ImageIcon("./src/vue/digger.gif");
	ImageIcon imageMiner = new ImageIcon("./src/vue/miner.png");
	ImageIcon imageForward = new ImageIcon("./src/vue/forward.png");
	ImageIcon imagePlus = new ImageIcon("./src/vue/plus.png");
	ImageIcon imageKill = new ImageIcon("./src/vue/kill.png");
	ImageIcon imageMoins = new ImageIcon("./src/vue/minus.png");
	ImageIcon imagePause = new ImageIcon("./src/vue/pause.png");

	// Images des elements
	imageDestructible = new ImageIcon("./src/vue/destructible.png");
	imageBloquant = new ImageIcon("./src/vue/blocker.png");
	imageBroyeur = new ImageIcon("./src/vue/broyeur.png");
	imageIndestructible = new ImageIcon("./src/vue/indestructible.png");
	imageLanceFlamme = new ImageIcon("./src/vue/lanceFlamme.png");
	imageSemiDestructibleDessus = new ImageIcon("./src/vue/destructibleDessus.png");
	imageSemiDestructibleDroite = new ImageIcon("./src/vue/destructibleDroite.png");
	imageSemiDestructibleGauche = new ImageIcon("./src/vue/destructibleGauche.png");
	imageSortie = new ImageIcon("./src/vue/sortie.png");
	imageTrappe = new ImageIcon("./src/vue/trappe.png");
	imageVide = new ImageIcon("./src/vue/transparent.png");


	// Creation de la fenetre
	this.fenetre = new JFrame("Jeu des Lemmings");
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// Contenu de la fenetre
	this.contenu = fenetre.getContentPane();
	contenu.setLayout(new BorderLayout());

	zoneJeu = new JPanel() ;
	zoneJeu.setLayout(new BorderLayout());
	JPanel zoneBoutons = new JPanel();
	zoneBoutons.setLayout(new GridLayout(1,11));
	JPanel zoneGraphique = new JPanel();
	zoneGraphique.setLayout(new GridLayout(level.getHeight(),level.getLength()));
	JPanel zoneEtat = new JPanel();
	zoneEtat.setLayout(new BoxLayout(zoneEtat,BoxLayout.Y_AXIS));

	buttonClimber = new JToggleButton("Climber",imageClimber);
	buttonClimber.setVerticalTextPosition(JLabel.TOP);
	buttonClimber.setHorizontalTextPosition(JLabel.CENTER);
	buttonClimber.setName("Climber");

	buttonFloater = new JToggleButton("Floater",imageFloater);
	buttonFloater.setVerticalTextPosition(JLabel.TOP);
	buttonFloater.setHorizontalTextPosition(JLabel.CENTER);
	buttonFloater.setName("Floater");

	buttonBlocker = new JToggleButton("Blocker",imageBlocker);
	buttonBlocker.setVerticalTextPosition(JLabel.TOP);
	buttonBlocker.setHorizontalTextPosition(JLabel.CENTER);
	buttonBlocker.setName("Blocker");

	buttonBomber = new JToggleButton("Bomber",imageBomber);
	buttonBomber.setVerticalTextPosition(JLabel.TOP);
	buttonBomber.setHorizontalTextPosition(JLabel.CENTER);
	buttonBomber.setName("Bomber");

	buttonBuilder = new JToggleButton("Builder",imageBuilder);
	buttonBuilder.setVerticalTextPosition(JLabel.TOP);
	buttonBuilder.setHorizontalTextPosition(JLabel.CENTER);
	buttonBuilder.setName("Builder");

	buttonBasher = new JToggleButton("Basher",imageBasher);
	buttonBasher.setVerticalTextPosition(JLabel.TOP);
	buttonBasher.setHorizontalTextPosition(JLabel.CENTER);
	buttonBasher.setName("Basher");

	buttonDigger = new JToggleButton("Digger",imageDigger);
	buttonDigger.setVerticalTextPosition(JLabel.TOP);
	buttonDigger.setHorizontalTextPosition(JLabel.CENTER);
	buttonDigger.setName("Digger");

	buttonMiner = new JToggleButton("Miner",imageMiner);
	buttonMiner.setVerticalTextPosition(JLabel.TOP);
	buttonMiner.setHorizontalTextPosition(JLabel.CENTER);
	buttonMiner.setName("Miner");

	labelTime = new JLabel("Timer : ");
        labelCurrentTime = new JLabel();
        controller.setCurrentTime(labelCurrentTime);
	
	labelSave = new JLabel();
	controller.setSave(labelSave);
	
	labelHome = new JLabel();
	controller.setHome(labelHome);
	
	labelOut = new JLabel();
	controller.setOut(labelOut);
	
	labelSpeed = new JLabel("Vitesse actuelle :");
        labelCurrentSpeed = new JLabel("x1");

	buttonPlus = new JButton(imagePlus);
	buttonPlus.setName("Plus");
	buttonPlus.addActionListener(new ActionMoreLemmings(controller));
	buttonMoins = new JButton(imageMoins);
	buttonMoins.setName("Minus");
	buttonMoins.addActionListener(new ActionLessLemmings(controller));
	
	buttonPause = new JButton("Pause",imagePause);
	buttonPause.setVerticalTextPosition(JLabel.TOP);
	buttonPause.setHorizontalTextPosition(JLabel.CENTER);
	buttonPause.setName("Pause");
        buttonPause.addActionListener(new ActionPause(controller,labelCurrentSpeed));
	buttonFastForward = new JButton("Fast forward",imageForward);
	buttonFastForward.setVerticalTextPosition(JLabel.TOP);
	buttonFastForward.setHorizontalTextPosition(JLabel.CENTER);
	buttonFastForward.setName("FastForward");
        buttonFastForward.addActionListener(new ActionFastForward(controller,labelCurrentSpeed));
	buttonKillThemAll = new JButton("Kill them all",imageKill);
	buttonKillThemAll.setVerticalTextPosition(JLabel.TOP);
	buttonKillThemAll.setHorizontalTextPosition(JLabel.CENTER);
	buttonKillThemAll.addActionListener(new ActionKillThemAll(controller));
	buttonKillThemAll.setName("KillThemAll");

	zoneBoutons.add(buttonClimber);
	zoneBoutons.add(buttonFloater);
	zoneBoutons.add(buttonBlocker);
	zoneBoutons.add(buttonBomber);
	zoneBoutons.add(buttonBuilder);
	
zoneBoutons.add(buttonBasher);
	zoneBoutons.add(buttonDigger);
	zoneBoutons.add(buttonMiner);
	zoneBoutons.add(buttonPlus);
	zoneBoutons.add(buttonMoins);
	zoneBoutons.add(buttonPause);
	zoneBoutons.add(buttonFastForward);
	zoneBoutons.add(buttonKillThemAll);

	buttonClimber.addActionListener(new ActionDeselect(zoneBoutons));
	buttonFloater.addActionListener(new ActionDeselect(zoneBoutons));
	buttonBlocker.addActionListener(new ActionDeselect(zoneBoutons));
	buttonBomber.addActionListener(new ActionDeselect(zoneBoutons));
	buttonBuilder.addActionListener(new ActionDeselect(zoneBoutons));
	buttonBasher.addActionListener(new ActionDeselect(zoneBoutons));
	buttonDigger.addActionListener(new ActionDeselect(zoneBoutons));
	buttonMiner.addActionListener(new ActionDeselect(zoneBoutons));

	// Créer la carte du jeu
	for (int y = level.getHeight()-1 ; y >= 0 ; --y){
	    for (int x = 0 ; x < level.getLength() ; ++x){
		grille[x][y] = new JLabel(getImage(level.getMap()[x][y]));
		grille[x][y].setOpaque(true);
		zoneGraphique.add(grille[x][y]);
		grille[x][y].addMouseListener(new ActionAffecterCompetence(zoneBoutons,x,y,controller));
	    }
	}

	// Creer la barre d'etat du jeu
	zoneEtat.add(labelTime) ;
        zoneEtat.add(labelCurrentTime);
        zoneEtat.add(labelSpeed);
        zoneEtat.add(labelCurrentSpeed);
	zoneEtat.add(labelSave) ;
	zoneEtat.add(labelOut) ;
	zoneEtat.add(labelHome) ;

	JScrollPane zoneScroll = new JScrollPane(zoneGraphique);
	zoneJeu.add(zoneScroll,BorderLayout.CENTER);
	zoneJeu.add(zoneBoutons,BorderLayout.PAGE_END);
	zoneJeu.add(zoneEtat,BorderLayout.LINE_END);
	contenu.add(zoneJeu);

	// Creation des items de menu
	itemRestart = new JMenuItem("Restart");
	itemChangeLevel = new JMenuItem("Change level");
	itemQuit = new JMenuItem("Quit");
	itemRestart.addActionListener(new ActionRestart(controller));
	itemChangeLevel.addActionListener(new ActionChangeLevel(controller));
	itemQuit.addActionListener(new ActionQuitter());

	// Creation du menu
	menuGame = new JMenu("Game");
	menuGame.add(itemRestart);
	menuGame.add(itemChangeLevel);
	menuGame.add(itemQuit);

	// Creation de la barre de menu
	menu = new JMenuBar();
	fenetre.setJMenuBar(menu);
	menu.add(menuGame);

	fenetre.setVisible(true);
	fenetre.pack();

    }

    public void update(Observable o, Object arg){
        controller.setCurrentTime(labelCurrentTime);
	controller.afficherLemmings(grille);
	controller.setOut(labelOut);
	controller.setHome(labelHome);
        controller.endGame();

    }


    public static ImageIcon getImage(Element_itf element) {
	if (element instanceof ElementDestructible) {
	    return imageDestructible ;
	} else if (element instanceof ElementBloquant){
	    return imageBloquant ;
	} else if (element instanceof ElementSortie){
	    return imageSortie ;
	} else if (element instanceof ElementBroyeur) {
	    return imageBroyeur ;
	} else if (element instanceof ElementVide){
	    return imageVide ;
	} else if (element instanceof ElementLanceFlamme){
	    return imageLanceFlamme ;
	} else if (element instanceof ElementSemiDestructibleDessus){
	    return imageSemiDestructibleDessus ;
	} else if (element instanceof ElementSemiDestructibleDroite){
	    return imageSemiDestructibleDroite ;
	} else if (element instanceof ElementSemiDestructibleGauche){
	    return imageSemiDestructibleGauche ;
	} else if (element instanceof ElementIndestructible) {
	    return imageIndestructible ;
	} else if (element instanceof ElementTrappe){
	    return imageTrappe ;
	} else {
	    return null ;
	}
    }
}
