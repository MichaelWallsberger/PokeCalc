package Classes;

public class PokemonStats {
    public int pokemonId;
    public int pokemonStatsId;
    public String type1;
    public String type2;
    public String itemName;
    public String abilityName;
    public String natureName;
    public String statusCondition;
    public int level;
    public String gender;
    public int friendship;
    public int attackStat;
    public int defenseStat;
    public int specialAttackStat;
    public int specialDefenseStat;
    public int speedStat;

    public PokemonStats(int pokemonId, int pokemonStatsId, String type1, String type2, String itemName, String abilityName, String natureName, String statusCondition, int level, String gender, int friendship, int attackStat, int defenseStat, int specialAttackStat, int specialDefenseStat, int speedStat) {
        this.pokemonId = pokemonId;
        this.pokemonStatsId = pokemonStatsId;
        this.type1 = type1;
        this.type2 = type2;
        this.itemName = itemName;
        this.abilityName = abilityName;
        this.natureName = natureName;
        this.statusCondition = statusCondition;
        this.level = level;
        this.gender = gender;
        this.friendship = friendship;
        this.attackStat = attackStat;
        this.defenseStat = defenseStat;
        this.specialAttackStat = specialAttackStat;
        this.specialDefenseStat = specialDefenseStat;
        this.speedStat = speedStat;
    }
}
