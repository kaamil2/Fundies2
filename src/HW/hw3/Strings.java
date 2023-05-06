package HW.hw3;

import tester.*;

// to represent a list of Strings
interface ILoString {
  // combine all Strings in this list into one
  String combine();

  ILoString sort();

  int length();

  boolean isSorted();

  ILoString insert(String that);

  boolean isSortedHelp(String that);

  ILoString interLeaveHelper(ILoString that);

  ILoString interleave(ILoString that);

  ILoString mergeHelper(String thisFirst, ILoString thisRest);

  ILoString merge(ILoString that);

  ILoString reverse();

  ILoString reverseHelper(String that);

  boolean isDoubledList();

  boolean isDoubledListHelper(String that);

  boolean isPalindromeList();

  int indexOf(String that);


}

// to represent an empty list of Strings
class MtLoString implements ILoString {
  MtLoString() {
  }


  // combine all Strings in this list into one
  public String combine() {
    return "";
  }

  // sort this list of Strings
  public ILoString sort() {
    return this;
  }

  // count the number of Strings in this list
  public int length() {
    return 0;
  }

  // is this list of Strings sorted?
  public boolean isSorted() {
    return true;
  }

  // insert a String into this list of Strings
  public ILoString insert(String that) {
    return new ConsLoString(that, this);
  }

  // is this list of Strings sorted?
  public boolean isSortedHelp(String that) {
    return true;
  }

  // interleave this list of Strings with that list of Strings
  public ILoString interLeaveHelper(ILoString that) {
    return that;
  }

  // interleave this list of Strings with that list of Strings
  public ILoString interleave(ILoString that) {
    return that;
  }


  // returns the rest of the list once this list is empty
  public ILoString mergeHelper(String thisFirst, ILoString thisRest) {
    return new ConsLoString(thisFirst, thisRest);
  }

  // merge this list of Strings with that list of Strings
  public ILoString merge(ILoString that) {
    return that;
  }

  // returns the list in reverse
  public ILoString reverse() {
    return this;
  }

  // returns the empty list as empty
  public ILoString reverseHelper(String that) {
    return new ConsLoString(that, new MtLoString());
  }

  // returns if the list is doubled
  public boolean isDoubledList() {
    return true;
  }

  // returns if the list is doubled will not be if the list is empty
  public boolean isDoubledListHelper(String that) {
    return false;
  }

  // returns if the list is a palindrome
  public boolean isPalindromeList() {
    return true;
  }

  public int indexOf(String that) {
    return -1;
  }


}

