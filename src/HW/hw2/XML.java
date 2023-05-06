package HW.hw2;


import tester.Tester;


// To represent a List of XML
interface ILoXML {

  // Returns the first element of the list
  IXML getFirst();

  // Returns the rest of the list
  ILoXML getRest();

  // Returns the length of the items in the list
  int contentLength();

  // Returns true if the list has the given attribute
  boolean hasAttribute(String att);

  // Returns true if the list has the given tag
  boolean hasTag(String tag);

  // Returns the list as an XML string
  String renderAsXMLString();

  // Returns the list as a string
  String renderAsString();
}

// To represent a empty list of XML
class MtLoXML implements ILoXML {

  /*
   * TEMPLATE
   * Fields:
   * Methods:
   * ... this.getFirst() ... -- IXML
   * ... this.getRest() ... -- ILoXML
   * ... this.contentLength() ... -- int
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.hasTag(String tag) ... -- boolean
   * ... this.renderAsXMLString() ... -- String
   * ... this.renderAsString() ... -- String
   * Methods for Fields:
   * ... this.getFirst().contentLength() ... -- int
   * ... this.getFirst().hasAttribute(String att) ... -- boolean
   * ... this.getFirst().hasTag(String tag) ... -- boolean
   * ... this.getFirst().renderAsXMLString() ... -- String
   * ... this.getFirst().renderAsString() ... -- String
   * ... this.getRest().contentLength() ... -- int
   * ... this.getRest().hasAttribute(String att) ... -- boolean
   * ... this.getRest().hasTag(String tag) ... -- boolean
   * ... this.getRest().renderAsXMLString() ... -- String
   * ... this.getRest().renderAsString() ... -- String
   *
   */
  public IXML getFirst() {
    return new Plaintext("");
  }

  public ILoXML getRest() {
    return new MtLoXML();
  }

  public int contentLength() {
    return 0;
  }

  public boolean hasTag(String tag) {
    return false;
  }

  public boolean hasAttribute(String att) {
    return false;

  }

  public String renderAsXMLString() {
    return "";
  }

  public String renderAsString() {
    return "";
  }

}

// To represent a non-empty list of XML
class ConsLoXML implements ILoXML {
  IXML first;
  ILoXML rest;

  ConsLoXML(IXML first, ILoXML rest) {
    this.first = first;
    this.rest = rest;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.first ... -- IXML
   * ... this.rest ... -- ILoXML
   * Methods:
   * ... this.getFirst() ... -- IXML
   * ... this.getRest() ... -- ILoXML
   * ... this.contentLength() ... -- int
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.hasTag(String tag) ... -- boolean
   * ... this.renderAsXMLString() ... -- String
   * ... this.renderAsString() ... -- String
   * Methods for Fields:
   * ... this.getFirst().contentLength() ... -- int
   * ... this.getFirst().hasAttribute(String att) ... -- boolean
   * ... this.getFirst().hasTag(String tag) ... -- boolean
   * ... this.getFirst().renderAsXMLString() ... -- String
   * ... this.getFirst().renderAsString() ... -- String
   * ... this.getRest().contentLength() ... -- int
   * ... this.getRest().hasAttribute(String att) ... -- boolean
   * ... this.getRest().hasTag(String tag) ... -- boolean
   * ... this.getRest().renderAsXMLString() ... -- String
   * ... this.getRest().renderAsString() ... -- String
   *
   */

  public IXML getFirst() {
    return this.first;
  }

  public ILoXML getRest() {
    return this.rest;
  }

  public int contentLength() {
    return this.first.contentLength() + this.rest.contentLength();
  }

  public boolean hasTag(String tag) {
    return this.first.hasTag(tag) || this.rest.hasTag(tag);
  }

  public boolean hasAttribute(String att) {
    return this.first.hasAttribute(att) || this.rest.hasAttribute(att);
  }

  public String renderAsXMLString() {
    return this.first.renderAsXMLString() + this.rest.renderAsXMLString();
  }

  public String renderAsString() {
    return this.first.renderAsString() + this.rest.renderAsString();
  }

}

// To represent a List of Attributes
interface ILoAtt {

  // Returns true if the list has the given attribute
  boolean hasAttribute(String att);

  // Returns the first element of the list
  Att getFirst();


  // Returns the rest of the list
  ILoAtt getRest();

  // Returns the name of the first element of the list
  String name();

  // Returns the value of the first element of the list
  String value();
}

// To represent a empty list of Attributes
class MtLoAtt implements ILoAtt {

