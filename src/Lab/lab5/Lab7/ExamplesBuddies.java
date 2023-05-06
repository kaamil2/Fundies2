package Lab.lab5.Lab7;

import tester.Tester;

// runs tests for the buddies problem
class ExamplesBuddies {

  Person ann ;
  Person  bob;
  Person cole;
  Person dan;
  Person ed;
  Person fay;
  Person gabi;
  Person hank ;
  Person jan;
  Person kim;
  Person len ;

  Person mark ;


  void initBuddies() {

    this.ann = new Person("Ann");
    this.bob = new Person("Bob");
    this.cole = new Person("Cole");
    this.dan = new Person("Dan");
    this.ed = new Person("Ed");
    this.fay = new Person("Fay");
    this.gabi = new Person("Gabi");
    this.hank = new Person("Hank");
    this.jan = new Person("Jan");
    this.kim = new Person("Kim");
    this.len = new Person("Len");

    this.mark = new Person("Mark");


    // Ann's buddies are Bob and Cole
    ann.addBuddy(bob);
    ann.addBuddy(cole);
    // Bob's buddies are Ann, Ed, and Hank
    bob.addBuddy(ann);
    bob.addBuddy(ed);
    bob.addBuddy(hank);
    // Cole's buddy is Dan
    cole.addBuddy(dan);
    // Dan's buddy is Cole
    dan.addBuddy(cole);
    // Ed's buddy is Fay
    ed.addBuddy(fay);
    // Fay's buddies are Ed and Gabi
    fay.addBuddy(ed);
    fay.addBuddy(gabi);
    // Gabi's buddies are Ed and Fay
    gabi.addBuddy(ed);
    gabi.addBuddy(fay);
    // Hank does not have any buddies
    // Jan's buddies are Kim and Len
    jan.addBuddy(kim);
    jan.addBuddy(len);
    // Kim's buddies are Jan and Len
    kim.addBuddy(jan);
    kim.addBuddy(len);
    // Len's buddies are Jan and Kim
    len.addBuddy(jan);
    len.addBuddy(kim);
    // Mark's buddies are jan and kim and ann and bob and len
    mark.addBuddy(jan);
    mark.addBuddy(kim);
    mark.addBuddy(ann);
    mark.addBuddy(bob);
    mark.addBuddy(len);





  }

  /*
  boolean testDirectBuddyHelper(Tester t) {
    this.initBuddies();
    return t.checkExpect(this.ann.directBuddyHelpers(this.bob), true)
   && t.checkExpect(this.ann.directBuddyHelpers(this.dan), false)
    && t.checkExpect(this.ann.directBuddyHelpers(this.ann), false)
   && t.checkExpect(this.ed.directBuddyHelpers(this.fay), true)
   && t.checkExpect(this.hank.directBuddyHelpers(this.jan), false)
   && t.checkExpect(this.jan.directBuddyHelpers(this.len), true);
  }

  boolean testHasDirectBuddy(Tester t) {
    this.initBuddies();
    return t.checkExpect(this.ann.hasDirectBuddy(this.bob), true)
   && t.checkExpect(this.ann.hasDirectBuddy(this.dan), false)
    && t.checkExpect(this.ann.hasDirectBuddy(this.ann), false)
    && t.checkExpect(this.ann.hasDirectBuddy(this.fay), false)
    && t.checkExpect(this.ann.hasDirectBuddy(this.jan), false)
   && t.checkExpect(this.ann.hasDirectBuddy(this.len), false)
    &&  t.checkExpect(this.jan.hasDirectBuddy(this.len), true)
   &&  t.checkExpect(this.jan.hasDirectBuddy(this.ann), false)
    && t.checkExpect(this.jan.hasDirectBuddy(this.jan), false)
   &&  t.checkExpect(this.jan.hasDirectBuddy(this.fay), false)
   &&  t.checkExpect(this.jan.hasDirectBuddy(this.kim), true)
   &&  t.checkExpect(this.jan.hasDirectBuddy(this.ed), false)
   &&  t.checkExpect(this.hank.hasDirectBuddy(this.gabi), false);
  }


  // tests for the method countCommonBuddies
  boolean testCountCommonBuddies(Tester t) {
    this.initBuddies();
    return t.checkExpect(this.ann.countCommonBuddies(this.bob), 0)
    && t.checkExpect(this.bob.countCommonBuddies(this.fay), 1)
   &&  t.checkExpect(this.jan.countCommonBuddies(this.len), 1)
    && t.checkExpect(this.hank.countCommonBuddies(this.kim), 0)
    && t.checkExpect(this.ann.countCommonBuddies(this.dan), 1)
   &&  t.checkExpect(this.ann.countCommonBuddies(this.fay), 0)
    && t.checkExpect(this.mark.countCommonBuddies(this.jan), 2);
  }

  // tests for the method hasExtendedBuddy
  boolean testHasExtendedBuddy(Tester t) {
    this.initBuddies();
    return t.checkExpect(this.ann.hasExtendedBuddy(this.bob), true)
        && t.checkExpect(this.ann.hasExtendedBuddy(this.dan), true)
        && t.checkExpect(this.ann.hasExtendedBuddy(this.fay), true)
        && t.checkExpect(this.bob.hasExtendedBuddy(this.jan), false)
        && t.checkExpect(this.hank.hasExtendedBuddy(this.len), false)
        && t.checkExpect(this.jan.hasExtendedBuddy(this.len), true)
        && t.checkExpect(this.jan.hasExtendedBuddy(this.ann), false)
        && t.checkExpect(this.gabi.hasExtendedBuddy(this.hank), false);

  }
  */


