package nl.tudelft.scrumbledore;

/**
 * A Test LevelElement, temporarily used to test the Gravity class until the player class has been
 * implemented.
 * 
 * @author Jesse Tilro
 *
 */
public class TestElement extends LevelElement {

  /**
   * Constructs a new TestElement instance affected by Gravity.
   */
  public TestElement() {
    super(new Vector(0, 0), new Vector(32, 32));
    setGravity(true);
  }
  
}
