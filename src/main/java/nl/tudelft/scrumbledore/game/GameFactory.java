package nl.tudelft.scrumbledore.game;

import nl.tudelft.scrumbledore.level.CollisionsLevelModifier;
import nl.tudelft.scrumbledore.level.GravityLevelModifier;
import nl.tudelft.scrumbledore.level.KineticsLevelModifier;
import nl.tudelft.scrumbledore.level.NPCLevelModifier;
import nl.tudelft.scrumbledore.level.PlayerActionsLevelModifier;
import nl.tudelft.scrumbledore.level.WarpLevelModifier;
import nl.tudelft.scrumbledore.projectile.ProjectileActionsLevelModifier;

/**
 * Class responsible for creating different types of Games. Conforming to the Factory design
 * pattern.
 * 
 * @author Jesse Tilro
 *
 */
public abstract class GameFactory {

  /**
   * Constructs a new GameFactory that utilizes a given LevelParser.
   */
  public GameFactory() {
  }

  /**
   * Makes a new Single / MultiPlayer game.

   * @return A SinglePlayer or MultiPlayer game.
   */
  public Game makeGame() {
    Game game = createGame();
    makeLevelModifiers(game);
    return game;
  }

  /**
   * Abstract method implemented by child classes for the creation of a specific type of game.
   * 
   * @return A Single Player or MultiPlayer game.
   */
  abstract Game createGame();

  /**
   * Registers Level Modifiers to a given game.
   * 
   * @param game A given game.
   */
  protected void makeLevelModifiers(Game game) {
    game.registerLevelModifier(new PlayerActionsLevelModifier());
    game.registerLevelModifier(new GravityLevelModifier());
    game.registerLevelModifier(new NPCLevelModifier());
    game.registerLevelModifier(new ProjectileActionsLevelModifier());
    game.registerLevelModifier(new CollisionsLevelModifier(game.getScoreCounter()));
    game.registerLevelModifier(new KineticsLevelModifier());
    game.registerLevelModifier(new WarpLevelModifier());
  }

}
