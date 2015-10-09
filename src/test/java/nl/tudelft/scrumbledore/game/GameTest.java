package nl.tudelft.scrumbledore.game;

//import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.level.Fruit;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;

/**
 * Testing the Game class.
 * 
 * @author Floris Doolaard
 * @author Jesse Tilro
 *
 */
public abstract class GameTest {
  private Game game;
  private ArrayList<Level> levels;

  /**
   * Setting up test properties.
   * 
   * @throws Exception
   */
  @Before
  public void before() {
    Level level1 = new Level();
    Level level2 = new Level();
    level2.addElement(new Fruit(new Vector(32, 32), new Vector(32, 32)));
    levels = new ArrayList<Level>();
    levels.add(level1);
    levels.add(level2);
    game = make(levels);
  }

  /**
   * Make the Game to be considered test object.
   * 
   * @param levels
   *          The levels the Game needs to consist of.
   * @return The test object.
   */
  protected abstract Game make(ArrayList<Level> levels);

  /**
   * Test the constructor of the Game.
   */
  @Test
  public void testConstructor() {
    assertEquals(levels, game.getLevels());
    assertEquals(levels.get(0), game.getCurrentLevel());
  }

  /**
   * The remaining levels method should initially return the total number of levels - 1.
   */
  @Test
  public void testRemainingLevelsInitial() {
    assertEquals(levels.size() - 1, game.remainingLevels());
  }

  /**
   * After proceeding to the next level, the remaining levels method should return the total number
   * of levels - 2.
   */
  @Test
  public void testRemainingLevelsAfterGoingToNextLevel() {
    game.goToNextLevel();
    assertEquals(levels.size() - 2, game.remainingLevels());
  }

  /**
   * When going to the next Level, the current Level should be updated to the next Level in the
   * list.
   */
  @Test
  public void testGoToNextLevel() {
    assertEquals(levels.get(0), game.getCurrentLevel());
    game.goToNextLevel();
    assertEquals(levels.get(1), game.getCurrentLevel());
  }

  /**
   * When the Game is restarted, it's current Level should be reset to the first Level again.
   */
  @Test
  public void testRestart() {
    game.goToNextLevel();
    game.restart();
    // Since on restart the game loads levels from the file system instead of our fixtures, we use
    // an assertion relative to the Game's own levels instead of our fixtures.
    assertEquals(game.getLevels().get(0), game.getCurrentLevel());
  }

  /**
   * The step counting mechanism should accumulate the exact numbers of steps correctly.
   */
  @Test
  public void testSteps() {
    game.addSteps(3.2);
    game.addSteps(6.4);
    assertEquals(9.6, game.getSteps(), Constants.DOUBLE_PRECISION);
    assertEquals(9, game.getFullSteps());
  }

}
