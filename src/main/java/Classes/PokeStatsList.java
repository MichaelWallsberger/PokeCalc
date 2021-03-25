package Classes;

import Classes.Abstract.BasicPokeStats;

public class PokeStatsList extends BasicPokeStats {
    public int pokemonStatsId;
    public int dexNr;
    public String nameOfPokemon;

    public PokeStatsList(int pokemonStatsId, int dexNr, String nameOfPokemon) {
        this.pokemonStatsId = pokemonStatsId;
        this.dexNr = dexNr;
        this.nameOfPokemon = nameOfPokemon;
    }

    @Override
    public String toString() {
        return nameOfPokemon;
    }
}
