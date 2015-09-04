package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class representing a Level in the Game.
 * 
 * @author Jesse Tilro
 *
 */
public class Level {

  private ArrayList<LevelElement> elements;

  /**
   * Constructs a new Level instance.
   */
  public Level() {
    elements = new ArrayList<LevelElement>();
  }

  /**
   * Add a LevelElement to this Level.
   * 
   * @param element
   *          A LevelElement.
   */
  public void addElement(LevelElement element) {
    elements.add(element);
  }
  
  /**
   * Returns an ArrayList of level elements.
   * 
   * @return
   *        An ArrayList of level elements
   */
  public ArrayList<LevelElement> getElements() {
    return elements;
  }

  /**
   * Get all elements in the level.
   * 
   * @return All elements in the level.
   */
  public ArrayList<LevelElement> getElements() {
    return elements;
  }

}
