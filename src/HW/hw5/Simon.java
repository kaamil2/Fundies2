package HW.hw5;

import javalib.funworld.World;
import javalib.funworld.WorldScene;
import javalib.worldimages.*;
import tester.Tester;

import java.awt.*;
import java.util.Random;


class SimonWorld extends World {

  // constants

  // the size of the world
  static int SCENE_SIZE = 500;


  // the buttons

  // the red button
  Button redButton;
  // the green button
  Button greenButton;
  // the blue button
  Button blueButton;

  // the yellow button
  Button yellowButton;

  // the litButtons

  // is the red button lit?
  boolean redLit;

  // is the green button lit?
  boolean greenLit;
  // is the blue button lit?
  boolean blueLit;
  // is the yellow button lit?
  boolean yellowLit;
  // litButton list
  ILoButton litButtons;

  // is the function being displayed?
  boolean display;

  // the id of the button being displayed
  int buttonId;

  // the index of the user's input
  int userIndex;

  // is game finished
  boolean gameOver;

  // the current level
  int level;

  // the random number generator
  Random rand;

  // methods

  // constructor
  SimonWorld(Random rand) {

    this.display = true;

    this.gameOver = false;

    this.level = 1;

    this.rand = rand;

    this.yellowButton = new Button(Color.YELLOW, 75, 75);
    this.blueButton = new Button(Color.BLUE, 0, 75);
    this.redButton = new Button(Color.RED, 0, 0);
    this.greenButton = new Button(Color.GREEN, 75, 0);

    this.redLit = false;
    this.greenLit = false;
    this.blueLit = false;
    this.yellowLit = false;


    this.litButtons = new MtLoButton();
    this.litButtons = this.litButtons.diffButton(rand);
  }

  // real constructor
  SimonWorld() {
    this(new Random());
  }



  // makes the world scene depneding on the state of the world
  public WorldScene makeScene() {

    if (this.gameOver) {
      return this.lastScene("Game Over");
    }


    WorldScene background = new WorldScene(SCENE_SIZE, SCENE_SIZE);


    WorldImage buttonsImage = new BesideAlignImage(AlignModeY.TOP,
        new AboveImage(
            this.redButton.drawDark(),
            this.blueButton.drawDark()),
        new AboveImage(
            this.greenButton.drawDark(),
            this.yellowButton.drawDark()));


    background = background.placeImageXY(
        new TextImage("level " + this.level, 20, Color.BLACK),
        50, 50);


    if (this.buttonId == this.litButtons.length()) {
      this.display = false;
      this.buttonId = 0;
    }


    if (this.display) {
      background = background.placeImageXY(
          new TextImage("Watch the litButtons", 20, Color.BLACK),
          200,400);
    } else {
      background = background.placeImageXY(
          new TextImage("Repeat the litButtons", 20, Color.BLACK),
          200, 400);
    }

    // adjust the appearance of the buttons' image depending on whether the buttons are lit
    if (this.redLit) {
      buttonsImage = new BesideAlignImage(AlignModeY.TOP,
          new AboveImage(
              this.redButton.drawLit(),
              this.blueButton.drawDark()),
          new AboveImage(
              this.greenButton.drawDark(),
              this.yellowButton.drawDark()));
      this.redLit = false;
    } else if (this.greenLit) {
      buttonsImage = new BesideAlignImage(AlignModeY.TOP,
          new AboveImage(
              this.redButton.drawDark(),
              this.blueButton.drawDark()),
          new AboveImage(
              this.greenButton.drawLit(),
              this.yellowButton.drawDark()));
      this.greenLit = false;
    } else if (this.blueLit) {
      buttonsImage = new BesideAlignImage(AlignModeY.TOP,
          new AboveImage(
              this.redButton.drawDark(),
              this.blueButton.drawLit()),
          new AboveImage(
              this.greenButton.drawDark(),
              this.yellowButton.drawDark()));
      this.blueLit = false;
    } else if (this.yellowLit) {
      buttonsImage = new BesideAlignImage(AlignModeY.TOP,
          new AboveImage(
              this.redButton.drawDark(),
              this.blueButton.drawDark()),
          new AboveImage(
              this.greenButton.drawDark(),
              this.yellowButton.drawLit()));
      this.yellowLit = false;
    }

    return background.placeImageXY(buttonsImage, SCENE_SIZE / 2, SCENE_SIZE / 2);
  }

