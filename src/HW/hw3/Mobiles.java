package HW.hw3;

import tester.*;
import javalib.worldimages.*;
import javalib.funworld.*;
import javalib.worldcanvas.*;
import java.awt.Color;


interface IMobile {

  int totalWeight();

  int totalHeight();

  boolean isBalanced();

  int curRight();

  int curLeft();

  int curWidth();

  WorldImage drawMobile();

  IMobile buildMobile(IMobile that, int l, int total);

  int buildThatThang(int total, int b, IMobile that);


}



class Simple implements IMobile {
  int length;
  int weight;
  Color color;

    /* TEMPLATE
  FIELDS:
  ... this.length ...                                            -- int
  ... this.weight ...                                            -- int
  ... this.color ...                                             -- Color
  METHODS:
  ... this.totalWeight() ...                                     -- int
  ... this.totalHeight() ...                                     -- int
  ... this.isBalanced() ...                                      -- boolean
  ... this.buildMobile(int l, int total, IMobile that) ...       -- IMobile
  ... this.buildThatThang(int total, int b, IMobile that) ...    -- int
  ... this.curWidth() ...                                        -- int
  ... this.curRight() ...                                        -- int
  ... this.curLeft() ...                                         -- int
  ... this.drawMobile() ...                                      -- WorldImage
  METHODS FOR FIELDS: none
  */

  Simple(int length, int weight, Color color) {
    this.length = length;
    this.weight = weight;
    this.color = color;

  }




  // Returns the weight of Simple Mobile
  public int totalWeight() {
    return weight;
  }

  // Returns the height of Simple Mobile
  public int totalHeight() {
    return length + weight / 10;
  }

  // Computes whether a mobile is balanced
  public boolean isBalanced() {
    return true;
  }

  // curWidth method helper
  public int curRight() {
    if (this.weight % 20 == 0) {
      return this.weight / 20;
    }
    else {
      return this.weight / 20 + 1;
    }
  }

  // curWidth method helper
  public int curLeft() {
    if (this.weight % 20 == 0) {
      return this.weight / 20;
    }
    else {
      return this.weight / 20 + 1;
    }
  }

  // Computes the current width of a mobile
  public int curWidth() {
    return this.curLeft() + this.curRight();
  }


  //Draws the mobile

  public WorldImage drawMobile() {
    WorldImage str = new LineImage(new
        Posn(0, this.length * 20), Color.BLACK);
    WorldImage weight = new RectangleImage(this.curWidth() * 20,
        this.totalWeight() * 2, "solid", this.color);
    WorldImage overallMobile = new AboveImage(str, weight);
    return overallMobile.movePinhole(0, (-(overallMobile.getHeight()) / 2));
  }


  //Combines this balanced mobile with the given balanced mobile
  //and produces a new mobile
  public IMobile buildMobile(IMobile that, int l, int total) {
    return new Complex(l, this.buildThatThang(total, 0, that), total
        - this.buildThatThang(total, 0, that), this, that);
  }

  // Count the length of the side to make mobile balance
  public int buildThatThang(int total, int b, IMobile that) {
    if (new Complex(this.length, total - b, b, this, that).isBalanced()) {
      return total - b;
    }
    else {
      return this.buildThatThang(total, b + 1, that);
    }
  }



}


class Complex implements IMobile {
  int length;
  int leftside;
  int rightside;
  IMobile left;
  IMobile right;

