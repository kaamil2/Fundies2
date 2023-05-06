package HW.hw9;

import java.util.ArrayList;

import tester.Tester;
import javalib.impworld.*;

import java.awt.Color;

import javalib.worldimages.*;

import java.util.Random;

// the reasoning for this is that the game is a 2D grid of cells and for cells on the corner if we
// were to ask for there top, left, right, or bottom cell we would get an error. So we need to
// represent the cells that are not there with a new class and not j null.
interface ICell {


  boolean flooded = false;

  void floodCell(Color color);

  //void floodNeighborCell(Color color);


}

// represents a cell that is not there in the game world
class MTCell implements ICell {

  Color color;
  boolean flooded;


  MTCell(Color color, boolean flooded) {
    this.color = color;
    this.flooded = flooded;
  }


  //draws the cell white so that if called upon an empty cell it would not be mistaken for a cell
  public WorldImage drawCell() {
    return new RectangleImage(15, 15, OutlineMode.SOLID, this.color);
  }

  //public void floodNeighborCell(Color color) {
  // do nothing
  //}

  public boolean isNeighborFlooded() {
    return false;
  }

  public void floodCell(Color color) {
    // do nothing


  }


}


// Represents a single square of the game area
class Cell implements ICell {
  //In logical coordinates, with the origin in the top-left corner of the screen
  int x;
  int y;
  Color color;
  boolean flooded;
  // the four adjacent cells to this one
  ICell top;
  ICell left;
  ICell right;
  ICell bottom;

  Cell(int x, int y, Color color) {
    this.x = (x * 15) + 8;
    this.y = (y * 15) + 8;
    this.color = color;
    this.flooded = false;

  }

  //draws the cell
  public WorldImage drawCell() {
    return new RectangleImage(15, 15, OutlineMode.SOLID, this.color);
  }


  boolean isClicked(Posn pos) {
    System.out.println("x cord" + pos.x);
    System.out.println("y cord" + pos.y);
    System.out.println("x cord" + (pos.x + 8));
    System.out.println("x cord" + (pos.x - 8));
    System.out.println("y cord" + (pos.y + 8));
    System.out.println("y cord" + (pos.y - 8));


    return (pos.x + 8 > this.x) && (pos.x - 8 < this.x)
        && (pos.y + 8 > this.y) && (pos.y - 8 < this.y);
  }

  public void floodCell(Color color) {
    this.color = color;
    this.flooded = true;
    /*this.top.floodNeighborCell(color);
    this.left.floodNeighborCell(color);
    this.right.floodNeighborCell(color);
    this.bottom.floodNeighborCell(color);*/

  }

  public boolean isNeighborFlooded() {
    System.out.println("flood top" + this.top.flooded);
    System.out.println("flood left" + this.left.flooded);
    System.out.println("flood right" + this.right.flooded);
    System.out.println("flood bottom" + this.bottom.flooded);

    return this.left.flooded || this.right.flooded || this.top.flooded || this.bottom.flooded;
  }


}


// represents the entire game
class FloodItWorld extends World {
  // all the cells of the game
  ArrayList<ArrayList<Cell>> board;
  int colorCount;
  int size;

  ArrayList<Cell> floodingList = new ArrayList<Cell>();

  ArrayList<Cell> beenThereList = new ArrayList<Cell>();

  // the number of clicks remaining before the player loses the game
  double stepsRemaining;
  ArrayList<Color> colorsList = new ArrayList<Color>();
  ArrayList<Color> colorsList2 = new ArrayList<Color>();
  Random rand = new Random();


