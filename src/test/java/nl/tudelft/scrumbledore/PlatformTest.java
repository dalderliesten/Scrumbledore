package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

//import org.junit.Test;

/**
 * Testing the Platform class.
 * 
 * @author Floris Doolaard
 *
 */
public class PlatformTest extends LevelElementTest {

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  /**
   * Testing the equals method.
   */
  @Test
  public void testEquals() {

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
    return new Platform(position, size);
  }

}