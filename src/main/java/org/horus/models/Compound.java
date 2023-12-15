package org.horus.models;

import java.util.ArrayList;
import java.util.List;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;

public class Compound implements CompositeBlock {


  private String color;
  private String material;
  private List<Block> blocks;

    public Compound(String color, String material) {
        this.color = color;
        this.material = material;
        this.blocks = new ArrayList<>();
    }
  @Override
  public String getColor() {
    return this.color;
  }

  @Override
  public String getMaterial() {
    return this.material;
  }

  @Override
  public String toString() {
    return "BlockModel{" +
        "color='" + color + '\'' +
        ", material='" + material + '\'' +
        '}';
  }

  @Override
  public List<Block> getBlocks() {
    return this.blocks;
  }
}
