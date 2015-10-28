package nl.tudelft.scrumbledore.level.modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.scrumbledore.Constants;
import nl.tudelft.scrumbledore.game.ScoreCounter;
import nl.tudelft.scrumbledore.level.Level;
import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.Bubble;
import nl.tudelft.scrumbledore.level.element.DynamicElement;
import nl.tudelft.scrumbledore.level.element.Fruit;
import nl.tudelft.scrumbledore.level.element.NPC;
import nl.tudelft.scrumbledore.level.element.Platform;
import nl.tudelft.scrumbledore.level.element.Player;
import nl.tudelft.scrumbledore.level.powerup.ChiliChicken;
import nl.tudelft.scrumbledore.level.powerup.ChiliChickenPickUp;
import nl.tudelft.scrumbledore.level.powerup.PowerupPickUp;
import nl.tudelft.scrumbledore.level.powerup.TurtleTaco;
import nl.tudelft.scrumbledore.level.powerup.TurtleTacoPickUp;

/**
 * Test suite for the CollisionsLevelModifier class.
 * 
 * @author Niels Warnars
 * @author Jesse Tilro
 */
@SuppressWarnings({ "PMD.JUnitTestsShouldIncludeAssert", "PMD.TooManyMethods",
    "PMD.TooManyStaticImports" })
public class CollisionsLevelModifierTest {
  private ScoreCounter sc;
  private CollisionsLevelModifier clm;

  /**
   * Set up mock objects and an CollisionsLevelModifier instance.
   */
  @Before
  public void setUp() {
    sc = mock(ScoreCounter.class);

    clm = new CollisionsLevelModifier(sc);
  }

  /**
   * Helper method for stubbing a mocked dependency DynamicElement class.
   * 
   * @param instance
   *          The reference instance determining which class is to be mocked and how it should be
   *          stubbed.
   */
  private DynamicElement mockLevelElement(DynamicElement instance) {
    DynamicElement mock = mock(instance.getClass());

    when(mock.getPosition()).thenReturn(instance.getPosition());
    when(mock.getSpeed()).thenReturn(instance.getSpeed());
    when(mock.posX()).thenReturn(instance.posX());
    when(mock.posY()).thenReturn(instance.posY());
    when(mock.hSpeed()).thenReturn(instance.hSpeed());
    when(mock.vSpeed()).thenReturn(instance.vSpeed());
    when(mock.getLeft()).thenReturn(instance.getLeft());
    when(mock.getRight()).thenReturn(instance.getRight());
    when(mock.getTop()).thenReturn(instance.getTop());
    when(mock.getBottom()).thenReturn(instance.getBottom());

    return mock;
  }

  /**
   * Test the CollisionsLevelModifier constructor and the getter methods.
   */
  @Test
  public void testCollisionsLevelModifier() {
    ScoreCounter sc = new ScoreCounter();

    CollisionsLevelModifier clm = new CollisionsLevelModifier(sc);
    assertEquals(sc, clm.getScore());
  }