// to represent a nonempty list of Strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;


  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
     TEMPLATE
     FIELDS:
     ... this.first ...         -- String
     ... this.rest ...          -- ILoString

     METHODS
     ... this.combine() ...     -- String
     ... this.sort() ...        -- ILoString
     ... this.length() ...      -- int
     ... this.isSorted() ...    -- boolean
     ... this.insert(String) ... -- ILoString
     ... this.isSortedHelp(String) ... -- boolean
     ... this.interLeaveHelper(ILoString) ... -- ILoString
     ... this.interLeave(ILoString) ... -- ILoString
     ... this.mergeHelper(ILoString) ... -- ILoString
     ... this.merge(ILoString) ... -- ILoString
     ... this.reverse() ... -- ILoString
     ... this.reverseHelper(String) ... -- ILoString
     ... this.isDoubledList() ... -- boolean
      ... this.isDoubledListHelper(String) ... -- boolean
      ... this.isPalindrome() ... -- boolean




     METHODS FOR FIELDS
     ... this.first.concat(String) ...        -- String
     ... this.first.compareTo(String) ...     -- int
     ... this.rest.combine() ...              -- String
      ... this.rest.sort() ...                 -- ILoString
      ... this.rest.length() ...               -- int
      ... this.rest.isSorted() ...             -- boolean
      ... this.rest.insert(String) ...         -- ILoString
      ... this.rest.isSortedHelp(String) ...   -- boolean
      ... this.rest.interLeaveHelper(ILoString) ... -- ILoString
      ... this.rest.interLeave(ILoString) ... -- ILoString
      ... this.rest.mergeHelper(ILoString) ... -- ILoString
      ... this.rest.merge(ILoString) ... -- ILoString
      ... this.rest.reverse() ... -- ILoString
      ... this.rest.reverseHelper(String) ... -- ILoString
      ... this.rest.isDoubledList() ... -- boolean
      ... this.rest.isDoubledListHelper(String) ... -- boolean
      ... this.rest.isPalindrome() ... -- boolean



    */

  // combine all Strings in this list into one
  public String combine() {
    return this.first.concat(this.rest.combine());
  }

  // gets the length of the list
  public int length() {
    return 1 + this.rest.length();
  }

  // sorts this list of Strings
  public ILoString sort() {
    return this.rest.sort().insert(this.first);
  }

  // inserts the string into the list
  public ILoString insert(String that) {
    if (this.first.toLowerCase().compareTo(that.toLowerCase()) < 0) {
      return new ConsLoString(this.first, this.rest.insert(that));
    } else if (this.first.toLowerCase().compareTo(that.toLowerCase()) > 0) {
      return new ConsLoString(that, this.rest.insert(this.first));
    } else {
      return new ConsLoString(this.first, this.rest.insert(that));
    }
  }


  // sees if the each string in the list is sorted
  public boolean isSortedHelp(String that) {
    return this.first.toLowerCase().compareTo(that.toLowerCase()) >= 0
        && this.rest.isSortedHelp(this.first.toLowerCase());
  }

  // sees if the list is sorted
  public boolean isSorted() {
    return this.rest.isSortedHelp(this.first.toLowerCase());
  }


  // interleaves two lists of strings
  public ILoString interLeaveHelper(ILoString that) {
    return new ConsLoString(this.first, that.interleave(this.rest));
  }

  // returns a list of strings that are interleaved
  public ILoString interleave(ILoString that) {

    return this.interLeaveHelper(that);

  }

  // returns a merged list of strings that are sorted
  public ILoString merge(ILoString that) {
    return that.mergeHelper(this.first, this.rest);
  }

  // merges two sorted list of string
  public ILoString mergeHelper(String thisFirst, ILoString thisRest) {
    if (this.first.toLowerCase().compareTo(thisFirst.toLowerCase()) > 0) {
      return new ConsLoString(thisFirst, thisRest.mergeHelper(this.first, this.rest));
    } else {
      return new ConsLoString(this.first, this.rest.mergeHelper(thisFirst, thisRest));
    }

  }


  // returns list in reverse order
  public ILoString reverse() {
    return this.rest.reverse().reverseHelper(this.first);
  }

  // reverese the given list
  public ILoString reverseHelper(String that) {
    return new ConsLoString(this.first, this.rest.reverseHelper(that));
  }

  // checks if each pairing in the list is equal
  public boolean isDoubledListHelper(String that) {
    return this.first.equals(that) && this.rest.isDoubledList();
  }

  // returns if the list is a doubled list
  public boolean isDoubledList() {
    return this.rest.isDoubledListHelper(this.first);
  }


  // returns if the list is a palindrome
  public boolean isPalindromeList() {
    return (this.interleave(this.reverse())).isDoubledList();
  }

  public int indexOf(String that) {
    if (this.first.equals(that)) {
      return 0;
    } else {
      return 1 + this.rest.indexOf(that);
    }
  }


}

// to represent examples for lists of strings
class ExamplesStrings {

  ILoString mary = new ConsLoString("Mary ",
      new ConsLoString("had ",
          new ConsLoString("a ",
              new ConsLoString("little ",
                  new ConsLoString("lamb.", new MtLoString())))));


  ILoString maryReverse = new ConsLoString("lamb.",
      new ConsLoString("little ",
          new ConsLoString("a ",
              new ConsLoString("had ",
                  new ConsLoString("Mary ", new MtLoString())))));

  ILoString LebronTheGoat = new ConsLoString("Lebron ",
      new ConsLoString("is ",
          new ConsLoString("the ",
              new ConsLoString("only ",
                  new ConsLoString("Goat.", new MtLoString())))));

  ILoString MichaelTheGoat = new ConsLoString("Michael ",
      new ConsLoString("is ",
          new ConsLoString("the ",
              new ConsLoString("only ",
                  new ConsLoString("Goat.", new MtLoString())))));


  ILoString alphabet = new ConsLoString("A",
      new ConsLoString("C",
          new ConsLoString("b",
              new ConsLoString("Z",
                  new ConsLoString("d", new MtLoString())))));

