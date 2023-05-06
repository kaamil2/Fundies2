package HW.Practice;

import tester.Tester;


interface ICard {

  boolean isExpired();

  boolean sameCard(ICard that);

  boolean cNumEqauls(int that);

  boolean yearEquals(int that);

  boolean cvvEquals(int that);

  boolean nameEquals(String that);

  boolean balanceEquals(double that);




}


abstract class ACard implements ICard {

  int cNum;
  int year;
  int cvv;

  ACard(int cNum, int year, int cvv) {


    this.cNum = new Utils().properCNum(cNum);
    this.year = new Utils().yearForm(year);
    this.cvv = new Utils().properCvv(cvv);

  }


  public boolean isExpired() {

    return (this.year < 23);

  }

  public boolean sameCard(ICard that) {

    return (that.cNumEqauls(this.cNum) && that.yearEquals(this.year) && that.cvvEquals(this.cvv));

  }


  public boolean cNumEqauls(int that) {

    return this.cNum == that;

  }

  public boolean yearEquals(int that) {

    return this.year == that;

  }

  public boolean cvvEquals(int that) {

    return this.cvv == that;

  }

  public boolean nameEquals(String that) {

    return false;
  }

  public boolean balanceEquals(double that) {

    return false;
  }


  public double makeTransaction(double payment) {

    return 0;
  }

}


class CreditCard extends ACard {

  String name;

  CreditCard(int cNum, int year, int cvv, String name) {

    super(cNum, year, cvv);
    this.name = name;

  }

  @Override
  public boolean sameCard(ICard that) {

    return (that.nameEquals(this.name) && super.sameCard(that));


  }

  @Override
  public boolean nameEquals(String that) {

    return this.name == that;
  }



}

class DebitCard extends ACard {


  String name;
  double balance;

  DebitCard(int cNum, int year, int cvv, String name, double balance) {

    super(cNum, year, cvv);
    this.name = name;
    this.balance = new Utils().balanceNonNeg(balance);

  }

  @Override
  public boolean sameCard(ICard that) {

    return (that.nameEquals(this.name) && that.balanceEquals(this.balance) && super.sameCard(that));


  }

  @Override
  public boolean nameEquals(String that) {

    return this.name == that;
  }

  @Override
  public boolean balanceEquals(double that) {

    return this.balance == that;
  }

  @Override
  public double makeTransaction(double payment) {

    if (this.balance < payment) {

      return 0;


    } else {
      return this.balance = balance - payment;
    }

  }


}

class GiftCard extends ACard {

  double balance;

  GiftCard(int cNum, int year, int cvv, double balance) {

    super(cNum, year, cvv);
    this.balance = new Utils().balanceNonNeg(balance);

  }

  @Override
  public boolean sameCard(ICard that) {

    return (that.balanceEquals(this.balance) && super.sameCard(that));


  }

  @Override
  public boolean balanceEquals(double that) {

    return this.balance == that;
  }

  @Override
  public double makeTransaction(double payment) {

    if (this.balance < payment) {

      return 0;


    } else {
      return this.balance = balance - payment;
    }

  }

}

class Utils {

  int properCNum(int cNum) {

    if (Integer.toString(cNum).length() == 5 && Integer.toString(cNum).substring(0, 1) != "0") {
      return cNum;
    } else {

      throw new IllegalArgumentException("card number incorrect");

    }

  }


  int properCvv(int cvv) {

    if (Integer.toString(cvv).length() == 3 && !Integer.toString(cvv).substring(0, 1).equals("9")
        && !(Integer.toString(cvv).substring(0, 1)).equals("0")) {
      return cvv;
    } else {

      throw new IllegalArgumentException("cvv incorrect");

    }


  }


  double balanceNonNeg(double balance) {

    if (balance >= 0) {

      return balance;

    } else {

      throw new IllegalArgumentException("neg balance");


    }

  }

  int yearForm(int year) {

    if (Integer.toString(year).length() == 2) {

      return year;

    } else {

      throw new IllegalArgumentException("incorrect year format");

    }


  }


}

class Examples {

  ACard c1 = new CreditCard(12345, 20, 123, "John");
  ACard c1Same = new CreditCard(12345, 20, 123, "John");
  ACard c1Diff = new CreditCard(12545, 20, 123, "John");

  ACard d1 = new DebitCard(12345, 20, 123, "John", 100);


  boolean testProperCNum(Tester t) {
    return t.checkExpect(c1.cNum, 12345);
  }

  boolean testSameCard(Tester t) {
    return t.checkExpect(c1.sameCard(c1Same), true) &&
        t.checkExpect(c1.sameCard(c1Diff), false);
  }

  boolean testMakeTransaction(Tester t) {
    return t.checkExpect(d1.makeTransaction(10), 90.0) &&
        t.checkExpect(c1.makeTransaction(10), 0.0);
  }

  boolean checkConstructerExceptions(Tester t) {
    return t.checkConstructorException(new
            IllegalArgumentException("card number incorrect"),
        "DebitCard", 02345, 20, 123, "John", 100);

  }

}







