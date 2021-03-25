package Classes;

public class Badge {
    public int badgeId;
    public int spriteId;
    public String badgeName;
    public String description;
    public int levelCap;
    public boolean ownBadge;
    public String statBoost;
    public double boostPower;
    public int gymOrder;

    public Badge(int badgeId, int spriteId, String badgeName, String description, int levelCap, boolean ownBadge, String statBoost, double boostPower, int gymOrder) {
        this.badgeId = badgeId;
        this.spriteId = spriteId;
        this.badgeName = badgeName;
        this.description = description;
        this.levelCap = levelCap;
        this.ownBadge = ownBadge;
        this.statBoost = statBoost;
        this.boostPower = boostPower;
        this.gymOrder = gymOrder;
    }

    @Override
    public String toString() {
        return badgeName;
    }
}
