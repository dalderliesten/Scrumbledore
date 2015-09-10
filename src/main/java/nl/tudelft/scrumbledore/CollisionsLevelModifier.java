package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 *
 */
public class CollisionsLevelModifier implements LevelModifier {

  private KineticsLevelModifier kinetics;
  private ScoreCounter score;

  /**
   * Constructs a new Collisions Level Modifier using a given Kinetics Level Modifier.
   * 
   * @param kinetics
   *          The Kinetics Level Modifier to be used.
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
    detectBubble(level, delta);
    detectPlatform(level, delta);
    detectPlayerFruit(level, delta);
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
  public void detectPlatform(Level level, double delta) {
    Player player = level.getPlayer();
    ArrayList<Bubble> bubbles = level.getBubbles();

    for (Platform platform : level.getPlatforms()) {
      // Check if platform is in collision range.
      if (platform.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
        // Detect collision.
        Collision collision = new Collision(player, platform, delta);
        if (collision.collidingFromTop() && player.vSpeed() > 0) {
          kinetics.stopVertically(player);
          kinetics.snapTop(player, platform);
          // Collision is detected, no further evaluation of candidates necessary.
          break;
        }
      }
      // Checking if a bubble collides with a wall.
      for (int i = 0; i < bubbles.size(); i++) {
        if (platform.inBoxRangeOf(bubbles.get(i), Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(bubbles.get(i), platform, delta);
          if (collision.collidingFromBottom()) {
            bubbles.get(i).getSpeed().setY(Constants.BUBBLE_BOUNCE);
            kinetics.snapBottom(bubbles.get(i), platform);
            break;
          }
          if (collision.collidingFromLeft()) {
            bubbles.get(i).getSpeed().setX(-Constants.BUBBLE_BOUNCE);
            kinetics.snapLeft(bubbles.get(i), platform);
            break;
          }
          if (collision.collidingFromRight()) {
            bubbles.get(i).getSpeed().setX(Constants.BUBBLE_BOUNCE);
            kinetics.snapRight(bubbles.get(i), platform);
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
  public void detectBubble(Level level, double delta) {
    Player player = level.getPlayer();
    ArrayList<Bubble> bubbles = level.getBubbles();

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
    ArrayList<Bubble> bubbles = level.getBubbles();
    ArrayList<LevelElement> enemies = level.getMovingElements();
    ArrayList<Fruit> fruits = level.getFruits();

    if (bubbles.size() > 0 && enemies.size() > 0) {
      for (int i = 0; i < enemies.size(); i++) {
        for (int j = 0; j < bubbles.size(); j++) {
          if (enemies.get(i).inBoxRangeOf(bubbles.get(j), Constants.COLLISION_RADIUS)) {
            Collision collision = new Collision(bubbles.get(j), enemies.get(i), delta);
            if (collision.colliding()) {
              bubbles.remove(j);
              Fruit newFruit = new Fruit(enemies.get(i).getPosition().clone(),
                  new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));
              // Adding a new Fruit element in place of where the enemy died.
              enemies.remove(i);
              fruits.add(newFruit);

            }
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

}
