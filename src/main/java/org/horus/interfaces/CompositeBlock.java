package org.horus.interfaces;

import java.util.List;

public interface CompositeBlock extends Block {
  List<Block> getBlocks();
}
