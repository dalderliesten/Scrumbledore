package nl.tudelft.scrumbledore.sprite;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import nl.tudelft.scrumbledore.Constants;

/**
 * Test Suite for the Animated Sprite class.
 * 
 * @author Jesse Tilro
 *
 */
@RunWith(Parameterized.class)
public class AnimatedSpriteTest {

  private AnimatedSprite sprite;
  private double steps;
  private String expectedFrame;

  /**
   * Construct a new test suite with the given parameters.
   * 
   * @param interval
   *          The interval of steps between two frames.
   * @param steps
   *          The number of steps that should have passed after retrieving the current frame.
   * @param expectedFrame
   *          The ID of the expected frame to be retrieved given the interval and steps.
   */
  @SuppressWarnings("PMD.AvoidDuplicateLiterals")
  public AnimatedSpriteTest(double interval, double steps, String expectedFrame) {
    this.steps = steps;
    this.expectedFrame = expectedFrame;
    sprite = new AnimatedSprite("test", interval);
    sprite.addFrame(new Sprite("frame-1"));
    sprite.addFrame(new Sprite("frame-2"));
    sprite.addFrame(new Sprite("frame-3"));
  }

  /**
   * Test get frame.
   */
  @Test
  public void testGetFrame() {
    Sprite frame = sprite.getFrame(steps);
    assertEquals(expectedFrame, frame.getID());
  }

  /**
   * Data for the boundary tests.
   * 
   * @return Collection of tuples of input values.
   */
  @Parameters
  public static Collection<Object[]> data() {
    Collection<Object[]> input = new ArrayList<Object[]>();
    input.add(new Object[] { 5, 0, "frame-1" });
    input.add(new Object[] { 5, 5, "frame-2" });
    input.add(new Object[] { 5, 6, "frame-2" });
    input.add(new Object[] { 5, 14, "frame-3" });
    input.add(new Object[] { 5, 15, "frame-1" });
    input.add(new Object[] { 20, 30, "frame-2" });
    return input;
  }
  
  /**
   * Test the getID() method.
   */
  @Test
  public void testGetID() {
    AnimatedSprite as = new AnimatedSprite("test", 42d, new ArrayList<Sprite>());
    assertEquals("test", as.getID());
  }
  
  /**
   * Test the getInterval() method.
   */
  @Test
  public void testGetInterval() {
    AnimatedSprite as = new AnimatedSprite("test", 42d, new ArrayList<Sprite>());
    assertEquals(42, as.getInterval(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the SetInterval() method.
   */
  @Test
  public void testSetInterval() {
    AnimatedSprite as = new AnimatedSprite("test", 42d, new ArrayList<Sprite>());
    as.setInterval(21d);
    assertEquals(21, as.getInterval(), Constants.DOUBLE_PRECISION);
  }
}
