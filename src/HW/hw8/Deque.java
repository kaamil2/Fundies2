package HW.hw8;

import tester.Tester;

import java.util.function.Predicate;

// Represeents a Sentinel

class Sentinel<T> extends ANode<T> {
  Sentinel() {
    super();
    this.next = this;
    this.prev = this;

  }


  // Adds a new node with the given item at the head of the list
  @Override
  void addAtHead(T item, ANode<T> sentinel) {
    if (this.next == sentinel) {
      this.next = new Node<T>(item, this, sentinel);
      this.prev = this.next;
    } else {
      this.next.addAtHead(item, sentinel);
    }
  }


  // Adds a new node with the given item at the tail of the list
  @Override
  void addAtTail(T item, ANode<T> sentinel) {
    if (this.prev == sentinel) {
      this.prev = new Node<T>(item, sentinel, this);
      this.next = this.prev;
    } else {
      this.prev.addAtTail(item, sentinel);
    }
  }



  // finds the node with the given predicate true
  ANode<T> find(Predicate<T> pred, ANode<T> sentinel) {

    return this.next.findHelper(pred, sentinel);
    /*
    ANode<T> temp = this.next;
    while (temp != sentinel) {
      if (pred.test(((Node<T>) temp).data)) {
        return temp;
      } else {
        temp = temp.next;
      }
    }
    return this;

     */
  }

  ANode<T> findHelper(Predicate<T> pred, ANode<T> sentinel) {
    return this;
  }


  // removes the head of the list

  T removeFromHead() {
    if (this.prev == this) {
      throw new RuntimeException("Cannot remove from an empty list");
    } else {
      Node<T> temp = (Node<T>) this.next;
      this.next = temp.next;
      temp.next.prev = this;
      return temp.data;
    }
  }

  // removes the tail of the list

  T removeFromTail() {
    if (this.next == this) {
      throw new RuntimeException("Cannot remove from an empty list");
    } else {
      Node<T> temp = (Node<T>) this.prev;
      this.prev = temp.prev;
      temp.prev.next = this;
      return temp.data;
    }
  }

  // last data in the list

  // returns the size of the list
  @Override
  int size(int size, ANode<T> sentinel) {
    if (this.next == sentinel) {
      return 0;
    } else {
      return this.next.size(size + 1, sentinel);
    }
  }


}

// Represents a Node
class Node<T> extends ANode<T> {
  T data;

  Node(T data, ANode<T> next, ANode<T> prev) {
    super(next, prev);
    if (next == null || prev == null) {
      throw new IllegalArgumentException("Data cannot be null");
    }
    prev.next = this;
    next.prev = this;
    this.data = data;
  }

  Node(T data) {
    super();
    this.data = data;
  }


  // Adds a new node with the given item at the head of the list
  @Override
  void addAtHead(T item, ANode<T> sentinel) {
    new Node<T>(item, this, this.prev);
  }


  // Adds a new node with the given item at the tail of the list
  @Override
  void addAtTail(T item, ANode<T> sentinel) {
    new Node<T>(item, this.next, this);

  }

  // finds the node with the given predicate true
  ANode<T> findHelper(Predicate<T> item, ANode<T> sentinel) {
    if (item.test(this.data)) {
      return this;
    } else {
      return this.next.findHelper(item, sentinel);
    }
  }

}

// Represents an ANode
abstract class ANode<T> {

  ANode<T> next;
  ANode<T> prev;

  ANode() {
    this.next = null;
    this.prev = null;
  }

  ANode(ANode<T> next, ANode<T> prev) {
    this.next = next;
    this.prev = prev;
  }

  // the size of the ANode
  int size(int size, ANode<T> node) {
    ANode<T> current = this.next;
    while (current != node) {
      size++;
      current = current.next;
    }
    return size;
  }

  // Adds a new node with the given item at the head of the list
  void addAtHead(T item, ANode<T> node) {
    node.addAtHead(item, node);

  }

  // Adds a new node with the given item at the tail of the list
  void addAtTail(T item, ANode<T> node) {
    node.addAtTail(item, node);

  }

  // finds the node with the given predicate true helper
  abstract ANode<T> findHelper(Predicate<T> item, ANode<T> sentinel);


}

// Represents a Deque
class Deque<T> {

  Sentinel<T> header = new Sentinel<T>();


  public Deque() {
    this.header = new Sentinel<T>();

  }

  public Deque(Sentinel<T> header) {
    this.header = header;
  }

  // Returns the number of items in the Deque
  int size() {
    int size = 0;
    return this.header.size(size, this.header);
  }

  //adds a new node with the given item at the head of the list
  void addAtHead(T item) {
    this.header.addAtHead(item, this.header);


  }

  //adds a new node with the given item at the tail of the list
  void addAtTail(T item) {
    this.header.addAtTail(item, this.header);

  }

  //removes the head of the list
  T removeFromHead() {
    if (this.size() == 0) {
      throw new RuntimeException("Cannot remove from an empty deque");
    } else {
      return this.header.removeFromHead();

    }

  }

