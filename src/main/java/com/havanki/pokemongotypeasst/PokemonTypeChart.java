package com.havanki.pokemongotypeasst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PokemonTypeChart {

  // A key type is strong against types in its value list.
  private static final Map<PokemonType, List<PokemonType>> STRONG_AGAINST;
  // A key type is weak against types in its value list.
  private static final Map<PokemonType, List<PokemonType>> WEAK_AGAINST;

  static {
    STRONG_AGAINST = new HashMap<>();
    STRONG_AGAINST.put(PokemonType.NORMAL,
                       List.<PokemonType>of());
    STRONG_AGAINST.put(PokemonType.FIGHTING,
                       List.<PokemonType>of(PokemonType.NORMAL,
                                            PokemonType.ROCK,
                                            PokemonType.STEEL,
                                            PokemonType.ICE,
                                            PokemonType.DARK));
    STRONG_AGAINST.put(PokemonType.FLYING,
                       List.<PokemonType>of(PokemonType.FIGHTING,
                                            PokemonType.BUG,
                                            PokemonType.GRASS));
    STRONG_AGAINST.put(PokemonType.POISON,
                       List.<PokemonType>of(PokemonType.GRASS,
                                            PokemonType.FAIRY));
    STRONG_AGAINST.put(PokemonType.GROUND,
                       List.<PokemonType>of(PokemonType.POISON,
                                            PokemonType.ROCK,
                                            PokemonType.STEEL,
                                            PokemonType.FIRE,
                                            PokemonType.ELECTRIC));
    STRONG_AGAINST.put(PokemonType.ROCK,
                       List.<PokemonType>of(PokemonType.FLYING,
                                            PokemonType.BUG,
                                            PokemonType.FIRE,
                                            PokemonType.ICE));
    STRONG_AGAINST.put(PokemonType.BUG,
                       List.<PokemonType>of(PokemonType.GRASS,
                                            PokemonType.PSYCHIC,
                                            PokemonType.DARK));
    STRONG_AGAINST.put(PokemonType.GHOST,
                       List.<PokemonType>of(PokemonType.GHOST,
                                            PokemonType.PSYCHIC));
    STRONG_AGAINST.put(PokemonType.STEEL,
                       List.<PokemonType>of(PokemonType.ROCK,
                                            PokemonType.ICE,
                                            PokemonType.FAIRY));
    STRONG_AGAINST.put(PokemonType.FIRE,
                       List.<PokemonType>of(PokemonType.BUG,
                                            PokemonType.STEEL,
                                            PokemonType.GRASS,
                                            PokemonType.ICE));
    STRONG_AGAINST.put(PokemonType.WATER,
                       List.<PokemonType>of(PokemonType.GROUND,
                                            PokemonType.ROCK,
                                            PokemonType.FIRE));
    STRONG_AGAINST.put(PokemonType.GRASS,
                       List.<PokemonType>of(PokemonType.GROUND,
                                            PokemonType.ROCK,
                                            PokemonType.WATER));
    STRONG_AGAINST.put(PokemonType.ELECTRIC,
                       List.<PokemonType>of(PokemonType.FLYING,
                                            PokemonType.WATER));
    STRONG_AGAINST.put(PokemonType.PSYCHIC,
                       List.<PokemonType>of(PokemonType.FIGHTING,
                                            PokemonType.POISON));
    STRONG_AGAINST.put(PokemonType.ICE,
                       List.<PokemonType>of(PokemonType.FLYING,
                                            PokemonType.GROUND,
                                            PokemonType.GRASS,
                                            PokemonType.DRAGON));
    STRONG_AGAINST.put(PokemonType.DRAGON,
                       List.<PokemonType>of(PokemonType.DRAGON));
    STRONG_AGAINST.put(PokemonType.DARK,
                       List.<PokemonType>of(PokemonType.GHOST,
                                            PokemonType.PSYCHIC));
    STRONG_AGAINST.put(PokemonType.FAIRY,
                       List.<PokemonType>of(PokemonType.FIGHTING,
                                            PokemonType.DRAGON,
                                            PokemonType.DARK));

    WEAK_AGAINST = new HashMap<>();
    WEAK_AGAINST.put(PokemonType.NORMAL,
                     List.<PokemonType>of(PokemonType.ROCK,
                                          PokemonType.GHOST,
                                          PokemonType.STEEL));
    WEAK_AGAINST.put(PokemonType.FIGHTING,
                     List.<PokemonType>of(PokemonType.FLYING,
                                          PokemonType.POISON,
                                          PokemonType.BUG,
                                          PokemonType.GHOST,
                                          PokemonType.PSYCHIC,
                                          PokemonType.FAIRY));
    WEAK_AGAINST.put(PokemonType.FLYING,
                     List.<PokemonType>of(PokemonType.ROCK,
                                          PokemonType.STEEL,
                                          PokemonType.ELECTRIC));
    WEAK_AGAINST.put(PokemonType.POISON,
                     List.<PokemonType>of(PokemonType.POISON,
                                          PokemonType.GROUND,
                                          PokemonType.ROCK,
                                          PokemonType.GHOST,
                                          PokemonType.STEEL));
    WEAK_AGAINST.put(PokemonType.GROUND,
                     List.<PokemonType>of(PokemonType.FLYING,
                                          PokemonType.BUG,
                                          PokemonType.GRASS));
    WEAK_AGAINST.put(PokemonType.ROCK,
                     List.<PokemonType>of(PokemonType.FIGHTING,
                                          PokemonType.GROUND,
                                          PokemonType.STEEL));
    WEAK_AGAINST.put(PokemonType.BUG,
                     List.<PokemonType>of(PokemonType.FIGHTING,
                                          PokemonType.FLYING,
                                          PokemonType.POISON,
                                          PokemonType.GHOST,
                                          PokemonType.STEEL,
                                          PokemonType.FIRE,
                                          PokemonType.FAIRY));
    WEAK_AGAINST.put(PokemonType.GHOST,
                     List.<PokemonType>of(PokemonType.NORMAL,
                                          PokemonType.DARK));
    WEAK_AGAINST.put(PokemonType.STEEL,
                     List.<PokemonType>of(PokemonType.STEEL,
                                          PokemonType.FIRE,
                                          PokemonType.WATER,
                                          PokemonType.ELECTRIC));
    WEAK_AGAINST.put(PokemonType.FIRE,
                     List.<PokemonType>of(PokemonType.ROCK,
                                          PokemonType.FIRE,
                                          PokemonType.WATER,
                                          PokemonType.DRAGON));
    WEAK_AGAINST.put(PokemonType.WATER,
                     List.<PokemonType>of(PokemonType.WATER,
                                          PokemonType.GRASS,
                                          PokemonType.DRAGON));
    WEAK_AGAINST.put(PokemonType.GRASS,
                     List.<PokemonType>of(PokemonType.FLYING,
                                          PokemonType.POISON,
                                          PokemonType.BUG,
                                          PokemonType.STEEL,
                                          PokemonType.FIRE,
                                          PokemonType.GRASS,
                                          PokemonType.DRAGON));
    WEAK_AGAINST.put(PokemonType.ELECTRIC,
                     List.<PokemonType>of(PokemonType.GROUND,
                                          PokemonType.GRASS,
                                          PokemonType.ELECTRIC,
                                          PokemonType.DRAGON));
    WEAK_AGAINST.put(PokemonType.PSYCHIC,
                     List.<PokemonType>of(PokemonType.STEEL,
                                          PokemonType.PSYCHIC,
                                          PokemonType.DARK));
    WEAK_AGAINST.put(PokemonType.ICE,
                     List.<PokemonType>of(PokemonType.STEEL,
                                          PokemonType.FIRE,
                                          PokemonType.WATER,
                                          PokemonType.ICE));
    WEAK_AGAINST.put(PokemonType.DRAGON,
                     List.<PokemonType>of(PokemonType.STEEL,
                                          PokemonType.FAIRY));
    WEAK_AGAINST.put(PokemonType.DARK,
                     List.<PokemonType>of(PokemonType.FIGHTING,
                                          PokemonType.DARK,
                                          PokemonType.FAIRY));
    WEAK_AGAINST.put(PokemonType.FAIRY,
                     List.<PokemonType>of(PokemonType.POISON,
                                          PokemonType.STEEL,
                                          PokemonType.FIRE));
  }

  public static final PokemonTypeChart INSTANCE = new PokemonTypeChart();

  private PokemonTypeChart() {
  }

  public List<PokemonType> whatIsThisStrongAgainst(PokemonType pt) {
    return STRONG_AGAINST.get(pt);
  }

  public List<PokemonType> whatIsThisWeakAgainst(PokemonType pt) {
    return WEAK_AGAINST.get(pt);
  }

  public List<PokemonType> whatIsStrongAgainstThis(PokemonType pt) {
    return STRONG_AGAINST.keySet().stream()
        .filter(k -> STRONG_AGAINST.get(k).contains(pt))
        .collect(Collectors.toList());
  }

  public List<PokemonType> whatIsWeakAgainstThis(PokemonType pt) {
    return WEAK_AGAINST.keySet().stream()
        .filter(k -> WEAK_AGAINST.get(k).contains(pt))
        .collect(Collectors.toList());
  }
}
