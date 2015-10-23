package nl.tudelft.scrumbledore.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import nl.tudelft.scrumbledore.level.BubbleActionsLevelModifier;
import nl.tudelft.scrumbledore.level.CollisionsLevelModifier;
import nl.tudelft.scrumbledore.level.GravityLevelModifier;
import nl.tudelft.scrumbledore.level.KineticsLevelModifier;
import nl.tudelft.scrumbledore.level.LevelModifier;
import nl.tudelft.scrumbledore.level.NPCLevelModifier;
import nl.tudelft.scrumbledore.level.PlayerActionsLevelModifier;
import nl.tudelft.scrumbledore.level.WarpLevelModifier;

/**
 * Test suite for the GameFactory class.
 * 
 * @author Niels Warnars
 */
public class GameFactoryTest {

  /**
   * Make sure that a SinglePlayerGame instance contains the specified Level Modifiers.
   */
  @Test
  public final void testMakeSinglePlayerGame() {
    SinglePlayerGameFactory sFactory = new SinglePlayerGameFactory();
    Game game = sFactory.makeGame();
    ArrayList<LevelModifier> modifiers = game.getModifiers();

    assertEquals(7, modifiers.size());
    assertEquals(1, countLevelModifiers(PlayerActionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(GravityLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(NPCLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(BubbleActionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(CollisionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(KineticsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(WarpLevelModifier.class, modifiers));
  }

  /**
   * Make sure that a MultiPlayerGame instance contains the specified Level Modifiers.
   */
  @Test
  public final void testMakeMultiPlayerGame() {
    MultiPlayerGameFactory mFactory = new MultiPlayerGameFactory();
    Game game = mFactory.makeGame();
    ArrayList<LevelModifier> modifiers = game.getModifiers();

    assertEquals(7, modifiers.size());
    assertEquals(1, countLevelModifiers(PlayerActionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(GravityLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(NPCLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(BubbleActionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(CollisionsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(KineticsLevelModifier.class, modifiers));
    assertEquals(1, countLevelModifiers(WarpLevelModifier.class, modifiers));
  }

  /**
   * Counts the number of instances of a given class in a list of LevelModifiers.
   * 
   * @param c
   *          A class to counted
   * @param modifiers
   *          An ArrayList of LevelModifiers
   * @return The number of instances of an input object counted in the list of modifiers
   */
  private static int countLevelModifiers(Class c, ArrayList<LevelModifier> modifiers) {
    int count = 0;

    for (LevelModifier modifier : modifiers) {
      if (modifier.getClass() == c) {
        count++;
      }
    }

    return count;
  }
}
