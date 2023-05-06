package Lab.lab5.Lab7;

interface ILoBuddy {

  // Returns true if this person is a direct buddy of that person
  boolean directBuddyHelper(Person that);

  // Returns the number of common buddies between this person and that person
  int countCommmonBuddieshelper(Person that, ILoBuddy thisBuddies);

  // Returns true if this person has extended buddies with that person
  boolean hasExtendedBuddies(Person that, ILoBuddy partyPeople);

  // Returns the list of people who are in both this list and the given list
  ILoBuddy partyCountHelper(ILoBuddy partyList);

  // Returns the number of people in this list
  int count();





}
