

import tester.Tester;

// To represent a circuit
interface ICircuit {

  int countComponents();

  double totalVoltage();

  double totalCurrent();

  double totalResistance();

  ICircuit reversePolarity();

}

// A class to represent a battery of a Circuit
class Battery implements ICircuit {
  String name;
  double voltage;
  double nominalResistance;

  Battery(String name, double voltage, double nominalResistance) {
    this.name = name;
    this.voltage = voltage;
    this.nominalResistance = nominalResistance;
  }

  /* TEMPLATE
   * Fields:
   * ... this.name ... -- String
   * ... this.voltage ... -- double
   * ... this.nominalResistance ... -- double
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * Methods for Fields:
   */

  // Counts the number of components in the circuit in regards of a battery
  public int countComponents() {
    return 1;
  }

  // counts the total voltage in the circuit in regards of a battery
  public double totalVoltage() {
    return this.voltage;
  }

  // counts the total current in the circuit in regards of a battery
  public double totalCurrent() {
    return this.voltage / this.nominalResistance;
  }


  // counts the total resistance in the circuit in regards of a battery
  public double totalResistance() {
    return this.nominalResistance;
  }


  // reverses the polarity of the circuit in regards of a battery
  public ICircuit reversePolarity() {
    return new Battery(this.name, -this.voltage, this.nominalResistance);
  }

}


// A class to represent a resistor of a circuit
class Resistor implements ICircuit {
  String name;
  double resistance;



  Resistor(String name, double resistance) {
    this.name = name;
    this.resistance = resistance;

  }
  /* TEMPLATE
   * Fields:
   * ... this.name ... -- String
   * ... this.resistance ... -- double
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * Methods for Fields:
   */

  // Counts the number of components in the circuit in regards to the resistor
  public int countComponents() {
    return 1;
  }


  // counts the total voltage in the circuit in regards to the resistor
  public double totalVoltage() {
    return 0.0;
  }


  // counts the total current in the circuit in regards to the resistor
  public double totalCurrent() {
    return 0.0;
  }


  // counts the total resistance in the circuit in regards to the resistor
  public double totalResistance() {
    return this.resistance;
  }


  // reverses the polarity of the circuit in regards to the resistor
  public ICircuit reversePolarity() {
    return this;
  }

}


// A class to represent a series circuit
class Series implements ICircuit {
  ICircuit first;
  ICircuit second;

  Series(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }

  /* TEMPLATE
   * Fields:
   * ... this.first ... -- ICircuit
   * ... this.second ... -- ICircuit
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * Methods for Fields:
   * ... this.first.countComponents() ... -- int
   * ... this.first.totalVoltage() ... -- double
   * ... this.first.totalCurrent() ... -- double
   * ... this.first.totalResistance() ... -- double
   * ... this.first.reversePolarity() ... -- ICircuit
   * ... this.second.countComponents() ... -- int
   * ... this.second.totalVoltage() ... -- double
   * ... this.second.totalCurrent() ... -- double
   * ... this.second.totalResistance() ... -- double
   * ... this.second.reversePolarity() ... -- ICircuit
   */


  // Counts the number of components in the circuit as a series
  public int countComponents() {
    return (this.first.countComponents() + this.second.countComponents());
  }

  // counts the total voltage in the circuit as a series
  public double totalVoltage() {
    return (this.first.totalVoltage() + this.second.totalVoltage());
  }

  // counts the total resistance in the circuit as a series
  public double totalResistance() {
    return (this.first.totalResistance() + this.second.totalResistance());
  }

  // counts the total current in the circuit as a series
  public double totalCurrent() {
    return (this.totalVoltage() / this.totalResistance());
  }

  // reverses the polarity of the circuit as a series
  public ICircuit reversePolarity() {
    return new Series(this.first.reversePolarity(), this.second.reversePolarity());
  }

}

// A class to represent a parallel circuit
class Parallel implements ICircuit {
  ICircuit first;
  ICircuit second;

  Parallel(ICircuit first, ICircuit second) {
    this.first = first;
    this.second = second;
  }

