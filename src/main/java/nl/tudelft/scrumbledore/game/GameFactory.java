package nl.tudelft.scrumbledore.game;

import nl.tudelft.scrumbledore.level.BubbleActionsLevelModifier;
import nl.tudelft.scrumbledore.level.CollisionsLevelModifier;
import nl.tudelft.scrumbledore.level.GravityLevelModifier;
import nl.tudelft.scrumbledore.level.KineticsLevelModifier;
import nl.tudelft.scrumbledore.level.NPCLevelModifier;
import nl.tudelft.scrumbledore.level.PlayerActionsLevelModifier;
import nl.tudelft.scrumbledore.level.WarpLevelModifier;

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
   * Makes a new Single / Multiplayer game.
   * @param c 
   *          A given class of game.
   * 
   * @return A SinglePlayer or MultiPlayer game.
   */
  public Game makeGame(Class c) {
    Game game = null;
    
    if (c == SinglePlayerGame.class) {
      game = new SinglePlayerGame();
    } else if (c == MultiPlayerGame.class) {
      game = new MultiPlayerGame();
    }
    
    makeLevelModifiers(game);
    return game;
  }


  private void makeLevelModifiers(Game game) {
    game.registerLevelModifier(new PlayerActionsLevelModifier());
    game.registerLevelModifier(new GravityLevelModifier());
    game.registerLevelModifier(new NPCLevelModifier());
    game.registerLevelModifier(new BubbleActionsLevelModifier());
    game.registerLevelModifier(new CollisionsLevelModifier(game.getScoreCounter()));
    game.registerLevelModifier(new KineticsLevelModifier());
    game.registerLevelModifier(new WarpLevelModifier());
  }

}
