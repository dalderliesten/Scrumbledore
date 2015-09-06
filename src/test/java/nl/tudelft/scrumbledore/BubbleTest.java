package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;

//import org.junit.Test;

/**
 * Testing the Bubble class.
 * 
 * @author Floris Doolaard
 *
 */
public class BubbleTest extends ProjectileTest{

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * Deleting test properties after testing.
   * 
   * @throws Exception
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Override
  public LevelElement make(Vector position, Vector size) {
    return new Bubble(position, size);
  }

}
