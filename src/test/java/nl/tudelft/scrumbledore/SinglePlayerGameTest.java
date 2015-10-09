package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Test Suite for the SinglePlayerGame class.
 * 
 * @author Jesse Tilro
 *
 */
public class SinglePlayerGameTest extends GameTest {

  @Override
  protected Game make(ArrayList<Level> levels) {
    return new SinglePlayerGame(levels);
  }

}
