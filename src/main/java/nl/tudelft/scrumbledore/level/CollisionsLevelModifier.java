package nl.tudelft.scrumbledore.level;

import java.util.ArrayList;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.Logger;
import nl.tudelft.scrumbledore.game.ScoreCounter;
import nl.tudelft.scrumbledore.powerup.BlueberryBubble;
import nl.tudelft.scrumbledore.powerup.BlueberryBubblePickUp;
import nl.tudelft.scrumbledore.powerup.ChiliChicken;
import nl.tudelft.scrumbledore.powerup.ChiliChickenPickUp;
import nl.tudelft.scrumbledore.powerup.PowerupPickUp;
import nl.tudelft.scrumbledore.powerup.PyroPepper;
import nl.tudelft.scrumbledore.powerup.PyroPepperPickUp;
import nl.tudelft.scrumbledore.powerup.TurtleTaco;
import nl.tudelft.scrumbledore.powerup.TurtleTacoPickUp;

/**
 * Class responsible for collision detection between given elements.
 * 
 * @author Jesse Tilro
 * @author David Alderliesten
 */
@SuppressWarnings({ "checkstyle:methodlength", "PMD.ModifiedCyclomaticComplexity",
    "PMD.NPathComplexity", "PMD.StdCyclomaticComplexity", "PMD.CyclomaticComplexity",
    "PMD.TooManyMethods" })
public class CollisionsLevelModifier implements LevelModifier {

  private ScoreCounter score;

  /**
   * Constructs a new Collisions Level Modifier using a given Kinetics Level Modifier.
   * 
   * @param kinetics
   *          The Kinetics Level Modifier to be used.
   * @param score
   *          The Score Counter to be used.
   */
  public CollisionsLevelModifier(ScoreCounter score) {
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
    detectPlayerPowerup(level, delta);
    detectPlayerBubble(level, delta);
    detectBubbleEnemy(level, delta);
    detectBubbleBubble(level, delta);
    detectFruitPlatform(level, delta);
    detectBubblePlatform(level, delta);
    detectPlayerPlatform(level, delta);
    detectPlayerFruit(level, delta);
    detectPlayerEnemy(level, delta);
    detectNPCPlatform(level, delta);
  }

