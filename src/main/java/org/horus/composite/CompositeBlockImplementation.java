package org.horus.composite;

import java.util.List;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;

public class CompositeBlockImplementation implements CompositeBlock {

  private final List<Block> blocks;

  public CompositeBlockImplementation(List<Block> blocks) {
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
