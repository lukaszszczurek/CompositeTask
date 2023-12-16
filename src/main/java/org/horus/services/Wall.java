package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.horus.exceptions.NotFoundException;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;
import org.horus.interfaces.Structure;

public class Wall implements Structure {
  private List<Block> blocks;

  public Wall() {
    this.blocks = new ArrayList<>();
  }

  public void addBlock(List<Block> block) {
    blocks.addAll(block);
  }

  @Override
  public Optional<Block> findBlockByColor(String color) {
    for (Block block : blocks) {
      if (block instanceof CompositeBlock) {
        Optional<Block> foundBlock = findBlockByColor((CompositeBlock) block, color);
        if (foundBlock.isPresent()) {
          return foundBlock;
        }
      } else if (block.getColor().equals(color)) {
        return Optional.of(block);
      }
    }
    throw new NotFoundException("Block with color " + color + " not found");
  }

  private Optional<Block> findBlockByColor(CompositeBlock compositeBlock, String color) {
    for (Block block : compositeBlock.getBlocks()) {
      if (block instanceof CompositeBlock) {
        Optional<Block> foundBlock = findBlockByColor((CompositeBlock) block, color);
        if (foundBlock.isPresent()) {
          return foundBlock;
        }
      } else if (block.getColor().equals(color)) {
        return Optional.of(block);
      }
    }
    return Optional.empty();
  }

  @Override
  public List<Block> findBlocksByMaterial(String material) {
    List<Block> blocksByMaterial = new ArrayList<>();
    for (Block block : blocks) {
      if (block instanceof CompositeBlock) {
        blocksByMaterial.addAll(findBlocksByMaterial((CompositeBlock) block, material));
      } else if (block.getMaterial().equals(material)) {
        blocksByMaterial.add(block);
      }
    }
    return blocksByMaterial;
  }

  private List<Block> findBlocksByMaterial(CompositeBlock compositeBlock, String material) {
    List<Block> blocksByMaterial = new ArrayList<>();
    for (Block block : compositeBlock.getBlocks()) {
      if (block instanceof CompositeBlock) {
        blocksByMaterial.addAll(findBlocksByMaterial((CompositeBlock) block, material));
      } else if (block.getMaterial().equals(material)) {
        blocksByMaterial.add(block);
      }
    }
    return blocksByMaterial;
  }

  @Override
  public int count() {
    int count = 0;
    for (Block block : blocks) {
      if (block instanceof CompositeBlock) {
        count += count((CompositeBlock) block);
      } else {
        count++;
      }
    }
    return count;
  }

  private int count(CompositeBlock compositeBlock) {
    int count = 0;
    for (Block block : compositeBlock.getBlocks()) {
      if (block instanceof CompositeBlock) {
        count += count((CompositeBlock) block);
      } else {
        count++;
      }
    }
    return count;
  }
}
