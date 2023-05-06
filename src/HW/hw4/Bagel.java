package HW.hw4;

import tester.Tester;

class BagelRecipe {
  double flour;
  double water;
  double salt;
  double yeast;
  double malt;

  /* Template:
  Fields:
  this.flour ... double
  this.water ... double
  this.salt ... double
  this.yeast ... double
  this.malt ... double
  Methods:
  this.sameRecipe(BagelRecipe that) ... boolean
  Methods for Fields:
  none
   */


  // constructor for a bagel recipe with all ingredients
  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {

    if ((this.flour = new Utils().checkEqualsTo(flour, water,
        "Flour and water must be equal")) == 0) {
      this.flour = flour;
      this.water = water;
    }

    if ((this.yeast = new Utils().checkEqualsTo(yeast, malt,
        "Yeast and malt must be equal")) == 0) {
      this.yeast = yeast;
      this.malt = malt;
    }

    if ((this.salt = new Utils().checkOneTwenty(yeast, salt, flour,
        "checkOneTwenty")) == 0) {
      this.salt = salt;
    }

  }

  // constructor for a bagel recipe with only flour and yeast
  BagelRecipe(double flour, double yeast) {
    this.flour = flour;
    this.water = flour;
    this.yeast = yeast;
    this.malt = yeast;
    this.salt = ((this.flour / 20) - this.yeast);


  }


  // constructor for a bagel recipe with only flour, yeast, and salt

  BagelRecipe(double flour, double yeast, double salt) {
    this.flour = flour * 4.25;
    this.water = this.flour;
    this.yeast = ((yeast / 48) * 5);
    this.malt = this.yeast;
    if ((this.salt = new Utils().checkOneTwenty(this.yeast, ((salt / 48) * 10), this.flour,
        "checkOneTwenty")) == 0) {
      this.salt = ((salt / 48) * 10);
    }


  }

  // checks to see if this bagel recipe is the same as that bagel recipe
  boolean sameRecipe(BagelRecipe that) {
    return
        Math.abs(this.flour - that.flour) < .001
            && Math.abs(this.water - that.water) < .001
            && Math.abs(this.salt - that.salt) < .001
            && Math.abs(this.yeast - that.yeast) < .001
            && Math.abs(this.malt - that.malt) < .001;
  }


}


class Utils {


  /*Template:
    Fields:
      none
    Methods:
      this.checkEqualsTo(double a, double b, String msg) ... double
      this.checkOneTwenty(double a, double b, double c, String msg) ... double
    Methods for Fields:
      none
 */

  //checks if a and b are equal to each other
  double checkEqualsTo(double a, double b, String msg) {
    if (Math.abs(a - b) < .001) {
      return 0;
    } else {
      throw new IllegalArgumentException(msg);
    }
  }

  //checks if a and b are equal to c / 20
  double checkOneTwenty(double a, double b, double c, String msg) {
    if (Math.abs((a + b) - (c / 20)) < .001) {
      return 0;
    } else {
      throw new IllegalArgumentException(msg);
    }
  }


}



class ExamplesBagelRecipe {

  BagelRecipe bagel1 = new BagelRecipe(100, 100, 3, 2, 3);
  BagelRecipe bagel2 = new BagelRecipe(100, 3);
  BagelRecipe bagel3 = new BagelRecipe(23.5294118, 28.8, 9.6);

  BagelRecipe bagel4Wrong = new BagelRecipe(80, 80, 2, 2, 2);


  // tests the sameRecipe method

  boolean testSameRecipe(Tester t) {
    return t.checkExpect(bagel1.sameRecipe(bagel2), true)
        && t.checkExpect(bagel1.sameRecipe(bagel3), true)
        && t.checkExpect(bagel2.sameRecipe(bagel3), true)
        && t.checkExpect(bagel1.sameRecipe(bagel4Wrong), false)
        && t.checkExpect(bagel3.sameRecipe(bagel4Wrong), false);
  }

  // tests the checkEqualsTo method

  boolean testCheckEqualsTo(Tester t) {
    return t.checkExpect(new Utils().checkEqualsTo(1, 1, "msg"), 0.0)
        && t.checkExpect(new Utils().checkEqualsTo(0, 0.0001, "msg"), 0.0);
  }

  // tests the checkOneTwenty method

  boolean testCheckOneTwenty(Tester t) {
    return t.checkExpect(new Utils().checkOneTwenty(1, 1, 40, "msg"), 0.0)
        && t.checkExpect(new
        Utils().checkOneTwenty(1, 1.0000001, 40, "msg"), 0.0);
  }


  // tests the checkConstructerExceptions method

  boolean checkConstructerExceptions(Tester t) {
    return t.checkConstructorException(new
            IllegalArgumentException("Flour and water must be equal"),
        "BagelRecipe", 100, 100.0001, 3, 2, 3)
        && t.checkConstructorException(new
            IllegalArgumentException("Yeast and malt must be equal"),
        "BagelRecipe", 100, 100, 3, 2, 3.0001)
        && t.checkConstructorException(new IllegalArgumentException("checkOneTwenty"),
        "BagelRecipe", 100, 100, 3, 2, 3)
        && t.checkConstructorException(new IllegalArgumentException("checkOneTwenty"),
        "BagelRecipe", 100, 100, 3, 2.0001, 3)
        && t.checkConstructorException(new IllegalArgumentException("checkOneTwenty"),
        "BagelRecipe", 100, 100, 3, 3, 3)
        && t.checkConstructorException(new
        IllegalArgumentException("checkOneTwenty"),
        "Utils", 1, 1, 40, "msg")
        && t.checkConstructorException(new
            IllegalArgumentException("Yeast and malt must be equal"),
        "Utils", 1, 2, "msg")
        && t.checkConstructorException(new
            IllegalArgumentException("Flour and water must be equal"),
        "Utils", 3, 4, 40, "msg");

  }


}


