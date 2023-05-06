
import tester.Tester;

import java.util.ArrayList;
import java.util.Iterator;


class LOLIterator<T> implements Iterator<T> {
  ListOfLists<T> lists;
  int counter;

  public LOLIterator(ListOfLists<T> lists) {
    this.lists = lists;
    this.counter = 0;
  }

  public boolean hasNext() {
    return this.counter >= this.lists.size();
  }

  public T next() {
    return this.lists.get(counter).get(counter);
  }
}


class ListOfLists<T> implements Iterable<T> {

  ArrayList<ArrayList<T>> lists = new ArrayList<ArrayList<T>>();

  ListOfLists() {

  }


  // addNewList() that adds a new empty ArrayList<T> to the end of the list-of-lists.
  void addNewList() {
    this.lists.add(new ArrayList<T>());
  }

  // adds the provided object to the end of the ArrayList<T>
  // at the provided index in the list-of-lists.
  void add(int index, T object) {
    if (index > this.size() || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is not valid.");
    } else {
      this.lists.get(index).add(object);
    }
  }


  // returns the ArrayList<T> at the provided index in the list-of-lists.
  ArrayList<T> get(int index) {
    if (index > this.size() || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index + " is not valid.");
    } else {
      return this.lists.get(index);
    }
  }


  int size() {

    int counter = 0;

    for (ArrayList<T> lists : this.lists) {
      return counter = counter + lists.size();
    }
    return counter;
  }

  @Override
  public Iterator<T> iterator() {
    return new LOLIterator<T>(this);
  }
}


class ExamplesListOfLists {

  // test the iterator method
  void testIterator(Tester t) {

    ListOfLists<Boolean> lolBoolean = new ListOfLists<Boolean>();
    ListOfLists<Integer> lolInteger = new ListOfLists<Integer>();
    ListOfLists<String> lolString = new ListOfLists<String>();

    t.checkExpect(lolBoolean.iterator(), new LOLIterator<Boolean>(lolBoolean));
    t.checkExpect(lolInteger.iterator(), new LOLIterator<Integer>(lolInteger));
    t.checkExpect(lolString.iterator(), new LOLIterator<String>(lolString));


  }

  // test the get method
  void testGet(Tester t) {

    ListOfLists<Integer> lolGet3 = new ListOfLists<Integer>();
    lolGet3.addNewList();
    lolGet3.add(0, 1);
    lolGet3.add(0, 2);
    lolGet3.add(0, 3);

    ListOfLists<Integer> lolGet2 = new ListOfLists<Integer>();
    lolGet2.addNewList();
    lolGet2.add(0, 1);
    lolGet2.add(0, 2);

    ListOfLists<Integer> lolGet1 = new ListOfLists<Integer>();
    lolGet1.addNewList();
    lolGet1.add(0, 1);

    ListOfLists<Integer> lolGet4 = new ListOfLists<Integer>();
    lolGet4.addNewList();
    lolGet4.add(0, 1);
    lolGet4.add(0, 2);
    lolGet4.add(0, 3);
    lolGet4.add(0, 4);

    t.checkExpect(lolGet3.get(0).get(2), 3);
    t.checkExpect(lolGet2.get(0).get(1), 2);
    t.checkExpect(lolGet1.get(0).get(0), 1);
    t.checkExpect(lolGet4.get(0).get(3), 4);

  }

  // test the addlist method
  void testAddList(Tester t) {

    ListOfLists<Integer> lolAddList = new ListOfLists<Integer>();
    lolAddList.addNewList();
    t.checkExpect(lolAddList.get(0), new ArrayList<Integer>());


  }


  //test given tests
  void testListOfLists(Tester t) {
    ListOfLists<Integer> lol = new ListOfLists<Integer>();
    //add 3 lists
    lol.addNewList();
    lol.addNewList();
    lol.addNewList();

    //add elements 1,2,3 in first list
    lol.add(0, 1);
    lol.add(0, 2);
    lol.add(0, 3);

    //add elements 4,5,6 in second list
    lol.add(1, 4);
    lol.add(1, 5);
    lol.add(1, 6);

    //add elements 7,8,9 in third list
    lol.add(2, 7);
    lol.add(2, 8);
    lol.add(2, 9);

    //iterator should return elements in order 1,2,3,4,5,6,7,8,9

    int number = 1;
    for (Integer num : lol) {
      t.checkExpect(num, number);
      number = number + 1;
    }


  }


  // test the add method
  void testAdd(Tester t) {
    ListOfLists<Integer> lolAdd3 = new ListOfLists<Integer>();
    lolAdd3.addNewList();
    lolAdd3.add(0, 1);
    lolAdd3.add(0, 2);
    lolAdd3.add(0, 3);

    ListOfLists<Integer> lolAdd2 = new ListOfLists<Integer>();
    lolAdd2.addNewList();
    lolAdd2.addNewList();
    lolAdd2.add(0, 1);
    lolAdd2.add(1, 2);

    ListOfLists<Integer> lolAdd1 = new ListOfLists<Integer>();
    lolAdd1.addNewList();
    lolAdd3.add(0, 1);

    ListOfLists<Integer> lolAdd4 = new ListOfLists<Integer>();
    lolAdd4.addNewList();
    lolAdd4.addNewList();
    lolAdd4.addNewList();
    lolAdd4.add(0, 1);
    lolAdd4.add(1, 2);
    lolAdd4.add(0, 3);
    lolAdd4.add(1, 4);

    t.checkExpect(lolAdd3.get(0).size(), 4);
    t.checkExpect(lolAdd2.size(), 1);
    t.checkExpect(lolAdd1.get(0).size(), 0);
    t.checkExpect(lolAdd4.size(), 2);


  }


  // test the size method
  void testSize(Tester t) {

    ListOfLists<String> lolSize3 = new ListOfLists<String>();
    lolSize3.addNewList();
    lolSize3.addNewList();
    lolSize3.addNewList();
    lolSize3.add(0, "1");
    lolSize3.add(1, "2");


    ListOfLists<String> lolSize2 = new ListOfLists<String>();
    lolSize2.addNewList();
    lolSize2.add(0, "1");
    lolSize2.add(0, "2");

    ListOfLists<Boolean> lolSize1 = new ListOfLists<Boolean>();
    lolSize1.addNewList();
    lolSize1.add(0, true);

    ListOfLists<String> lolSize4 = new ListOfLists<String>();
    lolSize4.addNewList();
    lolSize4.add(0, "1");
    lolSize4.add(0, "2");
    lolSize4.add(0, "3");
    lolSize4.add(0, "4");

    t.checkExpect(lolSize3.size(), 1);
    t.checkExpect(lolSize2.size(), 2);
    t.checkExpect(lolSize1.size(), 1);
    t.checkExpect(lolSize4.size(), 4);

  }


}

