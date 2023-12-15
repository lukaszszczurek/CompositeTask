package org.horus.models;

import org.horus.interfaces.Block;

public class BlockModel implements Block {

  private String color;
  private String material;

  public BlockModel(String color, String material) {
    this.color = color;
    this.material = material;
  }

  @Override
  public String getColor() {
    return this.color;
  }

  @Override
  public String getMaterial() {
    return this.material;
  }
}
