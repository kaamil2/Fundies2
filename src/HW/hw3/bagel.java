package HW.hw3;

import tester.Tester;

class BagelRecipe {

  double flour;

  double water;

  double yeast;

  double salt;

  double malt;

  BagelRecipe(double flour, double water, double yeast, double salt, double malt) {

    this.flour = flour;

    this.water = water;

    this.yeast = yeast;

    this.salt = salt;

    this.malt = malt;

    /* Fields:

     * this.flourW -- double

     * this.waterW -- double

     * this.yeastW -- double

     * this.saltW -- double

     * this.maltW -- double

     *

     * Method:

     * this.sameRecipe(BagelRecipe) -- boolean

     *

     * Methods for Fields:

     * NONE

     *

     */

    if (Math.abs(this.flour - this.water) <= 0.001) {

      throw new IllegalArgumentException("This flour to water ratio is incorrect, "

          + "so the bagel is not perfect!");

    }

    if (Math.abs(this.yeast - this.malt) <= 0.001) {

      throw new IllegalArgumentException("This is yeast to malt ratio is incorrect, "

          + "so the bagel is not perfect!");

    }

    if (Math.abs(((0.05 * this.flour) - this.yeast) - this.salt) <= 0.001) {

      throw new IllegalArgumentException("This salt to yeast to flour ratio is incorrect, "

          + "so the bagel is not perfect!");

    }

  }

  BagelRecipe(double flour, double yeast) {

    this(flour,

        flour,

        yeast,

        (0.05 * flour) - yeast,

        yeast);

  }

  BagelRecipe(double flourV, double yeastV, double saltV) {

    this(flourV * 4.25,

        flourV * 4.25,

        (yeastV / 48) * 5,

        (saltV / 48) * 10,

        (yeastV / 48) * 5);

  }

  public boolean sameRecipe(BagelRecipe other) {

    /* Parameters:

     * this.other.sameRecipe(BagelRecipe) -- boolean

     */

    return (Math.abs(this.flour - other.flour) <= 0.001

        && Math.abs(this.water - other.water) <= 0.001

        && Math.abs(this.yeast - other.yeast) <= 0.001

        && Math.abs(this.salt - other.salt) <= 0.001

        && Math.abs(this.malt - other.malt) <= 0.001);

  }

}

class ExamplesBagels {

  BagelRecipe bagel1 = new BagelRecipe(10.0, 10.0, 0.3, 0.2, 0.3);

  BagelRecipe bagel2 = new BagelRecipe(20.0, 20.0, 0.7, 0.3, 0.7);

//non perfect bagels

  BagelRecipe bagel3 = new BagelRecipe(10.0, 10.0, 0.3, 0.2, 0.1);

  BagelRecipe bagel4 = new BagelRecipe(10.0, 11.0, 0.3, 0.2, 0.3);

  BagelRecipe bagel5 = new BagelRecipe(10.0, 10.0, 0.3, 0.1, 0.3);

  BagelRecipe bagel6 = new BagelRecipe(10.0, 0.3);

  BagelRecipe bagel7 = new BagelRecipe(21.0, 0.8);

  BagelRecipe bagel8 = new BagelRecipe(42.5, 0.03125, 0.0416667);

  BagelRecipe bagel9 = new BagelRecipe(42.5, 0.03125, 1);

  boolean testSameRecipe(Tester t) {

    return

        t.checkExpect(this.bagel1.sameRecipe(bagel2), false)

            && t.checkExpect(this.bagel1.sameRecipe(bagel3), false)

            && t.checkExpect(this.bagel1.sameRecipe(bagel4), false)

            && t.checkExpect(this.bagel1.sameRecipe(bagel5), false)

            && t.checkExpect(this.bagel1.sameRecipe(bagel6), true)

            && t.checkExpect(this.bagel1.sameRecipe(bagel7), false)

            && t.checkExpect(this.bagel1.sameRecipe(bagel8), true)

            && t.checkExpect(this.bagel1.sameRecipe(bagel9), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel3), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel4), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel5), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel6), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel7), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel8), false)

            && t.checkExpect(this.bagel2.sameRecipe(bagel9), false)

            && t.checkExpect(this.bagel6.sameRecipe(bagel3), false)

            && t.checkExpect(this.bagel6.sameRecipe(bagel4), false)

            && t.checkExpect(this.bagel6.sameRecipe(bagel5), false)

            && t.checkExpect(this.bagel6.sameRecipe(bagel1), true)

            && t.checkExpect(this.bagel8.sameRecipe(bagel3), false)

            && t.checkExpect(this.bagel8.sameRecipe(bagel4), false)

            && t.checkExpect(this.bagel8.sameRecipe(bagel5), false)

            && t.checkExpect(this.bagel8.sameRecipe(bagel1), true);

  }

}