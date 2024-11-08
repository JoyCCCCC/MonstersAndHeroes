import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeroFactory implements RoleFactory<Hero>{
    public static final List<String> HERO_NAMES = Arrays.asList("Parzival", "Sehanine_Moonbow", "Skoraeus_Stonebones", "Garl_Glittergold", "Amaryllis_Astra", "Caliber_Heist",
            "Rillifane_Rallathil", "Segojan_Earthcaller", "Reign_Havoc", "Reverie_Ashels", "Kalabar", "Skye_Soar",
            "Gaerdal_Ironhand", "Sehanine_Monnbow", "Muamman_Duathall", "Flandal_Steelskin", "Undefeated_Yoj", "Eunoia_Cyn");
    public static final List<String> HERO_TYPES = Arrays.asList("P", "P", "P", "P", "P", "P",
            "S", "S", "S", "S", "S", "S",
            "W", "W", "W", "W", "W", "W");
    public static final List<Integer> HERO_MANA = Arrays.asList(700, 700, 400, 500, 500, 800,
            900, 500, 800, 700, 600, 500,
            700, 800, 600, 700, 700, 600);
    public static final List<Integer> HERO_STRENGTH = Arrays.asList(750, 750, 650, 600, 500, 400,
            750, 800, 800, 800, 850, 700,
            700, 700, 900, 750, 800, 700);
    public static final List<Integer> HERO_AGILITY = Arrays.asList(650, 700, 600, 500, 500, 400,
            450, 500, 800, 700, 400, 400,
            500, 800, 500, 650, 400, 800);
    public static final List<Integer> HERO_DEXTERITY = Arrays.asList(700, 700, 350, 400, 500, 400,
            500, 650, 800, 400, 600, 500,
            600, 500, 750, 700, 700, 600);
    public static final List<Integer> HERO_MONEY = Arrays.asList(7000, 7000, 4000, 5000, 5000, 8000,
            9000, 5000, 8000, 7000, 6000, 5000,
            7000, 8000, 6000, 7000, 7000, 6000);
    public static final List<Integer> HERO_EXPERIENCE = Arrays.asList(7, 7, 4, 5, 5, 8,
            9, 5, 8, 7, 6, 5,
            7, 8, 6, 7, 7, 6);
    private List<Paladin> paladins = new ArrayList<>();
    private List<Sorcerer> sorcerers = new ArrayList<>();
    private List<Warrior> warriors = new ArrayList<>();
    private List<Hero> heroes = new ArrayList<>();
    private static HeroFactory heroFactory;

    private HeroFactory() {
        generateRoles();
    }

    @Override
    public void generateRoles() {
        for (int i = 0; i < HERO_NAMES.size(); i++) {
            if (HERO_TYPES.get(i).equals("P")) {
                Paladin paladin = new Paladin(HERO_NAMES.get(i),1,100,null,HERO_MANA.get(i),HERO_STRENGTH.get(i),HERO_AGILITY.get(i),HERO_DEXTERITY.get(i),HERO_MONEY.get(i),HERO_EXPERIENCE.get(i),new Inventory());
                paladins.add(paladin);
                heroes.add(paladin);
            } else if (HERO_TYPES.get(i).equals("S")) {
                Sorcerer sorcerer = new Sorcerer(HERO_NAMES.get(i),1,100,null,HERO_MANA.get(i),HERO_STRENGTH.get(i),HERO_AGILITY.get(i),HERO_DEXTERITY.get(i),HERO_MONEY.get(i),HERO_EXPERIENCE.get(i),new Inventory());
                sorcerers.add(sorcerer);
                heroes.add(sorcerer);
            } else if (HERO_TYPES.get(i).equals("W")) {
                Warrior warrior = new Warrior(HERO_NAMES.get(i),1,100,null,HERO_MANA.get(i),HERO_STRENGTH.get(i),HERO_AGILITY.get(i),HERO_DEXTERITY.get(i),HERO_MONEY.get(i),HERO_EXPERIENCE.get(i),new Inventory());
                warriors.add(warrior);
                heroes.add(warrior);
            }
        }
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    @Override
    public Hero create(List<Hero> heroes) {
        while (true) {
            double randNum = Math.random();
            if(randNum < 0.3) {
                int index = (int)(Math.random() * warriors.size());
                return createWarrior(index);
            } else if(randNum < 0.6) {
                int index = (int)(Math.random() * sorcerers.size());
                return createSorcerer(index);
            } else if(randNum < 0.9) {
                int index = (int)(Math.random() * paladins.size());
                return createPaladin(index);
            }
            // If randNum is between 0.9 and 1, then discard the current round.
        }
    }

    public Warrior createWarrior(int index) {
        Warrior warrior = warriors.get(index);
        warrior.setDamage((int)(warrior.getStrength()*0.5));
        warrior.setDefense();
        warrior.setDodge(warrior.getAgility());
        return warrior;
    }
    public int getWarriorSize() {
        return warriors.size();
    }
    public Sorcerer createSorcerer(int index) {
        Sorcerer sorcerer = sorcerers.get(index);
        sorcerer.setDamage((int)(sorcerer.getStrength()*0.5));
        sorcerer.setDefense();
        sorcerer.setDodge(sorcerer.getAgility());
        return sorcerer;
    }
    public int getSorcererSize() {
        return sorcerers.size();
    }
    public Paladin createPaladin(int index) {
        Paladin paladin = paladins.get(index);
        paladin.setDamage((int)(paladin.getStrength()*0.5));
        paladin.setDefense();
        paladin.setDodge(paladin.getAgility());
        return paladin;
    }
    public int getPaladinSize() {
        return paladins.size();
    }
    public static HeroFactory getInstance() {
        if(heroFactory == null) {
            heroFactory = new HeroFactory();
        }
        return heroFactory;
    }
}
