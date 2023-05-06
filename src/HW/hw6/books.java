package HW.hw6;

import tester.Tester;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;


abstract class ABST<T> {

  Comparator<T> order;

  ABST(Comparator<T> order) {
    this.order = order;
  }

  /*Template
   * Fields:
   * this.order -- Comparator<T>
   * Methods:
   * insert -- T -> ABST<T>
   * present -- T -> boolean
   * getLeftmost -- void -> T
   * getLeftHelper -- T -> T
   * getRightMost -- void -> T
   * getRightHelper -- T -> T
   * getRight -- void -> ABST<T>
   * getLeft -- void -> ABST<T>
   * sameTree -- ABST<T> -> boolean
   * sameTreeHelper -- Node<T> -> boolean
   * sameTreeHelper -- T, ABST<T>, ABST<T> -> boolean
   *  sameData -- ABST<T> -> boolean
   * sameDataHelper -- Node<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T>, ABST<T> -> boolean
   * Methods for fields:
   * this.order.compare -- T, T -> int
   * this.order.equals -- Object -> boolean
   * this.order.hashCode -- void -> int
   * this.order.toString -- void -> String
   * this.order.reversed -- void -> Comparator<T>
   */

  // insert the given item into this tree
  ABST<T> insert(T t) {
    return this;
  }


  // is the given item present in this tree?
  boolean present(T t) {
    return false;
  }


  // get the leftmost item in this tree
  T getLeftmost() {
    throw new RuntimeException("No leftmost item of an empty tree");
  }


  // helping find if the leftmost item in is equal to that
  T getLeftHelper(T t) {
    return t;
  }

  // get the rightmost item in this tree
  T getRightMost() {
    throw new RuntimeException("No rightmost of an empty tree");
  }

  // helping find if the rightmost item in is equal to that
  T getRightHelper(T t) {
    return t;
  }

  // get the right subtree of this tree
  ABST<T> getRight() {
    throw new RuntimeException("No right of an empty tree");
  }

  // get the left subtree of this tree
  ABST<T> getLeft() {
    throw new RuntimeException("No left of an empty tree");
  }


  // is this tree the same as that tree?
  boolean sameTree(ABST<T> that) {
    return true;
  }

  // is this tree the same as that tree?
  boolean sameTreeHelper(Node<T> that) {
    return false;
  }


  // is this tree the same as that tree?
  boolean sameData(ABST<T> that) {
    return true;
  }

  // is this tree the same as that tree?
  boolean sameDataHelper(ABST<T> that) {
    return true;
  }

  // is this tree the same as that tree?
  IList<T> buildList() {
    return new MtList<T>();
  }



}

// represents an empty tree
class Leaf<T> extends ABST<T> {
  Leaf(Comparator<T> order) {
    super(order);
  }

  /*Template
   * Fields:
   * this.order -- Comparator<T>
   * Methods:
   * insert -- T -> ABST<T>
   * present -- T -> boolean
   * getLeftmost -- void -> T
   * getLeftHelper -- T -> T
   * getRightMost -- void -> T
   * getRightHelper -- T -> T
   * getRight -- void -> ABST<T>
   * getLeft -- void -> ABST<T>
   * sameTree -- ABST<T> -> boolean
   * sameTreeHelper -- Node<T> -> boolean
   * sameTreeHelper -- T, ABST<T>, ABST<T> -> boolean
   *  sameData -- ABST<T> -> boolean
   * sameDataHelper -- Node<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T>, ABST<T> -> boolean
   * Methods for fields:
   * this.order.compare -- T, T -> int
   * this.order.equals -- Object -> boolean
   * this.order.hashCode -- void -> int
   * this.order.toString -- void -> String
   * this.order.reversed -- void -> Comparator<T>
   */

  // insert the given item into this tree
  @Override
  ABST<T> insert(T t) {
    return new Node<T>(this.order, t, new Leaf<T>(this.order), new Leaf<T>(this.order));
  }


}

// represents a non-empty tree
class Node<T> extends ABST<T> {
  T data;
  ABST<T> left;
  ABST<T> right;

  Node(Comparator<T> order, T data, ABST<T> left, ABST<T> right) {
    super(order);
    this.data = data;
    this.left = left;
    this.right = right;
  }