  // handles what happens to the world on the tick
  public World onTick() {

    if (gameOver) {
      return this;
    }


    if (this.display) {

      Color nextColor = this.litButtons.properButton(this.buttonId).color;
      if (nextColor.equals(Color.RED) && !this.redLit ) {
        this.redLit = true;
        this.buttonId++;
      } else if (nextColor.equals(Color.GREEN) && !this.greenLit) {
        this.greenLit = true;
        this.buttonId++;
      } else if (nextColor.equals(Color.BLUE) && !this.blueLit ) {
        this.blueLit = true;
        this.buttonId++;

      } else if (nextColor.equals(Color.YELLOW) && !this.yellowLit ) {
        this.yellowLit = true;
        this.buttonId++;
      }
    }
    return this;
  }

  // handles what happens to the world when it is the last scene
  public WorldScene lastScene(String msg) {

    WorldScene background = new WorldScene(SCENE_SIZE, SCENE_SIZE);
    background = background.placeImageXY(new TextImage(msg, 20, Color.BLACK),
        SCENE_SIZE / 2 , SCENE_SIZE / 2 );
    return background;
  }

  // handles mouse clicks and is given the location
  public SimonWorld onMousePressed(Posn pos) {
    /* Template
     * Fields:
     * ...this.redButton... -- Button
     * ...this.greenButton... -- Button
     * ...this.blueButton... -- Button
     * ...this.yellowButton... -- Button
     * ...this.litButtons... -- ILoButton
     * ...this.userIndex... -- int
     * ...this.buttonId... -- int
     * ...this.display... -- boolean
     * ...this.gameOver... -- boolean
     * ...this.level... -- int
     * ...this.rand... -- Random
     * ...this.redLit... -- boolean
     * ...this.greenLit... -- boolean
     * ...this.blueLit... -- boolean
     * ...this.yellowLit... -- boolean
     * Methods:
     * ...this.litButtons.properButton(int)... -- Button
     * ...this.litButtons.length()... -- int
     * ...this.litButtons.diffButton(Random)... -- ILoButton
     * Methods on Fields:
     * ...this.redButton.drawDark()... -- WorldImage
     * ...this.greenButton.drawDark()... -- WorldImage
     * ...this.blueButton.drawDark()... -- WorldImage
     * ...this.yellowButton.drawDark()... -- WorldImage
     * ...this.redButton.drawLit()... -- WorldImage
     * ...this.greenButton.drawLit()... -- WorldImage
     * ...this.blueButton.drawLit()... -- WorldImage
     * ...this.yellowButton.drawLit()... -- WorldImage
     * ...this.litButtons.properButton(int).color... -- Color
     * ...this.litButtons.diffButton(Random).length()... -- int
     * ...this.litButtons.diffButton(Random).properButton(int)... -- Button
     * ...this.litButtons.diffButton(Random).properButton(int).color... -- Color
     * ...this.litButtons.diffButton(Random).diffButton(Random)... -- ILoButton
     * ...this.litButtons.diffButton(Random).diffButton(Random).length()... -- int
     * ...this.litButtons.diffButton(Random).diffButton(Random).properButton(int)... -- Button
     * ...this.litButtons.diffButton(Random).diffButton(Random).properButton(int).color... -- Color
     * ...this.litButtons.diffButton(Random).diffButton(Random).diffButton(Random)...

     */

    //if the game is over or the computer is displaying the litButtons, do nothing
    if (this.gameOver || this.display) {
      return this;
    }

    // if the mouse click is on the red button
    if (pos.x >= 176 && pos.x <= 250
        && pos.y >= 175 && pos.y <= 255) {
      // update the button to be lit
      this.redLit = true;

      // if the red button is the next button in the litButtons
      if (this.litButtons.properButton(this.userIndex).color.equals(Color.RED)) {
        if (this.userIndex == this.litButtons.length() - 1) {
          this.litButtons = this.litButtons.diffButton(this.rand);
          this.level += 1;
          this.display = true;
          this.userIndex = 0;

        } else {

          this.userIndex += 1;
        }
      } else {
        this.gameOver = true;
      }
    }
    else if ((pos.x >= 250 && pos.x <= 325
        && pos.y >= 175 && pos.y <= 255)) {
      this.greenLit = true;

      if (this.litButtons.properButton(this.userIndex).color.equals(Color.GREEN)) {
        if (this.userIndex == this.litButtons.length() - 1) {
          this.litButtons = this.litButtons.diffButton(this.rand);
          this.level += 1;
          this.display = true;
          this.userIndex = 0;

        } else {
          this.userIndex += 1;
        }
      } else {

        this.gameOver = true;
      }
    }

    else if ((pos.x >= 176 && pos.x <= 250
        && pos.y >= 250 && pos.y <= 325)) {

      this.blueLit = true;


      if (this.litButtons.properButton(this.userIndex).color.equals(Color.BLUE)) {

        if (this.userIndex == this.litButtons.length() - 1) {

          this.litButtons = this.litButtons.diffButton(this.rand);

          this.userIndex = 0;

          this.level += 1;
          this.display = true;

        } else {
          this.userIndex += 1;
        }
      } else {
        this.gameOver = true;
      }
    }
    else if ((pos.x >= 250 && pos.x <= 325
        && pos.y >= 250 && pos.y <= 325)) {
      this.yellowLit = true;

      if (this.litButtons.properButton(this.userIndex).color.equals(Color.YELLOW)) {
        if (this.userIndex == this.litButtons.length() - 1) {
          this.litButtons = this.litButtons.diffButton(this.rand);
          this.userIndex = 0;
          this.level += 1;
          this.display = true;

        } else {

          this.userIndex += 1;
        }
      } else {

        this.gameOver = true;
      }
    }

    return this;
  }
}

