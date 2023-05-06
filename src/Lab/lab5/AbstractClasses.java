package Lab.lab5;

import tester.Tester;

interface IBook {


  boolean isOverdue(int todayDay);



}

abstract class ABook implements IBook {

  String title;


  int dayTaken;

  ABook(String title, int dayTaken) {
    this.title = title;
    this.dayTaken = dayTaken;
  }

  public int daysOverdue(int todayDay) {
    return (this.dayTaken + 14) - todayDay ;
  }

  public boolean isOverdue(int todayDay) {

    if (this.daysOverdue(todayDay) < 0) {
      return false;
    } else {
      return true;
    }

  }

  public double computeFine(int todayDay) {
    if (isOverdue(todayDay)) {
      return (daysOverdue(todayDay) * .10);
    } else {
      return 0;
    }
  }
}

class Book extends ABook {
  String author;


  Book(String title, String author, int dayTaken) {

    super(title, dayTaken);
    this.author = author;

  }



}

class RefBook extends ABook {
  String title;
  int dayTaken;

  RefBook(String title, int dayTaken) {
    super(title, dayTaken);
  }


  @Override
  public int daysOverdue(int todayDay) {
    return (this.dayTaken + 2) - todayDay ;
  }



}

class AudioBook extends ABook {
  String title;
  String author;

  int dayTaken;

  AudioBook(String title, String author, int dayTaken) {

    super(title, dayTaken);
    this.author = author;

  }


  @Override
  public double computeFine(int todayDay) {
    if (isOverdue(todayDay)) {
      return (daysOverdue(todayDay) * .20);
    } else {
      return 0;
    }
  }

}

class BookExamples {
  IBook book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1);
  IBook book2 = new Book("The Lord of the Rings", "J.R.R. Tolkien", 13);
  IBook book3 = new Book("The Silmarillion", "J.R.R. Tolkien", 20);
  IBook book4 = new RefBook("The Hobbit", 0);
  IBook book5 = new RefBook("The Lord of the Rings", 0);
  IBook book6 = new RefBook("The Silmarillion", 0);
  IBook book7 = new AudioBook("The Hobbit", "J.R.R. Tolkien", 4);
  IBook book8 = new AudioBook("The Lord of the Rings", "J.R.R. Tolkien", 2);
  IBook book9 = new AudioBook("The Silmarillion", "J.R.R. Tolkien", 3);

  boolean testIsOverdue(Tester t) {
    return t.checkExpect(book1.isOverdue(14), true) &&
        t.checkExpect(book2.isOverdue(20), false) &&
        t.checkExpect(book3.isOverdue(25), false) &&
        t.checkExpect(book4.isOverdue(0), false) &&
        t.checkExpect(book5.isOverdue(0), false) &&
        t.checkExpect(book6.isOverdue(0), false) &&
        t.checkExpect(book7.isOverdue(5), false) &&
        t.checkExpect(book8.isOverdue(4), false) &&
        t.checkExpect(book9.isOverdue(7), true);
  }



}

