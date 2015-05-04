package modele.parser;

import java.io.*;
import java.util.*;
import modele.position.*;
import modele.element.*;

/** La classe Parser permet de recuperer les informations d'un niveau a partir du fichier
 * ecrit a un certain format. Il y a plusieurs formats possibles pour un fichier mais apres la
 * lecture les infos sont dans un tableau toujours au meme format et la carte est dans une matrice
 *
 * @author  Philippe Leleux
 * @version 1.0
 */

public class Parser implements Parser_itf {

    // Attributs
    int[] info = new int[18];
    List<List<Character>> map = new ArrayList<List<Character>>();

    // Constructeur
    /** Recupere toutes les informations sur le niveau a partir de son fichier a un
     * format special. Si le format n'est pas conforme, la methode leve une
     * NiveauNonConformeException.
     * @param file le fichier du niveau
     * @throws NiveauNonConformeException si le format du niveau n'est pas conforme (beaucoup, beaucoup de conditions possibles!)
     */
    public Parser(File file) throws NiveauNonConformeException {
        BufferedReader reader = null;   // lecteur du fichier
        String line;                    // la ligne lue
        String[] temp;                  // le tableau contenant la ligne splitter avec \t comme separateur
        int i;                          // nombre de parametres saisis puis indice de parcours de boucle
        boolean blocValable = false;    // vrai si le caractere rencontre est reconnu comme un bloc valide (*, +, >, <, v, @, %, !,  , O )
        boolean speedPresent = false;   // vrai si on trouve un parametre speed
        boolean timePresent = false;    // idem avec time
        boolean countPresent = false;   // idem avec count
        boolean rescuePresent = false;  // idem avec rescue
        boolean climberPresent = false; // idem avec climber
        boolean floaterPresent = false; // idem avec floater
        boolean blockerPresent = false; // idem avec blocker
        boolean bomberPresent = false;  // idem avec bomber
        boolean builderPresent = false; // idem avec builder
        boolean basherPresent = false;  // idem avec basher
        boolean diggerPresent = false;  // idem avec digger
        boolean minerPresent = false;   // idem avec miner
        boolean trappePresente = false; // idem avec trappe
        boolean sortiePresente = false; // idem avec sortie
        try {
            // creation du lecteur
            reader = new BufferedReader(new FileReader(file));
            i = 0;
            /* on boucle jusqu'à ce que tous les paramètres soient rencontrés
             * ou jusqu'a la fin du fichier
             * ou jusqu'a rencontrer le mot cle "map"
             */
            while ((line = reader.readLine()) != null && (i < 12) && !(line.substring(0, 3).equalsIgnoreCase("map"))) {
                // A chaque ligne on teste la presence d'une declaration de parametre en omettant les commentaires
                if (line.charAt(0) != '#') {
                    temp = line.split("\t");
                    // si il n'y a pas au moins 2 string dans le temp => exception
                    if (temp.length < 2) {
                        throw new NiveauNonConformeException();
                    }
                    // Saisie du parametre precise, en ne le prenant qu'une seule fois maximum
                    else {
                        if (temp[0].equalsIgnoreCase("speed") && !speedPresent) {
                            info[0] = Integer.parseInt(temp[1]);
                            if (info[0] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("speed") && speedPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("time") && !timePresent) {
                            info[1] = Integer.parseInt(temp[1]);
                            if (info[1] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("time") && timePresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("count") && !countPresent) {
                            info[2] = Integer.parseInt(temp[1]);
                            if (info[2] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("count") && countPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("rescue") && !rescuePresent) {
                            info[3] = Integer.parseInt(temp[1]);
                            if (info[3] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("rescue") && rescuePresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("climber") && !climberPresent) {
                            info[4] = Integer.parseInt(temp[1]);
                            if (info[4] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("climber") && climberPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("floater") && !floaterPresent) {
                            info[5] = Integer.parseInt(temp[1]);
                            if (info[5] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("floater") && floaterPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("blocker") && !blockerPresent) {
                            info[6] = Integer.parseInt(temp[1]);
                            if (info[6] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("blocker") && blockerPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("bomber") && !bomberPresent) {
                            info[7] = Integer.parseInt(temp[1]);
                            if (info[7] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("bomber") && bomberPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("builder") && !builderPresent) {
                            info[8] = Integer.parseInt(temp[1]);
                            if (info[8] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("builder") && builderPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("basher") && !basherPresent) {
                            info[9] = Integer.parseInt(temp[1]);
                            if (info[9] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("basher") && basherPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("digger") && !diggerPresent) {
                            info[10] = Integer.parseInt(temp[1]);
                            if (info[10] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("digger") && diggerPresent) {
                            throw new NiveauNonConformeException();
                        }
                        else if (temp[0].equalsIgnoreCase("miner") && !minerPresent) {
                            info[11] = Integer.parseInt(temp[1]);
                            if (info[11] < 0) {
                                throw new NiveauNonConformeException();
                            }
                            speedPresent = true;
                            i++;
                        }
                        else if (temp[0].equalsIgnoreCase("miner") && minerPresent) {
                            throw new NiveauNonConformeException();
                        }
                        // else inutile : si on a autre chose, on passe a la ligne suivante
                    }
                }
            }
            // Si on a quitté la boucle pour une autre raison que la saisie des parametres
            // ou si tous les parametres ne sont pas presents => Exception
            if (i != 12) {
                throw new NiveauNonConformeException();
            }

            // initialisation de la longueur et la hauteur
            info[12] = 0;
            info[13] = 0;
            // Boucle tant que l'on arrive pas a la fin du fichier
            while ((line = reader.readLine()) != null) {
                i = 0;
                // blocValable : le caractere est reconnu comme un bloc valide (*, +, >, <, v, @, %, !,  , O )
                blocValable = (line.charAt(i) == '*' || line.charAt(i) == '+' || line.charAt(i) == '>' || line.charAt(i) == '<' || line.charAt(i) == 'v' || line.charAt(i) == '@' || line.charAt(i) == '%' || line.charAt(i) == '!' || line.charAt(i) == 'O' || line.charAt(i) == ' ');
                // si le premier caractere est un bloc on crée un nouvel element de la liste et on incremente la hauteur
                if (blocValable) {
                    map.add(new ArrayList<Character>());
                    info[13]++;
                    // on parcours la ligne jusqu'au bout tant que le caractere rencontre est un bloc valide
                    while(i<line.length() && blocValable) {
                        // si on rencontre la trappe pour la premiere fois, on l'enregistre ainsi que sa position initiale
                        if (line.charAt(i) == 'O' && !trappePresente) {
                            info[15] = info[13];
                            info[14] = i;
                            trappePresente = true;
                        }
                        // sinon exception
                        else if (line.charAt(i) == 'O' && trappePresente) {
                            throw new NiveauNonConformeException();
                        }
                        // idem pour la sortie
                        if (line.charAt(i) == '@' && !sortiePresente) {
                            info[17] = info[13];
                            info[16] = i;
                            sortiePresente = true;
                        }
                        else if (line.charAt(i) == '@' && sortiePresente) {
                            throw new NiveauNonConformeException();
                        }
                        // le caractere est ajoute dynamiquement en suivant dans la map
                        map.get(info[13]-1).add(line.charAt(i));
                        i++;    // incrementation du parcours des colonnes
                    }
                    // on met a jour la longueur de la carte ssi elle est inferieur au nombre de colonne de cette iteration
                    if (i > info[12]) {
                        info[12] = i;
                    }
                }
            }
            info[15] = info[13] - info[15];
            info[17] = info[13] - info[17];
            // Si on a pas rencontre de trappe ou de sortie => exception
            if (!trappePresente || !sortiePresente) {
                throw new NiveauNonConformeException();
            }
            // liberation du lecteur
            reader.close();
        }
        catch(IndexOutOfBoundsException e)
            {
                System.out.println("erreur substring");
            }
        catch(NumberFormatException e)
            {
                System.out.println("pas un entier");
                throw new NiveauNonConformeException();
            }
        catch(FileNotFoundException exc)
            {
                System.out.println("Erreur d'ouverture");
            }
        catch (NullPointerException a)
            {
                System.out.println("Erreur : pointeur null");
            }
        catch (IOException a)
            {
                System.out.println("Probleme d'IO");
            }
    }

    //Gestion des informations
    /** obtenir le parametre speed du niveau : la vitesse du jeu (en millisecondes)
     * @return speed le parametre speed
     */
    public int getSpeed() {
        return this.info[0];
    }

    /** obtenir le parametre time du niveau : le temps maximum (en secondes)
     * @return time le parametre time
     */
    public int getTime() {
        return this.info[1];
    }

    /** obtenir le parametre count du niveau : nombre total de Lemmings
     * @return count le parametre count
     */
    public int getCount() {
        return this.info[2];
    }

    /** obtenir le parametre rescue du niveau : nombre de Lemmings qu’il faut sauver
     * @return rescue le parametre rescue
     */
    public int getRescue() {
        return this.info[3];
    }

    /** obtenir le parametre climber du niveau : nombre d’aptitudes climber disponibles
     * @return climber le parametre climber
     */
    public int getClimber() {
        return this.info[4];
    }

    /** obtenir le parametre floater du niveau : nombre d’aptitudes floater disponibles
     * @return floater le parametre floater
     */
    public int getFloater() {
        return this.info[5];
    }

    /** obtenir le parametre blocker du niveau : nombre d’aptitudes blocker disponibles
     * @return blocker le parametre blocker
     */
    public int getBlocker() {
        return this.info[6];
    }

    /** obtenir le parametre bomber du niveau : nombre d’aptitudes bomber disponibles
     * @return bomber le parametre bomber
     */
    public int getBomber() {
        return this.info[7];
    }

    /** obtenir le parametre builder du niveau : nombre d’aptitudes builder disponibles
     * @return builder le parametre builder
     */
    public int getBuilder() {
        return this.info[8];
    }

    /** obtenir le parametre basher du niveau : nombre d’aptitudes basher disponibles
     * @return basher le parametre basher
     */
    public int getBasher() {
        return this.info[9];
    }

    /** obtenir le parametre digger du niveau : nombre d’aptitudes digger disponibles
     * @return digger le parametre digger
     */
    public int getDigger() {
        return this.info[10];
    }

    /** obtenir le parametre miner du niveau : nombre d’aptitudes miner disponibles
     * @return miner le parametre miner
     */
    public int getMiner() {
        return this.info[11];
    }

    /** obtenir la longueur de la carte
     * @return length le parametre length
     */
    public int getLength() {
        return this.info[12];
    }

    /** obtenir la hauteur de la carte
     * @return height le parametre height
     */
    public int getHeight() {
        return this.info[13];
    }

    /** obtenir la position de la trappe
     * @return positionTrappe la position de la trappe
     */
    public Position getPositionTrappe() {
        return new Position(info[14], info[15], info[12], info[13]);
    }

    /** obtenir la position de la sortie
     * @return positionSortie la position de la sortie
     */
    public Position getPositionSortie() {
        return new Position(info[16], info[17], info[12], info[13]);
    }

    //Gestion de la carte
    /** obtenir la carte du niveau sous la forme d'une matrice de caracteres avec
     * solide indestructible:*, destructible:+, semi-destructibles:< > v, sortie:@,
     * broyeur:%, lance-flamme:!, trappe:0, vide:
     * @return map la carte du niveau
     */
    public Element_itf[][] getMap() {
        // retourne un tableau d'Element de taille longueur, hauteur
        Element_itf[][] res = new Element_itf[this.info[12]][this.info[13]];
        // initialisation avec des elements vides du tableau
        for (int i = 0; i < info[12]; ++i) {
            for (int j = 0; j< info[13]; ++j) {
                res[i][j] = new ElementVide(new Position(i, j, info[12], info[13]));
            }
        }
        // Remplissage avec les elements trouves en inversant les indices de ligne : i, j => x, y
        for (int i = 0; i < info[13]; ++i) {
            for (int j = 0; j < this.map.get(i).size(); ++j) {
                switch (this.map.get(i).get(j)) {
                case '*' : res[j][this.info[13]-i-1] = new ElementIndestructible(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '+' : res[j][this.info[13]-i-1] = new ElementDestructible(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '<' : res[j][this.info[13]-i-1] = new ElementSemiDestructibleDroite(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '>' : res[j][this.info[13]-i-1]= new ElementSemiDestructibleGauche(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case 'v' : res[j][this.info[13]-i-1] = new ElementSemiDestructibleDessus(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '@' : res[j][this.info[13]-i-1] = new ElementSortie(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '%' : res[j][this.info[13]-i-1] = new ElementBroyeur(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case '!' : res[j][this.info[13]-i-1] = new ElementLanceFlamme(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case 'O' : res[j][this.info[13]-i-1] = new ElementTrappe(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                case ' ' : res[j][this.info[13]-i-1] = new ElementVide(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                default : res[j][this.info[13]-i-1] = new ElementIndestructible(new Position(j, this.info[13]-i-1, info[12]-1, info[13]-1));
                    break;
                }
            }
        }
        return res;
    }

    //Affichage des informations du niveau
    /** afficher tous les details du niveau
     * @return string detail du niveau
     */
    public String toString() {
        String string = "";     // Le contenu du fichier de carte
        char[][] map = new char[this.info[12]][this.info[13]];
        string = "speed "+this.info[0]+"\ntime "+this.info[1]+"\ncount "+this.info[2]+"\nrescue "+this.info[3]+"\nclimber "+this.info[4];
        string += "\nfloater "+this.info[5]+"\nblocker "+this.info[6]+"\nbomber "+this.info[7]+"\nbuilder "+this.info[8];
        string += "\nbasher "+this.info[9]+"\ndigger "+this.info[10]+"\nminer "+this.info[11]+"\n";
        string += "hauteur de la carte : " + this.info[13] + "\nlargeur de la carte : " + this.info[12] + "\n";
        string += "position initiale : " + this.getPositionTrappe() + "\n" ;
        string += "position finale : " + this.getPositionSortie() + "\n";
        for (int i = 0; i < this.info[12]; ++i) {
            for (int j = 0; j< this.info[13]; ++j) {
                map[i][j] = ' ';
            }
        }
        for (int i = 0; i < this.info[13]; ++i) {
            for (int j = 0; j < this.map.get(i).size(); ++j) {
                map[j][i] = this.map.get(i).get(j);
            }
        }
        for (int j = 0; j< this.info[13]; ++j) {
            for (int i = 0; i < this.info[12]; ++i) {

                string += map[i][j];
            }
            string += "\n";
        }
        return string;

    }
}

