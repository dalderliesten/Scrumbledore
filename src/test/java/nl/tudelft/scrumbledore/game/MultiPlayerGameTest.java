package nl.tudelft.scrumbledore.game;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.level.Level;

/**
 * Test Suite for the MultiPlayerGameTest.
 * 
 * @author Jesse Tilro
 *
 */
public class MultiPlayerGameTest extends GameTest {

  @Override
  protected Game make(ArrayList<Level> levels) {
    return new MultiPlayerGame(levels);
  }

}