  // tests for the method countCommonBuddies
  void testHasDirectBuddy(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.hasDirectBuddy(this.bob), true);
    t.checkExpect(this.ann.hasDirectBuddy(this.dan), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.ann), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.fay), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.jan), false);
    t.checkExpect(this.ann.hasDirectBuddy(this.len), false);
    t.checkExpect(this.jan.hasDirectBuddy(this.len), true);
    t.checkExpect(this.jan.hasDirectBuddy(this.ann), false);
    t.checkExpect(this.jan.hasDirectBuddy(this.jan), false);
    t.checkExpect(this.jan.hasDirectBuddy(this.fay), false);
    t.checkExpect(this.jan.hasDirectBuddy(this.kim), true);
    t.checkExpect(this.jan.hasDirectBuddy(this.ed), false);
    t.checkExpect(this.hank.hasDirectBuddy(this.gabi), false);
  }


  // tests for the method countCommonBuddies
  void testCountCommonBuddies(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.countCommonBuddies(this.bob), 0);
    t.checkExpect(this.bob.countCommonBuddies(this.fay), 1);
    t.checkExpect(this.jan.countCommonBuddies(this.len), 1);
    t.checkExpect(this.hank.countCommonBuddies(this.kim), 0);
    t.checkExpect(this.ann.countCommonBuddies(this.dan), 1);
    t.checkExpect(this.ann.countCommonBuddies(this.fay), 0);
    t.checkExpect(this.mark.countCommonBuddies(this.jan), 2);
  }

  // tests for the method hasExtendedBuddy
  void testHasExtendedBuddy(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(this.bob), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.dan), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.fay), true);
    t.checkExpect(this.bob.hasExtendedBuddy(this.jan), false);
    t.checkExpect(this.hank.hasExtendedBuddy(this.len), false);
    t.checkExpect(this.jan.hasExtendedBuddy(this.len), true);
    t.checkExpect(this.jan.hasExtendedBuddy(this.ann), false);
    t.checkExpect(this.gabi.hasExtendedBuddy(this.hank), false);
  }

  void testHasExtendedBuddyDouble(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.hasExtendedBuddy(this.bob, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.dan, new MTLoBuddy()), true);
    t.checkExpect(this.ann.hasExtendedBuddy(this.fay, new MTLoBuddy()), true);
    t.checkExpect(this.bob.hasExtendedBuddy(this.jan, new MTLoBuddy()), false);
    t.checkExpect(this.hank.hasExtendedBuddy(this.len, new MTLoBuddy()), false);
    t.checkExpect(this.jan.hasExtendedBuddy(this.len, new MTLoBuddy()), true);
    t.checkExpect(this.jan.hasExtendedBuddy(this.hank, new MTLoBuddy()), false);
  }



  // tests for the method partyCount
  void testPartyCount(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.partyCount(), 8);
    t.checkExpect(this.bob.partyCount(), 8);
    t.checkExpect(this.hank.partyCount(), 1);
    t.checkExpect(this.dan.partyCount(), 2);
    t.checkExpect(this.jan.partyCount(), 3);
    t.checkExpect(this.len.partyCount(),3);
    t.checkExpect(this.hank.partyCount(), 1);
  }






  void testDirectBuddyHelper(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.directBuddyHelpers(this.bob), true);
    t.checkExpect(this.ann.directBuddyHelpers(this.dan), false);
    t.checkExpect(this.ann.directBuddyHelpers(this.ann), false);
    t.checkExpect(this.ed.directBuddyHelpers(this.fay), true);
    t.checkExpect(this.hank.directBuddyHelpers(this.jan), false);
    t.checkExpect(this.jan.directBuddyHelpers(this.len), true);
  }

  void testCountCommonBuddieshelper(Tester t) {
    this.initBuddies();
    t.checkExpect(this.bob.buddies.countCommmonBuddieshelper(this.bob, ann.buddies), 0);
    t.checkExpect(this.bob.buddies.countCommmonBuddieshelper(this.bob, fay.buddies), 1);
    t.checkExpect(this.ann.buddies.countCommmonBuddieshelper(this.ann, dan.buddies), 1);
    t.checkExpect(this.ann.buddies.countCommmonBuddieshelper(this.ann, fay.buddies), 0);
    t.checkExpect(this.mark.buddies.countCommmonBuddieshelper(this.jan, len.buddies), 2);

  }

  void testBuddies(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.buddies, new ConsLoBuddy(cole, new ConsLoBuddy(bob, new MTLoBuddy())));
    t.checkExpect(bob.buddies, new ConsLoBuddy(hank,
        new ConsLoBuddy(ed, new ConsLoBuddy(ann, new MTLoBuddy()))));
    t.checkExpect(cole.buddies, new ConsLoBuddy(dan, new MTLoBuddy()));
    t.checkExpect(dan.buddies, new ConsLoBuddy(cole, new MTLoBuddy()));
    t.checkExpect(ed.buddies, new ConsLoBuddy(fay, new MTLoBuddy()));
    t.checkExpect(fay.buddies, new ConsLoBuddy(gabi, new ConsLoBuddy(ed, new MTLoBuddy())));
    t.checkExpect(gabi.buddies, new ConsLoBuddy(fay, new ConsLoBuddy(ed, new MTLoBuddy())));
    t.checkExpect(jan.buddies, new ConsLoBuddy(len, new ConsLoBuddy(kim, new MTLoBuddy())));
    t.checkExpect(kim.buddies, new ConsLoBuddy(len, new ConsLoBuddy(jan, new MTLoBuddy())));
    t.checkExpect(len.buddies, new ConsLoBuddy(kim, new ConsLoBuddy(jan, new MTLoBuddy())));
  }

  void testHasExtendedBuddies(Tester t) {
    this.initBuddies();
    t.checkExpect(this.ann.buddies.hasExtendedBuddies(this.bob,new MTLoBuddy()), true);
    t.checkExpect(this.kim.buddies.hasExtendedBuddies(this.jan,new MTLoBuddy()), true);
    t.checkExpect(this.len.buddies.hasExtendedBuddies(this.jan,new MTLoBuddy()), true);
    t.checkExpect(this.ann.buddies.hasExtendedBuddies(this.dan,new MTLoBuddy()), true);
    t.checkExpect(this.kim.buddies.hasExtendedBuddies(this.fay,new MTLoBuddy()), false);
    t.checkExpect(this.gabi.buddies.hasExtendedBuddies(this.hank,new MTLoBuddy()), false);
    t.checkExpect(this.fay.buddies.hasExtendedBuddies(this.dan,new MTLoBuddy()), false);



  }

  void testPartyCounthelper(Tester t) {
    this.initBuddies();
    t.checkExpect(this.hank.buddies.partyCountHelper(new MTLoBuddy()), new MTLoBuddy());
    t.checkExpect(this.dan.buddies.partyCountHelper(new MTLoBuddy()), new ConsLoBuddy(dan,
        new ConsLoBuddy(cole, new MTLoBuddy())));
    t.checkExpect(this.len.buddies.partyCountHelper(new MTLoBuddy()),
        new ConsLoBuddy(jan, new ConsLoBuddy(len, new ConsLoBuddy(kim, new MTLoBuddy()))));

  }

  void count(Tester t) {
    this.initBuddies();
    t.checkExpect(this.bob.buddies.count(), 4);
    t.checkExpect(this.hank.buddies.count(), 0);
    t.checkExpect(this.dan.buddies.count(), 2);
    t.checkExpect(this.jan.buddies.count(), 3);
    t.checkExpect(this.len.buddies.count(), 3);
  }


  void partyCount(Tester t) {
    this.initBuddies();
    t.checkExpect(this.bob.partyCount(), 8);
    t.checkExpect(this.hank.partyCount(), 1);
    t.checkExpect(this.dan.partyCount(), 2);
    t.checkExpect(this.jan.partyCount(), 3);
    t.checkExpect(this.len.partyCount(),3);
    t.checkExpect(this.hank.partyCount(), 1);
  }

  void getParty(Tester t) {
    this.initBuddies();
    t.checkExpect(this.bob.getParty(), new ConsLoBuddy(bob,
        new ConsLoBuddy(hank,
        new ConsLoBuddy(ed, new ConsLoBuddy(fay, new ConsLoBuddy(gabi,
            new MTLoBuddy()))))));
    t.checkExpect(this.hank.getParty(), new ConsLoBuddy(hank,
        new MTLoBuddy()));
    t.checkExpect(this.dan.getParty(), new ConsLoBuddy(dan,
        new ConsLoBuddy(cole, new MTLoBuddy())));
    t.checkExpect(this.jan.getParty(), new ConsLoBuddy(jan,
        new ConsLoBuddy(len,
        new ConsLoBuddy(kim, new MTLoBuddy()))));
    t.checkExpect(this.len.getParty(), new ConsLoBuddy(jan, new ConsLoBuddy(len,
        new ConsLoBuddy(kim, new MTLoBuddy()))));
    t.checkExpect(this.hank.getParty(), new ConsLoBuddy(hank, new MTLoBuddy()));
  }

  void getPartyDouble(Tester t) {
    this.initBuddies();
    t.checkExpect(this.bob.getParty(new MTLoBuddy()), new ConsLoBuddy(bob, new ConsLoBuddy(hank,
        new ConsLoBuddy(ed, new ConsLoBuddy(fay, new ConsLoBuddy(gabi, new MTLoBuddy()))))));
    t.checkExpect(this.hank.getParty(new MTLoBuddy()), new ConsLoBuddy(hank, new MTLoBuddy()));
    t.checkExpect(this.dan.getParty(new MTLoBuddy()), new ConsLoBuddy(dan, new ConsLoBuddy(cole,
        new MTLoBuddy())));
    t.checkExpect(this.jan.getParty(new MTLoBuddy()), new ConsLoBuddy(jan, new ConsLoBuddy(len,
        new ConsLoBuddy(kim, new MTLoBuddy()))));
    t.checkExpect(this.len.getParty(new MTLoBuddy()), new ConsLoBuddy(jan, new ConsLoBuddy(len,
        new ConsLoBuddy(kim, new MTLoBuddy()))));
  }









}
