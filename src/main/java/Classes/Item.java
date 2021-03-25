package Classes;

public class Item {
    public int itemId;
    public int spriteId;
    public String nameOfItem;
    public String description;
    public double itemValue;
    public int buyPrice;
    public int sellPrice;

    public Item(int itemId, int spriteId, String nameOfItem, String description, double itemValue, int buyPrice, int sellPrice) {
        this.itemId = itemId;
        this.spriteId = spriteId;
        this.nameOfItem = nameOfItem;
        this.description = description;
        this.itemValue = itemValue;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return nameOfItem;
    }
}
