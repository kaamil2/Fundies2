

import tester.Tester;

// a piece of media
interface IMedia {

  // is this media really old?
  boolean isReallyOld();

  // are captions available in this language?
  boolean isCaptionAvailable(String language);

  // a string showing the proper display of the media
  String format();
}

abstract class AMedia implements IMedia {

  String title;
  ILoString captionOptions;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.title... -- String
   * ...this.captionOptions... -- ILoString
   *
   * METHODS
   * ...this.isReallyOld()... -- boolean
   * ...this.isCaptionAvailable(String language)... -- boolean
   * ...this.format()... -- String
   *
   * METHODS FOR FIELDS
   * ...this.captionOptions.captionOptionAvailable(String language)... -- boolean
   */

  AMedia(String title, ILoString captionOptions) {
    this.title = title;
    this.captionOptions = captionOptions;
  }

  public boolean isReallyOld() {
    return false;
  }

  public boolean isCaptionAvailable(String language) {
    return this.captionOptions.captionOptionAvailable(language);
  }

  public abstract String format();

}

// represents a movie
class Movie extends AMedia {
  int year;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.title... -- String
   * ...this.captionOptions... -- ILoString
   * ...this.year... -- int
   *
   * METHODS
   * ...this.isReallyOld()... -- boolean
   * ...this.isCaptionAvailable(String language)... -- boolean
   * ...this.format()... -- String
   *
   * METHODS FOR FIELDS
   * ...this.captionOptions.captionOptionAvailable(String language)... -- boolean
   */

  Movie(String title, int year, ILoString captionOptions) {
    super(title, captionOptions);
    this.year = year;

  }

  @Override
  public boolean isReallyOld() {
    return this.year < 1930;
  }


  public boolean isCaptionAvailable(String language) {
    return this.captionOptions.captionOptionAvailable(language);
  }

  public String format() {
    return this.title + " (" + this.year + ")";
  }
}

// represents a TV episode
class TVEpisode extends AMedia {
  String showName;
  int seasonNumber;
  int episodeOfSeason;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.title... -- String
   * ...this.captionOptions... -- ILoString
   * ...this.showName... -- String
   * ...this.seasonNumber... -- int
   * ...this.episodeOfSeason... -- int
   *
   * METHODS
   * ...this.isReallyOld()... -- boolean
   * ...this.isCaptionAvailable(String language)... -- boolean
   * ...this.format()... -- String
   *
   * METHODS FOR FIELDS
   * ...this.captionOptions.captionOptionAvailable(String language)... -- boolean
   */

  TVEpisode(String title, String showName, int seasonNumber, int episodeOfSeason,
            ILoString captionOptions) {
    super(title, captionOptions);
    this.showName = showName;
    this.seasonNumber = seasonNumber;
    this.episodeOfSeason = episodeOfSeason;

  }

  public boolean isReallyOld() {
    return false;
  }


  // are captions available in this language?
  public String format() {
    return this.showName + " " + this.seasonNumber + "." + this.episodeOfSeason + " - "
        + this.title;
  }
}

// represents a YouTube video
class YTVideo extends AMedia {

  String channelName;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.title... -- String
   * ...this.captionOptions... -- ILoString
   * ...this.channelName... -- String
   *
   * METHODS
   * ...this.isReallyOld()... -- boolean
   * ...this.isCaptionAvailable(String language)... -- boolean
   * ...this.format()... -- String
   *
   * METHODS FOR FIELDS
   * ...this.captionOptions.captionOptionAvailable(String lang)... -- boolean
   */

  public YTVideo(String title, String channelName, ILoString captionOptions) {
    super(title, captionOptions);
    this.channelName = channelName;
  }

  // is this media really old?
  public boolean isReallyOld() {

    return false;
  }


  // are captions available in this language?
  public String format() {

    return this.title + " by " + this.channelName;
  }

}

// lists of strings
interface ILoString {

  // is this language available in this list of strings?
  boolean captionOptionAvailable(String lang);

}

// an empty list of strings
class MtLoString implements ILoString {

  MtLoString() {
  }
  /*
   * TEMPLATE
   * FIELDS
   * METHODS
   * ...this.captionOptionAvailable(String lang)... -- boolean
   */


  public boolean captionOptionAvailable(String lang) {
    return false;
  }
}

// a non-empty list of strings
class ConsLoString implements ILoString {
  String first;
  ILoString rest;

  /*
   * TEMPLATE
   * FIELDS
   * ...this.first... -- String
   * ...this.rest... -- ILoString
   *
   * METHODS
   * ...this.captionOptionAvailable(String lang)... -- boolean
   *
   * METHODS FOR FIELDS
   * ...this.rest.captionOptionAvailable(String lang)... -- boolean
   */


  ConsLoString(String first, ILoString rest) {
    this.first = first;
    this.rest = rest;
  }


  // is this language available in this list of strings?
  public boolean captionOptionAvailable(String lang) {
    return this.first.equals(lang) || this.rest.captionOptionAvailable(lang);
  }
}

class ExamplesMedia {

  ILoString captionOpt1 = new ConsLoString("English", new ConsLoString("Spanish",
      new ConsLoString("French", new ConsLoString("Dutch",
          new ConsLoString("Chinese",
              new ConsLoString("Urdu", new MtLoString()))))));

  ILoString captionOpt2 = new ConsLoString("English",
      new ConsLoString("Spanish", new MtLoString()));
  IMedia movie1 = new Movie("Shawshank Redemption", 1994, captionOpt1);
  IMedia movie2 = new Movie("The Godfather", 1972, captionOpt2);

  IMedia movie3 = new Movie("The It", 1929, captionOpt2);
  IMedia tv1 = new TVEpisode("The Office", "The Office", 1,
      1, captionOpt2);
  IMedia tv2 = new TVEpisode("Game Of Thrones", "Game Of Thrones",
      3, 7,
      captionOpt1);
  IMedia yt1 = new YTVideo("How to make a YouTube video",
      "YouTube", new MtLoString());
  IMedia yt2 = new YTVideo("Sidemen Tinder", "Sidemen", new MtLoString());

  // test the isReallyOld method
  boolean testIsReallyOld(Tester t) {
    return t.checkExpect(movie1.isReallyOld(), false)
        && t.checkExpect(movie2.isReallyOld(), false)
        && t.checkExpect(movie3.isReallyOld(), true)
        && t.checkExpect(tv2.isReallyOld(), false)
        && t.checkExpect(yt1.isReallyOld(), false)
        && t.checkExpect(yt2.isReallyOld(), false);

  }


  // test the isCaptionAvailable method
  boolean testCaptionOption(Tester t) {
    return t.checkExpect(movie1.isCaptionAvailable("English"), true)
        && t.checkExpect(movie2.isCaptionAvailable("Dutch"), false)
        && t.checkExpect(tv1.isCaptionAvailable("Spanish"), true)
        && t.checkExpect(tv2.isCaptionAvailable("Dutch"), true)
        && t.checkExpect(yt2.isCaptionAvailable("English"), false);

  }

  // test the format method
  boolean testFormat(Tester t) {
    return t.checkExpect(movie1.format(), "Shawshank Redemption (1994)")
        && t.checkExpect(movie2.format(), "The Godfather (1972)")
        && t.checkExpect(tv1.format(), "The Office 1.1 - The Office")
        && t.checkExpect(tv2.format(), "Game Of Thrones 3.7 - Game Of Thrones")
        && t.checkExpect(yt1.format(), "How to make a YouTube video by YouTube")
        && t.checkExpect(yt2.format(), "Sidemen Tinder by Sidemen");
  }


}
