package nl.tudelft.scrumbledore;

/**
 * Test Suite for the Bubble class.
 * 
 * @author Jesse Tilro
 *
 */
public class BubbleTest extends LevelElementTest {

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Bubble(position, size);
  }

}
