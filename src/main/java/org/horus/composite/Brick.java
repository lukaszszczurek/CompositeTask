package org.horus.composite;

import java.util.List;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;

public class Brick implements CompositeBlock {
  private List<Block> blocks;
  public Brick(List<Block> blocks) {
    this.blocks = blocks;
  }
  @Override
  public String getColor() {
    return null;
  }
  @Override
  public String getMaterial() {
    return null;
  }
  @Override
  public List<Block> getBlocks() {
    return this.blocks;
  }
}