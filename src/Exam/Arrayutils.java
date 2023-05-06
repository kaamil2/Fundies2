package Exam;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import tester.Tester;

class ArrayUtils {
  ArrayUtils(){
  }

  int findValue(ArrayList<ArrayList<Integer>> matrix) {

    //finds the largest numnbers in each row

    ArrayList<Integer> largestNumberInEachRow = new ArrayList<Integer>();

    for(int row = 0; row < matrix.size(); row++ ){
      int sizefRow = matrix.get(row).size();
      int maxSoFar = matrix.get(row).get(0);
      for (int col = 1; col < sizefRow; col++) {
        int curNum = matrix.get(row).get(col);

        if(curNum > maxSoFar) {
          maxSoFar = curNum;
        }
      }

      largestNumberInEachRow.add(maxSoFar);
    }
    int numCols = matrix.get(0).size();
    int numRows = matrix.size();

    for (int col = 0; col < numCols; col++) {

      int colIndexFoundMin;
      int minSoFar = matrix.get(0).get(col);

      for(int row = 0; row < numRows; row++) {
        colIndexFoundMin = row;
        int curNum = matrix.get(row).get(col);

        if(curNum < minSoFar) {
          minSoFar = curNum;
        }
      }

      if (largestNumberInEachRow.get(col) == minSoFar) {
        return minSoFar;
      }

    }

    return -1;
  }


  public int maxDiff(ArrayList<Integer> array){

    int maxDiff= 0;
    int curMin = array.get(0);

    for (int i = 1; i < array.size(); i++ ){

      if( curMin > array.get(i)){
        curMin = array.get(i);
      }

      if(maxDiff < array.get(i) - curMin){
        maxDiff = array.get(i) - curMin;
      }

    }


    return maxDiff;

  }











/*

  int maxDiff(ArrayList<Integer> nums) {
    int minSoFar = nums.get(0);
    int maxDiff = 0;

    for(int i = 1; i < nums.size(); i++){
      int curDiff = nums.get(i) - minSoFar;
      if(minSoFar > nums.get(i)){
        minSoFar = nums.get(i);
      }
      if (curDiff > maxDiff){
          maxDiff = curDiff;
      }
    }
    return maxDiff;
  }
*/


}




class ExampleArrayUtils{

  ArrayUtils u = new ArrayUtils();

  ArrayList<Integer> exampleMatrixRow0 = new ArrayList<Integer>(Arrays.asList(2,2,3));
  ArrayList<Integer> exampleMatrixRow1 = new ArrayList<Integer>(Arrays.asList(1,2,2));
  ArrayList<Integer> exampleMatrixRow2 = new ArrayList<Integer>(Arrays.asList(3,4,1));


  ArrayList<ArrayList<Integer>> exampleMatrixRow = new ArrayList<ArrayList<Integer>>
      (Arrays.asList(exampleMatrixRow0,exampleMatrixRow1,exampleMatrixRow2));

  ArrayList<Integer> num1 = new ArrayList<Integer>(Arrays.asList(11,2,6,4,10,7));
  ArrayList<Integer> num2 = new ArrayList<Integer>(Arrays.asList(9,8,7,6,5));

  void testNum(Tester t) {

    t.checkExpect(u.maxDiff(num1), 8);
    t.checkExpect(u.maxDiff(num2),0);
  }









}


