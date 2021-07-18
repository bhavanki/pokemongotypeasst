package com.havanki.pokemongotypeasst;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FancyNLP {

  private static final Pattern WHAT_IS_THIS_STRONG_AGAINST_PATTERN =
    Pattern.compile("(?:(?:What|Which) (?:types? )?(?:is|are)|What's) (.+) (?:strong|good) against(?:\\?)?",
                    Pattern.CASE_INSENSITIVE);
  private static final Pattern WHAT_IS_THIS_WEAK_AGAINST_PATTERN =
    Pattern.compile("(?:(?:What|Which) (?:types? )?(?:is|are)|What's) (.+) (?:weak|bad) against(?:\\?)?",
                    Pattern.CASE_INSENSITIVE);
  private static final Pattern WHAT_IS_STRONG_AGAINST_THIS_PATTERN =
    Pattern.compile("(?:(?:What|Which) (?:types? )?(?:is|are)|What's) (?:strong|good) against ([^?]+)(?:\\?)?",
                    Pattern.CASE_INSENSITIVE);
  private static final Pattern WHAT_IS_WEAK_AGAINST_THIS_PATTERN =
    Pattern.compile("(?:(?:What|Which) (?:types? )?(?:is|are)|What's) (?:weak|bad) against ([^?]+)(?:\\?)?",
                    Pattern.CASE_INSENSITIVE);
  private static final Pattern HELP_PATTERN =
    Pattern.compile(".*help.*", Pattern.CASE_INSENSITIVE);
  private static final Pattern USAGE_PATTERN =
    Pattern.compile(".*usage.*", Pattern.CASE_INSENSITIVE);
  private static final Pattern ABOUT_PATTERN =
    Pattern.compile(".*about.*", Pattern.CASE_INSENSITIVE);

  static final String WHAT_IS_THIS_STRONG_AGAINST_FORMAT =
    "The type %s is strong against: %s";
  static final String WHAT_IS_THIS_WEAK_AGAINST_FORMAT =
    "The type %s is weak against: %s";
  static final String WHAT_IS_STRONG_AGAINST_THIS_FORMAT =
    "The following types are strong against %s: %s";
  static final String WHAT_IS_WEAK_AGAINST_THIS_FORMAT =
    "The following types are weak against %s: %s";
  static final String SUMMARY_FORMAT =
    WHAT_IS_THIS_STRONG_AGAINST_FORMAT + "; " +
    WHAT_IS_THIS_WEAK_AGAINST_FORMAT + "; " +
    WHAT_IS_STRONG_AGAINST_THIS_FORMAT + "; " +
    WHAT_IS_WEAK_AGAINST_THIS_FORMAT;
  static final String HELP_MESSAGE = "Try asking: What is strong against " +
    "fighting? or What is fire weak against? Or, just say a type and I'll " +
    "tell you all about it.";
  static final String ERROR_MESSAGE = "Sorry, I don't understand. " +
    HELP_MESSAGE;
  static final String ABOUT_MESSAGE = "I am the Pokemon Go Type Assistant. " +
    HELP_MESSAGE;

  public String process(String message) {
    if (message == null) {
      return ERROR_MESSAGE;
    }
    if (HELP_PATTERN.matcher(message).matches() ||
        USAGE_PATTERN.matcher(message).matches()) {
      return HELP_MESSAGE;
    }
    if (ABOUT_PATTERN.matcher(message).matches()) {
      return ABOUT_MESSAGE;
    }
    Matcher m;
    PokemonType pt;
    List<String> results;

    // Questions
    try {
      m = WHAT_IS_THIS_STRONG_AGAINST_PATTERN.matcher(message);
      if (m.matches()) {
        pt = lookupPokemonType(m);
        results = toStringList(PokemonTypeChart.INSTANCE.whatIsThisStrongAgainst(pt));
        return String.format(WHAT_IS_THIS_STRONG_AGAINST_FORMAT,
                             pt.name(), results.toString());
      }

      m = WHAT_IS_THIS_WEAK_AGAINST_PATTERN.matcher(message);
      if (m.matches()) {
        pt = lookupPokemonType(m);
        results = toStringList(PokemonTypeChart.INSTANCE.whatIsThisWeakAgainst(pt));
        return String.format(WHAT_IS_THIS_WEAK_AGAINST_FORMAT,
                             pt.name(), results.toString());
      }

      m = WHAT_IS_STRONG_AGAINST_THIS_PATTERN.matcher(message);
      if (m.matches()) {
        pt = lookupPokemonType(m);
        results = toStringList(PokemonTypeChart.INSTANCE.whatIsStrongAgainstThis(pt));
        return String.format(WHAT_IS_STRONG_AGAINST_THIS_FORMAT,
                             pt.name(), results.toString());
      }

      m = WHAT_IS_WEAK_AGAINST_THIS_PATTERN.matcher(message);
      if (m.matches()) {
        pt = lookupPokemonType(m);
        results = toStringList(PokemonTypeChart.INSTANCE.whatIsWeakAgainstThis(pt));
        return String.format(WHAT_IS_WEAK_AGAINST_THIS_FORMAT,
                             pt.name(), results.toString());
      }
    } catch (PokemonTypeLookupException e) {
      return e.getMessage();
    }

    // Just a type
    String s = message.trim().toUpperCase();
    try {
      pt = PokemonType.valueOf(s);
      List<String> tsa = toStringList(PokemonTypeChart.INSTANCE.whatIsThisStrongAgainst(pt));
      List<String> twa = toStringList(PokemonTypeChart.INSTANCE.whatIsThisWeakAgainst(pt));
      List<String> sat = toStringList(PokemonTypeChart.INSTANCE.whatIsStrongAgainstThis(pt));
      List<String> wat = toStringList(PokemonTypeChart.INSTANCE.whatIsWeakAgainstThis(pt));
      return String.format(SUMMARY_FORMAT,
                           pt.name(), tsa, pt.name(), twa,
                           pt.name(), sat, pt.name(), wat);
    } catch (IllegalArgumentException e) {
      // oh well
    }

    return ERROR_MESSAGE;
  }

  private static PokemonType lookupPokemonType(Matcher m)
    throws PokemonTypeLookupException {
    try {
      String typeName = m.group(1).trim().toUpperCase();
      return PokemonType.valueOf(typeName);
    } catch (IllegalArgumentException e) {
      throw new PokemonTypeLookupException(m);
    }
  }

  private static class PokemonTypeLookupException extends Exception {
    public PokemonTypeLookupException(Matcher m) {
      super(String.format("I don't know anything about the type %s.",
                          m.group(1).trim()));
    }
  }

  private static List<String> toStringList(List<PokemonType> pts) {
    return pts.stream()
      .map(PokemonType::toString)
      .sorted()
      .collect(Collectors.toList());
  }
}
