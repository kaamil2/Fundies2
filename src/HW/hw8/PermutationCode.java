package HW.hw8;

import java.util.*;
import tester.Tester;


class PermutationCode {
  // The original list of characters to be encoded
  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  ArrayList<Character> code = new ArrayList<Character>(26);

  // A random number generator
  Random rand = new Random();

  // Create a new instance of the encoder/decoder with a new permutation code
  PermutationCode() {
    this.code = this.initEncoder();
  }

  // Create a new instance of the encoder/decoder with the given code
  PermutationCode(ArrayList<Character> code) {
    this.code = code;
  }

  // Initialize the encoding permutation of the characters
  ArrayList<Character> initEncoder() {
    ArrayList<Character> newCode = new ArrayList<Character>(this.alphabet);
    return randomize(newCode, newCode.size());
  }

  ArrayList<Character> randomize(ArrayList<Character> array, int x) {
    if (x == 1) {
      return array;
    }
    char temp = array.get(x - 1);
    int randInt = rand.nextInt(x);
    array.set(x - 1, array.get(randInt));
    array.set(randInt, temp);
    return randomize(array, x - 1);
  }

  // produce an encoded String from the given String
  String encode(String source) {
    if (source.isEmpty()) {
      return "";
    }
    char currentChar = source.charAt(0);
    char encodedChar = this.code.get(this.alphabet.indexOf(currentChar));
    return encodedChar + encode(source.substring(1));
  }

  // produce a decoded String from the given String
  String decode(String code) {
    if (code.isEmpty()) {
      return "";
    }
    char currentChar = code.charAt(0);
    char decodedChar = this.alphabet.get(this.code.indexOf(currentChar));
    return decodedChar + decode(code.substring(1));
  }
}

class CodeExamples {

  ArrayList<Character> keyboard = new ArrayList<Character>(
      Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h',
          'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'));

  ArrayList<Character> reverseKeyboard = new ArrayList<Character>(
      Arrays.asList('m', 'n', 'b', 'v', 'c', 'x', 'z', 'l', 'k', 'j', 'h', 'g', 'f', 'd', 's', 'a',
          'p', 'o', 'i', 'u', 'y', 't', 'r', 'e', 'w', 'q'));

  ArrayList<Character> alphabet = new ArrayList<Character>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));

  PermutationCode code1 = new PermutationCode();

  PermutationCode code2 = new PermutationCode(keyboard);

  PermutationCode code3 = new PermutationCode(reverseKeyboard);

  PermutationCode code4 = new PermutationCode(alphabet);

  boolean testEncode(Tester t) {
    return t.checkExpect(code2.encode("hello"), "itssg")
        && t.checkExpect(code3.encode("qwertyuiopasdfghjklzxcvbnm"),
        "prcouwyksamivxzljhgqebtndf")
        && t.checkExpect(code3.encode("hellohellohello"), "lcggslcggslcggs")
        && t.checkExpect(code4.encode("sameword"), "sameword");
  }

  boolean testDecode(Tester t) {
    return t.checkExpect(code2.decode("itssg"), "hello")
        && t.checkExpect(code3.decode("prcouwyksamivxzljhgqebtndf"),
        "qwertyuiopasdfghjklzxcvbnm")
        && t.checkExpect(code3.decode("lcggslcggslcggs"), "hellohellohello")
        && t.checkExpect(code4.decode("sameword"), "sameword");
  }

  boolean testInitEncoder(Tester t) {
    PermutationCode code1 = new PermutationCode();
    ArrayList<Character> originalCode = new ArrayList<Character>(code1.alphabet);
    ArrayList<Character> randomizedCode = code1.initEncoder();

    return t.checkExpect(randomizedCode.size(), originalCode.size())
        && t.checkExpect(randomizedCode.containsAll(originalCode), true)
        && t.checkExpect(randomizedCode.equals(originalCode), false);
  }

  boolean testRandomize(Tester t) {
    ArrayList<Character> originalCode = code1.code;
    ArrayList<Character> randomizedCode =
        code1.randomize(new ArrayList<Character>(originalCode), originalCode.size());
    return t.checkExpect(randomizedCode.size(), originalCode.size())
        && t.checkExpect(randomizedCode.containsAll(originalCode), true)
        && t.checkExpect(originalCode.containsAll(randomizedCode), true)
        && t.checkExpect(originalCode.equals(randomizedCode), false);
  }
}
