package Lab.lab5;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import tester.Tester;


interface ILoString {

}

interface IList<T> {

  //filter the list based on the given predicate
  IList<T> filter(Predicate<T> pred);

  //convert the list to a list of a different type
  <U> IList<U> map(Function<T, U> converter);

  //fold the list to a single value
  <U> U fold(BiFunction<T, U, U> converter, U initial);
}

//represents an empty list
class MtList<T> implements IList<T> {

  /*
   * TEMPLATE
   * FIELDS
   * METHODS
   * ... this.filter(Predicate<T> pred) ... -- IList<T>
   * ... this.map(Function<T,U> converter) ... -- IList<U>
   * ... this.fold(BiFunction<T,U,U> converter,U initial) ... -- U
   * METHODS FOR FIELDS
   *
   */

  MtList() {
  }

  //filter the list based on the given predicate
  @Override
  public IList<T> filter(Predicate<T> pred) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.pred... -- Predicate<T>
     * METHODS
     * ...this.test(T t)... -- boolean
     * METHODS FOR FIELDS
     * ...this.pred.test(T t)... -- boolean
     */

    // TODO Auto-generated method stub
    return new MtList<T>();
  }

  //convert the list to a list of a different type
  @Override
  public <U> IList<U> map(Function<T, U> converter) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.converter... -- Function<T,U>
     * METHODS
     * ...this.apply(T t)... -- U
     * METHODS FOR FIELDS
     * ...this.converter.apply(T t)... -- U
     */
    // TODO Auto-generated method stub
    return new MtList<U>();
  }


  //fold the list to a single value
  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.converter... -- BiFunction<T,U,U>
     * ...this.initial... -- U
     * METHODS
     * ...this.apply(T t,U u)... -- U
     * METHODS FOR FIELDS
     * ...this.converter.apply(T t,U u)... -- U
     */
    // TODO Auto-generated method stub
    return initial;
  }
}


//represents a non-empty list
class ConsList<T> implements IList<T> {

  //the first element of the list
  T first;

  //the rest of the list
  IList<T> rest;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.first... -- T
   * ...this.rest... -- IList<T>
   * METHODS
   * ...this.filter(Predicate<T> pred)... -- IList<T>
   * ...this.map(Function<T,U> converter)... -- IList<U>
   * ...this.fold(BiFunction<T,U,U> converter,U initial)... -- U
   * METHODS FOR FIELDS
   * ...this.rest.filter(Predicate<T> pred)... -- IList<T>
   * ...this.rest.map(Function<T,U> converter)... -- IList<U>
   * ...this.rest.fold(BiFunction<T,U,U> converter,U initial)... -- U
   */


  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  //filter the list based on the given predicate
  @Override
  public IList<T> filter(Predicate<T> pred) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.pred... -- Predicate<T>
     * METHODS
     * ...this.test(T t)... -- boolean
     * METHODS FOR FIELDS
     * ...this.pred.test(T t)... -- boolean
     */
    // TODO Auto-generated method stub
    if (pred.test(this.first)) {
      return new ConsList<T>(this.first, this.rest.filter(pred));
    } else {
      return this.rest.filter(pred);
    }
  }

  //convert the list to a list of a different type
  @Override
  public <U> IList<U> map(Function<T, U> converter) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.converter... -- Function<T,U>
     * METHODS
     * ...this.apply(T t)... -- U
     * METHODS FOR FIELDS
     * ...this.converter.apply(T t)... -- U
     */
    // TODO Auto-generated method stub
    return new ConsList<U>(converter.apply(this.first), this.rest.map(converter));
  }

  //fold the list to a single value
  @Override
  public <U> U fold(BiFunction<T, U, U> converter, U initial) {

    /*
     * TEMPLATE
     * FIELDS
     * ...this.converter... -- BiFunction<T,U,U>
     * ...this.initial... -- U
     * METHODS
     * ...this.apply(T t,U u)... -- U
     * METHODS FOR FIELDS
     * ...this.converter.apply(T t,U u)... -- U
     */
    // TODO Auto-generated method stub
    return converter.apply(this.first, this.rest.fold(converter, initial));
  }
}


//examples and tests
class ExamplesLists {

  ExamplesLists() {
  }

  IList<String> months = new ConsList<String>("January", new ConsList<String>("February",

      new ConsList<String>("March",
          new ConsList<String>("April",

              new ConsList<String>("May",
                  new ConsList<String>("June",

                      new ConsList<String>("July",
                          new ConsList<String>("August",

                              new ConsList<String>("September",
                                  new ConsList<String>("October",

                                      new ConsList<String>("November",
                                          new ConsList<String>("December",

                                              new MtList<String>()))))))))))));

  boolean testFilter(Tester t) {

    return t.checkExpect(months.filter(x -> x.startsWith("J")),
        new ConsList<String>("January",
            new ConsList<String>("June",
                new ConsList<String>("July",
                    new MtList<String>()))));
  }

  boolean testForER(Tester t) {
    return t.checkExpect(months.filter(x -> x.contains("er")),
        new ConsList<String>("September",
            new ConsList<String>("October",
                new ConsList<String>("November",
                    new MtList<String>()))));
  }


  boolean testMap(Tester t) {
    return t.checkExpect(months.map(x -> x.substring(0, 3)),
        new ConsList<String>("Jan",
            new ConsList<String>("Feb",
                new ConsList<String>("Mar",
                    new ConsList<String>("Apr",
                        new ConsList<String>("May",
                            new ConsList<String>("Jun",
                                new ConsList<String>("Jul",
                                    new ConsList<String>("Aug",
                                        new ConsList<String>("Sep",
                                            new ConsList<String>("Oct",
                                                new ConsList<String>("Nov",
                                                    new ConsList<String>("Dec",
                                                        new MtList<String>())))))))))))));
  }

}