package Exam;


import java.util.ArrayList;
import tester.Tester;
import java.util.Arrays;
import java.util.Iterator;


class EveryOther<T> implements Iterator<T> {

  Iterator<T> iter;

  EveryOther(Iterator<T> iter) {
    this.iter = iter;
  }

  // iterable vs iterator

  // transverable vs traveler

  /* Iterator goes through the Iterable, just like a traveler goes through
   * a traversable
   *
   */

  //checks whether this arrayList has a next value
  public boolean hasNext() {
    return this.iter.hasNext();
  }
  //returns the next value (whatever it's assigned)
  public T next() {

    if (!this.hasNext()) {
      throw new IllegalStateException("No more elements");
    }

    T result = this.iter.next();

    // 1 2 3 4 5
    // result = 1
    // this.iter.next() = 2
    // result = 3
    // this.iter.next() = 4
    // result = 5

    // what if there is no other for every other

    if (this.iter.hasNext()) {
      this.iter.next();
    }
    return result;
  }
  // removes a value based on the given index
  public void remove() {
    this.iter.remove();
  }
}

class ExamplesOtherIterator {

  Iterator<Integer> arrayList;
  Iterator<Integer> e ;

  void initData(){

    this.arrayList = new ArrayList<Integer>(
        Arrays.asList(1, 2, 3, 4, 5)).iterator();

    this.e = new EveryOther<Integer>(arrayList);

  }

  void testIterator(Tester t) {
    initData();
    t.checkExpect(arrayList.hasNext(), true);
    t.checkExpect(arrayList.next(), 1);
    t.checkExpect(arrayList.hasNext(), true);
    t.checkExpect(arrayList.next(), 2);
    t.checkExpect(arrayList.hasNext(), true);
    t.checkExpect(arrayList.next(), 3);
    t.checkExpect(arrayList.hasNext(), true);
    t.checkExpect(arrayList.next(), 4);
    t.checkExpect(arrayList.hasNext(), true);
    t.checkExpect(arrayList.next(), 5);
    t.checkExpect(arrayList.hasNext(), false);
  }

  void testEveryOther(Tester t) {
    initData();


   t.checkExpect(e.hasNext(), true);
   t.checkExpect(e.next(), 1);
    t.checkExpect(e.hasNext(), true);
    t.checkExpect(e.next(), 3);
    t.checkExpect(e.hasNext(), true);
    t.checkExpect(e.next(), 5);
    t.checkExpect(e.hasNext(), false);
    t.checkException(new IllegalStateException("No more elements"), e, "next");

  }
}
