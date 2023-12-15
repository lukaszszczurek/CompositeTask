package org.horus.models;

import java.util.ArrayList;
import java.util.List;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;

public class CompoundBlock implements CompositeBlock {

  protected List<Block> blocks = new ArrayList<>();
  @Override
  public String getColor() {
    if(blocks.isEmpty())
    {
      return null;
    }
    return blocks.get(0).getColor();
  }

  @Override
  public String getMaterial() {
    if(blocks.isEmpty())
    {
      return null;
    }
    return blocks.get(0).getMaterial();
  }

  @Override
  public List<Block> getBlocks() {
    return this.blocks;
  }
}
