package nl.tudelft.scrumbledore;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test suite for the NPCLevelModifier class.
 * 
 * @author Niels Warnars
 */
public class NPCLevelModifierTest {

  /**
   * Test the assignment of platforms to an NPC in the modify method.
   */
  @Test
  public void testInitializePlatforms() {
    Level level = new Level();
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(64, 32), new Vector(32, 32)));
    
    level.addElement(new NPC(new Vector(32, 0), new Vector(32, 32)));
    ArrayList<Platform> platforms = level.getPlatforms();
    
    // The number of platforms in a level should be three
    assertEquals(3, platforms.size());
    // No platforms should be assigned to an NPC
    assertEquals(null, level.getNPCs().get(0).getPlatforms());
    
    NPCLevelModifier npclm = new NPCLevelModifier();
    npclm.modify(level, 2);
    
    // The NPC should now have three platforms assigned
    assertEquals(3, level.getNPCs().get(0).getPlatforms().size());
  }
  
  /**
   * Test whether platforms assigned to an NPC will stay in place 
   * when modify is called multiple times.
   */
  @Test
  public void testPlatformsAlreadyInitialized() {
    Level level = new Level();
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(64, 32), new Vector(32, 32)));
    
    level.addElement(new NPC(new Vector(32, 0), new Vector(32, 32)));
    NPCLevelModifier npclm = new NPCLevelModifier();
    // Run modify twice
    npclm.modify(level, 2);
    npclm.modify(level, 2);
   
    // Three platforms should still be assigned to the NPC
    assertEquals(3, level.getNPCs().get(0).getPlatforms().size());
  }

  /**
   * Test whether an NPC moves to the left if this is indicated by the NPCAction.
   */
  @Test
  public void testMoveLeft() {
    Level level = new Level();
    NPC npc = new NPC(new Vector(64, 0), new Vector(32, 32));
    npc.getSpeed().setX(4);
    
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(64, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(96, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(128, 32), new Vector(32, 32)));
    level.addElement(npc);

    NPCLevelModifier npclm = new NPCLevelModifier();
    
    // Assert before modify
    assertEquals(NPCAction.MoveLeft, npc.getMovementDirection());
    assertEquals(4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
   
    // Modify the NPC
    npclm.modify(level, 1);
    
    // Assert after modify
    assertEquals(NPCAction.MoveLeft, npc.getMovementDirection());
    assertEquals(-4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }

  /**
   * Test whether an NPC moves to the right if this is indicated by the NPCAction.
   */
  @Test
  public void testMoveRight() {
    Level level = new Level();
    NPC npc = new NPC(new Vector(64, 0), new Vector(32, 32));
    npc.getSpeed().setX(4);
    
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(64, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(96, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(128, 32), new Vector(32, 32)));
    level.addElement(npc);

    NPCLevelModifier npclm = new NPCLevelModifier();
    
    // Assert before modify
    assertEquals(NPCAction.MoveLeft, npc.getMovementDirection());
    assertEquals(4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
   
    // Modify the NPC
    npc.setMovementDirection(NPCAction.MoveRight);
    npclm.modify(level, 1);
    
    // Assert after modify
    assertEquals(NPCAction.MoveRight, npc.getMovementDirection());
    assertEquals(4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }
  
  /**
   * Test whether an NPC moves to the right if it reaches the end of its movement
   * boundaries on a platform.
   */
  @Test
  public void testStopRight() {
    Level level = new Level();
    NPC npc = new NPC(new Vector(32, 0), new Vector(32, 32));
    npc.getSpeed().setX(4);
    npc.setMovementDirection(NPCAction.MoveRight);
    
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(npc);

    NPCLevelModifier npclm = new NPCLevelModifier();
    
    // Assert before modify
    assertEquals(NPCAction.MoveRight, npc.getMovementDirection());
    assertEquals(4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
   
    // Modify the NPC
    npclm.modify(level, 1);
    
    // Assert after modify
    assertEquals(NPCAction.MoveLeft, npc.getMovementDirection());
    assertEquals(0, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }
  
  
  /**
   * Test whether an NPC moves to the left if it reaches the end of its movement
   * boundaries on a platform.
   */
  @Test
  public void testStopLeft() {
    Level level = new Level();
    NPC npc = new NPC(new Vector(32, 0), new Vector(32, 32));
    npc.getSpeed().setX(-4);
    
    level.addElement(new Platform(new Vector(0, 32), new Vector(32, 32)));
    level.addElement(new Platform(new Vector(32, 32), new Vector(32, 32)));
    level.addElement(npc);

    NPCLevelModifier npclm = new NPCLevelModifier();
    
    // Assert before modify
    assertEquals(NPCAction.MoveLeft, npc.getMovementDirection());
    assertEquals(-4, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
   
    // Modify the NPC
    npclm.modify(level, 1);
    
    // Assert after modify
    assertEquals(NPCAction.MoveRight, npc.getMovementDirection());
    assertEquals(0, npc.getSpeed().getX(), Constants.DOUBLE_PRECISION);
  }
}
