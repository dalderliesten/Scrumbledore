package nl.tudelft.scrumbledore.level.powerup;

import nl.tudelft.scrumbledore.level.Vector;
import nl.tudelft.scrumbledore.level.element.LevelElement;
import nl.tudelft.scrumbledore.level.element.LevelElementTest;

public abstract class PowerupPickUpTest extends LevelElementTest {

  @Override
  public abstract LevelElement make(Vector position, Vector size);

}
