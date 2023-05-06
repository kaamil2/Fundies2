package Lab.lab5.Lab7;

class MTLoBuddy implements ILoBuddy {
  MTLoBuddy() {
  }

  /*
   * Template:
   * Fields:
   * None
   *
   * Methods:
   * directBuddyHelper(Person that) -- boolean
   * countCommmonBuddieshelper(Person that, ILoBuddy thisBuddies) -- int
   * hasExtendedBuddies(Person that, ILoBuddy partyPeople) -- boolean
   * partyCountHelper(ILoBuddy partyList) -- ILoBuddy
   * count() -- int
   * countHelp(int count) -- int
   *
   * Methods for fields:
   * None
   */


  // Returns true if this person is a direct buddy of that person
  public boolean directBuddyHelper(Person that) {
    return false;
  }

  // Returns the number of common buddies between this person and that person
  public int countCommmonBuddieshelper(Person that, ILoBuddy thisBuddies) {
    return 0;
  }

  // Returns true if this person has extended buddies with that person
  public boolean hasExtendedBuddies(Person that, ILoBuddy partyPeople) {
    return false;
  }

  // Returns the list of people who are in both this list and the given list
  public ILoBuddy partyCountHelper(ILoBuddy partyList) {
    return partyList;
  }

  // Returns the number of people in this list

  public int count() {
    return 0;
  }


}
