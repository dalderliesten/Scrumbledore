package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

/**
 * Class representing a Level in the Game.
 * 
 * @author Jesse Tilro
 *
 */
public class Level {

  private ArrayList<Platform> platforms;
  private ArrayList<Fruit> fruits;
  private ArrayList<NPC> npcs;
  private ArrayList<Bubble> bubbles;
  private ArrayList<Player> players;
  private ArrayList<Bubble> encapEnemies;

  /**
   * Constructs a new Level instance.
   */
  public Level() {
    platforms = new ArrayList<Platform>();
    bubbles = new ArrayList<Bubble>();
    encapEnemies = new ArrayList<Bubble>();
    fruits = new ArrayList<Fruit>();
    npcs = new ArrayList<NPC>();
    players = new ArrayList<Player>();
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
    } else if (element.getClass().equals(NPC.class)) {
      npcs.add((NPC) element);
    } else if (element.getClass().equals(Fruit.class)) {
      fruits.add((Fruit) element);
    } else if (element.getClass().equals(Player.class)) {
      players.add((Player) element);
    } else if (element.getClass().equals(Bubble.class)) {
      bubbles.add((Bubble) element);
    }
  }

  /**
   * Returns an ArrayList of Platform elements.
   * 
   * @return An ArrayList of Platform elements
   */
  public ArrayList<Platform> getPlatforms() {
    return platforms;
  }

  /**
   * Returns an ArrayList of Fruit elements.
   * 
   * @return An ArrayList of Fruit elements
   */
  public ArrayList<Fruit> getFruits() {
    return fruits;
  }

  /**
   * Returns an ArrayList of NPC elements.
   * 
   * @return An ArrayList of NPC elements
   */
  public ArrayList<NPC> getNPCs() {
    return npcs;
  }

  /**
   * Returns a Player object.
   * 
   * @return A player object
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }

  /**
   * Returns an ArrayList of Bubble objects.
   * 
   * @return An ArrayList of Bubble objects.
   */
  public ArrayList<Bubble> getBubbles() {
    return bubbles;
  }

  /**
   * Returns an ArrayList of Bubble objects with enemies in them.
   * 
   * @return An ArrayList of Bubble objects with enemies in them.
   */
  public ArrayList<Bubble> getEnemyBubbles() {
    return encapEnemies;
  }

}
