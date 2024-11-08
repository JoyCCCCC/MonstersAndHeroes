import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Role{
    private String name;
    private int[] position;
    private int damage;
    private int defense;
    private int dodge;
    private int level;
    private int hp;

    public Role(String name, int level, int hp, int damage, int defense, int dodge, int[] position) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.damage = damage;
        this.defense = defense;
        this.dodge = dodge;
        this.position = position;
    }

    public void attack(Role role) {
        int dodgeChance = role.getDodge();
        System.out.printf("%s Attacks %s\n", this.getName(), role.getName());
        if(role instanceof Hero){
            if(Math.random() < dodgeChance*0.002) {
                System.out.printf("Oops! %s dodges.\n", role.getName());
                return;
            }
            ((Hero)role).setWeaponDamage();
        } else {
            if(Math.random() < dodgeChance*0.01) {
                System.out.printf("Oops! %s dodges.\n", role.getName());
                return;
            }
        }
        int minusHp = Math.max(this.getDamage() - role.getDefense(), 0);
        role.minusHp(minusHp);
        System.out.printf("%s caused %d damage. Now %s has hp: %d.\n",
                this.getName(), minusHp,
                role.getName(), Math.max(role.getHp(), 0));
    }

    public void addLevel(int addition) {
        this.level += addition;
    }
    public void addHp(int addition) {
        this.hp += addition;
    }
    public void minusHp(int minus) {
        this.hp -= minus;
    }
    public void minusDodge(int minus) {
        this.dodge -= minus;
    }
    public void addDefense(int addition) {
        this.defense += addition;
    }
    public void minusDefense(int minus) {
        this.defense -= minus;
    }
    public void minusDamage(int minus) {
        this.damage -= minus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodge() {
        return dodge;
    }

    public void setDodge(int dodge) {
        this.dodge = dodge;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getRow() {
        return position[0];
    }

    public void setRow(int row) {
        this.position[0] = row;
    }

    public int getCol() {
        return position[1];
    }

    public void setCol(int col) {
        this.position[1] = col;
    }
}
