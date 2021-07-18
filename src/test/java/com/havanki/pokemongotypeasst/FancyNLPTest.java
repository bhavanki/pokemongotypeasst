package com.havanki.pokemongotypeasst;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FancyNLPTest {

  private FancyNLP nlp;
  private String expected;

  @BeforeEach
  public void beforeEach() {
    nlp = new FancyNLP();
  }

  @Test
  public void testWhatIsThisStrongAgainst() {
    expected = String.format(FancyNLP.WHAT_IS_THIS_STRONG_AGAINST_FORMAT,
                             "WATER", List.of("FIRE", "GROUND", "ROCK"));
    assertEquals(expected, nlp.process("What is water strong against?"));
    assertEquals(expected, nlp.process("What is water strong against"));
    assertEquals(expected, nlp.process("What is water good against?"));
    assertEquals(expected, nlp.process("What's water good against?"));
    assertEquals(expected, nlp.process("What types are water strong against?"));
    assertEquals(expected, nlp.process("Which type is water good against?"));
  }

  @Test
  public void testWhatIsThisWeakAgainst() {
    expected = String.format(FancyNLP.WHAT_IS_THIS_WEAK_AGAINST_FORMAT,
                             "WATER", List.of("DRAGON", "GRASS", "WATER"));
    assertEquals(expected, nlp.process("What is water weak against?"));
    assertEquals(expected, nlp.process("What is water weak against"));
    assertEquals(expected, nlp.process("What is water bad against?"));
    assertEquals(expected, nlp.process("What's water bad against?"));
    assertEquals(expected, nlp.process("What types are water weak against?"));
    assertEquals(expected, nlp.process("Which type is water bad against?"));
  }

  @Test
  public void testWhatIsStrongAgainstThis() {
    expected = String.format(FancyNLP.WHAT_IS_STRONG_AGAINST_THIS_FORMAT,
                             "WATER", List.of("ELECTRIC", "GRASS"));
    assertEquals(expected, nlp.process("What is strong against water?"));
    assertEquals(expected, nlp.process("What is strong against water"));
    assertEquals(expected, nlp.process("What is good against water?"));
    assertEquals(expected, nlp.process("What's good against water?"));
    assertEquals(expected, nlp.process("What types are strong against water?"));
    assertEquals(expected, nlp.process("Which type is good against water?"));
  }

  @Test
  public void testWhatIsWeakAgainstThis() {
    expected = String.format(FancyNLP.WHAT_IS_WEAK_AGAINST_THIS_FORMAT,
                             "WATER", List.of("FIRE", "ICE", "STEEL", "WATER"));
    assertEquals(expected, nlp.process("What is weak against water?"));
    assertEquals(expected, nlp.process("What is weak against water"));
    assertEquals(expected, nlp.process("What is weak against water?"));
    assertEquals(expected, nlp.process("What's weak against water?"));
    assertEquals(expected, nlp.process("What types are weak against water?"));
    assertEquals(expected, nlp.process("Which type is bad against water?"));
  }

  @Test
  public void testSummary() {
    expected = String.format(FancyNLP.SUMMARY_FORMAT,
                             "WATER", List.of("FIRE", "GROUND", "ROCK"),
                             "WATER", List.of("DRAGON", "GRASS", "WATER"),
                             "WATER", List.of("ELECTRIC", "GRASS"),
                             "WATER", List.of("FIRE", "ICE", "STEEL", "WATER"));
    assertEquals(expected, nlp.process("WATER"));
    assertEquals(expected, nlp.process("water"));
  }

  @Test
  public void testHelp() {
    assertEquals(FancyNLP.HELP_MESSAGE, nlp.process("help"));
    assertEquals(FancyNLP.HELP_MESSAGE, nlp.process("Help"));
    assertEquals(FancyNLP.HELP_MESSAGE, nlp.process("usage"));
  }

  @Test
  public void testAbout() {
    assertEquals(FancyNLP.ABOUT_MESSAGE, nlp.process("about"));
  }

  @Test
  public void testJunk() {
    assertEquals(FancyNLP.ERROR_MESSAGE, nlp.process(null));
    assertEquals(FancyNLP.ERROR_MESSAGE, nlp.process(""));
    assertEquals(FancyNLP.ERROR_MESSAGE, nlp.process("poop"));
  }
}
