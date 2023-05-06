package Lab.lab5.Lab7;

class Person {

  String username;
  ILoBuddy buddies;

  /*
  * Template:
  * Fields:
  * this.username -- String
  * this.buddies -- ILoBuddy
  * Methods:
  * addBuddy(Person buddy) -- void
  * hasDirectBuddy(Person that) -- boolean
  * directBuddyHelpers(Person that) -- boolean
  * countCommonBuddies(Person that) -- int
  * hasExtendedBuddy(Person that) -- boolean
  * hasExtendedBuddy(Person that,ILoBuddy partyPeople) -- boolean
  *  partyCount() -- int
  *  getParty() -- ILoBuddy
  * getParty(ILoBuddy partyList) -- ILoBuddy
  *  Methods for fields:
  * this.buddies.hasDirectBuddy() -- boolean
  * this.buddies.countCommonBuddies(Person that) -- int
  * this.buddies.hasExtendedBuddy(Person that, ILoBuddy partyPeople) -- boolean
  * this.buddies.partyCount() -- int
  * this.buddies.getParty() -- ILoBuddy
  * this.buddies.getParty(ILoBuddy partyList) -- ILoBuddy
  *
  */

  Person(String username) {
    this.username = username;
    this.buddies = new MTLoBuddy();
  }

  // EFFECT:
  // Change this person's buddy list so that it includes the given person
  void addBuddy(Person buddy) {
    this.buddies = new ConsLoBuddy(buddy, this.buddies);
  }

  // returns true if this Person has that as a direct buddy
  boolean hasDirectBuddy(Person that) {
    return this.directBuddyHelpers(that);
  }

  boolean directBuddyHelpers(Person that) {
    return this.buddies.directBuddyHelper(that);
  }


  // returns the number of people that are direct buddies
  // of both this and that person
  int countCommonBuddies(Person that) {
    return that.buddies.countCommmonBuddieshelper(that, this.buddies);
  }

  boolean hasExtendedBuddy(Person that) {
    return this.buddies.hasExtendedBuddies(that, new MTLoBuddy());
  }


  // will the given person be invited to a party
  // organized by this person?
  boolean hasExtendedBuddy(Person that, ILoBuddy partyPeople) {
    return this.buddies.hasExtendedBuddies(that,partyPeople);
    //return this.directBuddyHelpers(that) || this.buddies.hasExtendedBuddies(that);
  }

  // returns the number of people who will show up at the party
  // given by this person
  int partyCount() {
    ILoBuddy partyList = new MTLoBuddy();
    return this.getParty().count();
  }


  // get the list of people who will show up at the party
  ILoBuddy getParty() {
    return this.buddies.partyCountHelper(new ConsLoBuddy(this, new MTLoBuddy()));
  }

  // get the list of people who will show up at the party
  ILoBuddy getParty(ILoBuddy partyList) {
    return this.buddies.partyCountHelper(new ConsLoBuddy(this, partyList));
  }




}