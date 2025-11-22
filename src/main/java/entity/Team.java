package entity;

public class Team {
    static final int TOTAL_TEAM_SLOTS = 6;
    private String teamName;
    private Pokemon[] pokemon;

    public Team(String teamName) {
        this.teamName = teamName;
        this.pokemon = new Pokemon[TOTAL_TEAM_SLOTS];
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Pokemon[] getPokemon() {
        return pokemon;
    }

    /**
     * Get the Pokemon at the index.
     * @param index index of note
     * @return Pokemon at index
     */
    public Pokemon getPokemon(int index) {
        return pokemon[index];
    }

    public void setPokemon(Pokemon[] pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * Set the Pokemon at the index.
     * @param newPokemon Pokemon to be set
     * @param index Index to be set at
     */
    public void setPokemon(Pokemon newPokemon, int index) {
        this.pokemon[index] = newPokemon;
    }
}