  /*
   * TEMPLATE
   * Fields:
   * Methods:
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.getFirst() ... -- Att
   * ... this.getRest() ... -- ILoAtt
   * ... this.name() ... -- String
   * ... this.value() ... -- String
   * Methods for Fields:
   * ... this.getFirst().name() ... -- String
   * ... this.getFirst().value() ... -- String
   * ... this.getRest().hasAttribute(String att) ... -- boolean
   * ... this.getRest().getFirst() ... -- Att
   * ... this.getRest().getRest() ... -- ILoAtt
   * ... this.getRest().name() ... -- String
   * ... this.getRest().value() ... -- String
   *
   */
  public boolean hasAttribute(String att) {
    return false;
  }

  public ILoAtt getRest() {
    return new MtLoAtt();
  }

  public Att getFirst() {
    return new Att("", "");
  }

  public String name() {
    return "";
  }

  public String value() {
    return "";
  }
}

// To represent a non-empty list of Attributes
class ConsLoAtt implements ILoAtt {

  // The first element of the list, an attribute
  Att first;

  // The rest of the list
  ILoAtt rest;

  /*
   * TEMPLATE
   * Fields:
   * ... this.first ... -- Att
   * ... this.rest ... -- ILoAtt
   * Methods:
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.getFirst() ... -- Att
   * ... this.getRest() ... -- ILoAtt
   * ... this.name() ... -- String
   * ... this.value() ... -- String
   * Methods for Fields:
   * ... this.getFirst().name() ... -- String
   * ... this.getFirst().value() ... -- String
   * ... this.getRest().hasAttribute(String att) ... -- boolean
   * ... this.getRest().getFirst() ... -- Att
   * ... this.getRest().getRest() ... -- ILoAtt
   * ... this.getRest().name() ... -- String
   * ... this.getRest().value() ... -- String
   *
   */

  ConsLoAtt(Att first, ILoAtt rest) {
    this.first = first;
    this.rest = rest;
  }

  public boolean hasAttribute(String att) {
    return this.first.name().equals(att) || this.rest.hasAttribute(att);
  }

  public ILoAtt getRest() {
    return this.rest;
  }

  public Att getFirst() {
    return this.first;
  }

  public String name() {
    return this.first.name;
  }

  public String value() {
    return this.first.value;
  }

}

// To represent an interface of XML
interface IXML {

  // Returns the length of the content of this XML
  int contentLength();

  // Returns true if this XML has the given tag
  boolean hasTag(String tag);

  // Returns true if this XML has the given attribute
  boolean hasAttribute(String att);

  // Returns the XML as a string
  String renderAsXMLString();

  // Returns the content of this XML as a string
  String renderAsString();

}

// To represent a simple String as XML
class Plaintext implements IXML {
  String txt;

  Plaintext(String txt) {
    this.txt = txt;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.txt ... -- String
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String tag) ... -- boolean
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.renderAsXMLString() ... -- String
   * ... this.renderAsString() ... -- String
   * Methods for Fields:
   *
   */

  public int contentLength() {
    return this.txt.length();
  }

  public boolean hasTag(String tag) {
    return false;
  }

  public boolean hasAttribute(String att) {
    return false;
  }

  public String renderAsXMLString() {
    return this.txt;
  }

  public String renderAsString() {
    return this.txt;
  }
}

// To represent a untagged XML
class Untagged implements IXML {
  ILoXML content;

  Untagged(ILoXML content) {
    this.content = content;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.content ... -- ILoXML
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String tag) ... -- boolean
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.renderAsXMLString() ... -- String
   * ... this.renderAsString() ... -- String
   * Methods for Fields:
   * ... this.content.contentLength() ... -- int
   * ... this.content.hasTag(String tag) ... -- boolean
   * ... this.content.hasAttribute(String att) ... -- boolean
   * ... this.content.renderAsXMLString() ... -- String
   * ... this.content.renderAsString() ... -- String
   *
   */
  public int contentLength() {
    return this.content.getFirst().contentLength() + this.content.getRest().contentLength();
  }

  public boolean hasTag(String tag) {
    return this.content.getFirst().hasTag(tag) || this.content.getRest().hasTag(tag);
  }

  public boolean hasAttribute(String att) {
    return this.content.getFirst().hasAttribute(att) || this.content.getRest().hasAttribute(att);
  }

  public String renderAsXMLString() {
    return this.content.getFirst().renderAsXMLString()
        + this.content.getRest().renderAsXMLString();
  }

  public String renderAsString() {
    return this.content.getFirst().renderAsString() + this.content.getRest().renderAsString();
  }

}

// To represent a tagged XML
class Tagged implements IXML {
  Tag tag;
  ILoXML content;

