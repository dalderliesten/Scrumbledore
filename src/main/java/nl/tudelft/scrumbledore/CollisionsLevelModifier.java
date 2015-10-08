package nl.tudelft.scrumbledore;

import java.util.ArrayList;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 * @author David Alderliesten
 */
@SuppressWarnings({ "checkstyle:methodlength", "PMD.ModifiedCyclomaticComplexity",
    "PMD.NPathComplexity", "PMD.StdCyclomaticComplexity", "PMD.CyclomaticComplexity" })
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
    detectNPCPlatform(level, delta);
  }

  /**
   * Detect collisions between player and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  protected void detectFruitPlatform(Level level, double delta) {
    for (Fruit fruit : level.getFruits()) {
      // To prevent the player from instantaneously picking up fruit.
      boolean pickable = true;
      for (Player player : level.getPlayers()) {
        Collision playerCollision = new Collision(fruit, player, delta);
        if (playerCollision.colliding()) {
          pickable = false;
        }
      }
      // Since becoming pickable can't be undone.
      if (pickable) {
        fruit.setPickable(pickable);
      }

      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(fruit, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(fruit, platform, delta);

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
  protected void detectPlayerPlatform(Level level, double delta) {
    ArrayList<Player> players = level.getPlayers();

    for (Player player : players) {
      // To accelerate the second iteration over the platforms
      ArrayList<Platform> candidates = new ArrayList<Platform>();
      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          candidates.add(platform);
          Collision collision = new Collision(player, platform, delta);

          if (collision.collidingFromTop() && player.vSpeed() > 0) {
            kinetics.stopVertically(player);
            kinetics.snapTop(player, platform);
          }
        }
      }
      // Since vertical collision detection has to be done before horizontal
      for (Platform platform : candidates) {
        Collision collision = new Collision(player, platform, delta);

        if (!platform.isPassable()) {

          if (collision.collidingFromBottom() && player.vSpeed() < 0) {
            kinetics.stopVertically(player);
            kinetics.snapBottom(player, platform);
          }

          if (collision.collidingFromLeft() && player.hSpeed() > 0) {
            kinetics.stopHorizontally(player);
            kinetics.snapLeft(player, platform);
          }

          if (collision.collidingFromRight() && player.hSpeed() < 0) {
            kinetics.stopHorizontally(player);
            kinetics.snapRight(player, platform);
          }
        }
      }
    }
  }

  /**
   * Detect collisions between NPC's and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  public void detectNPCPlatform(Level level, double delta) {

    for (NPC npc : level.getNPCs()) {
      // To accelerate the second iteration over the platforms
      ArrayList<Platform> candidates = new ArrayList<Platform>();
      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(npc, Constants.COLLISION_RADIUS)) {
          candidates.add(platform);
          Collision collision = new Collision(npc, platform, delta);

          if (collision.collidingFromTop() && npc.vSpeed() > 0) {
            kinetics.stopVertically(npc);
            kinetics.snapTop(npc, platform);
          }
        }
      }
      // Since vertical collision detection has to be done before horizontal
      for (Platform platform : candidates) {
        Collision collision = new Collision(npc, platform, delta);

        if (!platform.isPassable()) {
          if (collision.collidingFromLeft() && npc.hSpeed() > 0) {
            kinetics.stopHorizontally(npc);
            kinetics.snapLeft(npc, platform);
            npc.addAction(NPCAction.MoveLeft);
          }

          if (collision.collidingFromRight() && npc.hSpeed() < 0) {
            kinetics.stopHorizontally(npc);
            kinetics.snapRight(npc, platform);
            npc.addAction(NPCAction.MoveRight);
          }
        }
      }

    }

  }

  /**
   * Detect collisions between bubble and platform.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  protected void detectBubblePlatform(Level level, double delta) {
    for (Bubble bubble : level.getBubbles()) {
      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(bubble, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(bubble, platform, delta);

          if (collision.collidingFromBottom()) {
            bubble.getSpeed().setY(Constants.BUBBLE_BOUNCE);
            kinetics.snapBottom(bubble, platform);
            break;
          }

          if (collision.collidingFromLeft()) {
            bubble.getSpeed().setX(-Constants.BUBBLE_BOUNCE);
            kinetics.snapLeft(bubble, platform);
            break;
          }

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
  protected void detectPlayerBubble(Level level, double delta) {
    ArrayList<Player> players = level.getPlayers();

    for (Player player : players) {

      ArrayList<Bubble> bubbles = new ArrayList<Bubble>();

      ArrayList<Fruit> fruits = level.getFruits();

      // To prevent a race condition when many bubbles are shot rapidly
      for (Bubble bubble : level.getBubbles()) {
        bubbles.add(bubble);
      }

      for (Bubble bubble : bubbles) {
        if (bubble.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(player, bubble, delta);
          if (collision.collidingFromTop() && player.vSpeed() > 0 && !(bubble.hasNPC())) {
            player.getSpeed().setY(-Constants.PLAYER_JUMP);
            kinetics.snapTop(player, bubble);
            break;
          }

          if (collision.colliding() && bubble.hasNPC()) {
            Fruit newFruit = null;
            try {
              newFruit = new Fruit(bubble.getPosition().clone(),
                  new Vector(Constants.BLOCKSIZE, Constants.BLOCKSIZE));
            } catch (CloneNotSupportedException e) {
              e.printStackTrace();
            }
            fruits.add(newFruit);
            level.getEnemyBubbles().remove(bubble);
            level.getBubbles().remove(bubble);

            if (Constants.LOGGING_WANTENEMY) {
              Logger.getInstance().log("Player executed an encapsulated enemy.");
            }

            break;
          }
        }
      }

      for (Bubble bubble : bubbles) {
        if (bubble.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(player, bubble, delta);
          if (collision.collidingFromTop() && player.vSpeed() > 0) {
            player.getSpeed().setY(-Constants.PLAYER_JUMP);
            kinetics.snapTop(player, bubble);
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
  }

  /**
   * Detect collisions between Bubbles and enemies.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The steps passed since this method wat last executed.
   */
  protected void detectBubbleEnemy(Level level, double delta) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Bubble> bubbles = level.getBubbles();
    ArrayList<Bubble> enemyBubbles = level.getEnemyBubbles();

    if (bubbles.size() > 0 && enemies.size() > 0) {
      for (int i = 0; i < enemies.size(); i++) {

        for (int j = 0; j < bubbles.size(); j++) {
          // Temporary fix to prevent race condition.
          if (!(bubbles.get(j).hasNPC()) && enemies.size() != i
              && enemies.get(i).inBoxRangeOf(bubbles.get(j), Constants.COLLISION_RADIUS)
              && new Collision(bubbles.get(j), enemies.get(i), delta).colliding()) {

            enemies.remove(i);
            enemyBubbles.add(bubbles.get(j));
            bubbles.get(j).setHasNPC(true);
            bubbles.get(j).setLifetime(1.5 * Constants.BUBBLE_LIFETIME);

            if (Constants.LOGGING_WANTENEMY) {
              Logger.getInstance().log("An enemy was encapsulated by a bubble.");
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
  protected void detectPlayerFruit(Level level, double delta) {
    ArrayList<Player> players = level.getPlayers();
    for (Player player : players) {

      ArrayList<Fruit> fruits = level.getFruits();

      if (fruits.size() > 0) {
        for (int i = 0; i < fruits.size(); i++) {
          if (fruits.get(i).isPickable()
              && fruits.get(i).inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
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

  /**
   * Restarting level on hit with enemy.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  protected void detectPlayerEnemy(Level level, double delta) {
    ArrayList<Player> players = level.getPlayers();
    for (Player player : players) {

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

  /**
   * Returns a KineticsLevelModifier.
   * 
   * @return The kinetics
   */
  public KineticsLevelModifier getKinetics() {
    return kinetics;
  }

  /**
   * Returns a ScoreCounter.
   * 
   * @return the score
   */
  public ScoreCounter getScore() {
    return score;
  }

}
