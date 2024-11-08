public class Armor extends Item{
    private int defense;

    public Armor(String name, int price, int minLevelReq, int defense) {
        super(name, price, minLevelReq);
        this.defense = defense;
    }

    public Armor(String name, int price, int minLevelReq) {
        super(name, price, minLevelReq);
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
}
