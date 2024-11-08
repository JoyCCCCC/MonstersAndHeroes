import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArmorFactory implements ItemFactory<Armor>{
    public static final List<String> ARMOR_NAMES = Arrays.asList("Platinum_Shield", "Breastplate", "Full_Body_Armor", "Wizard_Shield", "Guardian_Angel");
    public static final List<Integer> ARMOR_COSTS = Arrays.asList(150, 350, 1000, 1200, 1000);
    public static final List<Integer> ARMOR_LEVELS = Arrays.asList(1, 3, 8, 10, 10);
    public static final List<Integer> ARMOR_DAMAGE_REDUCTION = Arrays.asList(200, 600, 1100, 1500, 1000);
    private List<Armor> armors = new ArrayList<>();
    private static ArmorFactory armorFactory;

    private ArmorFactory() {
        generateItems();
    }

    @Override
    public void generateItems() {
        for (int i = 0; i < ARMOR_NAMES.size(); i++) {
            Armor armor = new Armor(ARMOR_NAMES.get(i), ARMOR_COSTS.get(i), ARMOR_LEVELS.get(i), ARMOR_DAMAGE_REDUCTION.get(i));
            armors.add(armor);
        }
    }

    @Override
    public List<Armor> getItems() {
        return armors;
    }

    @Override
    public int getItemsSize() {
        return armors.size();
    }

    public Armor create(int index) {
        return armors.get(index);
    }

    public static ArmorFactory getInstance() {
        if(armorFactory == null) {
            armorFactory = new ArmorFactory();
        }
        return armorFactory;
    }

}