  Tagged(Tag tag, ILoXML content) {
    this.tag = tag;
    this.content = content;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.tag ... -- Tag
   * ... this.content ... -- ILoXML
   * Methods:
   * ... this.contentLength() ... -- int
   * ... this.hasTag(String tag) ... -- boolean
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.renderAsXMLString() ... -- String
   * ... this.renderAsString() ... -- String
   * Methods for Fields:
   * ... this.tag.name() ... -- String
   * ... this.tag.attributes() ... -- ILoAtt
   * ... this.content.contentLength() ... -- int
   * ... this.content.hasTag(String tag) ... -- boolean
   * ... this.content.hasAttribute(String att) ... -- boolean
   * ... this.content.renderAsXMLString() ... -- String
   * ... this.content.renderAsString() ... -- String
   *
   */

  public int contentLength() {
    return this.content.getFirst().contentLength() + this.content.getRest().contentLength();
  }

  public boolean hasTag(String tag) {
    return this.tag.name.equals(tag)
        || this.content.getFirst().hasTag(tag)
        || this.content.getRest().hasTag(tag);
  }

  public boolean hasAttribute(String att) {
    return this.tag.hasAttribute(att) || this.content.hasAttribute(att);
  }

  public String renderAsXMLString() {
    return "<" + this.tag.name + this.tag.atts.name() + this.tag.atts.value()
        + this.tag.atts.getRest().name() + this.tag.atts.getRest().value() + ">"
        + this.content.getFirst().renderAsXMLString() + this.content.getRest().renderAsXMLString()
        + "</" + this.tag.name + ">";
  }

  public String renderAsString() {
    return this.content.getFirst().renderAsString() + this.content.getRest().renderAsString();
  }

}

// to represnt a tag as a list of attribute
class Tag {
  String name;
  ILoAtt atts;

  Tag(String name, ILoAtt atts) {
    this.name = name;
    this.atts = atts;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.name ... -- String
   * ... this.atts ... -- ILoAtt
   * Methods:
   * ... this.hasAttribute(String att) ... -- boolean
   * ... this.name() ... -- String
   * ... this.value() ... -- String
   * ... this.getRest() ... -- ILoAtt
   * Methods for Fields:
   * ... this.atts.hasAttribute(String att) ... -- boolean
   * ... this.atts.name() ... -- String
   * ... this.atts.value() ... -- String
   * ... this.atts.getRest() ... -- ILoAtt
   * ... this.atts.getFirst() ... -- Att
   *
   */
  public boolean hasAttribute(String att) {
    return this.atts.getFirst().name().equals(att) || this.atts.getRest().hasAttribute(att);
  }


  public Att getFirst() {
    return this.atts.getFirst();
  }

  public String name() {
    return this.name;
  }

  public String value() {
    return this.atts.getFirst().value() + this.atts.getRest().value();
  }


  public ILoAtt getRest() {
    return this.atts.getRest();
  }

}

class Att {
  String name;
  String value;

  Att(String name, String value) {
    this.name = name;
    this.value = value;
  }

  /*
   * TEMPLATE
   * Fields:
   * ... this.name ... -- String
   * ... this.value ... -- String
   * Methods:
   * ... this.name() ... -- String
   * ... this.value() ... -- String
   *
   */


  public String name() {
    return this.name;
  }

  public String value() {
    return this.value;
  }


}

class ExamplesXML {
  IXML xml1 = new Plaintext("I am XML!");
  IXML xmlScript = new Plaintext("XML");
  Tag tagYell = new Tag("yell", new MtLoAtt());

  ILoXML filledOne = new ConsLoXML(this.xmlScript, new MtLoXML());

  IXML xml2Tagged = new Tagged(this.tagYell, this.filledOne);

  IXML xml2FirstText = new Plaintext("I am ");

  IXML xml2SecondText = new Plaintext("!");

  IXML xml2 = new Untagged(new ConsLoXML(this.xml2FirstText, new ConsLoXML(this.xml2Tagged,
      new ConsLoXML(this.xml2SecondText, new MtLoXML()))));

  Tag tagItalix = new Tag("italic", new MtLoAtt());

  IXML xml3tagContentOne = new Plaintext("X");

  IXML getXml3tagContentTwo = new Plaintext("ML");

  ILoXML xml3ContentListOne = new ConsLoXML(this.xml3tagContentOne, new MtLoXML());

  IXML xml3tagItalix = new Tagged(this.tagItalix, this.xml3ContentListOne);

  IXML xml3YellTag = new Tagged(this.tagYell, new ConsLoXML(this.xml3tagItalix,
      new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML())));