  /*Template
   * Fields:
   * this.order -- Comparator<T>
   * this.data -- T
   * this.left -- ABST<T>
   * this.right -- ABST<T>
   * Methods:
   * insert -- T -> ABST<T>
   * present -- T -> boolean
   * getLeftmost -- void -> T
   * getLeftHelper -- T -> T
   * getRightMost -- void -> T
   * getRightHelper -- T -> T
   * getRight -- void -> ABST<T>
   * getLeft -- void -> ABST<T>
   * sameTree -- ABST<T> -> boolean
   * sameTreeHelper -- Node<T> -> boolean
   * sameTreeHelper -- T, ABST<T>, ABST<T> -> boolean
   *  sameData -- ABST<T> -> boolean
   * sameDataHelper -- Node<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T> -> boolean
   * sameDataHelper -- T, ABST<T>, ABST<T>, ABST<T> -> boolean
   * Methods for fields:
   * this.order.compare -- T, T -> int
   * this.order.equals -- Object -> boolean
   * this.order.hashCode -- void -> int
   * this.order.toString -- void -> String
   * this.order.reversed -- void -> Comparator<T>
   */

  @Override
  ABST<T> insert(T t) {
    /*Parameters:
    t -- T
    Returns:
    ABST<T>
    Fields of Paramters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    insert -- T -> ABST<T>
    present -- T -> boolean
    getLeftmost -- T -> T
    getLeftHelper -- T -> T
    getRightMost -- T -> T
    getRightHelper -- T -> T
     */


    if (this.order.compare(t, this.data) < 0) {
      this.left = this.left.insert(t);
    } else {
      this.right = this.right.insert(t);
    }
    return this;
  }

  @Override
  boolean present(T t) {
    /*Parameters:
    t -- T
    Returns:
    boolean
    Fields of Paramters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    present -- T -> boolean
    getLeftmost -- T -> T
    getLeftHelper -- T -> T
    getRightMost -- T -> T
    getRightHelper -- T -> T

     */
    if (this.order.compare(t, this.data) == 0) {
      return true;
    } else if (this.order.compare(t, this.data) < 0) {
      return this.left.present(t);
    } else {
      return this.right.present(t);
    }
  }

  //returns the leftmost item of a tree
  @Override
  T getLeftmost() {

    return this.left.getLeftHelper(this.data);

  }

  @Override
  T getLeftHelper(T t) {
    /*Parameters:
    t -- T
    Returns:
    T
    Fields of Paramters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    getLeftHelper -- T -> T
    getRightHelper -- T -> T
    getLeftmost -- T -> T
    getRightMost -- T -> T
    present -- T -> boolean
    insert -- T -> ABST<T>
    sameTree -- ABST<T> -> boolean
    sameTreeHelper -- ABST<T> -> boolean
    sameData -- ABST<T> -> boolean
    sameDataHelper -- ABST<T> -> boolean
    buildList -- ABST<T> -> IList<T>
    sameTreeHelper -- ABST<T> -> boolean

     */
    return this.left.getLeftHelper(this.data);


  }

  //returns the whole right of a tree no left sectors
  public ABST<T> getRight() {
    if (this.order.compare(this.getLeftmost(), this.data) == 0) {
      return this.right;
    } else {
      return new Node<T>(this.order, this.data, this.left.getRight(), this.right);
    }

  }

  //returns the whole left of a tree no right sectors
  public ABST<T> getLeft() {
    if (this.order.compare(this.getRightMost(), this.data) == 0) {
      return this.left;
    } else {
      return new Node<T>(this.order, this.data, this.left, this.right.getLeft());
    }

  }

  @Override
  public T getRightMost() {

    return this.right.getRightHelper(this.data);

  }

  @Override
  T getRightHelper(T t) {

    return this.right.getRightHelper(this.data);

  }


  @Override
  public boolean sameTree(ABST<T> that) {
    /* Fields of Parameters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    sameTree -- ABST<T> -> boolean
    sameTreeHelper -- ABST<T> -> boolean
    sameData -- ABST<T> -> boolean
    sameDataHelper -- ABST<T> -> boolean
    buildList -- ABST<T> -> IList<T>
    sameTreeHelper -- ABST<T> -> boolean
    Methods for Fields:
    this.order.compare -- T, T -> int
    this.order.equals -- Object -> boolean
    this.order.hashCode -- void -> int
    this.order.toString -- void -> String
    this.order.reversed -- void -> Comparator<T>
    Methods for Fields of Parameters:
    that.order.compare -- T, T -> int
    that.order.equals -- Object -> boolean
    that.order.toString -- void -> String
    that.order.reversed -- void -> Comparator<T>

     */

    return that.sameTreeHelper(this);
  }


