import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponFactory implements ItemFactory<Weapon>{
    public static final List<String> WEAPON_NAMES = Arrays.asList("Sword", "Bow", "Scythe", "Axe", "TSwords", "Dagger");
    public static final List<Integer> WEAPON_COSTS = Arrays.asList(500, 300, 1000, 550, 1400, 200);
    public static final List<Integer> WEAPON_LEVELS = Arrays.asList(1, 2, 6, 5, 8, 1);
    public static final List<Integer> WEAPON_DAMAGE = Arrays.asList(800, 500, 1100, 850, 1600, 250);
    public static final List<Integer> REQUIRED_HANDS = Arrays.asList(1, 2, 2, 1, 2, 1);

    private List<Weapon> weapons = new ArrayList<>();
    private static WeaponFactory weaponFactory;

    private WeaponFactory() {
        generateItems();
    }

    @Override
    public void generateItems() {
        for (int i = 0; i < WEAPON_NAMES.size(); i++) {
            Weapon weapon = new Weapon(WEAPON_NAMES.get(i), WEAPON_COSTS.get(i), WEAPON_LEVELS.get(i), WEAPON_DAMAGE.get(i), REQUIRED_HANDS.get(i));
            weapons.add(weapon);
        }
    }

    @Override
    public List<Weapon> getItems() {
        return weapons;
    }

    @Override
    public int getItemsSize() {
        return weapons.size();
    }

    public Weapon create(int index) {
        return weapons.get(index);
    }

    public static WeaponFactory getInstance() {
        if(weaponFactory == null) {
            weaponFactory = new WeaponFactory();
        }
        return weaponFactory;
    }
}
