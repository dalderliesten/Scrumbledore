package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import nl.tudelft.scrumbledore.game.Game;
import nl.tudelft.scrumbledore.game.MultiPlayerGame;
import nl.tudelft.scrumbledore.game.SinglePlayerGame;

/**
 * Test Suite for the StepTimer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 */
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.TooManyStaticImports",
    "PMD.TooManyMethods" })
public class StepTimerTest {

  /**
   * Testing the getting of the rate.
   */
  @Test
  public void testGetRate() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);

    assertEquals(30, toTest.getRate());
  }

  /**
   * Testing the setting of the rate, and verifying its function.
   */
  @Test
  public void testSetRate() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);

    toTest.setRate(60);
    assertEquals(60, toTest.getRate());
  }

  /**
   * Testing the getting of the game to which the timer is connected/linked.
   */
  @Test
  public void testGetGame() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);

    assertEquals(game, toTest.getGame());
  }

  /**
   * Testing the setting of the game.
   */
  @Test
  public void testSetGame() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);

    Game altGame = mock(Game.class);
    toTest.setGame(altGame);
    
    assertEquals(altGame, toTest.getGame());
  }
  
  /**
   * Test the pausing of the game.
   */
  @Test
  public void pauseGame() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);
    
    assertFalse(toTest.isPaused());
    
    toTest.pause();
    
    assertTrue(toTest.isPaused());
  }
  
  /**
   * Test the resuming/restarting of the game.
   */
  @Test
  public void resumeGame() {
    Game game = mock(Game.class);
    StepTimer toTest = new StepTimer(30, game);
    
    assertFalse(toTest.isPaused());
    
    toTest.pause();
    
    assertTrue(toTest.isPaused());
    
    toTest.resume();
    
    assertFalse(toTest.isPaused());
  }

  /**
   * The game loop should aim to iterate at a steady rate. In this case it is tested by verifying
   * that the Game's step method is called the right amount of times.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test case.
   */
  @Test
  public void testLoopGameMethodCalls() throws InterruptedException {
    int rate = 30;
    int deviation = 2;
    Game game = mock(Game.class);
    StepTimer timer = new StepTimer(rate, game);

    timer.start();
    Thread.sleep(1000);
    verify(game, atLeast(rate - deviation)).step(anyDouble());
    verify(game, atMost(rate + deviation)).step(anyDouble());
  }

  /**
   * The game loop should aim to iterate at a steady rate. In this case it is tested by checking the
   * Game's step counter.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test case.
   */
  @Test
  public void testLoopGameStepCounter() throws InterruptedException {
    int rate = 30;
    int deviation = 2;
    Game game = new MultiPlayerGame();

    StepTimer timer = new StepTimer(15, game);

    timer.setRate(rate);

    timer.start();
    Thread.sleep(1000);
    assertTrue(game.getSteps() >= rate - deviation);
    assertTrue(game.getSteps() <= rate + deviation);
  }

  /**
   * When the StepTimer is paused, no more calls to the Game's step method should be made. When
   * resumed, calls to step should be made again.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test case.
   */
  @Test
  public void testPauseResume() throws InterruptedException {
    Game game = mock(Game.class);
    StepTimer timer = new StepTimer(30, game);

    timer.start();
    timer.pause();
    Thread.sleep(100);

    assertTrue(timer.isPaused());
    verify(game, times(0)).step(anyDouble());

    timer.resume();
    Thread.sleep(100);

    verify(game, atLeast(1)).step(anyDouble());
  }

  /**
   * When the StepTimer is stopped, no more calls to the Game's step method should be made.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test case.
   */
  @Test
  public void testStop() throws InterruptedException {
    Game game = mock(Game.class);
    StepTimer timer = new StepTimer(30, game);

    timer.start();
    timer.stop();
    Thread.sleep(100);

    verify(game, times(0)).step(anyDouble());
  }

  /**
   * Test the Game getter / setter for line coverage.
   */
  @Test
  public void testGameGetterSetter() {
    Game game = new MultiPlayerGame();
    Game otherGame = new SinglePlayerGame();
    StepTimer timer = new StepTimer(30, game);
    timer.setGame(otherGame);

    assertEquals(otherGame, timer.getGame());
  }

  /**
   * Test the rate getter / setter for line coverage.
   */
  @Test
  public void testRateGetterSetter() {
    StepTimer timer = new StepTimer(30, mock(Game.class));
    timer.setRate(60);
    assertEquals(60, timer.getRate());
  }

  /**
   * When the StepTimer has already been started, it should not be able to start another parallel
   * loop running the Game.
   */
  @Test(expected = AssertionError.class)
  public void testStartAlreadyStarted() {
    Game game = mock(Game.class);
    StepTimer timer = new StepTimer(30, game);

    timer.start();
    timer.start();
  }

}
