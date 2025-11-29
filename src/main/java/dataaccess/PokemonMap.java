package dataaccess;

import java.util.*;

import entity.*;

public class PokemonMap {

    private final Map<String, Pokemon> pokemonMap = new HashMap<>();

    /**
     * Get a Pokemon for testing
     * @param name name of the Pokemon
     * @return Pokemon object
     */
    public Pokemon getPokemon(String name) {
        return pokemonMap.get(name.toLowerCase());
    }

    public PokemonMap() {
        final AbilityMap abilityMap = new AbilityMap();
        final MoveMap moveMap = new MoveMap();

        final Pokemon magikarp = new PokemonBuilder()
                .setName("magikarp")
                .setType1(new Type("water", 11,
                        new HashSet<>(Arrays.asList("rock", "fire", "ground")),
                        new HashSet<>(Arrays.asList("grass", "electric")),
                        new HashSet<>(Arrays.asList("steel", "fire", "ice", "water")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/11.png"))
                .setType2(null)
                .setStats(new ArrayList<>(Arrays.asList(20, 10, 55, 15, 20, 80)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(33))))
                .setHidden(abilityMap.getAbility(155))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(33), moveMap.getMove(56), moveMap.getMove(150), moveMap.getMove(175), moveMap.getMove(340))))
                .setEggGroups(new ArrayList<>(Arrays.asList(12, 14)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 11, 12, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 34)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/129.png")
                .build();
        pokemonMap.put(magikarp.getName(), magikarp);

        final Pokemon ludicolo = new PokemonBuilder()
                .setName("ludicolo")
                .setType1(new Type("water", 11,
                        new HashSet<>(Arrays.asList("rock", "fire", "ground")),
                        new HashSet<>(Arrays.asList("grass", "electric")),
                        new HashSet<>(Arrays.asList("steel", "fire", "ice", "water")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/11.png"))
                .setType2(new Type("grass", 12,
                        new HashSet<>(Arrays.asList("rock", "ground", "water")),
                        new HashSet<>(Arrays.asList("poison", "bug", "flying", "fire", "ice")),
                        new HashSet<>(Arrays.asList("grass", "electric", "ground", "water")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/12.png"))
                .setStats(new ArrayList<>(Arrays.asList(80, 70, 70, 90, 100, 70)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(33), abilityMap.getAbility(44))))
                .setHidden(abilityMap.getAbility(20))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(5), moveMap.getMove(7), moveMap.getMove(8), moveMap.getMove(9), moveMap.getMove(14), moveMap.getMove(25), moveMap.getMove(29), moveMap.getMove(34), moveMap.getMove(36), moveMap.getMove(38), moveMap.getMove(45), moveMap.getMove(54), moveMap.getMove(55), moveMap.getMove(56), moveMap.getMove(57), moveMap.getMove(58), moveMap.getMove(59), moveMap.getMove(61), moveMap.getMove(63), moveMap.getMove(68), moveMap.getMove(69), moveMap.getMove(70), moveMap.getMove(71), moveMap.getMove(72), moveMap.getMove(75), moveMap.getMove(76), moveMap.getMove(92), moveMap.getMove(102), moveMap.getMove(104), moveMap.getMove(118), moveMap.getMove(127), moveMap.getMove(129), moveMap.getMove(133), moveMap.getMove(148), moveMap.getMove(154), moveMap.getMove(156), moveMap.getMove(164), moveMap.getMove(168), moveMap.getMove(173), moveMap.getMove(175), moveMap.getMove(182), moveMap.getMove(189), moveMap.getMove(196), moveMap.getMove(202), moveMap.getMove(203), moveMap.getMove(207), moveMap.getMove(213), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(223), moveMap.getMove(227), moveMap.getMove(230), moveMap.getMove(235), moveMap.getMove(237), moveMap.getMove(240), moveMap.getMove(241), moveMap.getMove(249), moveMap.getMove(250), moveMap.getMove(252), moveMap.getMove(253), moveMap.getMove(258), moveMap.getMove(263), moveMap.getMove(264), moveMap.getMove(267), moveMap.getMove(280), moveMap.getMove(282), moveMap.getMove(290), moveMap.getMove(291), moveMap.getMove(298), moveMap.getMove(304), moveMap.getMove(310), moveMap.getMove(311), moveMap.getMove(321), moveMap.getMove(330), moveMap.getMove(341), moveMap.getMove(345), moveMap.getMove(352), moveMap.getMove(363), moveMap.getMove(374), moveMap.getMove(402), moveMap.getMove(409), moveMap.getMove(411), moveMap.getMove(412), moveMap.getMove(416), moveMap.getMove(428), moveMap.getMove(431), moveMap.getMove(437), moveMap.getMove(445), moveMap.getMove(447), moveMap.getMove(468), moveMap.getMove(496), moveMap.getMove(497), moveMap.getMove(503), moveMap.getMove(574), moveMap.getMove(580), moveMap.getMove(590), moveMap.getMove(612), moveMap.getMove(803), moveMap.getMove(851), moveMap.getMove(861), moveMap.getMove(885), moveMap.getMove(886))))
                .setEggGroups(new ArrayList<>(Arrays.asList(2, 7)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 4, 14, 15, 27, 32)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/272.png")
                .build();
        pokemonMap.put(ludicolo.getName(), ludicolo);

        final Pokemon aurorus = new PokemonBuilder()
                .setName("aurorus")
                .setType1(new Type("rock", 6,
                        new HashSet<>(Arrays.asList("bug", "flying", "fire", "ice")),
                        new HashSet<>(Arrays.asList("steel", "grass", "ground", "fighting", "water")),
                        new HashSet<>(Arrays.asList("normal", "poison", "flying", "fire")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/6.png"))
                .setType2(new Type("ice", 15,
                        new HashSet<>(Arrays.asList("grass", "flying", "ground", "dragon")),
                        new HashSet<>(Arrays.asList("rock", "steel", "fire", "fighting")),
                        new HashSet<>(Arrays.asList("ice")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/15.png"))
                .setStats(new ArrayList<>(Arrays.asList(123, 77, 72, 99, 92, 58)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(174))))
                .setHidden(abilityMap.getAbility(117))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(34), moveMap.getMove(36), moveMap.getMove(45), moveMap.getMove(46), moveMap.getMove(54), moveMap.getMove(58), moveMap.getMove(59), moveMap.getMove(62), moveMap.getMove(63), moveMap.getMove(85), moveMap.getMove(86), moveMap.getMove(87), moveMap.getMove(88), moveMap.getMove(89), moveMap.getMove(92), moveMap.getMove(94), moveMap.getMove(104), moveMap.getMove(113), moveMap.getMove(115), moveMap.getMove(138), moveMap.getMove(148), moveMap.getMove(156), moveMap.getMove(157), moveMap.getMove(164), moveMap.getMove(173), moveMap.getMove(181), moveMap.getMove(182), moveMap.getMove(196), moveMap.getMove(200), moveMap.getMove(201), moveMap.getMove(203), moveMap.getMove(207), moveMap.getMove(213), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(219), moveMap.getMove(227), moveMap.getMove(231), moveMap.getMove(237), moveMap.getMove(240), moveMap.getMove(244), moveMap.getMove(246), moveMap.getMove(249), moveMap.getMove(258), moveMap.getMove(263), moveMap.getMove(267), moveMap.getMove(290), moveMap.getMove(304), moveMap.getMove(311), moveMap.getMove(317), moveMap.getMove(333), moveMap.getMove(334), moveMap.getMove(341), moveMap.getMove(347), moveMap.getMove(350), moveMap.getMove(352), moveMap.getMove(393), moveMap.getMove(397), moveMap.getMove(399), moveMap.getMove(401), moveMap.getMove(414), moveMap.getMove(416), moveMap.getMove(419), moveMap.getMove(428), moveMap.getMove(430), moveMap.getMove(442), moveMap.getMove(444), moveMap.getMove(446), moveMap.getMove(451), moveMap.getMove(496), moveMap.getMove(497), moveMap.getMove(523), moveMap.getMove(524), moveMap.getMove(525), moveMap.getMove(573), moveMap.getMove(590), moveMap.getMove(800))))
                .setEggGroups(new ArrayList<>(Arrays.asList(1)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 13, 21, 23, 29, 34)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/699.png")
                .build();
        pokemonMap.put(aurorus.getName(), aurorus);

        final Pokemon durant = new PokemonBuilder()
                .setName("durant")
                .setType1(new Type("bug", 7,
                        new HashSet<>(Arrays.asList("grass", "dark", "psychic")),
                        new HashSet<>(Arrays.asList("rock", "flying", "fire")),
                        new HashSet<>(Arrays.asList("grass", "ground", "fighting")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/7.png"))
                .setType2(new Type("steel", 9,
                        new HashSet<>(Arrays.asList("rock", "ice", "fairy")),
                        new HashSet<>(Arrays.asList("fire", "ground", "fighting")),
                        new HashSet<>(Arrays.asList("rock", "normal", "steel", "poison", "bug", "grass", "flying", "ice", "psychic", "dragon", "fairy")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/9.png"))
                .setStats(new ArrayList<>(Arrays.asList(58, 109, 112, 48, 48, 109)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(68), abilityMap.getAbility(55))))
                .setHidden(abilityMap.getAbility(54))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(11), moveMap.getMove(12), moveMap.getMove(15), moveMap.getMove(28), moveMap.getMove(44), moveMap.getMove(70), moveMap.getMove(86), moveMap.getMove(91), moveMap.getMove(92), moveMap.getMove(97), moveMap.getMove(103), moveMap.getMove(104), moveMap.getMove(156), moveMap.getMove(157), moveMap.getMove(164), moveMap.getMove(173), moveMap.getMove(175), moveMap.getMove(182), moveMap.getMove(185), moveMap.getMove(201), moveMap.getMove(203), moveMap.getMove(207), moveMap.getMove(210), moveMap.getMove(213), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(226), moveMap.getMove(232), moveMap.getMove(237), moveMap.getMove(242), moveMap.getMove(249), moveMap.getMove(251), moveMap.getMove(263), moveMap.getMove(270), moveMap.getMove(276), moveMap.getMove(283), moveMap.getMove(290), moveMap.getMove(317), moveMap.getMove(319), moveMap.getMove(332), moveMap.getMove(334), moveMap.getMove(368), moveMap.getMove(397), moveMap.getMove(404), moveMap.getMove(412), moveMap.getMove(416), moveMap.getMove(421), moveMap.getMove(422), moveMap.getMove(430), moveMap.getMove(431), moveMap.getMove(442), moveMap.getMove(444), moveMap.getMove(450), moveMap.getMove(468), moveMap.getMove(494), moveMap.getMove(496), moveMap.getMove(514), moveMap.getMove(522), moveMap.getMove(590), moveMap.getMove(611), moveMap.getMove(660), moveMap.getMove(707), moveMap.getMove(796), moveMap.getMove(806))))
                .setEggGroups(new ArrayList<>(Arrays.asList(3)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 8, 9, 14, 27, 29)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/632.png")
                .build();
        pokemonMap.put(durant.getName(), durant);

        final Pokemon shedinja = new PokemonBuilder()
                .setName("shedinja")
                .setType1(new Type("bug", 7,
                        new HashSet<>(Arrays.asList("grass", "dark", "psychic")),
                        new HashSet<>(Arrays.asList("rock", "flying", "fire")),
                        new HashSet<>(Arrays.asList("grass", "ground", "fighting")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/7.png"))
                .setType2(new Type("ghost", 8,
                        new HashSet<>(Arrays.asList("ghost", "psychic")),
                        new HashSet<>(Arrays.asList("ghost", "dark")),
                        new HashSet<>(Arrays.asList("normal", "poison", "bug", "fighting")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/8.png"))
                .setStats(new ArrayList<>(Arrays.asList(1, 90, 45, 30, 30, 40)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(25))))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(10), moveMap.getMove(15), moveMap.getMove(28), moveMap.getMove(38), moveMap.getMove(63), moveMap.getMove(71), moveMap.getMove(76), moveMap.getMove(81), moveMap.getMove(91), moveMap.getMove(92), moveMap.getMove(97), moveMap.getMove(102), moveMap.getMove(104), moveMap.getMove(106), moveMap.getMove(109), moveMap.getMove(138), moveMap.getMove(141), moveMap.getMove(148), moveMap.getMove(154), moveMap.getMove(156), moveMap.getMove(164), moveMap.getMove(168), moveMap.getMove(170), moveMap.getMove(171), moveMap.getMove(173), moveMap.getMove(180), moveMap.getMove(182), moveMap.getMove(189), moveMap.getMove(201), moveMap.getMove(202), moveMap.getMove(203), moveMap.getMove(206), moveMap.getMove(207), moveMap.getMove(210), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(232), moveMap.getMove(237), moveMap.getMove(241), moveMap.getMove(247), moveMap.getMove(261), moveMap.getMove(263), moveMap.getMove(271), moveMap.getMove(288), moveMap.getMove(290), moveMap.getMove(332), moveMap.getMove(363), moveMap.getMove(377), moveMap.getMove(389), moveMap.getMove(404), moveMap.getMove(405), moveMap.getMove(416), moveMap.getMove(421), moveMap.getMove(425), moveMap.getMove(450), moveMap.getMove(468), moveMap.getMove(477), moveMap.getMove(496), moveMap.getMove(502), moveMap.getMove(506), moveMap.getMove(522), moveMap.getMove(566), moveMap.getMove(590), moveMap.getMove(806), moveMap.getMove(809))))
                .setEggGroups(new ArrayList<>(Arrays.asList(10)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 4, 12, 15, 27)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/292.png")
                .build();
        pokemonMap.put(shedinja.getName(), shedinja);

        final Pokemon garchomp = new PokemonBuilder()
                .setName("garchomp")
                .setType1(new Type("dragon", 16,
                        new HashSet<>(Arrays.asList("dragon")),
                        new HashSet<>(Arrays.asList("ice", "dragon", "fairy")),
                        new HashSet<>(Arrays.asList("grass", "electric", "fire", "water")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/16.png"))
                .setType2(new Type("ground", 5,
                        new HashSet<>(Arrays.asList("rock", "steel", "poison", "electric", "fire")),
                        new HashSet<>(Arrays.asList("grass", "ice", "water")),
                        new HashSet<>(Arrays.asList("rock", "poison", "electric")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/5.png"))
                .setStats(new ArrayList<>(Arrays.asList(108, 130, 95, 80, 85, 102)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(8))))
                .setHidden(abilityMap.getAbility(24))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(14), moveMap.getMove(15), moveMap.getMove(28), moveMap.getMove(29), moveMap.getMove(33), moveMap.getMove(34), moveMap.getMove(36), moveMap.getMove(38), moveMap.getMove(44), moveMap.getMove(46), moveMap.getMove(53), moveMap.getMove(57), moveMap.getMove(63), moveMap.getMove(70), moveMap.getMove(82), moveMap.getMove(89), moveMap.getMove(91), moveMap.getMove(92), moveMap.getMove(104), moveMap.getMove(126), moveMap.getMove(129), moveMap.getMove(156), moveMap.getMove(157), moveMap.getMove(163), moveMap.getMove(164), moveMap.getMove(173), moveMap.getMove(182), moveMap.getMove(184), moveMap.getMove(189), moveMap.getMove(191), moveMap.getMove(200), moveMap.getMove(201), moveMap.getMove(203), moveMap.getMove(206), moveMap.getMove(207), moveMap.getMove(210), moveMap.getMove(213), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(225), moveMap.getMove(231), moveMap.getMove(232), moveMap.getMove(237), moveMap.getMove(239), moveMap.getMove(240), moveMap.getMove(241), moveMap.getMove(242), moveMap.getMove(249), moveMap.getMove(250), moveMap.getMove(263), moveMap.getMove(270), moveMap.getMove(280), moveMap.getMove(290), moveMap.getMove(317), moveMap.getMove(328), moveMap.getMove(332), moveMap.getMove(337), moveMap.getMove(341), moveMap.getMove(363), moveMap.getMove(374), moveMap.getMove(398), moveMap.getMove(401), moveMap.getMove(406), moveMap.getMove(407), moveMap.getMove(408), moveMap.getMove(414), moveMap.getMove(416), moveMap.getMove(421), moveMap.getMove(422), moveMap.getMove(424), moveMap.getMove(431), moveMap.getMove(434), moveMap.getMove(442), moveMap.getMove(444), moveMap.getMove(445), moveMap.getMove(446), moveMap.getMove(468), moveMap.getMove(496), moveMap.getMove(510), moveMap.getMove(523), moveMap.getMove(525), moveMap.getMove(530), moveMap.getMove(590), moveMap.getMove(673), moveMap.getMove(693), moveMap.getMove(707), moveMap.getMove(710), moveMap.getMove(784), moveMap.getMove(799), moveMap.getMove(815), moveMap.getMove(851), moveMap.getMove(913))))
                .setEggGroups(new ArrayList<>(Arrays.asList(1, 14)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 5, 6, 11, 14, 16, 19, 21, 24, 29, 30, 31, 32, 34)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/445.png")
                .build();
        pokemonMap.put(garchomp.getName(), garchomp);

        final Pokemon scolipede = new PokemonBuilder()
                .setName("scolipede")
                .setType1(new Type("bug", 7,
                        new HashSet<>(Arrays.asList("grass", "dark", "psychic")),
                        new HashSet<>(Arrays.asList("rock", "flying", "fire")),
                        new HashSet<>(Arrays.asList("grass", "ground", "fighting")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/7.png"))
                .setType2(new Type("poison", 4,
                        new HashSet<>(Arrays.asList("grass", "fairy")),
                        new HashSet<>(Arrays.asList("ground", "psychic")),
                        new HashSet<>(Arrays.asList("poison", "bug", "grass", "fighting", "fairy")),
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/types/generation-vi/omega-ruby-alpha-sapphire/4.png"))
                .setStats(new ArrayList<>(Arrays.asList(60, 100, 89, 55, 69, 112)))
                .setAbilities(new ArrayList<>(Arrays.asList(abilityMap.getAbility(38), abilityMap.getAbility(68))))
                .setHidden(abilityMap.getAbility(3))
                .setMoves(new ArrayList<>(Arrays.asList(moveMap.getMove(14), moveMap.getMove(15), moveMap.getMove(36), moveMap.getMove(38), moveMap.getMove(40), moveMap.getMove(42), moveMap.getMove(63), moveMap.getMove(70), moveMap.getMove(76), moveMap.getMove(89), moveMap.getMove(91), moveMap.getMove(92), moveMap.getMove(97), moveMap.getMove(103), moveMap.getMove(104), moveMap.getMove(111), moveMap.getMove(156), moveMap.getMove(157), moveMap.getMove(164), moveMap.getMove(173), moveMap.getMove(182), moveMap.getMove(188), moveMap.getMove(191), moveMap.getMove(203), moveMap.getMove(205), moveMap.getMove(207), moveMap.getMove(213), moveMap.getMove(214), moveMap.getMove(216), moveMap.getMove(218), moveMap.getMove(224), moveMap.getMove(226), moveMap.getMove(228), moveMap.getMove(231), moveMap.getMove(237), moveMap.getMove(241), moveMap.getMove(249), moveMap.getMove(263), moveMap.getMove(276), moveMap.getMove(283), moveMap.getMove(289), moveMap.getMove(290), moveMap.getMove(317), moveMap.getMove(324), moveMap.getMove(334), moveMap.getMove(342), moveMap.getMove(360), moveMap.getMove(371), moveMap.getMove(372), moveMap.getMove(390), moveMap.getMove(398), moveMap.getMove(401), moveMap.getMove(404), moveMap.getMove(416), moveMap.getMove(431), moveMap.getMove(440), moveMap.getMove(450), moveMap.getMove(474), moveMap.getMove(496), moveMap.getMove(506), moveMap.getMove(522), moveMap.getMove(523), moveMap.getMove(537), moveMap.getMove(590), moveMap.getMove(599), moveMap.getMove(611), moveMap.getMove(675), moveMap.getMove(684), moveMap.getMove(707), moveMap.getMove(798), moveMap.getMove(806))))
                .setEggGroups(new ArrayList<>(Arrays.asList(3)))
                .setPokedexes(new ArrayList<>(Arrays.asList(1, 8, 9, 11, 12, 28, 34)))
                .setSprite("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/545.png")
                .build();
        pokemonMap.put(scolipede.getName(), scolipede);
    }
}