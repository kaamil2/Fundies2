package Exam;

import java.util.ArrayList;
import java.util.Arrays;
import tester.Tester;



class Student {
  String name;
  int age;

  Student(String name, int age) {
    this.name = name;
    this.age = age;

  }

  @Override
  public boolean equals(Object o){

    if(! (o instanceof Student)) {
      return false;

    }

    Student that = (Student) o;

    return this.age == that.age && this.name.equals(that.name);

  }

  @Override
  public int hashCode(){
    return this.name.hashCode()*100 + this.age;
  }






}





/*
class Student {
  String name;
  int age;

  Student(String name, int age) {
    this.name = name;
    this.age = age;

  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }
    Student that = (Student)o;
    return this.name.equals(that.name) && this.age == that.age;
  }

  @Override
  public int hashCode(){
    return this.name.hashCode()*1000 + this.age;
  }


}*/

class ExamplesStudent {
  ExamplesStudent(){

  }


  Student edward = new Student("Edward", 20);
  Student karen = new Student("Karen", 21);

  ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(this.edward,this.karen));

  void testArrayListContains(Tester t) {
    t.checkExpect(this.students.contains(this.edward), true);
    t.checkExpect(this.students.contains(new Student("Edward", 20)), true);
  }


}


