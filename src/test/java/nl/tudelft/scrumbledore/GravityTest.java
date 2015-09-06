package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test Suite for Gravity class.
 * 
 * @author Jesse Tilro
 *
 */
public class GravityTest {

  /**
   * When a level element is pulled that has not yet reached the maximal vertical speed and is not
   * about too in the next pull either, its vertical speed should be incremented with the strength.
   */
  @Test
  public void testPullNotReachedMaxSpeed() {
    int strength = 2;
    int max = 8;
    LevelElement test = new TestElement();
    Gravity gravity = new Gravity(strength, max);

    gravity.pull(test);

    assertEquals(0 + strength, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a level element is pulled that is about to reach or exceed the maximal vertical speed, its
   * vertical speed should be set to the maximal vertical speed.
   */
  @Test
  public void testPullAboutToReachMaxSpeed() {
    int strength = 2;
    int max = 8;
    LevelElement test = new TestElement();
    Gravity gravity = new Gravity(strength, max);
    test.getSpeed().setY(max - (strength - 1));

    gravity.pull(test);

    assertEquals(max, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a level element is pulled that whose vertical speed has in some way exceeded the maximal
   * vertical speed, its vertical speed should be set to the maximal vertical speed.
   */
  @Test
  public void testPullExceededMaxSpeed() {
    int strength = 2;
    int max = 8;
    LevelElement test = new TestElement();
    Gravity gravity = new Gravity(strength, max);
    test.getSpeed().setY(max + 1);

    gravity.pull(test);

    assertEquals(max, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When an entire level is pulled, all gravity affected LevelElements are pulled down.
   */
  @Test
  public void testPullLevel() {
    int strength = 2;
    int max = 8;

    Gravity gravity = new Gravity(strength, max);
    LevelElement test = new TestElement();
    LevelElement test2 = new TestElement();
    test.getSpeed().setY(0);
    test2.getSpeed().setY(7);
    Level level = new Level();
    level.addElement(test);
    level.addElement(test2);

    gravity.pull(level);

    assertEquals(0 + strength, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
    assertEquals(max, test2.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When an entire level is pulled, elements unaffected by gravity shouldn't be affected.
   */
  @Test
  public void testPullLevelUnaffected() {
    int strength = 2;
    int max = 8;

    Gravity gravity = new Gravity(strength, max);
    LevelElement test = new TestElement();
    test.setGravity(false);
    Level level = new Level();
    level.addElement(test);

    gravity.pull(level);

    assertEquals(0, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

}
