package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
//import org.junit.Test;
import org.junit.Test;

/**
 * Testing the Kinetics class.
 * 
 * @author Floris Doolaard
 *
 */
public class KineticsTest {

  private Vector pos;
  private Vector size;
  private Fruit f1;
  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void setUpBeforeClass() {
    pos = new Vector(1.0, 1.0);
    size = new Vector(1.0, 1.0);
    f1 = new Fruit(pos, size);
  }

  /**
   * Testing the addSpeed method.
   */
  @Test
  public void testAddSpeed() {
    // TO BE IMPLEMENTED
  }

  /**
   * Testing the update method.
   */
  @Test
  public void testUpdate() {
    // TO BE IMPLEMENTED
  }

  /**
   * Deleting test properties after testing.
   * 
   * @throws Exception
   */
  @After
  public void tearDownAfterClass() {
    pos = null;
    size = null;
    f1 = null;
  }

}