  /**
   * Detect collisions between player and powerups.
   * 
   * @param level
   *          , the level.
   * @param delta
   *          , the delta provided by StepTimer.
   */
  protected void detectPlayerPowerup(Level level, double delta) {
    ArrayList<PowerupPickUp> powerUps = level.getPowerups();
    ArrayList<DynamicElement> players = level.getPlayers();

    if (powerUps.size() > 0) {
      for (int j = 0; j < players.size(); j++) {
        DynamicElement player = players.get(j);
        for (int i = 0; i < powerUps.size(); i++) {
          PowerupPickUp currentPow = powerUps.get(i);
          if (currentPow.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
            Collision collision = new Collision(player, currentPow, delta);
            if (collision.colliding()) {
              if (currentPow instanceof ChiliChickenPickUp) {
                ChiliChicken newChick = new ChiliChicken(player);
                players.add(j, newChick);
                players.remove(j + 1);
              } else if (currentPow instanceof BlueberryBubblePickUp) {
                BlueberryBubble newBlue = new BlueberryBubble(player);
                players.add(j, newBlue);
                players.remove(j + 1);
              } else if (currentPow instanceof PyroPepperPickUp) {
                PyroPepper newPyro = new PyroPepper(player);
                players.add(j, newPyro);
                players.remove(j + 1);
              } else if (currentPow instanceof TurtleTacoPickUp) {
                TurtleTaco newTurtle = new TurtleTaco(player);
                players.add(j, newTurtle);
                players.remove(j + 1);
              }
              powerUps.remove(i);
            }
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
  protected void detectFruitPlatform(Level level, double delta) {
    for (Fruit fruit : level.getFruits()) {
      // To prevent the player from instantaneously picking up fruit.
      boolean pickable = true;
      for (DynamicElement player : level.getPlayers()) {
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
            fruit.stopVertically();
            fruit.snapTop(platform);
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
    for (DynamicElement player : level.getPlayers()) {
      // To accelerate the second iteration over the platforms
      ArrayList<Platform> candidates = new ArrayList<Platform>();
      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          candidates.add(platform);
          Collision collision = new Collision(player, platform, delta);

          if (collision.collidingFromTop() && player.vSpeed() > 0) {
            player.stopVertically();
            player.snapTop(platform);
          }
        }
      }
      // Since vertical collision detection has to be done before horizontal
      for (Platform platform : candidates) {
        Collision collision = new Collision(player, platform, delta);

        if (!platform.isPassable()) {
          if (collision.collidingFromBottom() && player.vSpeed() < 0) {
            player.stopVertically();
            player.snapBottom(platform);
          }

          if (collision.collidingFromLeft() && player.hSpeed() > 0) {
            player.stopHorizontally();
            player.snapLeft(platform);
          }

          if (collision.collidingFromRight() && player.hSpeed() < 0) {
            player.stopHorizontally();
            player.snapRight(platform);
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
            npc.stopVertically();
            npc.snapTop(platform);
          }
        }
      }
      // Since vertical collision detection has to be done before horizontal
      for (Platform platform : candidates) {
        Collision collision = new Collision(npc, platform, delta);

        if (!platform.isPassable()) {
          if (collision.collidingFromLeft() && npc.hSpeed() > 0) {
            npc.stopHorizontally();
            npc.snapLeft(platform);
            npc.addAction(LevelElementAction.MoveLeft);
          }

          if (collision.collidingFromRight() && npc.hSpeed() < 0) {
            npc.stopHorizontally();
            npc.snapRight(platform);
            npc.addAction(LevelElementAction.MoveRight);
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
  @SuppressWarnings("PMD.CollapsibleIfStatements")
  protected void detectBubblePlatform(Level level, double delta) {
    ArrayList<Projectile> projectiles = level.getProjectiles();
    for (int i = 0; i < projectiles.size(); i++) {
      Projectile currentProjectile = projectiles.get(i);
      for (Platform platform : level.getPlatforms()) {
        if (platform.inBoxRangeOf(currentProjectile, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(currentProjectile, platform, delta);

          if (currentProjectile instanceof Fireball) {
            if (collision.collidingFromLeft() || collision.collidingFromRight()) {
              projectiles.remove(i);
            }
            break;
          }

          if (collision.collidingFromBottom()) {
            currentProjectile.getSpeed().setY(Constants.BUBBLE_BOUNCE);
            currentProjectile.snapBottom(platform);
            break;
          }

          if (collision.collidingFromLeft()) {
            currentProjectile.getSpeed().setX(-Constants.BUBBLE_BOUNCE);
            currentProjectile.snapLeft(platform);
            break;
          }

          if (collision.collidingFromRight()) {
            currentProjectile.getSpeed().setX(Constants.BUBBLE_BOUNCE);
            currentProjectile.snapRight(platform);
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
    for (DynamicElement player : level.getPlayers()) {
      for (Projectile bubble : level.getProjectiles()) {
        if (bubble.inBoxRangeOf(player, Constants.COLLISION_RADIUS)) {
          Collision collision = new Collision(player, bubble, delta);
          if (collision.collidingFromTop() && !(bubble.hasNPC())) {
            player.getSpeed().setY(-Constants.PLAYER_JUMP);
            player.snapTop(bubble);
            break;
          }

          if (collision.colliding() && bubble.hasNPC()) {
            Fruit newFruit = null;
            try {
              newFruit = new Fruit(bubble.getPosition().clone(), new Vector(Constants.BLOCKSIZE,
                  Constants.BLOCKSIZE));
            } catch (CloneNotSupportedException e) {
              e.printStackTrace();
            }
            level.getFruits().add(newFruit);
            level.getEnemyBubbles().remove(bubble);
            level.getProjectiles().remove(bubble);

            if (Constants.isLoggingWantEnemy()) {
              Logger.getInstance().log("Player executed an encapsulated enemy.");
            }

            break;
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
   *          The steps passed since this method was last executed.
   */
  protected void detectBubbleEnemy(Level level, double delta) {
    ArrayList<NPC> enemies = level.getNPCs();
    ArrayList<Projectile> projectiles = level.getProjectiles();
    ArrayList<Projectile> enemyBubbles = level.getEnemyBubbles();

    if (projectiles.size() > 0 && enemies.size() > 0) {
      for (int i = 0; i < enemies.size(); i++) {

        for (int j = 0; j < projectiles.size(); j++) {
          Projectile currentProjectile = projectiles.get(i);
          // Temporary fix to prevent race condition.
          if (!(currentProjectile.hasNPC()) && enemies.size() != i
              && enemies.get(i).inBoxRangeOf(currentProjectile, Constants.COLLISION_RADIUS)
              && new Collision(currentProjectile, enemies.get(i), delta).colliding()) {

            enemies.remove(i);
            if (!(currentProjectile instanceof Fireball)) {
              enemyBubbles.add(currentProjectile);
              currentProjectile.setHasNPC(true);
              currentProjectile.setLifetime(1.5 * Constants.BUBBLE_LIFETIME);
            }

            if (Constants.isLoggingWantEnemy()) {
              Logger.getInstance().log("An enemy was encapsulated by a bubble.");
            }
          }
        }
      }
    }
  }

  /**
   * Detect and handle collisions between Bubbles.
   * 
   * @param level
   *          The level to be modified.
   * @param delta
   *          The number of steps passed since this method was last executed.
   */
  protected void detectBubbleBubble(Level level, double delta) {
    ArrayList<Projectile> bubbles = level.getProjectiles();

    for (Projectile bubble : bubbles) {
      for (Projectile other : bubbles) {
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
   * Detect collisions between player and fruit element.
   * 
   * @param level
   *          The Level.
   * @param delta
   *          The delta provided by the StepTimer.
   */
  protected void detectPlayerFruit(Level level, double delta) {
    for (DynamicElement player : level.getPlayers()) {
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
    for (DynamicElement player : level.getPlayers()) {
      ArrayList<NPC> npcs = level.getNPCs();

      if (npcs.size() > 0 && !(player instanceof TurtleTaco)) {
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
   * Returns a ScoreCounter.
   * 
   * @return the score
   */
  public ScoreCounter getScore() {
    return score;
  }

}