  // the constructor for the game
  FloodItWorld(int size, int colorCount) {
    this.colorCount = colorCount;
    this.size = size;
    this.stepsRemaining = Math.floor(25 * ((2 * size) * colorCount) / 145);

    // This is where we create the list of colors that will be used in the game

    // This is where we add the colors to the list it only goes up to RGB 255 because we save the
    // all white color for the MTCell
    for (int i = 0; i < colorCount; i++) {
      colorsList.add(new Color(rand.nextInt(255),
          rand.nextInt(255), rand.nextInt(255)));
    }

    // This is where we create the board of cells
    this.board = new ArrayList<ArrayList<Cell>>();
    for (int i = 0; i < size; i++) {
      ArrayList<Cell> row = new ArrayList<Cell>();
      for (int j = 0; j < size; j++) {
        row.add(new Cell(i, j, colorsList.get(rand.nextInt(colorCount))));


      }
      this.board.add(row);

    }


    // This is where we set the neighbors of each cell in the game world to be the cells around it
    // in the game world or a new MTCell if there is no cell there.
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j == 0 && i == 0) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (j == size - 1 && i == size - 1) {
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);


        } else if (j == 0 && i == size - 1) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);


        } else if (j == size - 1 && i == 0) {
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);


        } else if (j == 0) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (j == size - 1) {
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (i == 0) {
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);

        } else if (i == size - 1) {
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);


        } else {
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);
        }


      }
    }

    this.board.get(0).get(0).flooded = true;
    System.out.println("bout to flood");
    System.out.println("true there is a flooded neighbor");
    System.out.println("flooding this board");
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        System.out.println(this.board.get(i).get(j).flooded);


      }
    }


    this.bigBang(this.size * 50, this.size * 50, 1);
  }


  //draws the board
  public WorldScene makeScene() {
    WorldScene scene = new WorldScene(this.size * 15, this.size * 15);


    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        scene.placeImageXY(this.board.get(i).get(j).drawCell(),
            this.board.get(i).get(j).x, this.board.get(i).get(j).y);
      }
    }
    this.colorsList2.add(this.board.get(0).get(0).color);


    scene.placeImageXY(new TextImage("Steps Remaining: "
            + (stepsRemaining), this.size * 3, Color.BLACK),
        this.size * 21, this.size * 20);


    if (this.isGameOver() && stepsRemaining == 0) {
      return this.lastScene("SIKE! barely made it");
    }
    if (this.isGameOver() && stepsRemaining > 0) {
      return this.lastScene("you win");
    }
    if (stepsRemaining == 0 && !this.isGameOver()) {
      return this.lastScene("you lose");
    }


    return scene;


  }


  public WorldScene lastScene(String s) {
    WorldScene scene = new WorldScene(this.size * 15, this.size * 15);
    /* for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        scene.placeImageXY(this.board.get(i).get(j).drawCell(),
            this.board.get(i).get(j).x, this.board.get(i).get(j).y);
            }
      }*/

    scene.placeImageXY(new TextImage(s, this.size * 5, Color.BLACK),
        this.size * 21, this.size * 20);


    return scene;
  }

  //handles mouse clicks
  public void onMousePressed(Posn pos, String button) {

    stepsRemaining--;

    // This is where we set the neighbors of each cell in the game world to be the cells around it
    // in the game world or a new MTCell if there is no cell there.
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (j == 0 && i == 0) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (j == size - 1 && i == size - 1) {
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);

        } else if (j == 0 && i == size - 1) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);


        } else if (j == size - 1 && i == 0) {
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);


        } else if (j == 0) {
          this.board.get(i).get(j).left = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (j == size - 1) {
          this.board.get(i).get(j).right = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);

        } else if (i == 0) {
          this.board.get(i).get(j).top = new MTCell(Color.WHITE, false);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);

        } else if (i == size - 1) {
          this.board.get(i).get(j).bottom = new MTCell(Color.WHITE,
              false);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);


        } else {
          this.board.get(i).get(j).left = this.board.get(i).get(j - 1);
          this.board.get(i).get(j).right = this.board.get(i).get(j + 1);
          this.board.get(i).get(j).top = this.board.get(i - 1).get(j);
          this.board.get(i).get(j).bottom = this.board.get(i + 1).get(j);
        }


      }
    }


    if (button.equals("LeftButton")) {
      System.out.println("clicked");
      //System.out.print("flooded - " + this.board.get(pos.x).get(pos.y).flooded + " ");
      //System.out.println("neighbor flood" + this.board.get(pos.x).get(pos.y).isNeighborFlooded());

      for (int i = 0; i < this.size; i++) {
        for (int j = 0; j < this.size; j++) {
          System.out.println("clicked" + pos.x + " " + pos.y + " "
              + this.board.get(i).get(j).x + " "
              + this.board.get(i).get(j).y);

          System.out.print("isClicked" + this.board.get(i).get(j).isClicked(pos) + " ");

          System.out.print("flooded - " + this.board.get(i).get(j).flooded + " ");
          //System.out.println("neighbor flood" + this.board.get(i).get(j).isNeighborFlooded());
          if (this.board.get(i).get(j).isClicked(pos)) {
            System.out.println("clicked" + i + " " + j);


            if (this.board.get(i).get(j).isClicked(pos)
                && this.board.get(i).get(j).color != Color.WHITE
                && !this.board.get(i).get(j).flooded) {
              colorsList2.add(this.board.get(i).get(j).color);
              this.board.get(0).get(0).color = this.board.get(i).get(j).color;


              // this important this.board.get(0).get(0).floodCell(this.board.get(i).get(j).color);
              System.out.println("clicked entered to" + i + " " + j);
            }
          }
        }
      }
    }
  }


  //checks if the game is over
  public boolean isGameOver() {
    int counter = 0;

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j < this.size; j++) {
        if (this.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }
    return counter == this.size * this.size;
  }


  void initBoard() {

    FloodItWorld f = new FloodItWorld(this.size, this.colorCount);
    stepsRemaining = Math.floor(25 * ((2 * size) * colorCount) / 145);

    /*for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        this.board.get(i).get(j).flooded = false;
      }
    }*/

  }

  //handles key presses
  public void onKeyEvent(String key) {
    if (key.equals("r")) {
      initBoard();
    }
  }

  public boolean floods(int xBoard, int yBoard) {
    if (xBoard > 0 && xBoard < this.size - 1 && yBoard > 0 && yBoard < this.size - 1) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard == 0 && yBoard == 0) {
      if (this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard == 0 && yBoard == this.size - 1) {
      if (this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded) {
        return true;
      }
    }
    if (xBoard == this.size - 1 && yBoard == 0) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard == this.size - 1 && yBoard == this.size - 1) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded) {
        return true;
      }
    }
    if (xBoard == 0 && yBoard > 0 && yBoard < this.size - 1) {
      if (this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard == this.size - 1 && yBoard > 0 && yBoard < this.size - 1) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard > 0 && xBoard < this.size - 1 && yBoard == 0) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard + 1).flooded) {
        return true;
      }
    }
    if (xBoard > 0 && xBoard < this.size - 1 && yBoard == this.size - 1) {
      if (this.board.get(xBoard - 1).get(yBoard).flooded
          || this.board.get(xBoard + 1).get(yBoard).flooded
          || this.board.get(xBoard).get(yBoard - 1).flooded) {
        return true;
      }
    }

    return false;

  }

  public void onTick() {


    for (int k = 0; k < this.size; k++) {
      for (int l = 0; l < this.size; l++) {
        if (!this.board.get(k).get(l).flooded
            && this.board.get(k).get(l).color == this.board.get(0).get(0).color
            && this.floods(k, l)
            && this.board.get(0).get(0) != this.board.get(k).get(l)) {
          //System.out.println("check floods");
          this.board.get(k).get(l).flooded = true;
          this.board.get(k).get(l).color = (this.board.get(0).get(0).color);

        }
      }
    }


    for (int k = 0; k < this.size; k++) {
      for (int l = 0; l < this.size; l++) {
        if (this.board.get(k).get(l).flooded
            && this.board.get(0).get(0) != this.board.get(k).get(l)) {
          //System.out.println("check floods");
          this.board.get(k).get(l).flooded = true;
          this.board.get(k).get(l).color = (this.board.get(0).get(0).color);
        }
      }
    }


  }


}


