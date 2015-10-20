package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test suite of the Constants class.
 * 
 * @author Niels Warnars
 */
public class ConstantsTest {

  /**
   * Test the getter/setter methods of the loggingWantMovement field.
   */
  @Test
  public final void testLoggingWantMovement() {
    assertFalse(Constants.isLoggingWantMovement());
    Constants.setLoggingWantMovement(true);
    assertTrue(Constants.isLoggingWantMovement()); 
  }

  /**
   * Test the getter/setter methods of the loggingWantInput field.
   */
  @Test
  public final void testLoggingWantInput() {
    assertFalse(Constants.isLoggingWantInput());
    Constants.setLoggingWantInput(true);
    assertTrue(Constants.isLoggingWantInput());
  }

  /**
   * Test the getter/setter methods of the loggingWantStartStop field.
   */
  @Test
  public final void testLoggingWantStartStop() {
    assertFalse(Constants.isLoggingWantStartStop());
    Constants.setLoggingWantStartStop(true);
    assertTrue(Constants.isLoggingWantStartStop());
  }

  /**
   * Test the getter/setter methods of the loggingWantShooting field.
   */
  @Test
  public final void testLoggingWantShooting() {
    assertFalse(Constants.isLoggingWantShooting());
    Constants.setLoggingWantShooting(true);
    assertTrue(Constants.isLoggingWantShooting());
  }

  /**
   * Test the getter/setter methods of the loggingWantPoints field.
   */
  @Test
  public final void testLoggingWantPoints() {
    assertFalse(Constants.isLoggingWantPoints());
    Constants.setLoggingWantPoints(true);
    assertTrue(Constants.isLoggingWantPoints());
  }

  /**
   * Test the getter/setter methods of the loggingWantEnemy field.
   */
  @Test
  public final void testLoggingWantEnemy() {
    assertFalse(Constants.isLoggingWantEnemy());
    Constants.setLoggingWantEnemy(true);
    assertTrue(Constants.isLoggingWantEnemy());
  }

}