  @Override
  public boolean sameTreeHelper(Node<T> that) {
    return this.order.compare(this.data, that.data) == 0
        && that.left.sameTree(that.left)
        && this.right.sameTree(that.right);
  }

  /*
  @Override
  public boolean sameTree(ABST<T> that) {

    return that.sameTreeHelper(this.order, this.data, this.left, this.right)
        && this.right.sameTree(that.getRight());
  }



  @Override
  public boolean sameTreeHelper(ABST<T> that) {
    return this.order.compare(this.getLeftmost(), that.getLeftmost()) == 0
        && this.order.compare(this.getRightMost(), that.getRightMost()) == 0
        && that.getRight().sameData(this.getRight());
  }






  @Override
  public boolean sameTreeHelper(Comparator<T> thatOrder, T thatData,
                                ABST<T> thatLeft, ABST<T> thatRight) {
    return this.order.compare(this.data, thatData) == 0
        && thatOrder.compare(this.data, thatData) == 0
        && this.left.sameTree(thatLeft)
        && this.right.sameTree(thatRight);
  }

 */

  /*
  boolean sameData(ABST<T> that) {
    return that.sameDataHelper(this.data, this.left, this.right);
  }

  boolean sameDataHelper(T thatData, ABST<T> thatLeft, ABST<T> thatRight) {
    return this.order.compare(this.data, thatData) == 0
        || this.left.sameData(thatLeft)
        || this.right.sameData(thatRight);

  }
  */

  //sameData needs to take this.data and see if it is equal to any of the data in the tree
  //if it is equal to any of the data in the tree,then check if this.left's this.data is in the tree
  // and if this.right's this.data is in the tree repeat till end recursively then return true


  @Override
  public boolean sameData(ABST<T> that) {
    /* Fields of Parameters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    sameTree -- ABST<T> -> boolean
    sameTreeHelper -- ABST<T> -> boolean
    sameData -- ABST<T> -> boolean
    sameDataHelper -- ABST<T> -> boolean
    buildList -- ABST<T> -> IList<T>
    sameTreeHelper -- ABST<T> -> boolean
    Methods for Fields:
    this.order.compare -- T, T -> int
    this.order.equals -- Object -> boolean
    this.order.hashCode -- void -> int
    this.order.toString -- void -> String
    this.order.reversed -- void -> Comparator<T>
    Methods for Fields of Parameters:
    that.order.compare -- T, T -> int
    that.order.equals -- Object -> boolean
    that.order.toString -- void -> String
    that.order.reversed -- void -> Comparator<T>

     */
    return that.sameDataHelper(this);
  }

  @Override
  public boolean sameDataHelper(ABST<T> that) {
    /* Fields of Parameters:
    data -- T
    left -- ABST<T>
    right -- ABST<T>
    Methods of Parameters:
    sameTree -- ABST<T> -> boolean
    sameTreeHelper -- ABST<T> -> boolean
    sameData -- ABST<T> -> boolean
    sameDataHelper -- ABST<T> -> boolean
    buildList -- ABST<T> -> IList<T>
    sameTreeHelper -- ABST<T> -> boolean
    Methods for Fields:
    this.order.compare -- T, T -> int
    this.order.equals -- Object -> boolean
    this.order.hashCode -- void -> int
    this.order.toString -- void -> String
    this.order.reversed -- void -> Comparator<T>
    Methods for Fields of Parameters:
    that.order.compare -- T, T -> int
    that.order.equals -- Object -> boolean
    that.order.toString -- void -> String
    that.order.reversed -- void -> Comparator<T>
     */
    return this.order.compare(this.getLeftmost(), that.getLeftmost()) == 0
        && that.getRight().sameData(this.getRight());
  }




  @Override
  public IList<T> buildList() {
    return new ConsList<T>(this.getLeftmost(), this.getRight().buildList());
  }




  /*
        this.left.sameData(thatLeft)
        && this.right.sameData(thatRight)
        && (this.order.compare(this.data, thatData) == 0
        || this.sameData(thatLeft)
        || this.sameData(thatRight)
        || thatLeft.sameData(this)
        || thatRight.sameData(this));

        */





