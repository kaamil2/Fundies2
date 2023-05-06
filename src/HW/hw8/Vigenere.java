package HW.hw8;

import tester.Tester;

import java.util.ArrayList;
import java.util.Arrays;


class Vigenere {
  ArrayList<Character> alphabet = new ArrayList<Character>(Arrays.asList('a', 'b',
      'c', 'd', 'e', 'f', 'g', 'h',
      'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
      'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
  ArrayList<ArrayList<Character>> table;

  Vigenere() {
    this.table = this.initVigenere();
  }

  Vigenere(ArrayList<Character> alphabet) {
    this.alphabet = alphabet;
    this.table = this.initVigenere();
  }

  ArrayList<ArrayList<Character>> initVigenere() {
    ArrayList<ArrayList<Character>> newTable = new ArrayList<ArrayList<Character>>();
    for (int i = 0; i < alphabet.size(); i++) {
      ArrayList<Character> row = new ArrayList<Character>();
      for (int j = 0; j < alphabet.size(); j++) {
        char c = alphabet.get((j + i) % 26);
        row.add(c);
      }
      newTable.add(row);
    }
    return newTable;
  }

  String decode(String message, String keyword) {
    String key = this.generateKey(message, keyword);
    StringBuilder decodeText = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      char c = message.charAt(i);
      int row = this.alphabet.indexOf(c);
      int col = this.alphabet.indexOf(key.charAt(i));
      decodeText.append(this.table.get(row).get(col));
    }
    return decodeText.toString();
  }

  String generateKey(String message, String keyword) {
    StringBuilder key = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      key.append(keyword.charAt(i % keyword.length()));
    }
    return key.toString();
  }

  String encode(String message, String keyword) {
    String key = this.generateKey(message, keyword);
    StringBuilder newText = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      char c = message.charAt(i);
      int row = this.alphabet.indexOf(key.charAt(i));
      int col = this.alphabet.indexOf(c);
      newText.append(this.table.get(row).get(col));
    }
    return newText.toString();
  }

}

class ExamplesVigenere {
  Vigenere vtableempty = new Vigenere();
  Vigenere vtable2 = new Vigenere(new ArrayList<Character>(Arrays.asList('z', 'x', 'y', 'w',
      'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k',
      'j', 'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a')));

  void testInitVigenere(Tester t) {
    t.checkExpect(this.vtableempty.table.get(0).get(0), 'a');
    t.checkExpect(this.vtableempty.table.get(0).get(1), 'b');
    t.checkExpect(this.vtableempty.table.get(0).get(25), 'z');
    t.checkExpect(this.vtableempty.table.get(1).get(0), 'b');
    t.checkExpect(this.vtableempty.table.get(1).get(1), 'c');
    t.checkExpect(this.vtableempty.table.get(1).get(25), 'a');
    t.checkExpect(this.vtableempty.table.get(25).get(0), 'z');
    t.checkExpect(this.vtableempty.table.get(25).get(1), 'a');
    t.checkExpect(this.vtableempty.table.get(25).get(25), 'y');
    t.checkExpect(this.vtable2.table.get(0).get(0), 'z');
    t.checkExpect(this.vtable2.table.get(0).get(1), 'x');
    t.checkExpect(this.vtable2.table.get(0).get(25), 'a');
    t.checkExpect(this.vtable2.table.get(1).get(0), 'x');
    t.checkExpect(this.vtable2.table.get(1).get(1), 'y');
    t.checkExpect(this.vtable2.table.get(1).get(25), 'z');
    t.checkExpect(this.vtable2.table.get(25).get(0), 'a');
    t.checkExpect(this.vtable2.table.get(25).get(1), 'z');
    t.checkExpect(this.vtable2.table.get(25).get(25), 'b');
  }

  void testGenerateKey(Tester t) {
    t.checkExpect(this.vtable2.generateKey("hellooooooo", "world"), "worldworldw");
    t.checkExpect(this.vtable2.generateKey("hello", "worldworld"), "world");
    t.checkExpect(this.vtableempty.generateKey("hello", "worldworldworld"), "world");
    t.checkExpect(this.vtableempty.generateKey("", "worldisCrazy"), "");
  }

  void testEncode(Tester t) {
    t.checkExpect(this.vtableempty.encode("", "world"), "");
    t.checkExpect(this.vtableempty.encode("iceytopp", "isle"), "qupcbgat");
    t.checkExpect(this.vtableempty.encode("isleopopphscih", "world"), "egcprlcgakoqzs");
    t.checkExpect(this.vtableempty.encode("mincedmeat", "worldworldworld"), "iwenhzavlw");
  }

  void testDecode(Tester t) {
    t.checkExpect(this.vtableempty.decode("", "world"), "");
    t.checkExpect(this.vtableempty.decode("mintermatch", "mop"), "ywcfsgyoiov");
    t.checkExpect(this.vtableempty.decode("jgnnqjgnnqjgnnq", "worldworldworld"), "fueytfueytfueyt");
    t.checkExpect(this.vtableempty.decode("qupcbgat", "isle"), "ymagjylx");
  }


}

