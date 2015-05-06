# Tob-Lemmings
MVC oriented implementation of the famous Lemmings game with Java Swing

##Required
Java-6
Apache ant
Java Swing

##Launch
An **Apache ant** executable allows to launch the game and different commands:
* *ant info* (default): Display information on the ant launcher (default),
* *ant init*: Creates the directories for project build, distribution and documentation,
* *ant build*: Compile the sources and tests into the **build** dir,
* *ant jar*: Generate the distribution (jar file),
* *ant view*: Execute the view,
* *ant run*: Launch the game **Lemmings**,
* *ant run2*: Launch the game from the second level,
* *ant run3*: Launch the game from the third level,
* *ant tests*: Lauch the Unit Tests,
* *ant doc*: Generate the documentation of the project
* *ant clean*: Clean the prject, deleting build, distrib and doc.

##Creating new levels
The levels are composed of blocks described in a simple text file. These files are mainly composed of 2 parts:
* defintion of the parameters (number of blocker, miner, time maximum etc...)
* definition of the map.

![Text file and real level](https://github.com/pleleux-enseeiht/Tob-Lemmings/blob/master/graphics/text_to_level.png "Text file and real level")

Anyone can create levels as they want to. For examples and more details, look into the **levels** directory.

##Architecture
**MVC** Architecture was followed to develop the game:
![MVC Cycle](https://github.com/pleleux-enseeiht/Tob-Lemmings/blob/master/graphics/MVC.png "MVC Cycle")
