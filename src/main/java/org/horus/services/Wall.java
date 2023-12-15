package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.horus.exceptions.NotFoundException;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;
import org.horus.interfaces.Structure;

public class Wall implements Structure {
   List<Block> blocks;

  public Wall(List<Block> blocks) {
    this.blocks = blocks;
  }
  @Override
  public List<Block> findBlockByColor(String color) {
    List<Block> foundBlocks = new ArrayList<>();
    for (Block block : blocks) {
      if (block instanceof CompositeBlock) {
        if(block.getColor().equals(color))
        {
          foundBlocks.add(block);
        }
      } else if (block.getMaterial().equals(color)) {
        foundBlocks.add(block);
      }
    }

    if (foundBlocks.isEmpty()) {
      throw new NotFoundException("block not found (" + color + ")");
    }

    return foundBlocks;
  }


  @Override
  public List<Block> findBlocksByMaterial(String material) {
    List<Block> blocks1 = new ArrayList<>();
    for(Block block : blocks) {
      if(block.getMaterial().equals(material))
      {
        blocks1.add(block);
      }

    }
    if (blocks1.isEmpty()) {
      throw new NotFoundException("block not found (" + material + ")");
    }
    return blocks1;
  }

  @Override
  public int count() {
   return blocks.size();

  }
}