// Examples and Tests
class ExamplesFloodIt {


  // test the drawCell method

  //FloodItWorld f1 = new FloodItWorld(5, 3);


  FloodItWorld f2 = new FloodItWorld(10, 5);

  //FloodItWorld world = new FloodItWorld(2, 4);
  //FloodItWorld f = new FloodItWorld(10, 5);




 /* void testDrawCell(Tester t) {
    WorldImage cellImage = new RectangleImage(15, 15, OutlineMode.SOLID, Color.RED);
    t.checkExpect(new Cell(0, 0, Color.RED).drawCell(), cellImage);
    t.checkExpect(new Cell(0, 0, Color.BLUE).drawCell(),
        new RectangleImage(15, 15,
            OutlineMode.SOLID, Color.BLUE));
    t.checkExpect(new Cell(0, 0, Color.GREEN).drawCell(),
        new RectangleImage(15, 15,
            OutlineMode.SOLID, Color.GREEN));
    t.checkExpect(new Cell(0, 0, Color.YELLOW).drawCell(),
        new RectangleImage(15, 15,
            OutlineMode.SOLID, Color.YELLOW));
    t.checkExpect(new Cell(0, 4, Color.ORANGE).drawCell(),
        new RectangleImage(15, 15,
            OutlineMode.SOLID, Color.ORANGE));

  }

  void testMTCell(Tester t) {
    WorldImage cellImage = new RectangleImage(15, 15, OutlineMode.SOLID, Color.WHITE);
    t.checkExpect(new MTCell(Color.WHITE, false).drawCell(), cellImage);
    t.checkExpect(new MTCell(Color.WHITE, true).drawCell(),
        new RectangleImage(15, 15,
            OutlineMode.SOLID, Color.WHITE));

  }

  void testLayout(Tester t) {

    FloodItWorld world = new FloodItWorld(30, 30);

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size - 1; j++) {
        t.checkExpect(world.board.get(i).get(j + 1).left, world.board.get(i).get(j));
      }
    }

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size - 1; j++) {
        t.checkExpect(world.board.get(i).get(j + 1), world.board.get(i).get(j).right);
      }
    }

    for (int i = 0; i < world.size - 1; i++) {
      for (int j = 0; j < world.size; j++) {
        t.checkExpect(world.board.get(i + 1).get(j).top, world.board.get(i).get(j));
      }
    }

    for (int i = 0; i < world.size - 1; i++) {
      for (int j = 0; j < world.size; j++) {
        t.checkExpect(world.board.get(i).get(j).bottom, world.board.get(i + 1).get(j));
      }
    }
  }


  void testIsClicked(Tester t) {
    t.checkExpect(f1.board.get(0).get(0).isClicked(new Posn(0, 0)), false);
    t.checkExpect(f1.board.get(0).get(0).isClicked(new Posn(1, 1)), true);
    t.checkExpect(f1.board.get(1).get(0).isClicked(new Posn(15, 15)), false);
    t.checkExpect(f1.board.get(0).get(1).isClicked(new Posn(16, 16)), false);
    t.checkExpect(f1.board.get(1).get(0).isClicked(new Posn(14, 14)), false);
    t.checkExpect(f1.board.get(1).get(0).isClicked(new Posn(15, 15)), false);
    t.checkExpect(f1.board.get(0).get(1).isClicked(new Posn(16, 16)), false);
    t.checkExpect(f1.board.get(0).get(0).isClicked(new Posn(14, 14)), true);

  }


  void testFloodItWorld(Tester t) {
    f1.bigBang(75, 75, 0.1);
    f2.bigBang(150, 150, 0.1);
    world.bigBang(30, 30, 0.1);
    f.bigBang(150, 150, 0.1);
  }

  void testFloodCell(Tester t) {
    f1.board.get(0).get(0).color = (Color.BLUE);
    f1.board.get(0).get(1).color = (Color.BLUE);
    f1.board.get(0).get(0).floodCell(Color.BLUE);
    f1.board.get(1).get(1).color = (Color.RED);
    f1.board.get(1).get(1).flooded = false;


    t.checkExpect(f1.board.get(0).get(0).flooded, true);
    t.checkExpect(new Cell(0, 0, Color.BLUE).color, Color.BLUE);
    new Cell(0, 0, Color.RED).floodCell(Color.BLUE);
    t.checkExpect(f1.board.get(1).get(1).flooded, false);
    t.checkExpect(new Cell(1, 1, Color.RED).color, Color.RED);
  }

  void testMakeSceneAlpha(Tester t) {
    t.checkExpect(f1.makeScene().width, 75);
    t.checkExpect(f1.makeScene().height, 75);
    t.checkExpect(f2.makeScene().width, 150);
    t.checkExpect(f2.makeScene().height, 150);
  }

  void testOnMousePressedSame(Tester t) {
    FloodItWorld game = new FloodItWorld(5, 3);
    // set board to all same color
    for (int i = 0; i < game.size; i++) {
      for (int j = 0; j < game.size; j++) {
        game.board.get(i).set(j, new Cell(i, j, Color.BLUE));
      }
    }
    game.board.get(0).get(0).flooded = true;
    game.board.get(4).get(4).color = Color.GREEN;
    game.onMousePressed(new Posn(game.board.get(4).get(4).x, game.board.get(4).get(4).y),
        "LeftButton");
    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.GREEN);
    t.checkExpect(game.board.get(4).get(4).color, Color.GREEN);
    t.checkExpect(game.stepsRemaining, 4.0);
  }

  // test onMousePressed when player clicks a cell of a different color
  void testOnMousePressedDiff(Tester t) {
    FloodItWorld game = new FloodItWorld(5, 3);
    // set board to two different colors
    for (int i = 0; i < game.size; i++) {
      for (int j = 0; j < game.size; j++) {
        if (i % 2 == 0) {
          game.board.get(i).set(j, new Cell(i, j, Color.BLUE));
          game.board.get(i).get(j).flooded = true;
        } else {
          game.board.get(i).set(j, new Cell(i, j, Color.RED));
        }
      }
    }
    game.onMousePressed(new Posn(8, 8));
    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.stepsRemaining, 5.0);
  }

  // test onMousePressed when player clicks a cell on the edge
  void testOnMousePressedEdge(Tester t) {
    FloodItWorld game = new FloodItWorld(5, 3);
    // set board to all same color except for one on the edge
    for (int i = 0; i < game.size; i++) {
      for (int j = 0; j < game.size; j++) {
        game.board.get(i).set(j, new Cell(i, j, Color.BLUE));

      }
    }
    game.board.get(0).get(0).flooded = true;
    // set edge cell to a different color
    game.board.get(0).set(1, new Cell(0, 1, Color.RED));
    game.onMousePressed(new Posn(game.board.get(0).get(1).x, game.board.get(0).get(1).y)
        , "LeftButton");
    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.RED);
    t.checkExpect(game.board.get(0).get(1).flooded, false);
    t.checkExpect(game.stepsRemaining, 4.0);
    game.onMousePressed(new Posn(game.board.get(0).get(1).x, game.board.get(0).get(1).y)
        , "LeftButton");
    t.checkExpect(game.stepsRemaining, 3.0);
  }

  void testOnKeyEvent(Tester t) {
    FloodItWorld world = new FloodItWorld(2, 2);
    world.board.get(0).get(0).color = Color.BLUE;
    world.board.get(0).get(1).color = Color.RED;
    world.board.get(1).get(0).color = Color.GREEN;
    world.board.get(1).get(1).color = Color.YELLOW;
    world.onMousePressed(new Posn(0, 0));

    // simulate pressing the "r" key
    world.onKeyEvent("r");

    // check that the board has been reset
    for (ArrayList<Cell> row : world.board) {
      for (Cell cell : row) {
        t.checkExpect(cell.color == Color.RED ||
            cell.color == Color.BLUE ||
            cell.color == Color.GREEN ||
            cell.color == Color.YELLOW, true);
      }
    }

    // check that the steps remaining has been reset
    t.checkExpect(world.stepsRemaining, 1.0);

    // check that the game is not over
    t.checkExpect(world.isGameOver(), false);

  }

  void testOnTick(Tester t) {

    //testing a 2x2 board

    FloodItWorld world = new FloodItWorld(2, 2);
    world.board.get(0).get(0).color = Color.RED;
    world.board.get(0).get(1).color = Color.GREEN;
    world.board.get(1).get(0).color = Color.BLUE;
    world.board.get(1).get(1).color = Color.YELLOW;
    world.board.get(0).get(0).flooded = true;


    FloodItWorld game = new FloodItWorld(5, 3);
    // set board to all same color except for one on the edge
    for (int i = 0; i < game.size; i++) {
      for (int j = 0; j < game.size; j++) {
        if (i == 0 && j == 0) {
          game.board.get(i).set(j, new Cell(i, j, Color.RED));
        } else if (i == 1) {
          game.board.get(i).set(j, new Cell(i, j, Color.GREEN));
        } else if (i == 2) {
          game.board.get(i).set(j, new Cell(i, j, Color.BLUE));
        } else if (i == 3) {
          game.board.get(i).set(j, new Cell(i, j, Color.YELLOW));
        } else if (i == 4) {
          game.board.get(i).set(j, new Cell(i, j, Color.RED));
        } else {
          game.board.get(i).set(j, new Cell(i, j, Color.BLUE));
        }
      }
    }

    game.board.get(0).get(0).flooded = true;

    game.onMousePressed(new Posn(game.board.get(1).get(0).x, game.board.get(1).get(0).y)
        , "LeftButton");
    game.onTick();
    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.GREEN);
    t.checkExpect(game.board.get(1).get(0).flooded, true);
    t.checkExpect(game.board.get(1).get(0).color, Color.GREEN);
    t.checkExpect(game.board.get(2).get(0).flooded, false);
    t.checkExpect(game.board.get(2).get(0).color, Color.BLUE);
    t.checkExpect(game.board.get(3).get(0).flooded, false);
    t.checkExpect(game.board.get(3).get(0).color, Color.YELLOW);

    game.onMousePressed(new Posn(game.board.get(2).get(0).x, game.board.get(2).get(0).y)
        , "LeftButton");

    game.onTick();
    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.BLUE);
    t.checkExpect(game.board.get(1).get(0).flooded, true);
    t.checkExpect(game.board.get(1).get(0).color, Color.BLUE);
    t.checkExpect(game.board.get(2).get(0).flooded, true);
    t.checkExpect(game.board.get(2).get(0).color, Color.BLUE);
    t.checkExpect(game.board.get(3).get(0).flooded, false);
    t.checkExpect(game.board.get(3).get(0).color, Color.YELLOW);
    t.checkExpect(game.board.get(4).get(0).flooded, false);
    t.checkExpect(game.board.get(4).get(0).color, Color.RED);

    game.onMousePressed(new Posn(game.board.get(3).get(0).x, game.board.get(3).get(0).y)
        , "LeftButton");
    game.onTick();

    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.YELLOW);
    t.checkExpect(game.board.get(1).get(0).flooded, true);
    t.checkExpect(game.board.get(1).get(0).color, Color.YELLOW);
    t.checkExpect(game.board.get(2).get(0).flooded, true);
    t.checkExpect(game.board.get(2).get(0).color, Color.YELLOW);
    t.checkExpect(game.board.get(3).get(0).flooded, true);
    t.checkExpect(game.board.get(3).get(0).color, Color.YELLOW);
    t.checkExpect(game.board.get(4).get(0).flooded, false);
    t.checkExpect(game.board.get(4).get(0).color, Color.RED);

    game.onMousePressed(new Posn(game.board.get(4).get(0).x, game.board.get(4).get(0).y)
        , "LeftButton");
    game.onTick();

    t.checkExpect(game.board.get(0).get(0).flooded, true);
    t.checkExpect(game.board.get(0).get(0).color, Color.RED);
    t.checkExpect(game.board.get(1).get(0).flooded, true);
    t.checkExpect(game.board.get(1).get(0).color, Color.RED);
    t.checkExpect(game.board.get(2).get(0).flooded, true);
    t.checkExpect(game.board.get(2).get(0).color, Color.RED);
    t.checkExpect(game.board.get(3).get(0).flooded, true);
    t.checkExpect(game.board.get(3).get(0).color, Color.RED);
    t.checkExpect(game.board.get(4).get(0).flooded, true);
    t.checkExpect(game.board.get(4).get(0).color, Color.RED);
    t.checkExpect(game.board.get(4).get(0).flooded, true);
    t.checkExpect(game.board.get(4).get(4).color, Color.RED);

    for (int i = 0; i < game.size; i++) {
      for (int j = 0; j < game.size; j++) {
        if (game.board.get(i).get(j).flooded) {
          t.checkExpect(game.board.get(i).get(j).flooded, true);
          t.checkExpect(game.board.get(i).get(j).color, Color.RED);
        }
      }
    }

    // check initial state

    int counter = 0;

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size; j++) {
        if (world.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }

    t.checkExpect(counter, 1);

    //checking how many cells are flooded if 2 adjacent cells are the same color
    //before calling onTick.

    world.board.get(0).get(1).color = Color.RED;

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size; j++) {
        if (world.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }

    t.checkExpect(counter, 2);

    //checking amount of flooded cells after a tick

    world.onTick();

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size; j++) {
        if (world.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }

    t.checkExpect(counter, 4);

    //checking amount of flooded cells when more after another tick when more
    //adjacent cells are the same color

    world.board.get(1).get(0).color = Color.RED;

    world.onTick();

    for (int i = 0; i < world.size; i++) {
      for (int j = 0; j < world.size; j++) {
        if (world.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }

    t.checkExpect(counter, 7);

    //testing monochromatic 16x16 board

    FloodItWorld world2 = new FloodItWorld(16, 16);

    int counter2 = 0;

    world2.board.get(0).get(0).flooded = true;

    for (int i = 0; i < world2.size; i++) {
      for (int j = 0; j < world2.size; j++) {
        world2.board.get(i).get(j).color = Color.RED;
      }
    }

    for (int i = 0; i < world2.size; i++) {
      for (int j = 0; j < world2.size; j++) {
        if (world2.board.get(i).get(j).flooded) {
          counter2++;
        }
      }
    }

    t.checkExpect(counter2, 1);

    world2.onTick();
    counter = 0;
    for (int i = 0; i < world2.size; i++) {
      for (int j = 0; j < world2.size; j++) {
        if (world2.board.get(i).get(j).flooded) {
          counter++;
        }
      }
    }

    t.checkExpect(counter, 256);

  }


  void testSize(Tester t) {
    t.checkExpect(f1.size, 5);
    t.checkExpect(f2.size, 10);

  }

  void testLastScene(Tester t) {
    FloodItWorld world2 = new FloodItWorld(2, 3);
    world2.onMousePressed(new Posn(0, 0), "LeftButton");
    world2.onMousePressed(new Posn(0, 0), "LeftButton");
    world2.onTick();
    world2.makeScene();

    t.checkExpect(world2.stepsRemaining, 0.0);

    t.checkExpect(world2.makeScene(), world2.lastScene("You Win!"));


    t.checkExpect(f1.lastScene("You Win!"), f1.lastScene("You Win!"));
    t.checkExpect(f2.lastScene("You Win!"), f2.lastScene("You Win!"));

  }

  void testIsGameOver(Tester t) {
    // Check that the game is not over initially
    t.checkExpect(f1.isGameOver(), false);
    t.checkExpect(f2.isGameOver(), false);

    // Flood the entire board with one color and check that the game is over

    for (int i = 0; i < f1.size; i++) {
      for (int j = 0; j < f1.size; j++) {
        f1.board.get(i).get(j).color = Color.RED;
        f1.board.get(i).get(j).flooded = true;
      }
    }

    t.checkExpect(f1.isGameOver(), true);

    for (int i = 0; i < f2.size; i++) {
      for (int j = 0; j < f2.size; j++) {
        f2.board.get(i).get(j).color = Color.GREEN;
        f2.board.get(i).get(j).flooded = true;
      }
    }

    t.checkExpect(f2.isGameOver(), true);
  }

  // test the makeScene method
  void testMakeScene(Tester t) {

    t.checkExpect(f.makeScene().width, 150);
    t.checkExpect(f.makeScene().height, 150);


    // setting colors manually
    ArrayList<ArrayList<Cell>> board = world.board;
    //board.get(0).color = Color.RED;
    //board.get(1).color = Color.BLUE;
    // board.get(2).color = Color.GREEN;
    //board.get(3).color = Color.YELLOW;

    // creates expected scene
    WorldScene scene = new WorldScene(30, 30);
    scene.placeImageXY(new RectangleImage(15, 15, OutlineMode.SOLID,
            board.get(0).get(0).color),
        8, 8);
    scene.placeImageXY(new RectangleImage(15, 15, OutlineMode.SOLID,
            board.get(0).get(1).color),
        8, 23);
    scene.placeImageXY(new RectangleImage(15, 15, OutlineMode.SOLID,
            board.get(1).get(0).color),
        23, 8);
    scene.placeImageXY(new RectangleImage(15, 15, OutlineMode.SOLID,
            board.get(1).get(1).color),
        23, 23);

    t.checkExpect(world.makeScene(), scene);

    t.checkExpect(board.get(0).get(0).left, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(0).get(0).top, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(0).get(0).right, board.get(0).get(1));
    t.checkExpect(board.get(0).get(0).bottom, board.get(1).get(0));
    t.checkExpect(board.get(0).get(1).left, board.get(0).get(0));
    t.checkExpect(board.get(0).get(1).top, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(0).get(1).right, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(0).get(1).bottom, board.get(1).get(1));
    t.checkExpect(board.get(1).get(0).left, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(1).get(0).top, board.get(0).get(0));
    t.checkExpect(board.get(1).get(0).right, board.get(1).get(1));
    t.checkExpect(board.get(1).get(0).bottom, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(1).get(1).left, board.get(1).get(0));
    t.checkExpect(board.get(1).get(1).top, board.get(0).get(1));
    t.checkExpect(board.get(1).get(1).right, new MTCell(Color.WHITE, false));
    t.checkExpect(board.get(1).get(1).bottom, new MTCell(Color.WHITE, false));


  }*/

}