// Represents a button
interface ILoButton {

  // Returns the button at the given index
  Button properButton(int index);

  // Returns the length of this list of buttons
  int length();


  // Returns a new list of buttons with a new button added to the end
  ILoButton diffButton(Random rand);

  // Returns a new list of buttons with the buttons in reverse order
  ILoButton reverse();


  // Returns a new list of buttons with the buttons in reverse order
  ILoButton reverseHelper(ILoButton that);
}

// Represents an empty list of buttons
class MtLoButton implements ILoButton {
  /*
   * TEMPLATE
   * Fields:
   * ... none ...
   * Methods:
   * ... this.properButton
   * (int index) ... -- Button
   * ... this.length() ... -- int
   * ... this.diffButton(Random rand) ... -- ILoButton
   * ... this.reverse() ... -- ILoButton
   * ... this.reverseHelper(ILoButton acc) ... -- ILoButton
   */

  public Button properButton(int index) {
    return null;
  }


  public int length() {
    return 0;
  }


  public ILoButton diffButton(Random rand) {
    int randInt = rand.nextInt(5) ;
    if (randInt == 1) {
      return new ConsLoButton(new Button(Color.RED, 200, 200), this);
    } else if (randInt == 2) {
      return new ConsLoButton(new Button(Color.GREEN, 300, 200), this);
    } else if (randInt == 3) {
      return new ConsLoButton(new Button(Color.BLUE, 200, 300), this);
    } else {
      return new ConsLoButton(new Button(Color.YELLOW, 300, 300), this);
    }
  }


  public ILoButton reverse() {
    return this;
  }


  public ILoButton reverseHelper(ILoButton that) {
    return that;
  }
}


class ConsLoButton implements ILoButton {
  // the first button in the list
  Button first;
  // the rest of the buttons in the list
  ILoButton rest;

  // constructor
  ConsLoButton(Button first, ILoButton rest) {
    this.first = first;
    this.rest = rest;
  }

  /*Template
   * Fields:
   * ... this.first ... -- Button
   * ... this.rest ... -- ILoButton
   * Methods:
   * ... this.properButton
   * (int index) ... -- Button
   * ... this.length() ... -- int
   * ... this.diffButton(Random rand) ... -- ILoButton
   * ... this.reverse() ... -- ILoButton
   * ... this.reverseHelper(ILoButton acc) ... -- ILoButton
   * Methods for Fields:
   * ... this.first.drawDark() ... -- WorldImage
   * ... this.first.drawLit() ... -- WorldImage
   * ... this.first.drawButton(Color) ... -- WorldImage
   * ... this.first.isClicked(Posn) ... -- boolean
   * ... this.rest.properButton
   * (int index) ... -- Button
   * ... this.rest.length() ... -- int
   * ... this.rest.diffButton(Random rand) ... -- ILoButton
   * ... this.rest.reverse() ... -- ILoButton
   * ... this.rest.reverseHelper(ILoButton acc) ... -- ILoButton
   */



  public Button properButton(int index) {
    if (index == 0) {
      return this.first;
    } else {
      return this.rest.properButton(index - 1);
    }
  }

  public int length() {
    return 1 + this.rest.length();
  }


  public ILoButton diffButton(Random rand) {
    int randInt = rand.nextInt(5) ;
    if (randInt == 1) {
      return new ConsLoButton(new Button(Color.BLUE, 200, 200), this.reverse()).reverse();
    } else if (randInt == 2) {
      return new ConsLoButton(new Button(Color.RED, 300, 200), this.reverse()).reverse();
    } else if (randInt == 3) {
      return new ConsLoButton(new Button(Color.GREEN, 200, 300), this.reverse()).reverse();
    } else {
      return new ConsLoButton(new Button(Color.YELLOW, 300, 300), this.reverse()).reverse();
    }
  }