  /* TEMPLATE:
  FIELDS:
      ... this.length ...                                           -- int
  ... this.leftside ...                                         -- int
  ... this.rightside ...                                        -- int
  ... this.left ...                                             -- IMobile
  ... this.right ...                                            -- IMobile
  METHODS:
      ... this.totalWeight() ...                                    -- int
  ... this.totalHeight() ...                                    -- int
  ... this.isBalanced() ...                                     -- boolean
  ... this.buildMobile(int l, int total, IMobile that) ...      -- IMobile
  ... this.buildHelper(int total, int b, IMobile that) ...      -- int
  ... this.curWidth() ...                                       -- int
  ... this.countLeft() ...                                      -- int
  ... this.countRight() ...                                     -- int
  ... this.drawMobile() ...                                     -- WorldImage
  METHODS FOR FIELDS:
      ... none ...
      */

  Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
    this.length = length;
    this.leftside = leftside;
    this.rightside = rightside;
    this.left = left;
    this.right = right;

  }

  // Returns the weight of Simple Mobile
  public int totalWeight() {
    return this.right.totalWeight() + this.left.totalWeight();
  }

  // Returns the height of Simple Mobile
  public int totalHeight() {
    if (this.left.totalHeight() < this.right.totalHeight()) {
      return this.length + this.right.totalHeight();
    }
    else {
      return this.length + this.left.totalHeight();
    }
  }

  // Computes whether a mobile is balanced
  public boolean isBalanced() {
    return (this.right.totalWeight() * this.rightside)
        == (this.left.totalWeight() * this.leftside);
  }

  // curWidth helper
  public int curRight() {
    return Math.max(this.left.curRight() - this.leftside,
        this.rightside + this.right.curRight());
  }

  // curWidth helper
  public int curLeft() {
    return Math.max(this.leftside + this.left.curLeft(),
        this.right.curLeft() - this.rightside);
  }

  // Computes the current width of a mobile
  public int curWidth() {
    return Math.max(this.leftside + this.left.curLeft(),
        this.right.curLeft() - this.rightside)
        + Math.max(this.left.curRight() - this.leftside,
        this.rightside + this.right.curRight());
  }

  // Combines this balanced mobile with the given balanced mobile
  // and produces a new mobile
  public IMobile buildMobile(IMobile that, int l, int total) {
    return new Complex(l, this.buildThatThang(total, 0, that), total
        - this.buildThatThang(total, 0, that), this, that);
  }

  // Count the length of the side to make mobile balance
  public int buildThatThang(int total, int b, IMobile that) {
    if (new Complex(this.length, total - b, b, this, that).isBalanced()) {
      return total - b;
    }
    else {
      return this.buildThatThang(total, b + 1, that);

    }
  }

  //Draws the mobile

  public WorldImage drawMobile() {
    WorldImage rightMobile = this.right.drawMobile();
    WorldImage leftMobile = this.left.drawMobile();
    WorldImage rightStrut = new LineImage(new Posn(this.rightside * 20, 0), Color.BLACK)
        .movePinhole((this.rightside * 20) / 2, 0);
    WorldImage leftStrut = new LineImage(new Posn(this.leftside * 20, 0), Color.BLACK)
        .movePinhole((-(this.leftside) * 20) / 2, 0);
    WorldImage rightSide = new OverlayImage(rightMobile, rightStrut)
        .movePinhole(-(this.rightside) * 20, 0);
    WorldImage leftSide = new OverlayImage(leftMobile, leftStrut)
        .movePinhole(this.leftside * 20, 0);
    WorldImage overallMobile = new OverlayImage(rightSide, leftSide);
    WorldImage length = new LineImage(new Posn(0, this.length * 20), Color.BLACK)
        .movePinhole(0, this.length * 10);
    return new OverlayImage(length, overallMobile).movePinhole(0, -(this.length) * 20);
  }




}

class ExamplesMobiles {
  IMobile exampleSimple = new Simple(2, 20, Color.BLUE);

  IMobile simpleLeft1 = new Simple(1, 36, Color.BLUE);
  IMobile simpleLeft2 = new Simple(1, 12, Color.RED);
  IMobile simpleLeft3 = new Simple(2, 36, Color.RED);
  IMobile simpleRight1 = new Simple(1, 60, Color.GREEN);
  IMobile complexRight2 = new Complex(2, 5, 3,
      this.simpleLeft3, this.simpleRight1);
  IMobile complexRight1 = new Complex(2, 8, 1,
      this.simpleLeft2, this.complexRight2);
  IMobile exampleComplex = new Complex(1, 9, 3,
      this.simpleLeft1, this.complexRight1);

