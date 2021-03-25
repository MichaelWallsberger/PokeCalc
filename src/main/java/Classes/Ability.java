package Classes;

public class Ability {
    public int abilityId;
    public String abilityName;
    public String abilityDescription;

    public Ability(int abilityId, String abilityName, String abilityDescription) {
        this.abilityId = abilityId;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
    }

    @Override
    public String toString() {
        return abilityName;
    }
}
