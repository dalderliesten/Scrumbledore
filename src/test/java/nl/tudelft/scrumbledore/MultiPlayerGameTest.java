package nl.tudelft.scrumbledore;

import java.util.ArrayList;

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