  IMobile simpLeft1 = new Simple(1, 36, Color.BLUE);
  IMobile simpLeft2 = new Simple(1, 12, Color.RED);
  IMobile simpLeft3 = new Simple(2, 36, Color.RED);
  IMobile simpLeft4 = new Simple(5, 24, Color.GREEN);
  IMobile simpRight2 = new Simple(4, 22, Color.BLUE);
  IMobile simpRight1 = new Simple(1, 60, Color.GREEN);
  IMobile compRight3 = new Complex(4, 7, 2,
      this.simpLeft4, this.simpRight2);
  IMobile compLeft1 = new Complex(3, 6, 4,
      this.simpLeft2, this.compRight3);
  IMobile compRight2 = new Complex(2, 5, 3,
      this.simpLeft3, this.simpRight1);
  IMobile compRight1 = new Complex(2, 8, 1,
      this.compLeft1, this.compRight2);
  IMobile example3 = new Complex(1, 9, 3,
      this.simpLeft1, this.compRight1);

  // test the method totalWeight of a mobile
  boolean testTotalWeight(Tester t) {
    return t.checkExpect(this.exampleComplex.totalWeight(), 144)
        && t.checkExpect(this.example3.totalWeight(), 190)
        && t.checkExpect(this.example3.totalWeight(), 190);

  }
  // test the method totalHeight of a mobile
  boolean testTotalHeight(Tester t) {
    return t.checkExpect(this.exampleComplex.totalHeight(), 12)
        && t.checkExpect(this.exampleSimple.totalHeight(), 4)
        && t.checkExpect(this.example3.totalHeight(), 17);
  }
  // test the method isBalanced of a mobile
  boolean testIsBalanced(Tester t) {
    return t.checkExpect(this.exampleComplex.isBalanced(), true)
        && t.checkExpect(this.exampleSimple.isBalanced(), true)
        && t.checkExpect(this.example3.isBalanced(), false);
  }

  boolean testCurWidth(Tester t) {
    return t.checkExpect(this.exampleSimple.curWidth(), 2)
        && t.checkExpect(this.exampleComplex.curWidth(), 21)
        && t.checkExpect(this.example3.curWidth(), 22);
  }

  // test the method countLeft of a mobile
  boolean testCurLeft(Tester t) {
    return t.checkExpect(this.exampleComplex.curLeft(), 11)
        && t.checkExpect(this.exampleSimple.curLeft(), 1)
        && t.checkExpect(this.example3.curLeft(), 12);
  }


  // test the method countRight of a mobile
  boolean testCurRight(Tester t) {
    return t.checkExpect(this.exampleComplex.curRight(), 10)
        && t.checkExpect(this.exampleSimple.curRight(), 1)
        && t.checkExpect(this.example3.curRight(), 10);
  }

  // test the method buildMobile of a mobile
  boolean testBuildMobile(Tester t) {
    return t.checkExpect(this.exampleSimple.buildMobile(this.exampleSimple, 1, 40),
        new Complex(1, 20, 20, this.exampleSimple, this.exampleSimple))
        && t.checkExpect(this.exampleSimple.buildMobile(this.exampleSimple, 2, 40),
            new Complex(2, 20, 20, this.exampleSimple, this.exampleSimple))
        && t.checkExpect(this.exampleSimple.buildMobile(this.exampleSimple, 3, 40),
            new Complex(3, 20, 20, this.exampleSimple, this.exampleSimple));
  }


  // test the method drawMobile of a mobile
  boolean testDrawMobile(Tester t) {
    WorldCanvas c = new WorldCanvas(500, 500);
    WorldScene s = new WorldScene(500, 500);
    return c.drawScene(s.placeImageXY(exampleComplex.drawMobile(), 250, 250))
        && c.show();
  }



}