  IXML xml3 = new Untagged(new ConsLoXML(this.xml2FirstText, new ConsLoXML(this.xml3YellTag,
      new ConsLoXML(this.xml2SecondText, new MtLoXML()))));

  Att yellAtt = new Att("volume", "30db");

  Tag yellTag4 = new Tag("yell", new ConsLoAtt(this.yellAtt, new MtLoAtt()));


  IXML xml4Yell = new Tagged(this.yellTag4, new ConsLoXML(this.xml3tagItalix,
      new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML())));

  IXML xml4p2 = new Untagged(new ConsLoXML(new Plaintext("I am "), new ConsLoXML(
      new Tagged(new Tag("yell", new ConsLoAtt(
          new Att("volume", "30db"), new MtLoAtt())),
          new ConsLoXML(new Tagged(new Tag("italic", new MtLoAtt()),
              new ConsLoXML(new Plaintext("X"), new MtLoXML())),
              new ConsLoXML(new Plaintext("ML"), new MtLoXML()))),
      new ConsLoXML(new Plaintext("!"), new MtLoXML()))));

  IXML xml4 = new Untagged(new ConsLoXML(this.xml2FirstText, new ConsLoXML(this.xml4Yell,
      new ConsLoXML(this.xml2SecondText, new MtLoXML()))));


  Att yellAtt2 = new Att("duration", "5sec");

  ILoAtt yellAttList = new ConsLoAtt(this.yellAtt, new ConsLoAtt(this.yellAtt2, new MtLoAtt()));

  Tag yellTag5 = new Tag("yell", new ConsLoAtt(this.yellAtt,
      new ConsLoAtt(this.yellAtt2, new MtLoAtt())));

  IXML xml5Yell = new Tagged(this.yellTag5, new ConsLoXML(this.xml3tagItalix,
      new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML())));

  IXML xml5 = new Untagged(new ConsLoXML(this.xml2FirstText, new ConsLoXML(this.xml5Yell,
      new ConsLoXML(this.xml2SecondText, new MtLoXML()))));

  boolean testMtLoXMLMethod(Tester t) {
    return t.checkExpect(new MtLoXML().contentLength(), 0)
        && t.checkExpect(new MtLoXML().hasTag("italic"), false)
        && t.checkExpect(new MtLoXML().hasTag("yell"), false)
        && t.checkExpect(new MtLoXML().hasAttribute("volume"), false);
  }

  boolean testMtLoAttMethod(Tester t) {
    return t.checkExpect(new MtLoAtt().hasAttribute("volume"), false);
  }


  boolean testILoXMLMethod(Tester t) {
    return t.checkExpect(this.xml3ContentListOne.getFirst(), this.xml3tagContentOne)
        && t.checkExpect(this.xml3ContentListOne.getRest(), new MtLoXML())
        && t.checkExpect(this.xml3ContentListOne.contentLength(), 1)
        && t.checkExpect(this.xml3ContentListOne.hasTag("italic"), false)
        && t.checkExpect(this.xml3ContentListOne.hasTag("yell"), false)
        && t.checkExpect(this.xml3ContentListOne.hasAttribute("volume"), false);

  }

  boolean testAttMethod(Tester t) {
    return t.checkExpect(this.yellAtt.name(), "volume")
        && t.checkExpect(this.yellAtt.value(), "30db");
  }

  boolean testIXMLMethod(Tester t) {
    return t.checkExpect(this.xml1.contentLength(), 9)
        && t.checkExpect(this.xml1.hasTag("italic"), false)
        && t.checkExpect(this.xml1.hasTag("yell"), false)
        && t.checkExpect(this.xml1.hasAttribute("volume"), false)
        && t.checkExpect(xml3.renderAsXMLString(), "I am <yell>"
        + "<italic>X</italic>ML</yell>!")
        && t.checkExpect(xml4.renderAsString(), "I am XML!");
  }

  boolean testILoAttMethod(Tester t) {
    return t.checkExpect(this.yellAttList.name(), "volume")
        && t.checkExpect(this.yellAttList.value(), "30db")
        && t.checkExpect(this.yellAttList.getFirst(), this.yellAtt)
        && t.checkExpect(this.yellAttList.getRest(), new ConsLoAtt(this.yellAtt2, new MtLoAtt()))
        && t.checkExpect(this.yellAttList.hasAttribute("volume"), true)
        && t.checkExpect(this.yellAttList.hasAttribute("duration"), true)
        && t.checkExpect(this.yellAttList.hasAttribute("color"), false);
  }

