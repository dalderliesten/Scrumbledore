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
   * Returns a Player object.
   * 
   * @return
   *          A player object
   */
  public Player getPlayer() {
    return player;
  }

}
