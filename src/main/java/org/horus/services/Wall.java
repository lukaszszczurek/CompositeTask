package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;
import org.horus.interfaces.Structure;

public class Wall implements Structure {
  private final List<Block> blocks;

  public Wall() {
    this.blocks = new ArrayList<>();
  }

  public void addBlock(List<Block> block) {
    blocks.addAll(block);
  }

  @Override
  public Optional<Block> findBlockByColor(String color) {
    return findBlockByColorRecursively(color, blocks);
  }

  @Override
  public List<Block> findBlocksByMaterial(String material) {
    return findBlocksByMaterialRecursively(material, blocks);
  }

  @Override
  public int count() {
    return countRecursively(blocks);
  }

  public Optional<Block> findBlockByColorRecursively(String color, List<Block> blocks) {
    return blocks.stream()
        .filter(block -> color.equals(block.getColor()))
        .findFirst()
        .or(() -> blocks.stream()
            .filter(block -> block instanceof CompositeBlock)
            .map(block -> findBlockByColorRecursively(color, ((CompositeBlock) block).getBlocks()))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst());
  }

  public List<Block> findBlocksByMaterialRecursively(String material, List<Block> blocks) {
    List<Block> result = new ArrayList<>();
    blocks.forEach(block -> {
      if (material.equals(block.getMaterial())) {
        result.add(block);
      }
      if (block instanceof CompositeBlock) {
        result.addAll(findBlocksByMaterialRecursively(material, ((CompositeBlock) block).getBlocks()));
      }
    });
    return result;
  }

  public int countRecursively(List<Block> blocks) {
    return blocks.stream()
        .mapToInt(block -> block instanceof CompositeBlock ?
            countRecursively(((CompositeBlock) block).getBlocks()) :
            1)
        .sum();
  }
}