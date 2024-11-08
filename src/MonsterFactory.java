import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MonsterFactory implements RoleFactory<Monster>{
    public static final List<String> MONSTER_NAMES = Arrays.asList("Desghidorrah", "Chrysophylax", "BunsenBurner", "Natsunomeryu", "TheScaleless", "Kas-Ethelinh", "Alexstraszan", "Phaarthurnax", "D-Maleficent", "TheWeatherbe", "Igneel", "BlueEyesWhite",
            "Cyrrollalee", "Brandobaris", "BigBad-Wolf", "WickedWitch", "Aasterinian", "Chronepsish", "Kiaransalee", "St-Shargaas", "Merrshaullk", "St-Yeenoghu", "Venom", "Exodia",
            "Andrealphus", "Blinky", "Andromalius", "Chiang-shih", "FallenAngel", "Ereshkigall", "Melchiresas", "Jormunngand", "Rakkshasass", "Taltecuhtli", "Casper");
    public static final List<String> MONSTER_TYPES = Arrays.asList("D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D",
            "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E", "E",
            "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S");
    public static final List<Integer> MONSTER_LEVELS = Arrays.asList(3, 2, 4, 1, 7, 5, 10, 6, 9, 8, 6, 9,
            7, 3, 1, 2, 4, 6, 8, 5, 10, 9, 6, 10,
            2, 1, 3, 4, 5, 6, 7, 8, 9, 10, 1);
    public static final List<Integer> MONSTER_DAMAGE = Arrays.asList(300, 200, 400, 100, 700, 600, 1000, 600, 900, 800, 600, 900,
            700, 350, 150, 250, 400, 650, 850, 550, 1000, 950, 600, 1000,
            600, 450, 550, 700, 800, 950, 350, 600, 550, 300, 100);
    public static final List<Integer> MONSTER_DEFENSE = Arrays.asList(400, 500, 500, 200, 600, 500, 9000, 700, 950, 900, 400, 600,
            800, 450, 250, 350, 500, 750, 950, 650, 900, 850, 600, 1000,
            500, 350, 450, 600, 700, 450, 150, 900, 600, 200, 100);
    public static final List<Integer> MONSTER_DODGE_CHANCE = Arrays.asList(35, 20, 45, 10, 75, 60, 55, 60, 85, 80, 60, 75,
            75, 30, 15, 25, 45, 60, 85, 55, 55, 90, 55, 50,
            40, 35, 25, 40, 50, 35, 75, 20, 35, 50, 50);
    private List<Dragon> dragons = new ArrayList<>();
    private List<Exoskeleton> exoskeletons = new ArrayList<>();
    private List<Spirit> spirits = new ArrayList<>();
    private static MonsterFactory monsterFactory;

    private MonsterFactory() {
        generateRoles();
    }

    @Override
    public void generateRoles() {
        for (int i = 0; i < MONSTER_NAMES.size(); i++) {
            if (MONSTER_TYPES.get(i).equals("D")) {
                Dragon dragon = new Dragon(MONSTER_NAMES.get(i),MONSTER_LEVELS.get(i),100*MONSTER_LEVELS.get(i),MONSTER_DAMAGE.get(i),MONSTER_DEFENSE.get(i),MONSTER_DODGE_CHANCE.get(i),null);
                dragons.add(dragon);
            } else if (MONSTER_TYPES.get(i).equals("E")) {
                Exoskeleton exoskeleton = new Exoskeleton(MONSTER_NAMES.get(i),MONSTER_LEVELS.get(i),100*MONSTER_LEVELS.get(i),MONSTER_DAMAGE.get(i),MONSTER_DEFENSE.get(i),MONSTER_DODGE_CHANCE.get(i),null);
                exoskeletons.add(exoskeleton);
            } else if (MONSTER_TYPES.get(i).equals("S")) {
                Spirit spirit = new Spirit(MONSTER_NAMES.get(i),MONSTER_LEVELS.get(i),100*MONSTER_LEVELS.get(i),MONSTER_DAMAGE.get(i),MONSTER_DEFENSE.get(i),MONSTER_DODGE_CHANCE.get(i),null);
                spirits.add(spirit);
            }
        }
    }

    @Override
    public Monster create(List<Hero> heroes) {
        int maxLevel = heroes.stream()
                .max(Comparator.comparingInt(Hero::getLevel))
                .map(Hero::getLevel)
                .orElse(1);
        while (true) {
            double randNum = Math.random();
            if(randNum < 0.3) {
                int index = (int)(Math.random() * dragons.size());
                if(dragons.get(index).getLevel() != maxLevel)
                    continue;
                return createDragon(index);
            } else if(randNum < 0.6) {
                int index = (int)(Math.random() * exoskeletons.size());
                if(exoskeletons.get(index).getLevel() != maxLevel)
                    continue;
                return createExoskeleton(index);
            } else if(randNum < 0.9) {
                int index = (int)(Math.random() * spirits.size());
                if(spirits.get(index).getLevel() != maxLevel)
                    continue;
                return createSpirit(index);
            }
            // if randNum between 0.9 and 1, then discard current round
        }
    }

    public Dragon createDragon(int index) {
        return dragons.get(index);
    }

    public Exoskeleton createExoskeleton(int index) {
        return exoskeletons.get(index);
    }

    public Spirit createSpirit(int index) {
        return spirits.get(index);
    }

    public static MonsterFactory getInstance() {
        if(monsterFactory == null) {
            monsterFactory = new MonsterFactory();
        }
        return monsterFactory;
    }
}
