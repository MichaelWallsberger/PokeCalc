package Classes;

import Classes.Abstract.BasicPokemon;

public class Pokemon extends BasicPokemon {
    public int pokemonId;
    public int pokemonStatsId;
    public int itemId;
    public int abilityId;
    public String natureName;
    public int pokeball;
    public int metLocation;
    public String nickname;
    public String statusCondition;
    public int level;
    public int experience;
    public String gender;
    public int friendship;
    public boolean isShiny;
    public int ivHp;
    public int ivAttack;
    public int ivDefense;
    public int ivSpecialAttack;
    public int ivSpecialDefense;
    public int ivSpeed;
    public int evHp;
    public int evAttack;
    public int evDefense;
    public int evSpecialAttack;
    public int evSpecialDefense;
    public int evSpeed;

    public Pokemon(int pokemonId, int pokemonStatsId, int itemId, int abilityId, String natureName, int pokeball, int metLocation, String nickname, String statusCondition, int level, int experience, String gender, int friendship, boolean isShiny, int ivHp, int ivAttack, int ivDefense, int ivSpecialAttack, int ivSpecialDefense, int ivSpeed, int evHp, int evAttack, int evDefense, int evSpecialAttack, int evSpecialDefense, int evSpeed) {
        this.pokemonId = pokemonId;
        this.pokemonStatsId = pokemonStatsId;
        this.itemId = itemId;
        this.abilityId = abilityId;
        this.natureName = natureName;
        this.pokeball = pokeball;
        this.metLocation = metLocation;
        this.nickname = nickname;
        this.statusCondition = statusCondition;
        this.level = level;
        this.experience = experience;
        this.gender = gender;
        this.friendship = friendship;
        this.isShiny = isShiny;
        this.ivHp = ivHp;
        this.ivAttack = ivAttack;
        this.ivDefense = ivDefense;
        this.ivSpecialAttack = ivSpecialAttack;
        this.ivSpecialDefense = ivSpecialDefense;
        this.ivSpeed = ivSpeed;
        this.evHp = evHp;
        this.evAttack = evAttack;
        this.evDefense = evDefense;
        this.evSpecialAttack = evSpecialAttack;
        this.evSpecialDefense = evSpecialDefense;
        this.evSpeed = evSpeed;
    }


    @Override
    public String toString() {
        return nickname;
    }
}
