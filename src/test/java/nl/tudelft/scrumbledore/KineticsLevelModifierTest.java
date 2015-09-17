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
