public class Spell extends Item {
    private int damage;
    private int manaCost;

    public Spell(String name, int price, int minLevelReq, int damage, int manaCost) {
        super(name, price, minLevelReq);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    public Spell(String name, int price, int minLevelReq) {
        super(name, price, minLevelReq);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }
}
