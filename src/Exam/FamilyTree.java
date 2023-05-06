package Exam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import tester.Tester;

// to represent a person with a name
// and their list of children (for a family tree)
class Person implements Iterable<Person> {
  String name;
  ArrayList<Person> listOfChildren;

  // standard constructor
  Person(String name, ArrayList<Person> children) {
    this.name = name;
    this.listOfChildren = children;
  }

  // convenience constructor
  Person(String name) {
    this.name = name;
    this.listOfChildren = new ArrayList<Person>();
  }

  public Iterator<Person> iterator(){
    return new FamilyTreeIterator(this);
  }

}


class FamilyTreeIterator implements Iterator<Person>{

//  Person curPerson;
  ArrayList<Person> peopleInFamilyTree;
  int curIndex;

  FamilyTreeIterator(Person parent){
    this.peopleInFamilyTree = new ArrayList<Person>();
    this.peopleInFamilyTree.add(parent);
    curIndex = 0;

  }



  @Override
  public boolean hasNext(){
    return curIndex < peopleInFamilyTree.size();
  }

  @Override
  public Person next() {

    if(!this.hasNext()) {
      throw new NoSuchElementException("No more people in this family tree");
    }

    Person result = this.peopleInFamilyTree.get(curIndex);

    int preIndex = curIndex;
    curIndex ++;

    //add the childs to list of people

    for(Person p : result.listOfChildren) {
      if (!this.peopleInFamilyTree.contains(p)){
        this.peopleInFamilyTree.add(p);
      }

    }

    return result;
  }
}

//to represent examples of Person
class ExamplesPerson {

  ExamplesPerson() {
  }

  // simple example
  Person c;
  Person b;
  Person a;

  // complex example
  Person len;
  Person kim;
  Person jan;
  Person hank;
  Person gabi;
  Person fay;
  Person ed;
  Person dan;
  Person cole;
  Person bob;
  Person ann;

  // initializes the data and the family trees
  void initData() {
    // simple example

    /*
     *      A
     *      |
     *     (B C)
     *
     */

    this.c = new Person("C");
    this.b = new Person("B");
    this.a = new Person("A", new ArrayList<Person>(Arrays.asList(this.b, this.c)));


    // complex example

    /*
     *            Ann
     *            |
     *           (Bob         Cole         Dan)
     *            |           |            |
     *   (Ed Fay Gabi Hank)  (Jan Kim)     ()
     *       |
     *      (Len)
     *
     *
     * Ann's children: Bob, Cole, and Dan
     * Bob's children: Ed, Fay, Gabi, and Hank
     * Cole's children: Jan and Kim
     * Fay's children: Len
     *
     */

    this.len = new Person("Len");
    this.kim = new Person("Kim");
    this.jan = new Person("Jan");
    this.hank = new Person("Hank");
    this.gabi = new Person("Gabi");
    ArrayList<Person> fayChildren = new ArrayList<Person>(
        Arrays.asList(this.len));
    this.fay = new Person("Fay", fayChildren);
    this.ed = new Person("Ed");
    this.dan = new Person("Dan");
    ArrayList<Person> coleChildren = new ArrayList<Person>(
        Arrays.asList(this.jan, this.kim));
    this.cole = new Person("Cole", coleChildren);
    ArrayList<Person> bobChildren = new ArrayList<Person>(
        Arrays.asList(this.ed, this.fay, this.gabi, this.hank));
    this.bob = new Person("Bob", bobChildren);
    ArrayList<Person> annChildren = new ArrayList<Person>(
        Arrays.asList(this.bob, this.cole, this.dan));
    this.ann = new Person("Ann", annChildren);
  }

  void testForEachLoopForSimpleExample(Tester t) {
    this.initData();

    ArrayList<Person> aPeople = new ArrayList<Person>(
        Arrays.asList(this.a, this.b, this.c));
    ArrayList<Person> aTest = new ArrayList<Person>();

    for (Person p : this.a) {
      aTest.add(p);
      // System.out.println(p.name);
    }

    // order should be A -> B -> C
    t.checkExpect(aTest, aPeople);
  }

  void testForEachLoopForComplexExample(Tester t) {
    this.initData();

    ArrayList<Person> annPeople = new ArrayList<Person>(
        Arrays.asList(this.ann, this.bob, this.cole,
            this.dan, this.ed, this.fay, this.gabi, this.hank,
            this.jan, this.kim, this.len));
    ArrayList<Person> annTest = new ArrayList<Person>();

    for (Person p : this.ann) {
      annTest.add(p);
      // System.out.println(p.name);
    }

    // order should be Ann -> Bob -> Cole -> Dan -> Ed -> Fay
    //              -> Gabi -> Hank -> Jan -> Kim -> Len
    t.checkExpect(annTest, annPeople);
  }

  void testHasNextAndNextForComplexExample(Tester t) {
    this.initData();

    Iterator<Person> famIter = this.ann.iterator();

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.ann);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.bob);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.cole);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.dan);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.ed);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.fay);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.gabi);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.hank);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.jan);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.kim);

    t.checkExpect(famIter.hasNext(), true);
    t.checkExpect(famIter.next(), this.len);

    t.checkExpect(famIter.hasNext(), false);
    t.checkException(new NoSuchElementException("No more people in this family tree"),
        famIter, "next");
  }
}