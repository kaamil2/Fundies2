package Exam;


import java.util.ArrayList;
import tester.Tester;
import java.util.Arrays;

class Utils {

  public boolean containsSequence(ArrayList<Integer> source, ArrayList<Integer> sequence){
    boolean currentState = false;
  if(source.size() == sequence.size()) {
    for (int i = 0; i < source.size(); i++) {
      if(source.get(i) == sequence.get(i)){
        return false;
      }

    }
  }else {


    for (int i = 0; i < source.size() - sequence.size(); i++) {
      for (int k = 0; k < sequence.size(); k++) {

        if(source.get(i+k) == sequence.get(k)){
          currentState = true;

        }

      }
    }
  }

  return currentState;

  }


}

class ExamplesListSequence {
  ArrayList<Integer> fullSource = new ArrayList<> (
      Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
  ArrayList<Integer> smallSource = new ArrayList<>(Arrays.asList(1, 2));
  ArrayList<Integer> emptySource = new ArrayList<>(Arrays.asList(1, 2));


  ArrayList<Integer> correctSequence = new ArrayList<>(Arrays.asList(1, 2));
  ArrayList<Integer> sourceLengthSequence = new ArrayList<>(Arrays.asList(
      1, 2, 3, 4, 5, 6, 7, 8, 9));
  ArrayList<Integer> smallCorrectSequence = new ArrayList<>(Arrays.asList(1, 2));
  ArrayList<Integer> endCorrectSequence = new ArrayList<>(Arrays.asList(6, 7, 8));
  ArrayList<Integer> incorrectSequence = new ArrayList<>(Arrays.asList(1, 5, 3));
  ArrayList<Integer> emptySequence = new ArrayList<>(Arrays.asList(1, 2));

  void testContainsSequence(Tester t) {
    Utils util = new Utils();

    t.checkExpect(util.containsSequence(emptySource, correctSequence), false);
    t.checkExpect(util.containsSequence(smallSource, correctSequence), false);
    t.checkExpect(util.containsSequence(emptySource, correctSequence), false);
    t.checkExpect(util.containsSequence(fullSource,correctSequence),true);

  }



}