  //removes the tail of the list
  T removeFromTail() {
    if (this.size() == 0) {
      throw new RuntimeException("Cannot remove from an empty deque");
    } else {
      return this.header.removeFromTail();
      //return this.header.lastData(header);
    }
  }

  //finds the node with the given predicate true
  ANode<T> find(Predicate<T> predicate) {
    return this.header.find(predicate, this.header);
  }

}

class ExamplesDeque {

  Deque<String> deque1;
  Deque<String> deque2;
  Deque<String> deque3;
  Deque<String> deque4;
  Deque<String> deque5;
  Deque<String> deque6;

  Sentinel<String> sentinel0;
  Sentinel<String> sentinel1;
  Sentinel<String> sentinel2;
  Sentinel<String> sentinel3;
  Sentinel<String> sentinel4;
  Sentinel<String> sentinel5;

  Node<String> node0;
  Node<String> node1;
  Node<String> node2;
  Node<String> node3;
  Node<String> node4;
  Node<String> node5;
  Node<String> node6;
  Node<String> node7;
  Node<String> node8;
  Node<String> node9;
  Node<String> node10;
  Node<String> node11;
  Node<String> node12;
  Node<String> node13;
  Node<String> node14;


  void initDeque() {
    this.deque1 = new Deque<String>();

    this.deque2 = new Deque<String>();

    this.sentinel1 = new Sentinel<String>();

    this.node1 = new Node<String>("abc", sentinel1, sentinel1);
    this.node2 = new Node<String>("bcd", node1, sentinel1);
    this.node3 = new Node<String>("cde", node2, sentinel1);
    this.node4 = new Node<String>("def", node3, sentinel1);

    deque2.header = sentinel1;

    this.deque4 = new Deque<String>();

    this.sentinel3 = new Sentinel<String>();

    this.node1 = new Node<String>("abc", sentinel3, sentinel3);
    this.node2 = new Node<String>("bcd", node1, sentinel3);
    this.node3 = new Node<String>("cde", node2, sentinel3);

    deque4.header = sentinel3;

    deque4.addAtTail("def");

    System.out.println(deque4);

    this.deque5 = new Deque<String>();

    this.sentinel4 = new Sentinel<String>();

    this.node1 = new Node<String>("bcd", sentinel4, sentinel4);
    this.node2 = new Node<String>("cde", node1, sentinel4);
    this.node3 = new Node<String>("def", node2, sentinel4);

    deque5.header = sentinel4;

    sentinel0 = new Sentinel<String>();

    this.node0 = new Node<String>("abc", sentinel0, sentinel0);

    deque6 = new Deque<String>();
    sentinel5 = new Sentinel<String>();
    node9 = new Node<String>("abc", sentinel5, sentinel5);
    node10 = new Node<String>("bcd", node9, sentinel5);
    node11 = new Node<String>("cde", node10, sentinel5);
    node12 = new Node<String>("def", node11, sentinel5);
    node13 = new Node<String>("ghi", node12, sentinel5);

    deque6.header = sentinel5;












    /*
    Node<String> node1 = new Node<String>("abc", sentinel1, sentinel1);
    Node<String> node2 = new Node<String>("bcd", node1, sentinel1);
    Node<String> node3 = new Node<String>("cde", node2, sentinel1);
    Node<String> node4 = new Node<String>("def", node3, sentinel1);

   */


    this.deque3 = new Deque<String>();


    this.sentinel2 = new Sentinel<String>();


    this.node5 = new Node<String>("ghi", sentinel2, sentinel2);
    this.node6 = new Node<String>("abc", node5, sentinel2);
    this.node7 = new Node<String>("jkl", node6, sentinel2);
    this.node8 = new Node<String>("def", node7, sentinel2);

    deque3.header = sentinel2;
  }

