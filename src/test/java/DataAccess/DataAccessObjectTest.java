package DataAccess;
import data_access.BuildPokemonTeamDataAccessObject;
import entity.EmptyPokemonFactory;
import entity.Pokemon;
import entity.Team;
import org.junit.Test;
import java.util.ArrayList;


public class DataAccessObjectTest {

    @Test
    public void BuildPokemonTeamAccessTest() {
        BuildPokemonTeamDataAccessObject d = new BuildPokemonTeamDataAccessObject();
        Team t = new Team("Ash's Team");

        Pokemon pikachu = EmptyPokemonFactory.create();
        pikachu.setName("Pikachu");
        Pokemon charmander = EmptyPokemonFactory.create();
        charmander.setName("Charmander");
        Pokemon squirtle = EmptyPokemonFactory.create();
        squirtle.setName("Squirtle");
        Pokemon bulbasaur = EmptyPokemonFactory.create();
        bulbasaur.setName("Bulbasaur");
        Pokemon mewtwo = EmptyPokemonFactory.create();
        mewtwo.setName("Mewtwo");
        Pokemon mew = EmptyPokemonFactory.create();
        mew.setName("Mew");

        t.setPokemon(pikachu, 0);
        t.setPokemon(charmander, 1);
        t.setPokemon(squirtle, 2);
        t.setPokemon(bulbasaur, 3);
        t.setPokemon(mewtwo, 4);
        t.setPokemon(mew, 5);

        d.saveTeam(t);

    }
}
