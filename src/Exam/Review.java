package Exam;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;


class ArrayListIterator<T> implements Iterator<T> {
  ArrayList<ArrayList<T>> array;
  int counter;

  ArrayListIterator(ArrayList<ArrayList<T>> array){
    this.array = new ArrayList<ArrayList<T>>(array);
    counter = 0;
  }


  public boolean hasNext(){
    return counter < array.size();
  }

  public T next(){

    if(!this.hasNext()){
      throw new NoSuchElementException("");
    }

   T current = array.get(counter).get(counter);

   counter++;

   return current;
  }


}

class ExamplesIt{

  ArrayList<Integer> arrayList0;
  ArrayList<Integer> arrayList1;
  ArrayList<Integer> arrayList2;

  ArrayList<ArrayList<Integer>> arrays;

  ArrayListIterator<ArrayList<Integer>> arrayList;

  void initData() {
    this.arrayList0 = new ArrayList<Integer>(Arrays.asList(1, 0, 0));
    this.arrayList1 = new ArrayList<Integer>(Arrays.asList(0, 1, 0));
    this.arrayList2 = new ArrayList<Integer>(Arrays.asList(0, 0, 1));

    this.arrays = new ArrayList<ArrayList<Integer>>(Arrays.asList(arrayList0, arrayList1, arrayList2));

    this.arrayList = new ArrayListIterator(arrays);

  }

  void testHasNext(Tester t){
    this.initData();

    t.checkExpect(this.arrayList.hasNext(), true);
    t.checkExpect(this.arrayList.next(), 1);
    t.checkExpect(this.arrayList.hasNext(), true);
    t.checkExpect(this.arrayList.next(), 1);
    t.checkExpect(this.arrayList.hasNext(), true);
    t.checkExpect(this.arrayList.next(), 1);
    t.checkExpect(this.arrayList.hasNext(), false);
  }

}



/*

// represents a roster of students for a sports team
class Roster {
  // names of the students
  ArrayList<String> names;

  // create a roster from a given list of names
  Roster(ArrayList<String> names) {
    this.names = new ArrayList<String>(names);
  }

  // adds a name to this Roster's
  // list of names
  void addName(String name) {
    this.names.add(name);
  }

  // returns the list of students in
  // this Roster
  ArrayList<String> getNames() {
    return new ArrayList<String>(this.names);
  }
}

// examples class!
class ExamplesRoster {
  ArrayList<String> defaultRoster;
  Roster volleyball;
  Roster tennis;
  Roster swimming;
  Roster figureSkating;

  void initData() {
    this.defaultRoster = new ArrayList<String>(Arrays.asList("Edward"));

    this.volleyball = new Roster(this.defaultRoster);
    this.tennis = new Roster(this.defaultRoster);
    this.swimming = new Roster(this.defaultRoster);
    this.figureSkating = new Roster(this.defaultRoster);

  }
  // tests the addName method
  void testAddName(Tester t) {
    this.initData();

    t.checkExpect(this.volleyball.names.size(), 1);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

    this.volleyball.addName("Karen");

    t.checkExpect(this.volleyball.names.size(), 2);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the two tests below fail?
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

  }

  // tests the getNames method
  void testGetNames(Tester t) {
    this.initData();

    t.checkExpect(this.figureSkating.getNames().size(), 1);
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));

    ArrayList<String> fgList = this.figureSkating.getNames();
    fgList.add("Karen");

    t.checkExpect(fgList.size(), 2);
    t.checkExpect(fgList, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the three tests below fail?
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
  }
}
*/


/*
// effects statements should happen when mutation are happening
// has next does not typically mutate however next does


*//* WHAT HAS BEEN  DONE HERE IN ROSTER IN ORDER TO RETURN EACH INDIVIDUALIZED LIST WE TURN :
THIS.NAMES AND RETURN A LIST OF NAMES TO BECOME A WHOLE NEW ARRAY LIST
*//*

// represents a roster of students for a sports team
class Roster {
  // names of the students
  ArrayList<String> names;

  // create a roster from a given list of names
  Roster(ArrayList<String> names) {
    this.names = new ArrayList<String>(names);
  }

  // adds a name to this Roster's
  // list of names
  void addName(String name) {
    this.names.add(name);
  }

  // returns the list of students in
  // this Roster
  ArrayList<String> getNames() {
    return new ArrayList<String>(this.names);
  }
}

// examples class!
class ExamplesRoster {
  ArrayList<String> defaultRoster;
  Roster volleyball;
  Roster tennis;
  Roster swimming;
  Roster figureSkating;

  void initData() {
    this.defaultRoster = new ArrayList<String>(Arrays.asList("Edward"));

    this.volleyball = new Roster(this.defaultRoster);
    this.tennis = new Roster(this.defaultRoster);
    this.swimming = new Roster(this.defaultRoster);
    this.figureSkating = new Roster(this.defaultRoster);

  }
  // tests the addName method
  void testAddName(Tester t) {
    this.initData();

    t.checkExpect(this.volleyball.names.size(), 1);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

    this.volleyball.addName("Karen"); // essentially changing default roster

    t.checkExpect(this.volleyball.names.size(), 2);
    t.checkExpect(this.volleyball.names, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the two tests below fail?
    t.checkExpect(this.tennis.names.size(), 1);
    t.checkExpect(this.tennis.names, new ArrayList<String>(Arrays.asList("Edward")));

  }

  // tests the getNames method
  void testGetNames(Tester t) {
    this.initData();

    t.checkExpect(this.figureSkating.getNames().size(), 1);
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));

    ArrayList<String> fgList = this.figureSkating.getNames();
    fgList.add("Karen");

    t.checkExpect(fgList.size(), 2);
    t.checkExpect(fgList, new ArrayList<String>(Arrays.asList("Edward", "Karen")));

    // why do the three tests below fail?
    t.checkExpect(this.figureSkating.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
    t.checkExpect(this.swimming.getNames().size(), 1);
    t.checkExpect(this.swimming.getNames(), new ArrayList<String>(Arrays.asList("Edward")));
  }
}

*//*
WE(RODRIGO AND KAAMIL) ARE GOING TO GET A 100 ON OUR 2ND FUNDIES 2 EXAM
ITS JUST A FACT
 *//*








class IteratorArray<T> implements Iterator<T> {

  ArrayList<ArrayList<T>> array;
  int counter;

  IteratorArray(ArrayList<ArrayList<T>> array) {
    array = new ArrayList<ArrayList<T>>();
    counter = 0;

  }


  public boolean hasNext() {

    return array.size() > counter;


  }

  public T next() {

    if (!this.hasNext()) {
      throw new NoSuchElementException("no left elements");
    }

    T result = this.array.get(counter).get(counter);

    counter++;

    return result;

  }

}*/

