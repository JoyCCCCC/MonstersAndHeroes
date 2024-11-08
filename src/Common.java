import java.util.*;

public class Common extends Piece {
    public Common(String name) {
        super(name);
    }

    @Override
    public void event(List<Hero> heroes) {
        // Copy the initial heroes to compare conveniently after fight.
        List<Hero> heroes1 = new ArrayList<>(heroes);
        // 60% chance of encountering a monster.
        if(Math.random() < 0.6) {
            List<Monster> monsterTeam = createMonsterTeam(heroes.size(),heroes);
            List<? extends Role> winTeam = fight(heroes,monsterTeam);
            if(winTeam.get(0) instanceof Monster) {
                System.out.println("Monster team win, all heroes die. Game over!");
                System.exit(0);
            } else {
                System.out.println("hero team win, reward heroes.");
                List<Hero> winningHeroes = (List<Hero>) winTeam;
                List<String> winnerNames = new ArrayList<>();
                for(Hero hero : winningHeroes) {
                    rewardRole(hero, heroes1.size());
                    hero.levelUp();
                    winnerNames.add(hero.getName());
                }
                for(Hero hero : heroes1) {
                    //Get the member who lost.
                    if(!winnerNames.contains(hero.getName())) {
                        hero.setHp((int)(hero.getHp()*0.5));
                        hero.setMana((int)(hero.getMana()*0.5));
                        heroes.add(hero);
                    }
                }
            }
        }
    }

    private void recoverRole(Hero hero) {
        if(hero.getHp() > 0) {
            hero.setHp((int)(hero.getHp() * 1.1));
            hero.setMana((int)(hero.getMana() * 1.1));
            System.out.printf("%s is recovered. Now hp: %d, mana: %d\n",
                    hero.getName(), hero.getHp(), hero.getMana());
        }
    }

    private void rewardRole(Hero hero, int size) {
        int moneyAddition = 100 * hero.getLevel();
        int expAddition = 2 * size;
        hero.addMoney(moneyAddition);
        hero.addExp(expAddition);
        System.out.printf("%s gains money of %d, experience of %d.\n", hero.getName(), moneyAddition, expAddition);
    }

    private List<Monster> createMonsterTeam(int teamMember, List<Hero> heroes) {
        List<Monster> monsterTeam = new ArrayList<>();
        for(int i = 0; i < teamMember; i++) {
            Monster monster = MonsterFactory.getInstance().create(heroes);
            System.out.println("A/An " + monster.getName() + " has been created!");
            monsterTeam.add(monster);
        }
        return monsterTeam;
    }

