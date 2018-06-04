package edu.cnm.deepdive.diceware;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class runs a program to run the generator code in Generator.java to generate
 * pass-phrases all generated at the command line.
 */
public class Cmd {

  private static final String DEFAULT_FILENAME = "eff_large_wordlist.txt";
  private static final int DEFAULT_NUMBER_WORDS = 6;

  /**
   * This is the main method for the program.
   * @param args Two input arguments can be used.  The first argument for the filename and the second
   * for the number of words to be added to the random passphrase.
   * @throws FileNotFoundException Thrown if the file for the list of words cannot be found.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String fileName = DEFAULT_FILENAME;
    int numberOfWords = DEFAULT_NUMBER_WORDS;
    if (args.length > 0) {
      fileName = args[0];
      if (args.length > 1) {
        numberOfWords = Integer.parseInt(args[0]);
      }
    }
    String[] words = readWordList(fileName);
    Random rng = new SecureRandom();
    Generator generator = new Generator(words, rng);
    String[] passPhrase = generator.next(numberOfWords);
    for (String word : passPhrase) {
      System.out.print(word + " ");
    }
    System.out.println();
  }

  /**
   * This reads in the list of words from a text file to generate a random array of words.
   * @param filename specifies the file for the text document of words.
   * @return Returns an array of words to use to generate the random passphrase.
   * @throws FileNotFoundException Thrown if the filename cannot be located.
   */
   private static String[] readWordList(String filename) throws FileNotFoundException {
    try (Scanner scanner = new Scanner(new File(filename))) {
      List<String> words = new LinkedList<>();
      scanner.useDelimiter("(\\s*\\d+\\s+)");
      while (scanner.hasNext("\\S+")) {
        String word = scanner.next("\\S+");
        words.add(word);
      }
      return words.toArray(new String[words.size()]);
    }

   }
}
