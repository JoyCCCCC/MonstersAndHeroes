import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpellFactory implements ItemFactory<Spell>{
    public static final List<String> SPELL_NAMES = Arrays.asList("Flame_Tornado", "Breath_of_Fire", "Heat_Wave", "Lava_Comet", "Hell_Storm", "Snow_Cannon", "Ice_Blade", "Frost_Blizzard", "Arctic_Storm", "Lightning_Dagger", "Thunder_Blast", "Electric_Arrows", "Spark_Needles");
    public static final List<String> SPELL_TYPES = Arrays.asList("F", "F", "F", "F", "F", "C", "C", "C", "C", "L", "L", "L", "L");
    public static final List<Integer> SPELL_COSTS = Arrays.asList(700, 350, 450, 800, 600, 500, 250, 750, 700, 400, 750, 550, 500);
    public static final List<Integer> SPELL_LEVELS = Arrays.asList(4, 1, 2, 7, 3, 2, 1, 5, 6, 1, 4, 5, 2);
    public static final List<Integer> SPELL_DAMAGE = Arrays.asList(850, 450, 600, 1000, 950, 650, 450, 850, 800, 500, 950, 650, 600);
    public static final List<Integer> SPELL_MANA_COST = Arrays.asList(300, 100, 150, 550, 600, 250, 100, 350, 300, 150, 400, 200, 200);
    private List<FireSpell> fireSpells = new ArrayList<>();
    private List<IceSpell> iceSpells = new ArrayList<>();
    private List<LightningSpell> lightningSpells = new ArrayList<>();
    private List<Spell> spells = new ArrayList<>();
    private static SpellFactory spellFactory;

    private SpellFactory() {
        generateItems();
    }

    @Override
    public void generateItems() {
        for (int i = 0; i < SPELL_NAMES.size(); i++) {
            if (SPELL_TYPES.get(i).equals("F")) {
                FireSpell fireSpell = new FireSpell(SPELL_NAMES.get(i), SPELL_COSTS.get(i), SPELL_LEVELS.get(i), SPELL_DAMAGE.get(i), SPELL_MANA_COST.get(i));
                fireSpells.add(fireSpell);
                spells.add(fireSpell);
            } else if (SPELL_TYPES.get(i).equals("C")) {
                IceSpell iceSpell = new IceSpell(SPELL_NAMES.get(i), SPELL_COSTS.get(i), SPELL_LEVELS.get(i), SPELL_DAMAGE.get(i), SPELL_MANA_COST.get(i));
                iceSpells.add(iceSpell);
                spells.add(iceSpell);
            } else if (SPELL_TYPES.get(i).equals("L")) {
                LightningSpell lightningSpell = new LightningSpell(SPELL_NAMES.get(i), SPELL_COSTS.get(i), SPELL_LEVELS.get(i), SPELL_DAMAGE.get(i), SPELL_MANA_COST.get(i));
                lightningSpells.add(lightningSpell);
                spells.add(lightningSpell);
            }
        }
    }

    @Override
    public Spell create(int index) {
        return null;
    }

    @Override
    public List<Spell> getItems() {
        return spells;
    }

    @Override
    public int getItemsSize() {
        return spells.size();
    }

    public FireSpell createFireSpell(int index) {
        return fireSpells.get(index);
    }
    public int getFireSpellSize() {
        return fireSpells.size();
    }

    public IceSpell createIceSpell(int index) {
        return iceSpells.get(index);
    }
    public int getIceSpellSize() {
        return iceSpells.size();
    }

    public LightningSpell createLightningSpell(int index) {
        return lightningSpells.get(index);
    }
    public int getLightningSpellSize() {
        return lightningSpells.size();
    }

    public static SpellFactory getInstance() {
        if(spellFactory == null) {
            spellFactory = new SpellFactory();
        }
        return spellFactory;
    }
}
