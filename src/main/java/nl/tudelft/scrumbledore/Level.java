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

}
