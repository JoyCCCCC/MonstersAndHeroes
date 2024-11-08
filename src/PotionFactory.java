import java.util.*;

public class PotionFactory implements ItemFactory<Potion>{
    public static final List<String> POTION_NAMES = Arrays.asList("Healing_Potion", "Strength_Potion", "Magic_Potion", "Luck_Elixir", "Mermaid_Tears", "Ambrosia");
    public static final List<Integer> POTION_COSTS = Arrays.asList(250, 200, 350, 500, 850, 1000);
    public static final List<Integer> POTION_LEVELS = Arrays.asList(1, 1, 2, 4, 5, 8);
    public static final List<Integer> INCREASE_AMOUNT = Arrays.asList(100, 75, 100, 65, 100, 150);
    public static final List<List<String>> ATTRIBUTES_AFFECTED = Arrays.asList(Arrays.asList("Health"), Arrays.asList("Strength"), Arrays.asList("Mana"), Arrays.asList("Agility"), Arrays.asList("Health", "Mana", "Strength", "Agility"), Arrays.asList("Health", "Mana", "Strength", "Dexterity", "Defense", "Agility"));
    private List<Potion> potions = new ArrayList<>();
    private static PotionFactory potionFactory;
    private Map<String, Integer> attrIndexMap;

    private PotionFactory() {
        attrIndexMap = new HashMap<>();
        attrIndexMap.put("Health", 0);
        attrIndexMap.put("Mana", 1);
        attrIndexMap.put("Strength", 2);
        attrIndexMap.put("Dexterity", 3);
        attrIndexMap.put("Defense", 4);
        attrIndexMap.put("Agility", 5);
        generateItems();
    }

    @Override
    public void generateItems() {
        for (int i = 0; i < POTION_NAMES.size(); i++) {
            int attributeIncreaseVal = INCREASE_AMOUNT.get(i);
            List<String> attributes = ATTRIBUTES_AFFECTED.get(i);
            int[] attrIncrease = {0, 0, 0, 0, 0, 0};
            for(String attr : attributes) {
                attrIncrease[attrIndexMap.get(attr)] = attributeIncreaseVal;
            }
            Potion potion = new Potion(POTION_NAMES.get(i), POTION_COSTS.get(i), POTION_LEVELS.get(i), attrIncrease, ATTRIBUTES_AFFECTED.get(i));
            potions.add(potion);
        }
    }

    @Override
    public List<Potion> getItems() {
        return potions;
    }

    @Override
    public int getItemsSize() {
        return potions.size();
    }

    public Potion create(int index) {
        return potions.get(index);
    }

    public static PotionFactory getInstance() {
        if(potionFactory == null) {
            potionFactory = new PotionFactory();
        }
        return potionFactory;
    }
}