  boolean testTagMethod(Tester t) {
    return t.checkExpect(new Tag("yell",
        new ConsLoAtt(this.yellAtt, new MtLoAtt())).name(), "yell")
        && t.checkExpect(new Tag("yell",
        new ConsLoAtt(this.yellAtt, new MtLoAtt())).value(), "30db")
        && t.checkExpect(new Tag("yell",
        new ConsLoAtt(this.yellAtt, new MtLoAtt())).hasAttribute("duration"), false)
        && t.checkExpect(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())).getFirst(), this.yellAtt)
        && t.checkExpect(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())).getRest(), new MtLoAtt())
        ;

  }

  boolean testPlaintextMethod(Tester t) {
    return t.checkExpect(new Plaintext("I am").contentLength(), 4)
        && t.checkExpect(new Plaintext("I am").hasTag("italic"), false)
        && t.checkExpect(new Plaintext("I am").hasTag("yell"), false)
        && t.checkExpect(new Plaintext("I am").hasAttribute("volume"), false)
        && t.checkExpect(new Plaintext("I am").renderAsString(), "I am");
  }

  boolean testTaggedMethod(Tester t) {
    return t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).contentLength(), 3)
        && t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasTag("italic"), true)
        && t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(yellAtt,
        new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasTag("yell"), true)
        && t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasAttribute("volume"), true)
        && t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(this.yellAtt,
            new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
            new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).renderAsXMLString(),
        "<yellvolume30db><italic>X</italic>ML</yell>")
        && t.checkExpect(new Tagged(new Tag("yell", new ConsLoAtt(this.yellAtt,
        new MtLoAtt())), new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).renderAsString(), "XML");
  }

  boolean testUntaggedMethod(Tester t) {
    return t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).contentLength(), 3)
        && t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasTag("italic"), true)
        && t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasTag("yell"), false)
        && t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).hasAttribute("volume"), false)
        && t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
            new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).renderAsXMLString(),
        "<italic>X</italic>ML")
        && t.checkExpect(new Untagged(new ConsLoXML(this.xml3tagItalix,
        new ConsLoXML(this.getXml3tagContentTwo, new MtLoXML()))).renderAsString(), "XML");
  }

  // test for contentLength of charcters

  boolean testContentLength(Tester t) {
    return t.checkExpect(this.xml1.contentLength(), 9)
        && t.checkExpect(this.xml2.contentLength(), 9)
        && t.checkExpect(this.xml3.contentLength(), 9)
        && t.checkExpect(this.xml4.contentLength(), 9)
        && t.checkExpect(this.xml5.contentLength(), 9);
  }


  // test for if this has the same tag as provided

  boolean testHasTag(Tester t) {
    return t.checkExpect(this.xml1.hasTag("yell"), false)
        && t.checkExpect(this.xml2.hasTag("yell"), true)
        && t.checkExpect(this.xml2.hasTag("italic"), false)
        && t.checkExpect(this.xml3.hasTag("yell"), true)
        && t.checkExpect(this.xml4.hasTag("italic"), true)
        && t.checkExpect(this.xml5.hasTag("yell"), true);
  }


  // test for if this has the same attribute as provided
  boolean testHasAttribute(Tester t) {
    return t.checkExpect(this.xml1.hasAttribute("volume"), false)
        && t.checkExpect(this.xml2.hasAttribute("volume"), false)
        && t.checkExpect(this.xml3.hasAttribute("volume"), false)
        && t.checkExpect(this.xml4.hasAttribute("volume"), true)
        && t.checkExpect(this.xml5.hasAttribute("volume"), true)
        && t.checkExpect(this.xml4.hasAttribute("duration"), false)
        && t.checkExpect(this.xml5.hasAttribute("duration"), true)
        && t.checkExpect(this.xml3.hasAttribute("italic"), false)
        && t.checkExpect(this.xml4.hasAttribute("italic"), false);
  }

  // test for renderAsString

  boolean testRenderAsString(Tester t) {
    return t.checkExpect(this.xml1.renderAsString(), "I am XML!")
        && t.checkExpect(this.xml2.renderAsString(), "I am XML!")
        && t.checkExpect(this.xml3.renderAsString(), "I am XML!")
        && t.checkExpect(this.xml4.renderAsString(), "I am XML!")
        && t.checkExpect(this.xml5.renderAsString(), "I am XML!");
  }
  // Test for renderAsXMLString

  boolean testRenderAsXMLString(Tester t) {
    return t.checkExpect(this.xml1.renderAsXMLString(), "I am XML!")
        && t.checkExpect(this.xml2.renderAsXMLString(), "I am <yell>XML</yell>!")
        && t.checkExpect(this.xml3.renderAsXMLString(), "I am <yell>"
        + "<italic>X</italic>ML</yell>!");
  }

}



