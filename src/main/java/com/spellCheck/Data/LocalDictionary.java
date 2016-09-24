package com.spellCheck.Data;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public final class LocalDictionary {

  private static Logger logger = LoggerFactory.getLogger(LocalDictionary.class.getName());

  /*
   * If I was fancy here, I could do some of that fun localization stuff here for dictionaries based on request
   * Headers and such.
   */
  private final static String relativePath = "/src/main/resources/US.dic";

  /*
   * I could have coerced all of this into a single file. I chose not to to give it some actual delineation here.
   * As if I was actually doing something a little larger here. Just saying.
   *
   * I thought about impelementing @Cachable here. Something to talk about. I know it exists. I know fundamentally
   * how it works.
   */

  // @Cacheable(value = "words")
  public static List<String> words() {
      List<String> words = new ArrayList<>();

      /*
       * We gather the local path, and then we concatenate our relative path on it. I hope it works on any non-nix
       * File System, because I'm not sure it does. SORRY!
       */
      String prefix = System.getProperty("user.dir");
      String filePath = prefix.concat(relativePath);

      /*
       * We want to read the lines here from the file. That's essentially the purpose of LocalDictionary is to
       * turn a file into a java wrapper on top of it, and provide a method to get the words in memory.
       *
       * Typical catch on IOException, when the filepath is incorrect (and let me tell you, I spend 20 minutes
       * figuring out what the correct filepath was because I changed absolute directories twice).
       */
      Charset cs = Charset.forName("UTF-8");
      try(Stream<String> lines = Files.lines(Paths.get(filePath), cs)) {
    	  words = lines.collect(Collectors.toList());
      } catch (IOException e) {
          logger.error("Failed to load dictonary.", e);
      }

      if(words.size() == 0) {
          logger.error("Dictionary is empty");
      }

      return words;
  }
}