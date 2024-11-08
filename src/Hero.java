import java.util.ArrayList;
import java.util.List;

public class Hero extends Role{
    private Piece symbol; //The hero piece
    private Piece old; //The type of board the hero is currently on.
    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int money;
    private int experience;
    private Inventory inventory;
    private List<Weapon> weapons;
    private Armor armor;
    private Spell spell;

    public Hero(String name, int level, int hp, int mana, int strength, int agility, int dexterity, int money, int experience, Inventory inventory, int[] position) {
        super(name,level,hp,0,0,0,position);
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;
        this.inventory = inventory;
        this.weapons = new ArrayList<>();
        this.armor = null;
        this.spell = null;
        this.symbol = new Piece("H");
        this.old = new Common(" ");
    }

    public Piece getSymbol() {
        return symbol;
    }

    //set the piece type player chooses
    public void setSymbol(Piece symbol) {
        this.symbol = symbol;
    }

    public Piece getOld() {
        return old;
    }

    public void setOld(Piece old) {
        this.old = old;
    }

    public boolean levelUp() {
        int requireExp = 10 * getLevel();
        if(getExperience() >= requireExp) {
            this.addLevel(1);
            this.setExperience(getExperience() - requireExp);
            this.mana = (int)(this.mana * 1.1);
            this.setHp(Math.max(this.getHp(), this.getLevel() * 100));
            return true;
        }
        return false;
    }

    public void setWeaponDamage() {
        int damage = (int) (strength * 0.5);
        if(weapons != null) {
            int weaponDamage = 0;
            for (Weapon weapon : weapons) {
                weaponDamage += weapon.getDamage();
            }
            damage = (int) ((strength + weaponDamage) * 0.5);
        }
        super.setDamage(damage);
    }

    public void setSpellDamage(){
        int damage = (1+this.getDexterity()/10000)*spell.getDamage();
        super.setDamage(damage);
    }
    public void setDefense() {
        int defense = 0;
        if(armor != null) {
            defense = (int) (armor.getDefense() * 0.1);
        }
        super.setDefense(defense);
    }

    public Boolean buy(Item item) {
        if(item.getLevelReq() > this.getLevel() || this.getMoney() < item.getPrice()) {
            System.out.println("You cannot buy the item: Not enough money or level!");
            return false;
        } else {
            System.out.println("Transaction successful!");
            minusMoney(item.getPrice());
            this.getInventory().addItem(item);
            return true;
        }
    }

    public void addExp(int addition) {
        this.experience += addition;
        levelUp();
    }
    public void addMana(int addition) {
        this.mana += addition;
    }
    public void minusMana(int minus) {
        this.mana -= minus;
    }
    public void addStrength(int addition) {
        this.strength += addition;
    }
    public void addAgility(int addition) {
        this.agility += addition;
    }
    public void addDexterity(int addition) {
        this.dexterity += addition;
    }
    public void addMoney(int addition) {
        this.money += addition;
    }

    public void minusMoney(int minus) {
        this.money -= minus;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
        super.setDodge(agility);
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getMoney() {
        return money;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setSpell(Spell spell) {this.spell = spell;}


}
