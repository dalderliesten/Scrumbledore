package nl.tudelft.scrumbledore;

import java.util.ArrayList;

public class Level {

  private ArrayList<LevelElement> elements;

  public Level() {
    elements = new ArrayList<LevelElement>();
  }

  public void addElement(LevelElement element) {
    elements.add(element);
  }

}
