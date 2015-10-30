package nl.tudelft.scrumbledore.level.element;

import nl.tudelft.scrumbledore.level.Vector;

import org.junit.Test;

/**
 * Abstract Player test class.
 * 
 * @author Floris Dolaard
 */
@SuppressWarnings("PMD.TooManyMethods")
public abstract class PlayerElementTest extends LevelElementTest {

  /**
   * Returns an instance of a LevelElement subclass to be tested.
   * 
   * @return A LevelElement test object.
   * @param position
   *          The position to be passed into the constructor of the LevelElement.
   * @param size
   *          The size to be passed into the constructor of the LevelElement.
   */
  public abstract LevelElement make(Vector position, Vector size);

  
  /**
   * When a player action is added to a bubble's action queue, a call to hasAction for that action
   * should return true.
   */
  @Test
  public abstract void testHasActionTrue();
  
  /**
   * Test the firing field getter/setter.
   */
  @Test
  public abstract void testFiring();

  /**
   * Test the alive field getter/setter.
   */
  @Test
  public abstract void testAlive();

  /**
   * Test the id field getter/setter.
   */
  @Test
  public abstract void testPlayerNumber();

  /**
   * Test the lastMove field getter/setter.
   */
  @Test
  public abstract void testLastMove();

  /**
   * Two player instances with the same position and size should be considered equal.
   */
  @Test
  public abstract void testEqualsTrue();

  /**
   * Two player instances with a different position or size should not be considered equal.
   */
  @Test
  public abstract void testEqualsFalse();

  /**
   * A Player instance and an instance of another class should not be considered equal.
   */
  @Test
  public abstract void testEqualsFalseOtherClass();

  /**
   * The stubbed method hashCode should just return zero (line coverage).
   */
  @Test
  public abstract void testHashCode();
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned.
   */
  @Test
  public abstract void testGetSprites();
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned when it is shooting to the right.
   */
  @Test
  public abstract void testGetSpritesShootingRight();
  
  /**
   * Test the getSprites method to verify whether the correct
   * sprite(s) is/are being returned when it is shooting to the left.
   */
  @Test
  public abstract void testGetSpritesShootingLeft();
  
  /**
   * Test the getter/setter methods of the lifetime attribute of a Player object.
   */
  @Test
  public abstract void testLifetime();
  
  /**
   * Test the decreasing of the lifetime of a player by a given number of steps.
   */
  @Test
  public abstract void testDecreaseLifetime();
}
