package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class CollisionsLevelModifier implements LevelModifier {

  private KineticsLevelModifier kinetics;
  private ScoreCounter score;

  /**
   * Constructs a new Collisions Level Modifier using a given Kinetics Level Modifier.
   * 
   * @param kinetics
   *          The Kinetics Level Modifier to be used.
   * @param score
   *          The Score Counter to be used.
   */
  public CollisionsLevelModifier(KineticsLevelModifier kinetics, ScoreCounter score) {
    this.kinetics = kinetics;
    this.score = score;
  }

  /**
   * Detect collisions in level.
   * 
   * @param level
   *          The level.
   * @param delta
   *          The steps passed since this method wat last executed.
   */
  public void modify(Level level, double delta) {
    detectPlayerBubble(level, delta);
    detectFruitPlatform(level, delta);
    detectBubblePlatform(level, delta);
    detectPlayerPlatform(level, delta);
    detectPlayerFruit(level, delta);
    detectPlayerEnemy(level, delta);
    detectBubbleEnemy(level, delta);
  }

  /**
   * Detect collisions between player and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public void detectFruitPlatform(Level level, double delta) {
    for (Fruit fruit : level.getFruits()) {
      for (Platform platform : level.getPlatforms()) {
        // Check if platform is in collision range.
        if (platform.inBoxRangeOf(fruit, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(fruit, platform, delta);

          // Collision while falling
          if (collision.collidingFromTop() && fruit.vSpeed() > 0) {
            kinetics.stopVertically(fruit);
            kinetics.snapTop(fruit, platform);
          }
        }
      }
    }
  }

  /**
   * Detect collisions between player and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public void detectPlayerPlatform(Level level, double delta) {
    Player player = level.getPlayer();

    for (Platform platform : level.getPlatforms()) {
      // Check if platform is in collision range.
      if (platform.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
        // Detect collision.
        Collision collision = new Collision(player, platform, delta);

        // Collision while falling
        if (collision.collidingFromTop() && player.vSpeed() > 0) {
          kinetics.stopVertically(player);
          kinetics.snapTop(player, platform);
        }

        // Only check platform collisions with the walls of a level
        if (!platform.isPassable()) {
          // Collision when jumping
          if (collision.collidingFromBottom() && player.vSpeed() < 0) {
            kinetics.stopVertically(player);
            kinetics.snapBottom(player, platform);
          }

          // Collision while moving to the right
          if (collision.collidingFromLeft() && player.hSpeed() > 0) {
            kinetics.stopHorizontally(player);
            // kinetics.snapLeft(player, platform);
          }

          // Collision while moving to the right
          if (collision.collidingFromRight() && player.hSpeed() < 0) {
            kinetics.stopHorizontally(player);
            // kinetics.snapRight(player, platform);
          }
        }
      }
    }
  }

  /**
   * Detect collisions between player and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public void detectBubblePlatform(Level level, double delta) {
    for (Bubble bubble : level.getBubbles()) {
      for (Platform platform : level.getPlatforms()) {
        // Check if platform is in collision range.
        if (platform.inBoxRangeOf(bubble, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(bubble, platform, delta);

          // Check for collision from the bottom
          if (collision.collidingFromBottom()) {
            bubble.getSpeed().setY(Constants.BUBBLE_BOUNCE);
            kinetics.snapBottom(bubble, platform);
            break;
          }

          // Check for collision from the left
          if (collision.collidingFromLeft()) {
            bubble.getSpeed().setX(-Constants.BUBBLE_BOUNCE);
            kinetics.snapLeft(bubble, platform);
            break;
          }

          // Check for collision from the right
          if (collision.collidingFromRight()) {
            bubble.getSpeed().setX(Constants.BUBBLE_BOUNCE);
            kinetics.snapRight(bubble, platform);
            break;
          }
        }
      }
    }
  }

  /**
   * Detect collision between the player an bubbles.
   * 
   * @param level
   *          The level.
   * @param delta
   *          The delta.
   */
  @SuppressWarnings("checkstyle:methodlength")
  public void detectPlayerBubble(Level level, double delta) {
    Player player = level.getPlayer();
    ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    ArrayList<Fruit> fruits = level.getFruits();

    // Copy bubbles to prevent a race condition when many bubbles are shot rapidly
    for (Bubble bubble : level.getBubbles()) {
      bubbles.add(bubble);
    }

    for (Bubble bubble : bubbles) {
      // Check if platform is in collision range.
      if (bubble.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
        // Detect collision.
        Collision collision = new Collision(player, bubble, delta);
        if (collision.collidingFromTop() && player.vSpeed() > 0) {
          player.getSpeed().setY(-Constants.PLAYER_JUMP);
          kinetics.snapTop(player, bubble);
          // Collision is detected, no further evaluation of candidates necessary.
          break;
        }
        
        // If a bubble contains an enemy, drop a fruit.
        if (collision.colliding() && bubble.hasNPC()) {
          Fruit newFruit = new Fruit(bubble.getPosition().clone(),
              new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));
          fruits.add(newFruit);
        }
      }

      for (Bubble other : bubbles) {
        if (!other.equals(bubble) && other.inBoxRangeOf(bubble, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(bubble, other, delta);
          if (collision.colliding()) {
            if (other.posX() < bubble.posX()) {
              other.getSpeed().setX(-Constants.BUBBLE_BOUNCE);
              bubble.getSpeed().setX(Constants.BUBBLE_BOUNCE);
            } else {
              other.getSpeed().setX(Constants.BUBBLE_BOUNCE);
              bubble.getSpeed().setX(-Constants.BUBBLE_BOUNCE);
            }
          }
        }
      }
    }
  }

  /**
   * Detect collisions between Bubbles and enemies.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The steps passed since this method wat last executed.
   */
  public void detectBubbleEnemy(Level level, double delta) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Fruit> fruits = level.getFruits();
    ArrayList<Bubble> bubbles = level.getBubbles();

    if (bubbles.size() > 0 && enemies.size() > 0) {
      for (int i = 0; i < enemies.size(); i++) {
        for (int j = 0; j < bubbles.size(); j++) {

          // Temp fix to prevent race condition
          if (enemies.size() != i
              && enemies.get(i).inBoxRangeOf(bubbles.get(j), Constants.COLLISION_RADIUS)
              && new Collision(bubbles.get(j), enemies.get(i), delta).colliding()) {
            bubbles.get(j).setHasNPC(true);
            // The lifetime of the bubble gets extended if the bubble cathes an enemy.
            bubbles.get(j).setLifetime(1.5 * Constants.BUBBLE_LIFETIME);
            // The enemy gets removed and a new encapsulated enemy will appear.
            enemies.remove(i);
          }
        }
      }
    }
  }

  /**
   * Detect collisions between player and fruit element.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public void detectPlayerFruit(Level level, double delta) {
    Player player = level.getPlayer();
    ArrayList<Fruit> fruits = level.getFruits();

    if (fruits.size() > 0) {
      for (int i = 0; i < fruits.size(); i++) {
        if (fruits.get(i).inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(player, fruits.get(i), delta);
          if (collision.colliding()) {
            fruits.remove(i);
            score.updateScore(100);
          }
        }
      }
    }

  }

  /**
   * Restarting level on hit with enemy.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public void detectPlayerEnemy(Level level, double delta) {
    Player player = level.getPlayer();
    ArrayList<NPC> npcs = level.getNPCs();

    if (npcs.size() > 0) {
      for (int i = 0; i < npcs.size(); i++) {
        if (npcs.get(i).inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(player, npcs.get(i), delta);
          if (collision.colliding()) {
            player.setAlive(false);
          }
        }
      }
    }
  }

}
