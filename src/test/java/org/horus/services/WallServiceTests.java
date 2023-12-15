package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import org.horus.exceptions.NotFoundException;
import org.horus.interfaces.Block;
import org.horus.models.Compound;
import org.junit.Assert;
import org.junit.Test;

public class WallServiceTests {

  @Test
  public void findBLockByColorTestsPassCorrect() {

    List<Block> books = new ArrayList<>(
        List.of(
            new Compound("white", "wood"),
            new Compound("orange", "wood"),
            new Compound("purple", "wood"),
            new Compound("gray", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood"),
            new Compound("white", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood"),
            new Compound("white", "wood"),
            new Compound("orange", "wood"),
            new Compound("gray", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood")
        )
    );

    Wall wallService = new Wall(books);
    var block = wallService.findBlockByColor("white");
    Assert.assertTrue(block.stream().map(Block::getColor).allMatch("white"::equals));

    block = wallService.findBlockByColor("orange");
    Assert.assertTrue(block.stream().map(Block::getColor).allMatch("orange"::equals));

    block = wallService.findBlockByColor("purple");
    Assert.assertTrue(block.stream().map(Block::getColor).allMatch("purple"::equals));

  }

  @Test
  public void findBLockByColorTestsPassIncorrect() {
    List<Block> books = new ArrayList<>(
        List.of(
            new Compound("white", "wood"),
            new Compound("orange", "wood"),
            new Compound("purple", "wood"),
            new Compound("gray", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood"),
            new Compound("white", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood"),
            new Compound("white", "wood"),
            new Compound("orange", "wood"),
            new Compound("gray", "wood"),
            new Compound("red", "wood"),
            new Compound("blue", "wood"),
            new Compound("green", "wood"),
            new Compound("yellow", "wood"),
            new Compound("black", "wood")
        )
    );

    List<Block> empty = new ArrayList<>();
    Wall wallService = new Wall(books);
    Wall wallServiceEmpty = new Wall(empty);


    Assert.assertThrows("block not found (BROWN)", NotFoundException.class,
        () -> wallService.findBlockByColor("brown"));
    Assert.assertThrows("block not found (PINK)", NotFoundException.class,
        () -> wallService.findBlockByColor("pink"));
    Assert.assertThrows("block not found (LIGHT PURPLE)", NotFoundException.class,
        () -> wallService.findBlockByColor("light purple"));

    Assert.assertThrows(NotFoundException.class, () -> wallServiceEmpty.findBlockByColor("purple"));
    Assert.assertThrows(NotFoundException.class, () -> wallServiceEmpty.findBlockByColor("pink"));
    Assert.assertThrows(NotFoundException.class, () -> wallServiceEmpty.findBlockByColor("green"));
  }

  @Test
  public void findBLocksByColorTests() {



    List<Block> initialListOfBlocks = new ArrayList<>(
        List.of(
            new Compound("white", "wood"),
            new Compound("orange", "wood"),
            new Compound("gray", "wood"),
            new Compound("red", "cotton"),
            new Compound("blue", "stone"),
            new Compound("green", "metal"),
            new Compound("yellow", "brick"),
            new Compound("white", "metal"),
            new Compound("orange", "stone"),
            new Compound("gray", "cotton"),
            new Compound("red", "brick"),
            new Compound("blue", "wood"),
            new Compound("green", "wood")
        )
    );

    Wall wallService = new Wall(initialListOfBlocks);
    var blocks = wallService.findBlocksByMaterial("wood");
    Assert.assertTrue(blocks.stream().map(Block::getMaterial).allMatch("wood"::equals));

    blocks = wallService.findBlocksByMaterial("cotton");
    Assert.assertTrue(blocks.stream().map(Block::getMaterial).allMatch("cotton"::equals));

    blocks = wallService.findBlocksByMaterial("stone");
    Assert.assertTrue(blocks.stream().map(Block::getMaterial).allMatch("stone"::equals));

  }

    @Test
    public void countTests() {
        List<Block> empty = new ArrayList<>();
        Wall wallServiceEmpty = new Wall(empty);
        Assert.assertEquals(0, wallServiceEmpty.count());

        List<Block> initialListOfBlocks = new ArrayList<>(
                List.of(
                        new Compound("white", "wood"),
                        new Compound("orange", "wood"),
                        new Compound("gray", "wood"),
                        new Compound("red", "cotton"),
                        new Compound("blue", "stone"),
                        new Compound("green", "metal"),
                        new Compound("yellow", "brick"),
                        new Compound("white", "metal"),
                        new Compound("orange", "stone"),
                        new Compound("gray", "cotton"),
                        new Compound("red", "brick"),
                        new Compound("blue", "wood"),
                        new Compound("green", "wood")
                )
        );

        Wall wallService = new Wall(initialListOfBlocks);
        Assert.assertEquals(13, wallService.count());

        Wall wallService2 = new Wall(initialListOfBlocks);
        Assert.assertEquals(13, wallService2.count());

        Wall wallService3 = new Wall(initialListOfBlocks);
        Assert.assertEquals(13, wallService3.count());



    }
}