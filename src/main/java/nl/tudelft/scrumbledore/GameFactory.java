package nl.tudelft.scrumbledore;

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
    game.registerLevelModifier(collisions);
    game.registerLevelModifier(bubbles);
    game.registerLevelModifier(kinetics);
  }

}