  /* TEMPLATE
   * Fields:
   * ... this.first ... -- ICircuit
   * ... this.second ... -- ICircuit
   * Methods:
   * ... this.countComponents() ... -- int
   * ... this.totalVoltage() ... -- double
   * ... this.totalCurrent() ... -- double
   * ... this.totalResistance() ... -- double
   * ... this.reversePolarity() ... -- ICircuit
   * Methods for Fields:
   * ... this.first.countComponents() ... -- int
   * ... this.first.totalVoltage() ... -- double
   * ... this.first.totalCurrent() ... -- double
   * ... this.first.totalResistance() ... -- double
   * ... this.first.reversePolarity() ... -- ICircuit
   * ... this.second.countComponents() ... -- int
   * ... this.second.totalVoltage() ... -- double
   * ... this.second.totalCurrent() ... -- double
   * ... this.second.totalResistance() ... -- double
   * ... this.second.reversePolarity() ... -- ICircuit
   */


  // Counts the number of components in the circuit as a parallel
  public int countComponents() {
    return (this.first.countComponents() + this.second.countComponents());
  }

  // counts the total voltage in the circuit as a parallel
  public double totalVoltage() {
    return (this.first.totalVoltage() + this.second.totalVoltage());
  }

  // counts the total resistance in the circuit as a parallel
  public double totalResistance() {
    return (1 / ((1 / this.first.totalResistance()) + (1 / this.second.totalResistance())));

  }

  // counts the total current in the circuit as a parallel
  public double totalCurrent() {

    return (this.totalVoltage() / this.totalResistance());

  }

  // reverses the polarity of the circuit as a parallel
  public ICircuit reversePolarity() {
    return new Parallel(this.first.reversePolarity(), this.second.reversePolarity());
  }

}

class ExamplesCircuits {
  ICircuit batteryOne = new Battery("B 1", 10, 25);
  ICircuit batterytest = new Battery("t 1", .1, .5);
  ICircuit batterytesttwo = new Battery("t 2", .4, .10);
  ICircuit series = new Series(batterytest, batterytesttwo);
  ICircuit batteryTwo = new Battery("BT 1", 10, 0);
  ICircuit batteryThree = new Battery("BT 2", 20, 0);
  ICircuit resistorOne = new Resistor("R 1", 100);
  ICircuit resistorTwo = new Resistor("R 2", 250);
  ICircuit resistorThree = new Resistor("R 3", 500);
  ICircuit resistorFour = new Resistor("R 4", 200);
  ICircuit resistorFive = new Resistor("R 5", 50);
  ICircuit simpleSeries = new Series(batteryOne, resistorOne);
  ICircuit seriesOne = new Series(batteryTwo, batteryThree);
  ICircuit seriesTwo = new Series(resistorFour, resistorFive);
  ICircuit complexCircuitOne = new Parallel(seriesTwo, resistorOne);
  ICircuit complexCircuitTwo = new Parallel(complexCircuitOne, resistorTwo);
  ICircuit complexCircuitThree = new Parallel(complexCircuitTwo, resistorThree);
  ICircuit complexCircuit = new Series(seriesOne, complexCircuitThree);


  // Tests the countComponents method
  boolean testCountComponents(Tester t) {
    return t.checkExpect(this.batteryOne.countComponents(), 1)
        && t.checkExpect(this.complexCircuit.countComponents(), 7);

  }


  // Tests the totalVoltage method
  boolean testTotalVoltage(Tester t) {
    return t.checkInexact(this.batteryOne.totalVoltage(), 10.0, 0.001)
        && t.checkInexact(this.complexCircuit.totalVoltage(), 30.0, 0.001)
        && t.checkInexact(this.series.totalVoltage(), 0.5, 0.001)
        && t.checkInexact(this.series.totalCurrent(), 0.833, 0.001);

  }


  // Tests the totalResistance method
  boolean testTotalCurrent(Tester t) {
    return t.checkInexact(this.simpleSeries.totalCurrent(), 0.08, 0.001)
        && t.checkInexact(this.complexCircuit.totalCurrent(), 0.6, 0.001);
  }

  boolean testReversePolarity(Tester t) {
    return t.checkInexact(this.batteryOne.reversePolarity().totalVoltage(), -10.0, 0.001)
        && t.checkInexact(this.complexCircuit.reversePolarity().totalVoltage(), -30.0, 0.001)
        && t.checkInexact(this.simpleSeries.reversePolarity().totalCurrent(), -.08, 0.001)
        && t.checkInexact(this.complexCircuit.reversePolarity().totalCurrent(), -.6, 0.001);
  }

}