  /*
  @Override
  ABST<T> getLeftmost() {
    if (getLeftMostHelper(this.left) == true) {
      return this;
    }
    else {
      return this.left.getLeftmost();
    }
  }

  boolean getLeftMostHelper(ABST<T> t) {
      return Objects.equals(this.left, new Leaf<T>(this.order));
  }

  /*

  boolean getLeftMostHelper(T t) {
if (this.order.compare(t, this.data) == 0) {
      return true;
    }
    else if (this.order.compare(t, this.data) < 0) {
      return this.left.getLeftMostHelper(t);
    }
    else {
      return this.right.getLeftMostHelper(t);
    }


  }

  /*@Override
  ABST<T> getLeftmost() {
    if (this.order.compare(t, this.data) == 0) {
      return this.left;
    }
    else if (this.order.compare(t, this.data) < 0) {
      return this.left.getLeftmost(t);
    }
    else {
      return this.right.getLeftmost(t);
    }
  }

   */


}

class Book {
  String title;
  String author;
  int price;

  Book(String title, String author, int price) {
    this.title = title;
    this.author = author;
    this.price = price;
  }
  /* TEMPLATE
   * FIELDS
   * ... this.title ... -- String
   * ... this.author ... -- String
   * ... this.price ... -- int
   *
   * METHODS
   * ... this.sameBook(Book that) ... -- boolean
   * ... this.sameBookHelper(Book that) ... -- boolean
   * ... this.sameBookByTitle(Book that) ... -- boolean
   * ... this.sameBookByAuthor(Book that) ... -- boolean
   * ... this.sameBookByPrice(Book that) ... -- boolean
   * ... this.sameBookByTitleHelper(Book that) ... -- boolean
   * ... this.sameBookByAuthorHelper(Book that) ... -- boolean
   * ... this.sameBookByPriceHelper(Book that) ... -- boolean
   * ... this.sameBookByTitleAuthor(Book that) ... -- boolean
   * ... this.sameBookByTitleAuthorHelper(Book that) ... -- boolean
   * ... this.sameBookByTitlePrice(Book that) ... -- boolean
   * ... this.sameBookByTitlePriceHelper(Book that) ... -- boolean
   * ... this.sameBookByAuthorPrice(Book that) ... -- boolean
   * ... this.sameBookByAuthorPriceHelper(Book that) ... -- boolean
   * ... this.sameBookByTitleAuthorPrice(Book that) ... -- boolean
   * ... this.sameBookByTitleAuthorPriceHelper(Book that) ... -- boolean
   * Methods for Fields
   * ... this.title.sameString(String that) ... -- boolean
   * ... this.author.sameString(String that) ... -- boolean
   * ... this.price.sameInt(int that) ... -- boolean
   * Methods for Parameters
   * ... title.sameString(String that) ... -- boolean
   * ... author.sameString(String that) ... -- boolean
   * ... price.sameInt(int that) ... -- boolean
   */




}



class BooksByTitle implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.title.compareTo(t2.title);
  }
}

class BooksByAuthor implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.author.compareTo(t2.author);
  }

}

class BooksByPrice implements Comparator<Book> {
  public int compare(Book t1, Book t2) {
    return t1.price - t2.price;
  }

}


interface ILoString {

}

interface IList<T> {
  IList<T> filter(Predicate<T> pred);

  <U> IList<U> map(Function<T, U> converter);

  <U> U fold(BiFunction<T, U, U> converter, U initial);


}

class MtList<T> implements IList<T> {

  MtList() {
  }

  @Override
  public IList<T> filter(Predicate<T> pred) {

    return new MtList<T>();
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {

    return new MtList<U>();
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {

    return initial;
  }

}

class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;

  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public IList<T> filter(Predicate<T> pred) {

    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    } else {
      return this.rest.filter(pred);
    }
  }

  @Override
  public <U> IList<U> map(Function<T, U> converter) {

    return new ConsList<U>(converter.apply(this.first), this.rest.map(converter));
  }

  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {

    return converter.apply(this.first, this.rest.fold(converter, initial));
  }


}


class ExamplesLists {


  Book apple = new Book("apple", "Johnny AppleSeed", 1);
  Book catVsDog = new Book("catVsDog", "Shrek", 3);
  Book elephant = new Book("elephant", "dumbo", 5);
  Book fish = new Book("fish", "nemo", 6);
  Book gatorade = new Book("gatorade", "Lebron", 7);
  Book hobbit = new Book("hobbit", "JK Rowling", 8);
  Book iceCream = new Book("iceCream", "Ben and Jerry", 9);

