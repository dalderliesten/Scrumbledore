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
    game.registerLevelModifier(new PlayerActionsLevelModifier());
    game.registerLevelModifier(new GravityLevelModifier());
    game.registerLevelModifier(new NPCLevelModifier());
    game.registerLevelModifier(new ProjectileActionsLevelModifier());
    game.registerLevelModifier(new CollisionsLevelModifier(game.getScoreCounter()));
    game.registerLevelModifier(new KineticsLevelModifier());
    game.registerLevelModifier(new WarpLevelModifier());
  }

}
