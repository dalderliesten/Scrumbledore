package nl.tudelft.scrumbledore;


/**
 * Testing the NPC class.
 * 
 * @author Floris Doolaard
 * @author Niels Warnars
 */
public class NPCTest extends LevelElementTest {

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new NPC(position, size);
  }

}
