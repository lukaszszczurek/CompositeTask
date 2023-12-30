package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.horus.interfaces.Block;
import org.horus.interfaces.CompositeBlock;
import org.horus.interfaces.Structure;

public class Wall implements Structure {
  private final List<Block> blocks;

  public Wall() {
    this.blocks = new ArrayList<>();
  }

  // Metoda dodająca blok do struktury ściany
  public void addBlock(List<Block> block) {
    blocks.addAll(block);
  }

  @Override
  public Optional<Block> findBlockByColor(String color) {
    return blocks.stream()
        .flatMap(block -> {
          if (color.equals(block.getColor())) {
            return Stream.of(block);
          } else if (block instanceof CompositeBlock) {
            return ((CompositeBlock) block).getBlocks().stream();
          } else {
            return Stream.empty();
          }
        })
        .filter(block -> color.equals(block.getColor()))
        .findFirst();
  }

  @Override
  public List<Block> findBlocksByMaterial(String material) {
    List<Block> result = new ArrayList<>();

    blocks.stream()
        .flatMap(block -> {
          if (material.equals(block.getMaterial())) {
            return Stream.of(block);
          } else if (block instanceof CompositeBlock) {
            return ((CompositeBlock) block).getBlocks().stream();
          } else {
            return Stream.empty();
          }
        })
        .filter(block -> material.equals(block.getMaterial()))
        .forEach(result::add);
    return result;
  }

  @Override
  public int count() {
    return blocks.stream()
        .mapToInt(block -> block instanceof CompositeBlock ?
            ((CompositeBlock) block).getBlocks().size() :
            1)
        .sum();
  }
}
