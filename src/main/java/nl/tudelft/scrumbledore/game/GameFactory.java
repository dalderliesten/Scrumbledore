package nl.tudelft.scrumbledore.game;

import nl.tudelft.scrumbledore.level.BubbleActionsLevelModifier;
import nl.tudelft.scrumbledore.level.CollisionsLevelModifier;
import nl.tudelft.scrumbledore.level.GravityLevelModifier;
import nl.tudelft.scrumbledore.level.KineticsLevelModifier;
import nl.tudelft.scrumbledore.level.LevelModifier;
import nl.tudelft.scrumbledore.level.NPCLevelModifier;
import nl.tudelft.scrumbledore.level.PlayerActionsLevelModifier;

/**
 * Class responsible for creating different types of Games. Conforming to the Factory design
 * pattern.
 * 
 * @author Jesse Tilro
 *
 */
public class GameFactory {

  /**
   * Constructs a new GameFactory that utilizes a given LevelParser.
   */
  public GameFactory() {
  }

  /**
   * Make a new SinglePlayerGame.
   * 
   * @return The SinglePlayerGame.
   */
  public Game makeSinglePlayerGame() {
    Game game = new SinglePlayerGame();
    makeLevelModifiers(game);
    return game;
  }

  /**
   * Make a new MultiPlayerGame.
   * 
   * @return The MultiPlayerGame.
   */
  public Game makeMultiPlayerGame() {
    Game game = new MultiPlayerGame();
    makeLevelModifiers(game);
    return game;
  }

  private void makeLevelModifiers(Game game) {
    KineticsLevelModifier kinetics = new KineticsLevelModifier();
    LevelModifier player = new PlayerActionsLevelModifier();
    LevelModifier gravity = new GravityLevelModifier();
    LevelModifier npc = new NPCLevelModifier();
    LevelModifier collisions = new CollisionsLevelModifier(kinetics, game.getScoreCounter());
    LevelModifier bubbles = new BubbleActionsLevelModifier();

    game.registerLevelModifier(player);
    game.registerLevelModifier(gravity);
    game.registerLevelModifier(npc);
    game.registerLevelModifier(bubbles);
    game.registerLevelModifier(collisions);
    game.registerLevelModifier(kinetics);
  }

}
