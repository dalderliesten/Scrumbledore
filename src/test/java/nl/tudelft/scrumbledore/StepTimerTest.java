package nl.tudelft.scrumbledore;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;

/**
 * Test Suite for the StepTimer class.
 * 
 * @author Jesse Tilro
 *
 */
public class StepTimerTest {

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
    Game game = new Game();
    // Initially set the wrong rate.
    StepTimer timer = new StepTimer(15, game);
    // Set rate should update all internal parameters correctly.
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
    Game game = new Game();
    ArrayList<Level> levels = new ArrayList<Level>();
    levels.add(new Level());
    Game otherGame = new Game(levels);
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
