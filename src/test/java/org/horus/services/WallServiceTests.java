package org.horus.services;

import java.util.ArrayList;
import java.util.List;
import org.horus.exceptions.NotFoundException;
import org.horus.interfaces.Block;
import org.horus.models.BlockModel;
import org.junit.Assert;
import org.junit.Test;

public class WallServiceTests {

  @Test
  public void findBlockModelByColorTestsPassCorrect() {

    List<Block> books = new ArrayList<>(
        List.of(
            new BlockModel("white", "wood"),
            new BlockModel("orange", "wood"),
            new BlockModel("purple", "wood"),
            new BlockModel("gray", "wood"),
            new BlockModel("red", "wood"),
            new BlockModel("blue", "wood"),
            new BlockModel("green", "wood"),
            new BlockModel("yellow", "wood"),
            new BlockModel("black", "wood"),
            new BlockModel("white", "wood"),
            new BlockModel("red", "wood"),
            new BlockModel("blue", "wood"),
            new BlockModel("green", "wood"),
            new BlockModel("yellow", "wood"),
            new BlockModel("black", "wood"),
            new BlockModel("white", "wood"),
            new BlockModel("orange", "wood"),
            new BlockModel("gray", "wood"),
            new BlockModel("red", "wood"),
            new BlockModel("blue", "wood"),
            new BlockModel("green", "wood"),
            new BlockModel("yellow", "wood"),
            new BlockModel("black", "wood")
        )
    );

    Wall wallService = new Wall(books);

    Assert.assertEquals("white", wallService.findBlockByColor("white"));
    Assert.assertEquals("orange", wallService.findBlockByColor("orange"));
    Assert.assertEquals("purple", wallService.findBlockByColor("purple"));
    Assert.assertEquals("gray", wallService.findBlockByColor("gray"));



  }

  @Test
  public void findBlockModelByColorTestsPassIncorrect() {

 }
}