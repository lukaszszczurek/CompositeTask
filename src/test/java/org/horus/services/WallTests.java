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
      List<Block> initialData_1 = getBlocksData_1();
      wall.addBlock(initialData_1);

      var block = wall.findBlockByColor("red");
      Assert.assertEquals("red", block.get().getColor());

      block = wall.findBlockByColor("blue");
      Assert.assertEquals("blue", block.get().getColor());
    }

    @Test
    public void shouldReturnAppropriateColorOfBlockWhenGetCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initialData_2 = getBlocksData_2();
      wall.addBlock(initialData_2);

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
      List<Block> initialData_3 = getBlocksData_3();
      wall.addBlock(initialData_3);

      var block = wall.findBlockByColor("purple");
      Assert.assertFalse(block.isPresent());

      block = wall.findBlockByColor("white");
      Assert.assertFalse(block.isPresent());
    }


    @Test
    public void shouldReturnFalseForNonExistingBlocksWhenGetVeryComplexInput() {
      Wall wall = new Wall();
      List<Block> initialData_3 = getBlocksData_3();
      wall.addBlock(initialData_3);

      var block = wall.findBlockByColor("red");
      Assert.assertEquals("red", block.get().getColor());

      block = wall.findBlockByColor("blue");
      Assert.assertEquals("blue", block.get().getColor());

      block = wall.findBlockByColor("pink");
      Assert.assertEquals("pink", block.get().getColor());

      block = wall.findBlockByColor("orange");
      Assert.assertEquals("orange", block.get().getColor());
    }

    private List<Block> getBlocksData_1() {
      List<Block> initialData_1 = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initialData_1;
    }

    private List<Block> getBlocksData_2() {
      List<Block> initialData_2 = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone"),
          new CompositeBlockImplementation(Arrays.asList(
              new BlockModel("red", "wood"),
              new BlockModel("blue", "wood"),
              new BlockModel("orange", "stone")
          ))
      );
      return initialData_2;
    }

    private List<Block> getBlocksData_3() {
      List<Block> initialData_3 = Arrays.asList(
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
          ))
      );
      return initialData_3;
    }
  }

  @Nested
  class FindBlocksByMaterialTests {

    @Test
    public void shouldReturnAppropriateMaterialOfBlockWhenGetSimpleInput() {
      Wall wall = new Wall();
      List<Block> initialData_1 = getBlocksData_1();
      wall.addBlock(initialData_1);

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
      List<Block> initialData_2 = getBlocksData_2();
      wall.addBlock(initialData_2);

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
      List<Block> initialData_3 = getBlocksData_3();
      wall.addBlock(initialData_3);

      var blocks = wall.findBlocksByMaterial("wood");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("wood")));
      Assert.assertEquals(9, blocks.size());

      blocks = wall.findBlocksByMaterial("stone");
      Assert.assertTrue(blocks.stream().allMatch(block -> block.getMaterial().equals("stone")));
      Assert.assertEquals(3, blocks.size());
    }

    private List<Block> getBlocksData_1() {
      List<Block> initialData_1 = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initialData_1;
    }

    private List<Block> getBlocksData_2() {
      List<Block> initialData_2 = Arrays.asList(
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
      return initialData_2;
    }

    private List<Block> getBlocksData_3() {
      List<Block> initialData_3 = Arrays.asList(
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
              new BlockModel("pink", "wood")
          ))
      );

      return initialData_3;
    }
  }

  @Nested
  class CountTests {
    @Test
    public void shouldReturnAppropriateCountOfBlocksWhenGetSimpleInput() {
      Wall wall = new Wall();
      List<Block> initialData_1 = getBlocksData_1();
      wall.addBlock(initialData_1);

      Assert.assertEquals(3, wall.count());
    }

    @Test
    public void shouldReturnAppropriateCountOfBlocksWhenGetCompositeBlockInput() {
      Wall wall = new Wall();
      List<Block> initialData_2 = getBlocksData_2();
      wall.addBlock(initialData_2);

      Assert.assertEquals(13, wall.count());
    }

    private List<Block> getBlocksData_1() {
      List<Block> initialData_1 = Arrays.asList(
          new BlockModel("red", "wood"),
          new BlockModel("blue", "wood"),
          new BlockModel("red", "stone")
      );
      return initialData_1;
    }

    private List<Block> getBlocksData_2() {
      List<Block> initialData_2 = Arrays.asList(
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
      return initialData_2;
    }
  }
}
