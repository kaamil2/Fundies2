package Lab.lab5.Lab7;

class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;
  /*
   * Template:
   * Fields:
   *
   * this.first -- Person
   * this.rest -- ILoBuddy
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
   * this.rest.directBuddyHelper(Person that) -- boolean
   * this.rest.countCommmonBuddieshelper(Person that, ILoBuddy thisBuddies) -- int
   * this.rest.hasExtendedBuddies(Person that, ILoBuddy partyPeople) -- boolean
   * this.rest.partyCountHelper(ILoBuddy partyList) -- ILoBuddy
   * this.rest.count() -- int
   * this.rest.countHelp(int count) -- int
   */


  ConsLoBuddy(Person first, ILoBuddy rest) {

    this.first = first;
    this.rest = rest;
  }

  /*
   * Template:
   * Fields:
   *
   * this.first -- Person
   * this.rest -- ILoBuddy
   * Methods:
   * directBuddyHelper -- Person -> boolean
   *
   *
   * Methods for fields:
   * this,rest.directBuddyHelper -- Person -> boolean
   *
   */

  // Returns true if this person is a direct buddy of that person
  public boolean directBuddyHelper(Person that) {

    /*
     * Template:
     * Fields:
     *
     * this.first -- Person
     * this.rest -- ILoBuddy
     * Methods:
     * directBuddyHelper -- Person -> boolean
     *
     *
     * Methods for fields:
     * this,rest.directBuddyHelper -- Person -> boolean
     *
     */

    return this.first.username.equals(that.username)
        || this.rest.directBuddyHelper(that);
  }


  // Returns the number of common buddies between this person and that person
  public int countCommmonBuddieshelper(Person that, ILoBuddy thisBuddies) {

    /*
     * Template:
     * Fields:
     *
     * this.first -- Person
     * this.rest -- ILoBuddy
     * Methods:
     * directBuddyHelper -- Person -> boolean
     *
     *
     * Methods for fields:
     * this,rest.directBuddyHelper -- Person -> boolean
     *
     */
    if (thisBuddies.directBuddyHelper(this.first)) {
      return 1 + this.rest.countCommmonBuddieshelper(that, thisBuddies);
    } else {
      return this.rest.countCommmonBuddieshelper(that, thisBuddies);
    }
  }


  // Returns true if this person has extended buddies with that person
  public boolean hasExtendedBuddies(Person that, ILoBuddy partyPeople) {

    /*
     * Template:
     * Fields:
     *
     * this.first -- Person
     * this.rest -- ILoBuddy
     * Methods:
     * directBuddyHelper -- Person -> boolean
     *
     *
     * Methods for fields:
     * this,rest.directBuddyHelper -- Person -> boolean
     *
     */

    if (partyPeople.directBuddyHelper(this.first)) {
      return this.rest.hasExtendedBuddies(that, partyPeople);
    } else {
      return this.rest.hasExtendedBuddies(that, new ConsLoBuddy(this.first, partyPeople))
          || this.first.username.equals(that.username)
          || this.first.hasExtendedBuddy(that, new ConsLoBuddy(this.first, partyPeople));
    }

    //return this.first.hasDirectBuddy(that) || this.rest.hasExtendedBuddies(that);
  }


  // counts the number of people who will show up at the party
  public ILoBuddy partyCountHelper(ILoBuddy partyList) {

    /*
     * Template:
     * Fields:
     *
     * this.first -- Person
     * this.rest -- ILoBuddy
     * Methods:
     * directBuddyHelper -- Person -> boolean
     *
     *
     * Methods for fields:
     * this,rest.directBuddyHelper -- Person -> boolean
     *
     * methods for parameters:
     * this.first.getParty -- ILoBuddy -> ILoBuddy
     * this.rest.partyCountHelper -- ILoBuddy -> ILoBuddy
     * partyList.directBuddyHelper -- Person -> boolean
     *
     */

    if (partyList.directBuddyHelper(this.first)) {
      return this.rest.partyCountHelper(partyList);
    } else {
      return this.rest.partyCountHelper(this.first.getParty(partyList));
    }
  }

  // counts the number of people who will show up at the party
  public int count() {
    return 1 + this.rest.count();
  }







}