  public ILoButton reverse() {
    return this.reverseHelper(new MtLoButton());
  }

  public ILoButton reverseHelper(ILoButton that) {
    return this.rest.reverseHelper(new ConsLoButton(this.first, that));
  }
}


/*
    System.out.println("Mouse clicked at " + pos.x + ", " + pos.y + "the state is " + state);
    if (state == 1 || state == 8 ) {
      if (pos.x > 238 && pos.x < 310 && pos.y > 238 && pos.y < 310) {
        return new SimonWorld(1, false);
      } else {
        return new SimonWorld(6, true);
      }
    } else if (state == 2 || state == 8) {
      if (pos.x > 164 && pos.x < 238 && pos.y > 166 && pos.y < 238) {
        return new SimonWorld(2, false);
      } else {
        return new SimonWorld(6, true);
      }
    } else if (state == 3 || state == 8) {
      if (pos.x >= 238 && pos.x <= 310 && pos.y >= 166 && pos.y <= 238) {
        return new SimonWorld(3, false);
      } else {
        return new SimonWorld(6, true);
      }
    } else if (state == 4 || state == 8) {
      if (pos.x >= 164 && pos.x <= 238 && pos.y >= 238 && pos.y <= 310) {
        return new SimonWorld(4, false);
      } else {
        return new SimonWorld(6, true);
      }
      /*
    }else if (state == 5) {
      if((pos.x >= 200 && pos.x <= 250) && pos.y >= 200 && pos.y <= 250) {
        return new SimonWorld(5,false);
      }else{
        return new SimonWorld(6,true);
      }


    } else if (state == 7) {
      if (pos.x > 238 && pos.x < 310 && pos.y > 238 && pos.y < 310) {
        return new SimonWorld(1, false);
      } else {
        return new SimonWorld(6, true);
      }
    }
    return new SimonWorld(5, true);
  }
  */

class Button {

  Color color;

  int x;

  int y;

  // constructor
  Button(Color color, int x, int y) {
    this.color = color;
    this.x = x;
    this.y = y;
  }



  // returns the button darker
  WorldImage drawDark() {
    return this.drawButton(this.color.darker().darker().darker().darker());
  }


  // returns the button lighter
  WorldImage drawLit() {
    return this.drawButton(this.color.brighter().brighter().brighter().brighter());
  }

  // returns the button with the given color
  WorldImage drawButton(Color color) {
    return new CropImage(this.x, this.y, 75, 75,
        new CircleImage(75, OutlineMode.SOLID, color));
  }



}

// Examples
class ExamplesSimon {

  // test the bigBang method
  boolean testSimonSays(Tester t) {
    SimonWorld starterWorld = new SimonWorld();
    int sceneSize = SimonWorld.SCENE_SIZE;
    return starterWorld.bigBang(sceneSize, sceneSize, .5);
  }

  // test properButton method

