package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing the Kinetics class.
 * 
 * @author Floris Doolaard
 *
 */
public class KineticsLevelModifierTest {

  private KineticsLevelModifier kinetics;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void setUpBeforeClass() {
    kinetics = new KineticsLevelModifier();
  }

  /**
   * When the an element has a speed larger than its friction vector, the friction vector simply
   * needs to be absolutely subtracted from the speed vector. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedLargerThanFriction() {
    LevelElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(8);
    el.getFriction().setY(8);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(2, -2);

    kinetics.applyFriction(el, .25);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * When an element has a speed x entry being smaller than the friction x entry to be subtracted,
   * its speed x entry should be set to 0. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedXSmaller() {
    LevelElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(8);
    el.getFriction().setY(4);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(0, -1);

    kinetics.applyFriction(el, .75);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * When an element has a speed y entry being smaller than the friction y entry to be subtracted,
   * its speed y entry should be set to 0. (MCDC)
   */
  @Test
  public void testApplyFrictionSpeedYSmaller() {
    LevelElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    // Set speed
    el.getSpeed().sum(new Vector(4, -4));
    // Set friction
    el.getFriction().setX(4);
    el.getFriction().setY(8);
    // The expected resulting speed vector given the delta
    Vector expectedOut = new Vector(1, 0);

    kinetics.applyFriction(el, .75);

    assertEquals(expectedOut, el.getSpeed());
  }

  /**
   * The position of an element should be updated correctly when adding it's speed vector and taking
   * the delta into account.
   */
  @Test
  public void testAddSpeed() {
    LevelElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    el.getSpeed().sum(new Vector(2, 2));
    kinetics.addSpeed(el, 0.5);
    // For branch coverage:
    kinetics.addSpeed(null, 1);
    assertEquals(new Vector(2, 3), el.getPosition());
  }

  /**
   * The position of an element should be updated correctly when removing it's speed vector and
   * taking the delta into account.
   */
  @Test
  public void testRemoveSpeed() {
    LevelElement el = new Bubble(new Vector(1, 2), new Vector(0, 0));
    el.getSpeed().sum(new Vector(2, 2));
    kinetics.removeSpeed(el, 0.5);
    // For branch coverage:
    kinetics.removeSpeed(null, 1);
    assertEquals(new Vector(0, 1), el.getPosition());
  }

  /**
   * When stopping a Level Element horizontally, its horizontal speed should be zero.
   */
  @Test
  public void testStopHorizontally() {
    LevelElement el = new Bubble(new Vector(0, 0), new Vector(0, 0));
    el.getSpeed().setX(42);
    kinetics.stopHorizontally(el);
    assertEquals(0, el.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When stopping a Level Element vertically, its vertical speed should be zero.
   */
  @Test
  public void testStopVertically() {
    LevelElement el = new Bubble(new Vector(0, 0), new Vector(0, 0));
    el.getSpeed().setY(42);
    kinetics.stopVertically(el);
    assertEquals(0, el.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the left side of another one using the snapLeft
   * method.
   */
  @Test
  public void testSnapLeft() {
    LevelElement snapper = new Player(new Vector(0, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(64, 0), new Vector(32, 32));

    kinetics.snapLeft(snapper, snapTo);

    assertEquals(32, snapper.getPosition().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the right side of another one using the snapRight
   * method.
   */
  @Test
  public void testSnapRight() {
    LevelElement snapper = new Player(new Vector(64, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 0), new Vector(32, 32));

    kinetics.snapRight(snapper, snapTo);

    assertEquals(32, snapper.getPosition().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the top side of another one using the snapTop
   * method.
   */
  @Test
  public void testSnapTop() {
    LevelElement snapper = new Player(new Vector(0, 0), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 64), new Vector(32, 32));

    kinetics.snapTop(snapper, snapTo);

    assertEquals(32, snapper.getPosition().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * The LevelElement should be correctly snapped the top side of another one using the snapTop
   * method.
   */
  @Test
  public void testSnapBottom() {
    LevelElement snapper = new Player(new Vector(0, 64), new Vector(32, 32));
    LevelElement snapTo = new Platform(new Vector(0, 0), new Vector(32, 32));

    kinetics.snapBottom(snapper, snapTo);

    assertEquals(32, snapper.getPosition().getY(), Constants.DOUBLE_PRECISION);
  }

}
