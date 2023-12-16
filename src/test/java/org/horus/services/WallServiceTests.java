package org.horus.services;

import java.util.List;
import org.horus.composite.Brick;
import org.horus.models.BlockModel;
import org.junit.Assert;
import org.junit.Test;

public class WallServiceTests {

  @Test
  public void findBlockModelByColorTestsPassCorrect() {

    Brick brick = new Brick(List.of(
        new BlockModel("red", "paper"),
        new BlockModel("blue", "paper"),
        new BlockModel("green", "paper"),
        new BlockModel("yellow", "paper")
    ));

    Wall wall = new Wall();
    wall.addBlock(List.of(brick));
    var block = wall.findBlockByColor("red");
    Assert.assertTrue(block.isPresent());
    Assert.assertEquals("red", block.get().getColor());
    block = wall.findBlockByColor("red");
    Assert.assertTrue(block.isPresent());
    Assert.assertEquals("red", block.get().getColor());
  }

  @Test
  public void findBlockModelByColorTestsPassIncorrect() {

    Brick brick = new Brick(List.of(
        new BlockModel("pink", "paper"),
        new BlockModel("blue", "wood"),
        new BlockModel("green", "paper"),
        new BlockModel("yellow", "stone")
    ));

    Wall wall = new Wall();
    wall.addBlock(List.of(brick));
    Assert.assertThrows("Block with color black not found", RuntimeException.class, () -> {
      wall.findBlockByColor("black");
    });

    Wall wall2 = new Wall();
    Assert.assertThrows("Block with color red not found", RuntimeException.class, () -> {
      wall2.findBlockByColor("red");
    });

    Wall wall3 = new Wall();
    Assert.assertThrows("Block with color silver not found", RuntimeException.class, () -> {
      wall3.findBlockByColor("silver");
    });
  }

  @Test
  public void findBLocksByMaterialTestsPassCorrect() {

    Brick brick = new Brick(List.of(
        new BlockModel("red", "paper"),
        new BlockModel("blue", "paper"),
        new BlockModel("green", "paper"),
        new BlockModel("yellow", "paper"),
        new BlockModel("red", "cotton"),
        new BlockModel("blue", "cotton"),
        new BlockModel("green", "metal"),
        new BlockModel("yellow", "metal"),
        new BlockModel("red", "wood")
    ));

    Wall wall = new Wall();
    wall.addBlock(List.of(brick));
    var blocks = wall.findBlocksByMaterial("paper");
    Assert.assertEquals(4, blocks.size());
    Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("paper")));

    blocks = wall.findBlocksByMaterial("cotton");
    Assert.assertEquals(2, blocks.size());
    Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("cotton")));

    blocks = wall.findBlocksByMaterial("metal");
    Assert.assertEquals(2, blocks.size());
    Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("metal")));

    blocks = wall.findBlocksByMaterial("brick");
    Assert.assertEquals(0, blocks.size());
  }

  @Test
  public void countBLocksTestsPassCorrect() {

    Brick brick = new Brick(List.of(
        new BlockModel("red", "paper"),
        new BlockModel("blue", "paper"),
        new BlockModel("green", "paper"),
        new BlockModel("yellow", "paper")
    ));

    Wall wall = new Wall();
    wall.addBlock(List.of(brick));
    int count = wall.count();
    Assert.assertEquals(4, count);

    brick = new Brick(List.of(
        new BlockModel("red", "paper"),
        new BlockModel("blue", "paper"),
        new BlockModel("green", "paper"),
        new BlockModel("yellow", "paper"),
        new BlockModel("red", "cotton"),
        new BlockModel("blue", "cotton"),
        new BlockModel("green", "metal"),
        new BlockModel("yellow", "metal"),
        new BlockModel("red", "wood")
    ));

    wall = new Wall();
    wall.addBlock(List.of(brick));
    count = wall.count();
    Assert.assertEquals(9, count);
  }
}
