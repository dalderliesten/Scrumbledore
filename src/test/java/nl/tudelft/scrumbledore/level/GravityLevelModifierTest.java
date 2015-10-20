package nl.tudelft.scrumbledore.level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;

/**
 * Test Suite for Gravity class.
 * 
 * @author Jesse Tilro
 *
 */
public class GravityLevelModifierTest {

  private LevelElement test;
  private LevelElement test2;
  private double strength;
  private double max;

  /**
   * Setup a new instance of this Test Suite, providing it which a test object.
   */
  @Before
  public void setUp() {
    test = new Fruit(new Vector(0, 0), new Vector(32, 32));
    test2 = new Fruit(new Vector(0, 0), new Vector(32, 32));
    test.setGravity(true);
    test2.setGravity(true);
    strength = 2;
    max = 8;
  }

  /**
   * A new Gravity Level Modifier should be constructed with Constants.GRAVITY_MAX and
   * Constants.GRAVITY_STRENGTH.
   */
  @Test
  public void testConstructor() {
    GravityLevelModifier gravity = new GravityLevelModifier();
    
    assertEquals(Constants.GRAVITY_MAX, gravity.getMax(), Constants.DOUBLE_PRECISION);
    assertEquals(Constants.GRAVITY_STRENGTH, gravity.getStrength(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * When a pull action is performed on a null element no actions should be performed on this 
   * element.
   */
  @Test
  public void testPullNull() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    LevelElement el = null;
    gravity.pull(el, 1);

    assertNull(el);
  }
  
  /**
   * When a level element is pulled that has not yet reached the maximal vertical speed and is not
   * about too in the next pull either, its vertical speed should be incremented with the strength.
   */
  @Test
  public void testPullNotReachedMaxSpeed() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    gravity.pull(test, 1);

    assertEquals(0 + strength, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a level element is pulled that is about to reach or exceed the maximal vertical speed, its
   * vertical speed should be set to the maximal vertical speed.
   */
  @Test
  public void testPullAboutToReachMaxSpeed() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);

    test.getSpeed().setY(max - (strength - 1));

    gravity.pull(test, 1);

    assertEquals(max, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When a level element is pulled that whose vertical speed has in some way exceeded the maximal
   * vertical speed, its vertical speed should be set to the maximal vertical speed.
   */
  @Test
  public void testPullExceededMaxSpeed() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    test.getSpeed().setY(max + 1);

    gravity.pull(test, 1);

    assertEquals(max, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When an entire level is pulled, all gravity affected LevelElements are pulled down.
   */
  @Test
  public void testModifyLevelBoundaryValues() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    test.getSpeed().setY(0);
    test2.getSpeed().setY(7);
    Level level = new Level();
    level.addElement(test);
    level.addElement(test2);

    gravity.modify(level, 1.0d);

    assertEquals(0 + strength, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
    assertEquals(max, test2.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When an entire level is pulled, all gravity affected LevelElements (NPC, Fruit, Player) 
   * are pulled down.
   */
  @Test
  public void testModifyLevelAllElements() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));

    npc.getSpeed().setY(0);
    fruit.getSpeed().setY(0);
    player.getSpeed().setY(0);

    Level level = new Level();
    level.addElement(npc);
    level.addElement(fruit);
    level.addElement(player);

    gravity.modify(level, 1.0d);

    assertEquals(0 + strength, npc.getSpeed().getY(), Constants.DOUBLE_PRECISION);
    assertEquals(0 + strength, fruit.getSpeed().getY(), Constants.DOUBLE_PRECISION);
    assertEquals(0 + strength, player.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * When an entire level is pulled, elements unaffected by gravity shouldn't be affected.
   */
  @Test
  public void testModifyLevelUnaffected() {
    GravityLevelModifier gravity = new GravityLevelModifier(strength, max);
    test.setGravity(false);
    Level level = new Level();
    level.addElement(test);

    gravity.modify(level, 1.0d);

    assertEquals(0, test.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

}