    public <T extends Role> List<T> fight(List<Hero> heroes, List<Monster> monsters) {
        Scanner scanner = new Scanner(System.in);
        while (!monsters.isEmpty() && !heroes.isEmpty()) {
            for(Hero hero : heroes) {
                System.out.println("Please choose a monster number to attack.(enter 0 - " + (monsters.size()-1) + "): ");
                int index = Market.inputCheck(monsters.size()-1);
                Boolean used = false;
                if(!hero.getInventory().getInventoryList().isEmpty()) {
                    if(hero.getInventory().getInventoryList().stream().anyMatch(item -> item instanceof Weapon)) {
                        System.out.println("Do you want to equip or change weapons?(Y or N, if you choose to change, you will take off all your weapons):");
                        String response1;
                        do {
                            response1 = scanner.next();
                            used = response1.equalsIgnoreCase("Y");
                        } while (!(response1.equalsIgnoreCase("Y")) && !(response1.equalsIgnoreCase("N")));
                        if(used) {
                            if(!hero.getWeapons().isEmpty()){
                                for(Weapon weapon : hero.getWeapons()) {
                                    hero.getInventory().addItem(weapon);
                                }
                                hero.getWeapons().clear();
                            }
                            System.out.println("Choose a weapon, please enter the name: ");
                            List<String> nameList1 = new ArrayList<>();
                            for(Item item : hero.getInventory().getInventoryList()) {
                                if(item instanceof Weapon) {
                                    nameList1.add(item.getName());
                                    System.out.println(item.getName());
                                }
                            }
                            String name1;
                            do {
                                name1 = scanner.next();
                            } while (!(nameList1.contains(name1)));
                            int handsReq = 0;
                            List<Weapon> weaponList = new ArrayList<>();
                            for(Weapon weapon : WeaponFactory.getInstance().getItems()){
                                if(name1.equals(weapon.getName())) {
                                    handsReq = weapon.getHandsReq();
                                    weaponList.add(weapon);
                                    hero.getInventory().removeItem(weapon);
                                    break;
                                }
                            }
                            used = false;
                            if(handsReq == 1) {
                                System.out.println("The weapon you choose only needs 1 hand. Do you want to add a weapon?(Y or N):");
                                String response2;
                                do {
                                    response2 = scanner.next();
                                    used = response2.equalsIgnoreCase("Y");
                                } while (!(response2.equalsIgnoreCase("Y")) && !(response2.equalsIgnoreCase("N")));
                                if(used) {
                                    System.out.println("Choose the second weapon, please enter the name: ");
                                    List<String> nameList2 = new ArrayList<>();
                                    for(Item item : hero.getInventory().getInventoryList()) {
                                        if(item instanceof Weapon) {
                                            nameList2.add(item.getName());
                                            System.out.println(item.getName());
                                        }
                                    }
                                    String name2;
                                    do {
                                        name2 = scanner.next();
                                    } while (!(nameList2.contains(name2)));
                                    for(Weapon weapon : WeaponFactory.getInstance().getItems()) {
                                        if (name2.equals(weapon.getName()) && weapon.getHandsReq() == 1) {
                                            weaponList.add(weapon);
                                            hero.getInventory().removeItem(weapon);
                                            break;
                                        }
                                    }
                                }
                            }
                            hero.setWeapons(weaponList);
                        }
                    }
                    used = false;
                    if(hero.getInventory().getInventoryList().stream().anyMatch(item -> item instanceof Armor)) {
                        System.out.println("Do you want to equip or change an armor?(Y or N):");
                        String response3;
                        do {
                            response3 = scanner.next();
                            used = response3.equalsIgnoreCase("Y");
                        } while (!(response3.equalsIgnoreCase("Y")) && !(response3.equalsIgnoreCase("N")));
                        if (used) {
                            if(hero.getArmor() != null) {
                                hero.getInventory().addItem(hero.getArmor());
                                hero.setArmor(null);
                            }
                            System.out.println("Choose an armor, please enter the name: ");
                            List<String> nameList3 = new ArrayList<>();
                            for (Item item : hero.getInventory().getInventoryList()) {
                                if (item instanceof Armor) {
                                    nameList3.add(item.getName());
                                    System.out.println(item.getName());
                                }
                            }
                            String name3;
                            do {
                                name3 = scanner.next();
                            } while (!(nameList3.contains(name3)));
                            for (Armor armor : ArmorFactory.getInstance().getItems()) {
                                if (name3.equals(armor.getName())) {
                                    hero.setArmor(armor);
                                    hero.setDefense();
                                    hero.getInventory().removeItem(armor);
                                    break;
                                }
                            }
                        }
                    }
                    used = false;
                    if(hero.getInventory().getInventoryList().stream().anyMatch(item -> item instanceof Potion)) {
                        System.out.println("Do you want to use a potion?(Y or N):");
                        String response4;
                        do {
                            response4 = scanner.next();
                            used = response4.equalsIgnoreCase("Y");
                        } while (!(response4.equalsIgnoreCase("Y")) && !(response4.equalsIgnoreCase("N")));
                        if (used) {
                            System.out.println("Choose a potion, please enter the name: ");
                            List<String> nameList4 = new ArrayList<>();
                            for (Item item : hero.getInventory().getInventoryList()) {
                                if (item instanceof Spell) {
                                    nameList4.add(item.getName());
                                    System.out.println(item.getName());
                                }
                            }
                            String name4;
                            do {
                                name4 = scanner.next();
                            } while (!(nameList4.contains(name4)));
                            for (Potion potion : PotionFactory.getInstance().getItems()) {
                                if (name4.equals(potion.getName())) {
                                    potion.used(hero);
                                    break;
                                }
                            }
                        }
                    }
                    used = false;
                    if(hero.getInventory().getInventoryList().stream().anyMatch(item -> item instanceof Spell)) {
                        System.out.println("Do you want to use a spell?(Y or N):");
                        String response;
                        do {
                            response = scanner.next();
                            used = response.equalsIgnoreCase("Y");
                        } while (!(response.equalsIgnoreCase("Y")) && !(response.equalsIgnoreCase("N")));
                        if(used) {
                            System.out.println("Choose a spell, please enter the name: ");
                            List<String> nameList = new ArrayList<>();
                            for(Item item : hero.getInventory().getInventoryList()) {
                                if(item instanceof Spell) {
                                    nameList.add(item.getName());
                                    System.out.println(item.getName());
                                }
                            }
                            String name;
                            do {
                                name = scanner.next();
                            } while (!(nameList.contains(name)));
                            used = false;
                            for(Spell spell : SpellFactory.getInstance().getItems()){
                                if(name.equals(spell.getName()) && hero.getMana() >= spell.getManaCost()){
                                    if(spell instanceof IceSpell) {
                                        monsters.get(index).minusDamage((int)(0.1*monsters.get(index).getDamage()));
                                    } else if (spell instanceof FireSpell) {
                                        monsters.get(index).minusDefense((int)(0.1*monsters.get(index).getDefense()));
                                    } else if (spell instanceof LightningSpell) {
                                        monsters.get(index).minusDodge((int)(0.1*monsters.get(index).getDodge()));
                                    }
                                    hero.setSpell(spell);
                                    hero.getInventory().removeItem(spell);
                                    hero.minusMana(spell.getManaCost());
                                    hero.setSpellDamage();
                                    used = true;
                                    if(Math.random() < monsters.get(index).getDodge()*0.01) {
                                        System.out.printf("Oops! %s dodges.\n", monsters.get(index).getName());
                                    } else {
                                        int minusHp = Math.max(hero.getDamage() - monsters.get(index).getDefense(), 0);
                                        monsters.get(index).minusHp(minusHp);
                                        System.out.printf("%s caused %d damage. Now %s has hp: %d.\n",
                                                hero.getName(), minusHp,
                                                monsters.get(index).getName(), Math.max(monsters.get(index).getHp(), 0));
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
                if(!used) {
                    hero.attack(monsters.get(index));
                }
                if(monsters.get(index).getHp() <= 0) {
                    System.out.println(monsters.get(index).getName() + " has been defeated!");
                    monsters.remove(monsters.get(index));
                }
                recoverRole(hero);
            }
            if(monsters.isEmpty()){
                break;
            }
            for(Monster monster : monsters) {
                int index = (int)(Math.random() * heroes.size());
                monster.attack(heroes.get(index));
                if(heroes.get(index).getHp() <= 0) {
                    System.out.println(heroes.get(index).getName() + " fainted!");
                    heroes.remove(heroes.get(index));
                }
            }
        }
        if(monsters.isEmpty()) {
            return (List<T>) heroes;
        } else {
            return (List<T>) monsters;
        }
    }
}