  ILoString alphabetSorted = new ConsLoString("A",
      new ConsLoString("b",
          new ConsLoString("C",
              new ConsLoString("d",
                  new ConsLoString("Z", new MtLoString())))));

  ILoString alphabet2 = new ConsLoString("A",
      new ConsLoString("b",
          new ConsLoString("C", new MtLoString())));

  ILoString alphabetSorted2 = new ConsLoString("A",
      new ConsLoString("b",
          new ConsLoString("C", new MtLoString())));

  ILoString alphabetSorted3 = new ConsLoString("D",
      new ConsLoString("E",
          new ConsLoString("f", new MtLoString())));


  ILoString sortedSentence = new ConsLoString("actual",
      new ConsLoString("bob",
          new ConsLoString("Cool", new MtLoString())));

  ILoString mergedList = new ConsLoString("A", new ConsLoString("actual",
      new ConsLoString("b", new ConsLoString("bob",
          new ConsLoString("C", new ConsLoString("Cool",
              new MtLoString()))))));

  ILoString palindromeList = new ConsLoString("A", new ConsLoString("B",
      new ConsLoString("A", new MtLoString())));


  // test the method combine for the lists of Strings
  boolean testCombine(Tester t) {
    return
        t.checkExpect(this.mary.combine(), "Mary had a little lamb.");
  }

  // test the method sort for the lists of strings
  boolean testSort(Tester t) {
    return
        t.checkExpect(this.alphabet.sort(),
            new ConsLoString("A", new ConsLoString("b", new ConsLoString("C",
                new ConsLoString("d", new ConsLoString("Z", new MtLoString()))))))
            && t.checkExpect(this.alphabet2.sort(),alphabetSorted2);
  }

  // test the method isSorted for the lists of strings
  boolean testIsSorted(Tester t) {
    return
        t.checkExpect(this.alphabet.isSorted(), false)
            && t.checkExpect(this.alphabetSorted2.isSorted(), true)
        && t.checkExpect(this.sortedSentence.isSorted(),true);
  }

  // test the method merge for the lists of strings
  boolean testMerge(Tester t) {
    return
        t.checkExpect(this.alphabetSorted2.merge(alphabetSorted),
            new ConsLoString("A", new ConsLoString("A", new ConsLoString("b",
                new ConsLoString("b", new ConsLoString("C", new ConsLoString("C",
                    new ConsLoString("d", new ConsLoString("Z",
                        new MtLoString())))))))))
            && t.checkExpect(this.alphabetSorted2.merge(alphabetSorted3),
                new ConsLoString("A", new ConsLoString("b",
                    new ConsLoString("C", alphabetSorted3))));
  }

  // test the method reverse for the lists of strings
  boolean testReverse(Tester t) {
    return
        t.checkExpect(this.mary.reverse(), maryReverse)
            && t.checkExpect(this.alphabetSorted2.reverse(), new ConsLoString("C",
            new ConsLoString("b", new ConsLoString("A", new MtLoString()))));
  }

  // test the method isDoubledList for the lists of strings
  boolean isDoubledList(Tester t) {
    return
        t.checkExpect(this.mary.isDoubledList(), false) &&
            t.checkExpect(this.palindromeList.isDoubledList(), true);
  }

  // test the method isPalindromeList for the lists of strings
  boolean testIsPalindrome(Tester t) {
    return
        t.checkExpect(this.mary.isPalindromeList(), false) &&
            t.checkExpect(this.maryReverse.isPalindromeList(), false)
            && t.checkExpect(this.palindromeList.isPalindromeList(), true);
  }

  // test the method interleave for the lists of strings
  boolean testInterLeave(Tester t) {
    return
        t.checkExpect(this.alphabetSorted2.interleave(alphabetSorted),
            new ConsLoString("A", new ConsLoString("A", new ConsLoString("b",
                new ConsLoString("b",
                    new ConsLoString("C", new ConsLoString("C", new ConsLoString("d",
                        new ConsLoString("Z", new MtLoString())))))))))
            && t.checkExpect(this.alphabetSorted2.interleave(alphabetSorted3),
                new ConsLoString("A", new ConsLoString("D", new ConsLoString("b",
                    new ConsLoString("E", new ConsLoString("C", new ConsLoString("f",
                        new MtLoString())))))));
  }


}




