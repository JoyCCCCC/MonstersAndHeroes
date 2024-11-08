import java.util.List;

public class Potion extends Item{
    private int[] increaseStats;
    private List<String> attributesAffected;

    public Potion(String name, int price, int minLevelReq, int[] increaseStats, List<String> attributesAffected) {
        super(name, price, minLevelReq);
        this.increaseStats = increaseStats;
        this.attributesAffected = attributesAffected;
    }
    public void used(Hero hero) {
        hero.addHp(increaseStats[0]);
        hero.addMana(increaseStats[1]);
        hero.addStrength(increaseStats[2]);
        hero.addDexterity(increaseStats[3]);
        hero.addDefense(increaseStats[4]);
        hero.addAgility(increaseStats[5]);
        hero.getInventory().removeItem(this);
    }

    public int getIncreaseAmount(){
        return increaseStats[0];
    }

    public List<String> getAttributesAffected(){
        return attributesAffected;
    }

}
