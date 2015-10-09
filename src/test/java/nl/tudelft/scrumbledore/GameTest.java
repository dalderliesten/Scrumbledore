package nl.tudelft.scrumbledore;

//import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing the Game class.
 * 
 * @author Floris Doolaard
 * @author Jesse Tilro
 *
 */
public class GameTest {
  private Game g1;
  private Level l1;
  private Level l2;
  private ArrayList<Level> list1;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void before() {
    l1 = new Level();
    l2 = new Level();
    list1 = new ArrayList<Level>();
    list1.add(l1);
    list1.add(l2);
    g1 = new MultiPlayerGame(list1);
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testRemainingLevels() {
    assertEquals(l1, g1.getCurrentLevel());
    assertEquals(l1, g1.getLevels().get(0));
    assertEquals(1, g1.remainingLevels());
  }

  /**
   * Testing the remainingLevels method.
   */
  @Test
  public void testGoToNextLevel() {

    assertEquals(l1, g1.getCurrentLevel());
    assertEquals(1, g1.remainingLevels());
    g1.goToNextLevel();
    assertEquals(l2, g1.getCurrentLevel());
    assertEquals(0, g1.remainingLevels());
  }

  /**
   * Test the restart() method.
   */
  @Test
  public void testRestart() {
    assertEquals(1, g1.remainingLevels());

    g1.goToNextLevel();
    assertEquals(0, g1.remainingLevels());

    g1.restart();
    assertTrue(g1.remainingLevels() > 0);
  }

  /**
   * Test step counting methods.
   */
  @Test
  public void testSteps() {
    g1.addSteps(3.2);
    g1.addSteps(6.4);
    assertEquals(9.6, g1.getSteps(), Constants.DOUBLE_PRECISION);
    assertEquals(9, g1.getFullSteps());
  }

}
