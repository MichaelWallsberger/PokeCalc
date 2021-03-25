package Classes;

public class Attack {
    public int attackId;
    public String typeName;
    public String attackName;
    public String description;
    public int power;
    public int pp;
    public String effect;
    public String secondaryEffect;
    public int priority;
    public String targets;
    public String category;
    public boolean makesContact;
    public boolean protectAffected;
    public boolean magicCoatAffected;
    public boolean snatchAffected;
    public boolean mirrorMoveAffected;
    public boolean kingsRockAffected;
    public Integer multihits;

    public Attack(int attackId, String typeName, String attackName, String description, int power, int pp, String effect, String secondaryEffect, int priority, String targets, String category, boolean makesContact, boolean protectAffected, boolean magicCoatAffected, boolean snatchAffected, boolean mirrorMoveAffected, boolean kingsRockAffected) {
        this.attackId = attackId;
        this.typeName = typeName;
        this.attackName = attackName;
        this.description = description;
        this.power = power;
        this.pp = pp;
        this.effect = effect;
        this.secondaryEffect = secondaryEffect;
        this.priority = priority;
        this.targets = targets;
        this.category = category;
        this.makesContact = makesContact;
        this.protectAffected = protectAffected;
        this.magicCoatAffected = magicCoatAffected;
        this.snatchAffected = snatchAffected;
        this.mirrorMoveAffected = mirrorMoveAffected;
        this.kingsRockAffected = kingsRockAffected;
    }

    @Override
    public String toString() {
        return attackName;
    }
}
