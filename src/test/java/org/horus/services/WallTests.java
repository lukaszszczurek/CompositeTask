package org.horus.services;

import java.util.Arrays;
import java.util.List;
import org.horus.composite.CompositeBlockImplementation;
import org.horus.interfaces.Block;
import org.horus.models.BlockModel;
import org.junit.Assert;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class WallTests {
  @Nested
  public class FindBlockByColorTests {

    @Test
    public void shouldReturnAppropriateColorOfBlockWhenGetSimpleInput() {
      Wall wall = new Wall();
      List<Block> initialDataOne = getBlocksDataOne();
      wall.addBlock(initialDataOne);

      var block = wall.findBlockByColor("red");
      Assert.assertEquals("red", block.get().getColor());

      block = wall.findBlockByColor("blue");
      Assert.assertEquals("blue", block.get().getColor());
    }

    @Test
    public void shouldReturnAppropriateColorOfBlockWhenGetCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initialDataTwo = getBlocksDataTwo();
      wall.addBlock(initialDataTwo);

      var block = wall.findBlockByColor("red");
      Assert.assertEquals("red", block.get().getColor());

      block = wall.findBlockByColor("blue");
      Assert.assertEquals("blue", block.get().getColor());

      block = wall.findBlockByColor("orange");
      Assert.assertEquals("orange", block.get().getColor());
    }

    @Test
    public void shouldReturnFalseAtIsPresentSectionWhenBLocksWithGivenColorNoExists() {
      Wall wall = new Wall();
      List<Block> initialThree = getBlocksDataThree();
      wall.addBlock(initialThree);

      var block = wall.findBlockByColor("purple");
      Assert.assertFalse(block.isPresent());

      block = wall.findBlockByColor("white");
      Assert.assertFalse(block.isPresent());
    }


    @Test
    public void shouldReturnAppropriateColorOfBlockWhenGetVeryComplexInput() {
      Wall wall = new Wall();
      List<Block> initDataThree = getBlocksDataThree();
      wall.addBlock(initDataThree);

      var block = wall.findBlockByColor("red");
      Assert.assertEquals("red", block.get().getColor());

      block = wall.findBlockByColor("blue");
      Assert.assertEquals("blue", block.get().getColor());

      block = wall.findBlockByColor("pink");
      Assert.assertEquals("pink", block.get().getColor());

      block = wall.findBlockByColor("orange");
      Assert.assertEquals("orange", block.get().getColor());

      block = wall.findBlockByColor("yellow");
      Assert.assertEquals("yellow", block.get().getColor());
    }

    private List<Block> getBlocksDataOne() {
      List<Block> initDataOne = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initDataOne;
    }

    private List<Block> getBlocksDataTwo() {
      List<Block> initDataTwo = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("orange", "stone")
          ))
      );
      return initDataTwo;
    }

    private List<Block> getBlocksDataThree() {
      List<Block> initDataThree = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("red", "stone")
          )),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("pink", "stone"),
              new BlockModel("orange", "wood"),
              new CompositeBlockImplementation(Arrays.asList(
                  new BlockModel("yellow", "brick"),
                  new BlockModel("yellow", "brick"),
                  new BlockModel("yellow", "brick")
              ))
          ))
      );
      return initDataThree;
    }
  }

  @Nested
  class FindBlocksByMaterialTests {

    @Test
    public void shouldReturnAppropriateMaterialOfBlockWhenGetSimpleInput() {
      Wall wall = new Wall();
      List<Block> initDataOne = getBlocksDataOne();
      wall.addBlock(initDataOne);

      var blocks = wall.findBlocksByMaterial("wood");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("wood")));
      Assert.assertEquals(2, blocks.size());

      blocks = wall.findBlocksByMaterial("stone");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("stone")));
      Assert.assertEquals(1, blocks.size());
    }

    @Test
    public void shouldReturnAppropriateMaterialOfBlockWhenGetCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initDataTwo = getBlocksDataTwo();
      wall.addBlock(initDataTwo);

      var blocks = wall.findBlocksByMaterial("cotton");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("cotton")));
      Assert.assertEquals(4, blocks.size());

      blocks = wall.findBlocksByMaterial("stone");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("stone")));
      Assert.assertEquals(2, blocks.size());

      blocks = wall.findBlocksByMaterial("wood");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("wood")));
      Assert.assertEquals(3, blocks.size());
    }

    @Test
    public void shouldReturnAppropriateMaterialOfBlockWhenGetNestedCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initDataThree = getBlocksDataThree();
      wall.addBlock(initDataThree);

      var blocks = wall.findBlocksByMaterial("wood");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("wood")));
      Assert.assertEquals(10, blocks.size());

      blocks = wall.findBlocksByMaterial("stone");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("stone")));
      Assert.assertEquals(3, blocks.size());

      blocks = wall.findBlocksByMaterial("brick");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("brick")));
      Assert.assertEquals(2, blocks.size());
    }

    private List<Block> getBlocksDataOne() {
      List<Block> initDataOne = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initDataOne;
    }

    private List<Block> getBlocksDataTwo() {
      List<Block> initDataTwo = Arrays.asList(
          new BlockModel("red", "cotton"),
          new BlockModel("blue", "cotton"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("orange", "stone"),
              new BlockModel("red", "wood"),
              new BlockModel("blue", "cotton"),
              new BlockModel("orange", "cotton")
          ))
      );
      return initDataTwo;
    }

    private List<Block> getBlocksDataThree() {
      List<Block> initDataThree = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("red", "stone")
          )),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("pink", "stone")
          )),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("pink", "wood"),
              new CompositeBlockImplementation(Arrays.asList(
                  new BlockModel("yellow", "wood"),
                  new BlockModel("yellow", "brick"),
                  new BlockModel("yellow", "brick")
              ))
          ))
      );

      return initDataThree;
    }
  }

  @Nested
  class CountTests {
    @Test
    public void shouldReturnAppropriateCountOfBlocksWhenGetSimpleInput() {
      Wall wall = new Wall();
      List<Block> initDataOne = getBlocksDataOne();
      wall.addBlock(initDataOne);

      Assert.assertEquals(3, wall.count());
    }

    @Test
    public void shouldReturnAppropriateCountOfBlocksWhenGetCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initDataTwo = getBlocksDataTwo();
      wall.addBlock(initDataTwo);

      Assert.assertEquals(13, wall.count());
    }

    private List<Block> getBlocksDataOne() {
      List<Block> initDataOne = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initDataOne;
    }

    private List<Block> getBlocksDataTwo() {
      List<Block> initDataTwo = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("orange", "stone")
          )),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("pink", "stone")
          )),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("pink", "wood"),
              new BlockModel("red", "wood")
          ))
      );
      return initDataTwo;
    }
  }
}