  void testSize(Tester t) {
    initDeque();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque3.size(), 4);
  }

  void testRemoveFromTail(Tester t) {
    initDeque();
    t.checkExpect(deque2.removeFromTail(), "abc");
    t.checkExpect(deque4.removeFromTail(), "def");
    t.checkExpect(deque5.removeFromTail(), "bcd");
    t.checkException(new RuntimeException("Cannot remove from an empty deque"),
        deque1, "removeFromTail");


  }

  void testRemoveFromHead(Tester t) {
    initDeque();
    t.checkExpect(deque2.removeFromHead(), "def");
    t.checkExpect(deque4.removeFromHead(), "cde");
    t.checkExpect(deque5.removeFromHead(), "def");
    t.checkException(new RuntimeException("Cannot remove from an empty deque"),
        deque1, "removeFromHead");


  }

  public void testSentinel(Tester t) {
    Sentinel<Integer> sentinel = new Sentinel<>();
    t.checkExpect(sentinel.size(0, sentinel), 0);

    // Test adding elements
    sentinel.addAtHead(2, sentinel);
    sentinel.addAtTail(3, sentinel);
    t.checkExpect(sentinel.size(0, sentinel), 2);

    // Test removing elements
    t.checkExpect(sentinel.removeFromHead(), 2);
    t.checkExpect(sentinel.size(0, sentinel), 1);
    t.checkExpect(sentinel.removeFromTail(), 3);
    t.checkExpect(sentinel.size(0, sentinel), 0);

  }

  void testAddAtTailSize(Tester t) {
    initDeque();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque3.size(), 4);
    t.checkExpect(deque4.size(), 4);
    t.checkExpect(deque5.size(), 3);
    t.checkExpect(deque6.size(), 5);
  }


  void testAddAtHeadSize(Tester t) {
    initDeque();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque3.size(), 4);
    t.checkExpect(deque4.size(), 4);
    t.checkExpect(deque5.size(), 3);
    t.checkExpect(deque6.size(), 5);
  }


  void testRemoveFromHeadSize(Tester t) {
    initDeque();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque3.size(), 4);
    t.checkExpect(deque4.size(), 4);
    t.checkExpect(deque5.size(), 3);
    t.checkExpect(deque6.size(), 5);
  }

  void testRemoveFromHeadp2(Tester t) {
    initDeque();
    t.checkExpect(deque2.removeFromHead(), "def");
    t.checkExpect(deque4.removeFromHead(), "cde");
    t.checkExpect(deque5.removeFromHead(), "def");
    t.checkException(new RuntimeException("Cannot remove from an empty deque"),
        deque1, "removeFromHead");
  }

  void testRemoveFromTailSize(Tester t) {
    initDeque();
    t.checkExpect(deque1.size(), 0);
    t.checkExpect(deque2.size(), 4);
    t.checkExpect(deque3.size(), 4);
    t.checkExpect(deque4.size(), 4);
    t.checkExpect(deque5.size(), 3);
    t.checkExpect(deque6.size(), 5);
  }

  void testRemoveFromTailp2(Tester t) {
    initDeque();
    t.checkExpect(deque2.removeFromTail(), "abc");
    t.checkExpect(deque4.removeFromTail(), "def");
    t.checkExpect(deque5.removeFromTail(), "bcd");
    t.checkException(new RuntimeException("Cannot remove from an empty deque"),
        deque1, "removeFromTail");
  }


  void testAddAtHeadp2(Tester t) {
    initDeque();
    System.out.println("4 header " + deque4.header.next);
    System.out.println("2 header " + deque2.header.next);
    deque1.addAtHead("abc");
    deque2.addAtHead("abc");
    deque3.addAtHead("abc");
    deque4.addAtHead("abc");
    deque5.addAtHead("abc");
    deque6.addAtHead("abc");
    System.out.println("4 header " + deque4.header.next);
    System.out.println("2 header " + deque2.header.next);
    t.checkExpect(deque1.header.next, node0);
    t.checkExpect(deque2.header.next, deque2.header.next);
    t.checkExpect(deque3.header.next, deque3.header.next);
    t.checkExpect(deque4.header.next, deque4.header.next);
    t.checkExpect(deque5.header.next, deque5.header.next);
    t.checkExpect(deque6.header.next, deque6.header.next.prev.next);
  }

  void testAddAtTailp2(Tester t) {
    initDeque();
    deque1.addAtTail("abc");
    deque2.addAtTail("abc");
    deque3.addAtTail("abc");
    deque4.addAtTail("abc");
    deque5.addAtTail("abc");
    deque6.addAtTail("abc");
    t.checkExpect(deque1.header.prev, node0);
    t.checkExpect(deque2.header.prev, deque2.header.prev);
    t.checkExpect(deque3.header.prev, deque3.header.prev);
    t.checkExpect(deque4.header.prev, deque4.header.prev);
    t.checkExpect(deque5.header.prev, deque5.header.prev);
    t.checkExpect(deque6.header.prev, deque6.header.prev);
  }


  // Test the find method
  void testFind(Tester t) {
    initDeque();
    t.checkExpect(deque1.find(Predicate.isEqual("abc")), deque1.header);
    t.checkExpect(deque2.find(Predicate.isEqual("abc")), deque2.header.prev);
    t.checkExpect(deque3.find(Predicate.isEqual("abc")), node6);
    t.checkExpect(deque4.find(Predicate.isEqual("abc")), deque4.header.prev.prev);
    //t.checkExpect(deque3.find(Predicate.isEqual("abc")), node6);
    //t.checkExpect(deque4.find(), node1);
    //t.checkExpect(deque5.find(), node1);
  }


  void testAddAtHead(Tester t) {
    initDeque();
    System.out.println("4 header " + deque4.header.next);
    System.out.println("2 header " + deque2.header.next);
    deque1.addAtHead("abc");
    deque2.addAtHead("ghi");

    t.checkExpect(deque1.header.next, node0);
    t.checkExpect(deque2.header.next, node13);
    t.checkExpect(deque4.header.next.equals(deque2.header.next), true);


    //t.checkExpect(deque4.header.next.equals(deque2.header.next) , true);
    //t.checkExpect(deque4.header.addAtHead("lag", ), deque5);
  }


  void testAddAtTail(Tester t) {
    initDeque();
    deque1.addAtTail("abc");
    deque2.addAtTail("ghi");
    deque4.addAtTail("abc");

    t.checkExpect(deque4.header.prev.equals(deque4.header.prev), true);
    t.checkExpect(deque1.header.prev, node0);
    t.checkExpect(deque2.header.prev, node13);


  }


}





