  boolean testProperButton(Tester t) {
    return t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new MtLoButton()).properButton(0), new Button(Color.RED, 200, 200))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new MtLoButton()).properButton(1), null)
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new MtLoButton())).properButton(0), new Button(Color.RED, 200, 200))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new MtLoButton())).properButton(1), new Button(Color.GREEN, 300, 200))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new MtLoButton())).properButton(2), null);

  }


  // test length method

  boolean testLength(Tester t) {
    return t.checkExpect(new MtLoButton().length(), 0)
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new MtLoButton()).length(), 1)
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new MtLoButton())).length(), 2);
  }

  // test reverse method
  boolean testReverse(Tester t) {
    return t.checkExpect(new MtLoButton().reverse(), new MtLoButton())
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new MtLoButton()).reverse(), new ConsLoButton(new Button(Color.RED, 200, 200),
        new MtLoButton()))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new MtLoButton())).reverse(), new ConsLoButton(new Button(Color.GREEN, 300, 200),
        new ConsLoButton(new Button(Color.RED, 200, 200),
            new MtLoButton())))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
            new ConsLoButton(new Button(Color.GREEN, 300, 200),
                new ConsLoButton(new Button(Color.BLUE, 200, 300),
                    new MtLoButton()))).reverse(),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.GREEN, 300, 200),
                new ConsLoButton(new Button(Color.RED, 200, 200),
                    new MtLoButton()))));
  }

  // test reverseHelper method
  boolean testReverseHelper(Tester t) {
    return t.checkExpect(new MtLoButton().reverseHelper(new MtLoButton()), new MtLoButton())
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
            new MtLoButton()).reverseHelper(new MtLoButton()),
        new ConsLoButton(new Button(Color.RED, 200, 200),
            new MtLoButton()))
        && t.checkExpect(new ConsLoButton(new Button(Color.RED, 200, 200),
            new ConsLoButton(new Button(Color.GREEN, 300, 200),
                new MtLoButton())).reverseHelper(new MtLoButton()),
        new ConsLoButton(new Button(Color.GREEN, 300, 200),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new MtLoButton())));
  }

  // test drawdark method
  boolean testDrawDark(Tester t) {
    return t.checkExpect(new Button(Color.RED, 200, 200).drawDark(),
        new CropImage(200, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.RED.darker().darker().darker().darker())))
        && t.checkExpect(new Button(Color.GREEN, 300, 200).drawDark(),
        new CropImage(300, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.GREEN.darker().darker().darker().darker())))
        && t.checkExpect(new Button(Color.BLUE, 200, 300).drawDark(),
        new CropImage(200, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.BLUE.darker().darker().darker().darker())))
        && t.checkExpect(new Button(Color.YELLOW, 300, 300).drawDark(),
        new CropImage(300, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.YELLOW.darker().darker().darker().darker())));
  }

  // test drawLit method

  boolean testDrawLit(Tester t) {
    return t.checkExpect(new Button(Color.RED, 200, 200).drawLit(),
        new CropImage(200, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.RED.brighter().brighter().brighter().brighter())))
        && t.checkExpect(new Button(Color.GREEN, 300, 200).drawLit(),
        new CropImage(300, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.GREEN.brighter().brighter().brighter().brighter())))
        && t.checkExpect(new Button(Color.BLUE, 200, 300).drawLit(),
        new CropImage(200, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.BLUE.brighter().brighter().brighter().brighter())))
        && t.checkExpect(new Button(Color.YELLOW, 300, 300).drawLit(),
        new CropImage(300, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID,
                Color.YELLOW.brighter().brighter().brighter().brighter())));
  }

  //test drawButton method
  boolean testDrawButton(Tester t) {
    return t.checkExpect(new Button(Color.RED, 200, 200).drawButton(Color.RED),
        new CropImage(200, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID, Color.RED)))
        && t.checkExpect(new Button(Color.GREEN, 300, 200).drawButton(Color.GREEN),
        new CropImage(300, 200, 75, 75,
            new CircleImage(75, OutlineMode.SOLID, Color.GREEN)))
        && t.checkExpect(new Button(Color.BLUE, 200, 300).drawButton(Color.BLUE),
        new CropImage(200, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID, Color.BLUE)))
        && t.checkExpect(new Button(Color.YELLOW, 300, 300).drawButton(Color.YELLOW),
        new CropImage(300, 300, 75, 75,
            new CircleImage(75, OutlineMode.SOLID, Color.YELLOW)));
  }


  // test diffButton method

  boolean testdiffButton(Tester t) {
    Random rand = new Random();
    rand = new Random(2);


    return t.checkExpect(new MtLoButton().diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300), new MtLoButton()))
        && t.checkExpect(new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new MtLoButton()).diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 300, 200), new MtLoButton())))
        && t.checkExpect(new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new MtLoButton())).diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.YELLOW, 300, 300),
                    new MtLoButton()))))
        && t.checkExpect(new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.GREEN, 300, 200),
                    new MtLoButton()))).diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.GREEN, 300, 200),
                    new ConsLoButton(new Button(Color.RED, 300, 200),
                        new MtLoButton())))))
        && t.checkExpect(new MtLoButton().diffButton(rand),
        new ConsLoButton(new Button(Color.YELLOW, 300, 300), new MtLoButton()))
        && t.checkExpect(new ConsLoButton(new Button(Color.YELLOW, 200, 300),
            new MtLoButton()).diffButton(rand),
        new ConsLoButton(new Button(Color.YELLOW, 200, 300),
            new ConsLoButton(new Button(Color.YELLOW, 300, 300), new MtLoButton())))
        && t.checkExpect(new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new MtLoButton())).diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.BLUE, 200, 200),
                    new MtLoButton()))))
        && t.checkExpect(new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.GREEN, 300, 200),
                    new MtLoButton()))).diffButton(rand),
        new ConsLoButton(new Button(Color.BLUE, 200, 300),
            new ConsLoButton(new Button(Color.RED, 200, 200),
                new ConsLoButton(new Button(Color.GREEN, 300, 200),
                    new ConsLoButton(new Button(Color.YELLOW, 300, 300),
                        new MtLoButton())))));
  }



}


