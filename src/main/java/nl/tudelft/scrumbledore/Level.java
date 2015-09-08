package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing a Level in the Game.
 * 
 * @author Jesse Tilro
 *
 */
public class Level {

  private ArrayList<LevelElement> movingElements;
  private ArrayList<Platform> platforms;
  private ArrayList<Fruit> fruits;
  private ArrayList<NPC> npcs;
  private Player player;


  /**
   * Constructs a new Level instance.
   */
  public Level() {
    movingElements = new ArrayList<LevelElement>();
    platforms = new ArrayList<Platform>();
  }

  /**
   * Add a LevelElement to this Level.
   * 
   * @param element
   *          A LevelElement.
   */
  public void addElement(LevelElement element) {
    if (element.getClass().equals(Platform.class)) {
      platforms.add((Platform) element);
    } else if (element.getClass().equals(Player.class)) {
      player = (Player) element;
    } else {
      movingElements.add(element);
    }
  }

  
  /**
   * Returns an ArrayList of non-moving level elements.
   * 
   * @return
   *          An array of non-moving level elements
   */
  public ArrayList<LevelElement> getMovingElements() {
    return movingElements;
  }


  /**
   * Returns an ArrayList of Platform elements.
   * 
   * @return
   *          An ArrayList of Platform elements
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }
  
  /**
   * Returns an ArrayList of Fruit elements.
   * 
   * @return
   *          An ArrayList of Fruit elements
   */
  public ArrayList<Fruit> getFruits() {
    return fruits;
  }
  
  /**
   * Returns an ArrayList of NPC elements.
   * 
   * @return
   *          An ArrayList of NPC elements
   */
  public ArrayList<NPC> getNPCs() {
    return npcs;
  }


  /**
   * Returns a Player object.
   * 
   * @return
   *          A player object
   */
  public Player getPlayer() {
    return player;
  }

}