  ABST<Book> bookTitle = new Leaf<Book>(new BooksByTitle());
  ABST<Book> bookAuthor = new Leaf<Book>(new BooksByAuthor());
  ABST<Book> bookPrice = new Leaf<Book>(new BooksByPrice());


  ABST<Book> node1 = new Node<Book>(new BooksByTitle(), this.apple, this.bookTitle, this.bookTitle);
  ABST<Book> node2 = new Node<Book>(new BooksByAuthor(),
      this.catVsDog, this.bookAuthor, this.bookAuthor);
  ABST<Book> node3 = new Node<Book>(new BooksByPrice(),
      this.elephant, this.bookPrice, this.bookPrice);


  ABST<Book> p1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> p2 = new Node<Book>(new BooksByPrice(), this.catVsDog, this.p1, this.bookPrice);
  ABST<Book> p3 = new Node<Book>(new BooksByPrice(), this.fish, this.bookPrice, this.bookPrice);

  ABST<Book> p4 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.bookPrice);
  ABST<Book> p5 = new Node<Book>(new BooksByPrice(), this.gatorade, this.p3, this.p4);

  ABST<Book> priceTree = new Node<Book>(new BooksByPrice(), this.elephant, this.p2, this.p5);


  //                                  5
  //                             /        \
  //                          3             7
  //                       /    \         /    \
  //                     1     leaf      6      8
  //                   /  \   / \       / \     / \
  //              leaf leaf leaf leaf leaf leaf leaf leaf


  ABST<Book> t1 = new Node<Book>(new BooksByTitle(), this.apple, this.bookTitle, this.bookTitle);
  ABST<Book> t2 = new Node<Book>(new BooksByTitle(),
      this.elephant, this.bookTitle, this.bookTitle);
  ABST<Book> t3 = new Node<Book>(new BooksByTitle(), this.catVsDog, this.t1, this.t2);

  ABST<Book> t4 = new Node<Book>(new BooksByTitle(), this.hobbit, this.bookTitle, this.bookTitle);
  ABST<Book> t5 = new Node<Book>(new BooksByTitle(), this.gatorade, this.bookTitle, this.t4);

  ABST<Book> titleTree = new Node<Book>(new BooksByTitle(), this.fish, this.t3, this.t5);


  //                               fish
  //                             /        \
  //                       catVsDog       gatorade
  //                       /    \         /    \
  //                   apple  elephant   leaf  hobbit
  //                   /  \   / \       / \     / \
  //              leaf leaf leaf leaf leaf leaf leaf leaf


  ABST<Book> a1 = new Node<Book>(new BooksByAuthor(),
      this.hobbit, this.bookAuthor, this.bookAuthor);
  ABST<Book> a2 = new Node<Book>(new BooksByAuthor(), this.catVsDog, this.bookAuthor, this.a1);
  ABST<Book> a3 = new Node<Book>(new BooksByAuthor(),
      this.gatorade, this.bookAuthor, this.bookAuthor);

  ABST<Book> a4 = new Node<Book>(new BooksByAuthor(), this.apple, this.bookAuthor, this.a3);

  ABST<Book> a5 = new Node<Book>(new BooksByAuthor(),
      this.elephant, this.bookAuthor, this.bookAuthor);
  ABST<Book> a6 = new Node<Book>(new BooksByAuthor(), this.hobbit, this.a5, this.a4);

  ABST<Book> authorTree = new Node<Book>(new BooksByAuthor(), this.fish, this.a6, this.a2);


  //                                nemo
  //                             /        \
  //                       jk rowling       shrek
  //                       /    \         /    \
  //                   dumbo  johnny   leaf  hobbit
  //                   /  \   / \       / \     / \
  //              leaf leaf leaf lebron leaf leaf leaf leaf
  //                              / \
  //                          leaf leaf
  //

  ABST<Book> exa1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exa2 = new Node<Book>(new BooksByPrice(), this.elephant, this.exa1, this.bookPrice);
  ABST<Book> exa3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.bookPrice);

  ABST<Book> givenExampleBSTA = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.exa2, this.exa3);


  //                                 7
  //                             /        \
  //                          5             8
  //                       /    \         /    \
  //                    1       leaf     leaf  leaf
  //                  /  \
  //               leaf leaf


  ABST<Book> exb1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exb2 = new Node<Book>(new BooksByPrice(), this.elephant, this.exb1, this.bookPrice);
  ABST<Book> exb3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.bookPrice);

  ABST<Book> givenExampleBSTB = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.exb2, this.exb3);


  //                                 7
  //                             /        \
  //                          5             8
  //                       /    \         /    \
  //                    1       leaf     leaf  leaf
  //                  /  \
  //               leaf leaf


  ABST<Book> exc1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exc2 = new Node<Book>(new BooksByPrice(), this.gatorade,
      this.bookPrice, this.bookPrice);
  ABST<Book> exc3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.exc2, this.bookPrice);

  ABST<Book> givenExampleBSTC = new Node<Book>(new BooksByPrice(),
      this.elephant, this.exc1, this.exc3);


  //                                 5
  //                             /        \
  //                          1             8
  //                       /    \         /    \
  //                    leaf     leaf    7     leaf
  //                                   /  \
  //                                leaf leaf


  ABST<Book> exd1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exd2 = new Node<Book>(new BooksByPrice(),
      this.iceCream, this.bookPrice, this.bookPrice);
  ABST<Book> exd3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.exd2);

  ABST<Book> givenExampleBSTD = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.exd1, this.exd3);


  //                                 7
  //                             /        \
  //                          1             8
  //                       /    \         /    \
  //                    leaf     leaf   leaf   9
  //                                          /  \
  //                                         leaf leaf


  ABST<Book> exdF1 = new Node<Book>(new BooksByPrice(), this.fish, this.bookPrice, this.bookPrice);

  ABST<Book> exdF0 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.exdF1);
  ABST<Book> exdF2 = new Node<Book>(new BooksByPrice(),
      this.iceCream, this.bookPrice, this.bookPrice);
  ABST<Book> exdF3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.exdF2);

  ABST<Book> givenExampleBSTDFishInserted = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.exdF0, this.exdF3);


  //                                 7
  //                             /        \
  //                          1             8
  //                       /    \         /    \
  //                    leaf     6   leaf       9
  //                            / \            /  \
  //                        leaf  leaf       leaf leaf


  ABST<Book> exaA0 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exaA1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.exaA0);
  ABST<Book> exaA2 = new Node<Book>(new BooksByPrice(), this.elephant, this.exaA1, this.bookPrice);
  ABST<Book> exaA3 = new Node<Book>(new BooksByPrice(),
      this.hobbit, this.bookPrice, this.bookPrice);

  ABST<Book> givenExampleBSTAAppleInserted = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.exaA2, this.exaA3);


  //                                 7
  //                             /        \
  //                          5             8
  //                       /    \         /    \
  //                    1       leaf     leaf  leaf
  //                  /  \
  //               leaf leaf


  ABST<Book> t0iceInserted = new Node<Book>(new BooksByTitle(),
      this.iceCream, this.bookTitle, this.bookTitle);
  ABST<Book> t1iceInserted = new Node<Book>(new BooksByTitle(),
      this.apple, this.bookTitle, this.bookTitle);
  ABST<Book> t2iceInserted = new Node<Book>(new BooksByTitle(),
      this.elephant, this.bookTitle, this.bookTitle);
  ABST<Book> t3iceInserted = new Node<Book>(new BooksByTitle(),
      this.catVsDog, this.t1iceInserted, this.t2iceInserted);

  ABST<Book> t4iceInserted = new Node<Book>(new BooksByTitle(),
      this.hobbit, this.bookTitle, this.t0iceInserted);
  ABST<Book> t5iceInserted = new Node<Book>(new BooksByTitle(),
      this.gatorade, this.bookTitle, this.t4iceInserted);

  ABST<Book> titleTreeiceInserted = new Node<Book>(new BooksByTitle(),
      this.fish, this.t3iceInserted, this.t5iceInserted);


  //                               fish
  //                             /        \
  //                       catVsDog       gatorade
  //                       /    \         /    \
  //                   apple  elephant   leaf  hobbit
  //                   /  \   / \               / \
  //              leaf leaf leaf leaf        leaf iceCream
  //                                              / \
  //                                          leaf leaf




  ABST<Book> exaAR0 = new Node<Book>(new BooksByPrice(),
      this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exaAR1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.exaA0);
  ABST<Book> exaAR2 = new Node<Book>(new BooksByPrice(), this.elephant, this.exaA1, this.bookPrice);
  ABST<Book> exaAR3 = new Node<Book>(new BooksByPrice(),
      this.hobbit, this.bookPrice, this.bookPrice);

  ABST<Book> givenExampleBSTAAppleInsertedRIGHTONLY = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.bookPrice, this.exaAR3);


  //                                 7
  //                             /        \
  //                          leaf         8
  //                       /    \         /    \
  //                    leaf       leaf     leaf  leaf
  //                  /  \
  //               leaf leaf

  ABST<Book> exdR1 = new Node<Book>(new BooksByPrice(), this.apple, this.bookPrice, this.bookPrice);
  ABST<Book> exdR2 = new Node<Book>(new BooksByPrice(),
      this.iceCream, this.bookPrice, this.bookPrice);
  ABST<Book> exdR3 = new Node<Book>(new BooksByPrice(), this.hobbit, this.bookPrice, this.exdR2);

  ABST<Book> givenExampleBSTDRIGHTONLY = new Node<Book>(new BooksByPrice(),
      this.gatorade, this.bookPrice, this.exdR3);


  //                                 7
  //                             /        \
  //                         leaf             8
  //                       /    \         /    \
  //                    leaf     leaf   leaf   9
  //                                          /  \
  //                                         leaf leaf


  IList<Book> emptyList = new MtList<Book>();
  IList<Book> list1 = new ConsList<Book>(this.apple, this.emptyList);
  IList<Book> list2 = new ConsList<Book>(this.elephant, this.list1);
  IList<Book> listOFGivenexampleD = new ConsList<Book>(this.apple,
      new ConsList<Book>(this.gatorade, new ConsList<Book>(this.hobbit,
          new ConsList<Book>(this.iceCream, new MtList<Book>()))));

  IList<Book> listOFPrice = new ConsList<Book>(this.apple,
      new ConsList<Book>(this.catVsDog, new ConsList<Book>(this.elephant,
          new ConsList<Book>(this.fish,
          new ConsList<>(this.gatorade,
          new ConsList<Book>(this.hobbit,new MtList<Book>()))))));


  boolean testBuildList(Tester t) {
    return t.checkExpect(this.givenExampleBSTD.buildList(), this.listOFGivenexampleD)
        && t.checkExpect(this.bookPrice.buildList(), this.emptyList)
        && t.checkExpect(this.priceTree.buildList(), this.listOFPrice);
  }

  boolean testInsert(Tester t) {
    return t.checkExpect(this.givenExampleBSTD.insert(this.fish),
        this.givenExampleBSTDFishInserted)
        && t.checkExpect(this.givenExampleBSTA.insert(this.apple),
        this.givenExampleBSTAAppleInserted)
        && t.checkExpect(this.titleTree.insert(this.iceCream),
        this.titleTreeiceInserted);
  }

  boolean testPresent(Tester t) {
    return t.checkExpect(this.givenExampleBSTA.present(this.fish), false)
        && t.checkExpect(this.givenExampleBSTA.present(this.apple), true)
        && t.checkExpect(this.givenExampleBSTA.present(this.elephant), true)
        && t.checkExpect(this.givenExampleBSTA.present(this.hobbit), true)
        && t.checkExpect(this.givenExampleBSTA.present(this.gatorade), true)
        && t.checkExpect(this.givenExampleBSTA.present(this.iceCream), false)
        && t.checkExpect(this.givenExampleBSTA.present(this.catVsDog), false)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.present(this.apple), true)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.present(this.elephant), true)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.present(this.hobbit), true)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.present(this.gatorade), true)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.present(this.iceCream), false);

  }

  boolean checkConstructerExceptions(Tester t) {
    return t.checkConstructorException(new IllegalArgumentException(
        "The comparator cannot be null"),
        "ABST", new BooksByPrice(), null)
        && t.checkConstructorException(new IllegalArgumentException(
            "The comparator cannot be null"),
        "ABST", null, this.bookPrice)
        && t.checkConstructorException(new IllegalArgumentException(
            "No leftmost item of an empty tree"),
        "ABST", new BooksByPrice(), this.bookPrice)
        && t.checkConstructorException(new IllegalArgumentException(
            "No rightmost item of an empty tree"),
        "ABST", this.bookPrice, new BooksByPrice())
         && t.checkConstructorException(new IllegalArgumentException("No right of an empty tree"),
        "ABST", this.bookPrice, null)
        && t.checkConstructorException(new IllegalArgumentException("No left of an empty tree"),
        "ABST", null, this.bookPrice)
        && t.checkConstructorException(new IllegalArgumentException(
            "The comparator cannot be null"),
        "ABST", new BooksByAuthor(), null)
        && t.checkConstructorException(new IllegalArgumentException(
            "The comparator cannot be null"),
        "ABST", null, this.bookAuthor)
        && t.checkConstructorException(new IllegalArgumentException(
            "No leftmost item of an empty tree"),
        "ABST", new BooksByPrice(), this.bookAuthor)
        && t.checkConstructorException(new IllegalArgumentException(
            "No rightmost item of an empty tree"),
        "ABST", this.bookAuthor, new BooksByPrice())
        && t.checkConstructorException(new IllegalArgumentException("No right of an empty tree"),
        "ABST", this.bookTitle, null)
        && t.checkConstructorException(new IllegalArgumentException("No left of an empty tree"),
        "ABST", null, this.bookTitle);

  }

  boolean testGetLeftMost(Tester t) {
    return
        // one node case
        t.checkExpect(this.givenExampleBSTA.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTB.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTC.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTD.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTDFishInserted.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.getLeftmost(), this.apple)
        && t.checkExpect(this.titleTree.getLeftmost(), this.apple)
        && t.checkExpect(this.titleTreeiceInserted.getLeftmost(), this.apple)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.getLeftmost(), this.apple);



  }

  boolean testGetRightMost(Tester t) {
    return t.checkExpect(this.givenExampleBSTA.getRightMost(), this.hobbit)
        && t.checkExpect(this.givenExampleBSTB.getRightMost(), this.hobbit)
        && t.checkExpect(this.givenExampleBSTC.getRightMost(), this.hobbit);
  }


  /*
  boolean testGetRight(Tester t) {
    return t.checkExpect(this.givenExampleBSTAAppleInserted.getRight(),
        this.givenExampleBSTAAppleInsertedRIGHTONLY)
        && t.checkExpect(this.givenExampleBSTAAppleInsertedRIGHTONLY.getRight(),
            this.givenExampleBSTAAppleInsertedRIGHTONLY)
        && t.checkExpect(this.givenExampleBSTDRIGHTONLY.getRight(),
            this.givenExampleBSTDRIGHTONLY)
        && t.checkExpect(this.givenExampleBSTD.getRight(),
            this.givenExampleBSTDRIGHTONLY)
        ;



  }

 */







  boolean testSameTree(Tester t) {
    return t.checkExpect(this.givenExampleBSTD.sameTree(this.givenExampleBSTD), true)
        && t.checkExpect(this.givenExampleBSTD.sameTree(this.givenExampleBSTA), false)
        && t.checkExpect(this.givenExampleBSTA.sameTree(this.givenExampleBSTB), true)
        && t.checkExpect(this.givenExampleBSTA.sameTree(this.givenExampleBSTC), false);

  }


  boolean testSameData(Tester t) {

    return
        //empty case
        t.checkExpect(this.givenExampleBSTD.sameData(this.givenExampleBSTD), true)
        && t.checkExpect(this.givenExampleBSTD.sameData(this.givenExampleBSTA), false)
        //non-empty case
        //&& t.checkExpect(this.givenExampleBSTA.sameData(this.givenExampleBSTB), true)
        && t.checkExpect(this.givenExampleBSTA.sameData(this.givenExampleBSTC), true)
        && t.checkExpect(this.givenExampleBSTA.sameData(this.givenExampleBSTD), false)
        && t.checkExpect(this.givenExampleBSTA.sameData(this.givenExampleBSTAAppleInserted),
            false)
        && t.checkExpect(this.givenExampleBSTAAppleInserted.sameData(this.givenExampleBSTA),
            false);




  }

  /* boolean testBuildList(Tester t) {
    return t.checkExpect(this.givenExampleBSTA.buildList(),
        new ConsList<Book>(this.apple, new ConsList<Book>(this.elephant,
            new ConsList<Book>(this.gatorade, new ConsList<Book>(this.hobbit, new MtList<>())))));
  }

  */


  ExamplesLists() {
  }
}