  /**
   * Test the collision between a fruit and a platform.
   */
  @Test
  public void testDetectFruitPlatform() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 0), new Vector(32, 32));
    fruit.getSpeed().setY(4);
    DynamicElement fruitMock = mockLevelElement(fruit);

    Level level = new Level();
    level.addElement(fruitMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(fruitMock).stopVertically();
    verify(fruitMock).snapTop(platform);
  }

  /**
   * Test the collision between a platform and an NPC colliding from the top.
   */
  @Test
  public void testDetectNPCPlatformFromTop() {
    // Fixtures
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    npc.getSpeed().setY(8);
    DynamicElement npcMock = mockLevelElement(npc);

    Level level = new Level();
    level.addElement(platform);
    level.addElement(npcMock);
    clm.modify(level, 1);

    verify(npcMock).stopVertically();
    verify(npcMock).snapTop(platform);
  }

  /**
   * Test the collision between a platform and an NPC colliding from the left.
   */
  @Test
  public void testDetectNPCPlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 0), new Vector(32, 32));
    npc.getSpeed().setX(4);
    DynamicElement npcMock = mockLevelElement(npc);

    Level level = new Level();
    level.addElement(platform);
    level.addElement(npcMock);

    clm.modify(level, 1);
    verify(npcMock).stopHorizontally();
    verify(npcMock).snapLeft(platform);
  }

  /**
   * Test the collision between a platform and an NPC colliding from the right.
   */
  @Test
  public void testDetectNPCPlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(32, 0), new Vector(32, 32));
    npc.getSpeed().setX(-4);
    DynamicElement npcMock = mockLevelElement(npc);

    Level level = new Level();
    level.addElement(platform);
    level.addElement(npcMock);

    clm.modify(level, 1);
    verify(npcMock).stopHorizontally();
    verify(npcMock).snapRight(platform);
  }

  /**
   * Test the collision between a platform and a player colliding from the top.
   */
  @Test
  public void testDetectPlayerPlatformFromTop() {
    Platform platform = new Platform(new Vector(0, 32), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    player.getSpeed().setY(4);
    DynamicElement playerMock = mockLevelElement(player);

    Level level = new Level();
    level.addElement(playerMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(playerMock).stopVertically();
    verify(playerMock).snapTop(platform);
  }

  /**
   * Test the collision between a platform and a player colliding from the bottom.
   */
  @Test
  public void testDetectPlayerPlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 32), new Vector(32, 32));
    player.getSpeed().setY(-4);
    DynamicElement playerMock = mockLevelElement(player);

    Level level = new Level();
    level.addElement(playerMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(playerMock).stopVertically();
    verify(playerMock).snapBottom(platform);
  }

  /**
   * Test the collision between a platform and a player colliding from the left.
   */
  @Test
  public void testDetectPlayerPlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    player.getSpeed().setX(4);
    DynamicElement playerMock = mockLevelElement(player);

    Level level = new Level();
    level.addElement(playerMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(playerMock).stopHorizontally();
  }

  /**
   * Test the collision between a platform and a player colliding from the right.
   */
  @Test
  public void testDetectPlayerPlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Player player = new Player(new Vector(32, 0), new Vector(32, 32));
    player.getSpeed().setX(-4);
    DynamicElement playerMock = mockLevelElement(player);

    Level level = new Level();
    level.addElement(playerMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(playerMock).stopHorizontally();
  }

  /**
   * Test the collision between a platform and a bubble colliding from the bottom.
   */
  @Test
  public void testDetectBubblePlatformFromBottom() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 32), new Vector(32, 32));
    DynamicElement bubbleMock = mockLevelElement(bubble);

    Level level = new Level();
    level.addElement(bubbleMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(bubbleMock).snapBottom(platform);
    assertEquals(Constants.BUBBLE_BOUNCE, bubbleMock.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a platform and a bubble colliding from the left.
   */
  @Test
  public void testDetectBubblePlatformFromLeft() {
    Platform platform = new Platform(new Vector(32, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    DynamicElement bubbleMock = mockLevelElement(bubble);

    Level level = new Level();
    level.addElement(bubbleMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(bubbleMock).snapLeft(platform);
    assertEquals(-Constants.BUBBLE_BOUNCE, bubbleMock.getSpeed().getX(),
        Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a platform and a bubble colliding from the left.
   */
  @Test
  public void testDetectBubblePlatformFromRight() {
    Platform platform = new Platform(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(32, 0), new Vector(32, 32));
    DynamicElement bubbleMock = mockLevelElement(bubble);

    Level level = new Level();
    level.addElement(bubbleMock);
    level.addElement(platform);

    clm.modify(level, 1);
    verify(bubbleMock).snapRight(platform);
    assertEquals(Constants.BUBBLE_BOUNCE, bubbleMock.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a bubble and a player colliding from the top.
   */
  @Test
  public void testDetectPlayerBubbleFromTop() {
    Bubble bubble = new Bubble(new Vector(0, 32), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    player.getSpeed().setY(4);
    DynamicElement playerMock = mockLevelElement(player);

    Level level = new Level();
    level.addElement(playerMock);
    level.addElement(bubble);

    clm.modify(level, 1);
    verify(playerMock).snapTop(bubble);
    assertEquals(-Constants.PLAYER_JUMP, playerMock.getSpeed().getY(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a bubble containing an NPC and a player colliding from the top.
   */
  @Test
  public void testDetectPlayerBubbleWithNPCFromTop() {
    Bubble bubble = new Bubble(new Vector(0, 32), new Vector(32, 32));
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));

    bubble.setHasNPC(true);
    player.getSpeed().setY(4);

    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);

    clm.modify(level, 1);
    assertFalse(level.getBubbles().contains(bubble));
  }

  /**
   * Test the collision between a bubble and a bubble colliding.
   */
  @Test
  public void testDetectPlayerBubbleTwoSameBubbles() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble2 = new Bubble(new Vector(0, 0), new Vector(32, 32));

    bubble.getSpeed().setX(2);
    bubble2.getSpeed().setX(4);

    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);
    level.addElement(bubble2);

    clm.modify(level, 1);
    assertEquals(Constants.BUBBLE_BOUNCE, bubble.hSpeed(), Constants.DOUBLE_PRECISION);
    assertEquals(-Constants.BUBBLE_BOUNCE, bubble2.hSpeed(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a bubble and a bubble colliding.
   */
  @Test
  public void testDetectPlayerBubbleTwoDifferentBubbles() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    Bubble bubble2 = new Bubble(new Vector(32, 0), new Vector(32, 32));

    bubble.getSpeed().setX(2);
    bubble2.getSpeed().setX(4);

    Level level = new Level();
    level.addElement(player);
    level.addElement(bubble);
    level.addElement(bubble2);

    clm.modify(level, 1);
    assertEquals(-Constants.BUBBLE_BOUNCE, bubble.hSpeed(), Constants.DOUBLE_PRECISION);
    assertEquals(Constants.BUBBLE_BOUNCE, bubble2.hSpeed(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test the collision between a bubble and an enemy.
   */
  @Test
  public void testDetectBubbleEnemy() {
    Bubble bubble = new Bubble(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 32), new Vector(32, 32));

    Level level = new Level();
    level.addElement(bubble);
    level.addElement(npc);

    assertEquals(0, level.getFruits().size());
    clm.modify(level, 1);
    assertEquals(0, level.getNPCs().size());
  }

  /**
   * Test the collision between a player and a fruit.
   */
  @Test
  public void testDetectPlayerFruit() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    Fruit fruit = new Fruit(new Vector(0, 32), new Vector(32, 32));
    fruit.setPickable(true);

    Level level = new Level();
    level.addElement(player);
    level.addElement(fruit);

    clm.modify(level, 1);
    assertEquals(0, level.getFruits().size());
    verify(sc).updateScore(100);
  }

  /**
   * Test the collision between a player and an enemy.
   */
  @Test
  public void testDetectPlayerEnemy() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    NPC npc = new NPC(new Vector(0, 32), new Vector(32, 32));

    Level level = new Level();
    level.addElement(player);
    level.addElement(npc);

    assertTrue(player.isAlive());
    clm.modify(level, 1);
    assertFalse(player.isAlive());
  }

  /**
   * Test the collision between a layer and a ChiliChicken powerup.
   */
  @Test
  public void testDetectPlayerPowerupChiliChicken() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    PowerupPickUp pickup = new ChiliChickenPickUp(new Vector(32, 32), new Vector(32, 32));

    Level level = new Level();
    level.addElement(player);
    level.addElement(pickup);

    clm.modify(level, 1);
    assertTrue(level.getPowerups().size() == 0);
    assertTrue(level.getPlayers().get(0) instanceof ChiliChicken);
  }
  
  /**
   * Test the collision between a layer and a TurtleTaco powerup.
   */
  @Test
  public void testDetectPlayerPowerupTurtleTaco() {
    Player player = new Player(new Vector(0, 0), new Vector(32, 32));
    PowerupPickUp pickup = new TurtleTacoPickUp(new Vector(32, 32), new Vector(32, 32));

    Level level = new Level();
    level.addElement(player);
    level.addElement(pickup);

    clm.modify(level, 1);
    assertTrue(level.getPowerups().size() == 0);
    assertTrue(level.getPlayers().get(0) instanceof TurtleTaco);
  }
}
