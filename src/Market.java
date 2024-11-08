import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market extends Piece {
    private Scanner scanner = new Scanner(System.in);

    public Market(String name) {
        super(name);

    }

    @Override
    public void event(List<Hero> heroes) {
        System.out.println("Welcome to the market! Input 'M' to enter or 'S' to skip: ");
        String input;
        Boolean flag;
        do {
            input = scanner.next();
            flag = input.equalsIgnoreCase("S");
        } while (!(input.equalsIgnoreCase("M")) && !(input.equalsIgnoreCase("S")));

        if (flag) {
            return;
        }
        listAllItems();
        for(Hero hero : heroes) {
            System.out.println(hero.getName() + ", anything to buy? (Y/N)");
            String response;
            Boolean buy;
            do {
                response = scanner.next();
                buy = response.equalsIgnoreCase("Y");
            } while (!(response.equalsIgnoreCase("Y")) && !(response.equalsIgnoreCase("N")));

            if (buy) {
                buyFromMarket(hero);
            }
            if(hero.getInventory().getInventoryList().isEmpty()) {
                return;
            }
            System.out.println(hero.getName() + ", anything to sell? (Y/N)");
            String response2;
            Boolean sell;
            do {
                response2 = scanner.next();
                sell = response2.equalsIgnoreCase("Y");
            } while (!(response2.equalsIgnoreCase("Y")) && !(response2.equalsIgnoreCase("N")));
            if(sell) {
                System.out.println(hero.getName() + "'s inventory: ");
                for(Item item : hero.getInventory().getInventoryList()) {
                    System.out.println(item.getName());
                }
                sellToMarket(hero);
            }
        }
    }

    private void sellToMarket(Hero hero) {
        List<String> nameList = new ArrayList<>();
        for(Item item : hero.getInventory().getInventoryList()) {
            nameList.add(item.getName());
        }
        Boolean valid = true;
        while (valid) {
            System.out.println("Choose an item, please enter the type(Armory/Spells/Potions/Weaponry) and name: ");
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String type = parts[0];
            String name = parts[1];
            if(!(nameList.contains(name))) {
                System.out.println("Wrong name! Please enter again!");
                continue;
            }
            switch (type.toLowerCase()) {
                case "armory":
                    for(Armor armor : ArmorFactory.getInstance().getItems()){
                        if(name.equals(armor.getName())) {
                            hero.getInventory().removeItem(armor);
                            hero.addMoney((int)(armor.getPrice()*0.5));
                            valid = false;
                            System.out.printf("Transaction successful! Now you money is: %d.",hero.getMoney());
                        }
                    }
                    break;
                case "spells":
                    for(Spell spell : SpellFactory.getInstance().getItems()){
                        if(name.equals(spell.getName())) {
                            hero.getInventory().removeItem(spell);
                            hero.addMoney((int)(spell.getPrice()*0.5));
                            valid = false;
                            System.out.printf("Transaction successful! Now you money is: %d.",hero.getMoney());
                        }
                    }
                    break;
                case "potions":
                    for(Potion potion : PotionFactory.getInstance().getItems()){
                        if(name.equals(potion.getName())) {
                            hero.getInventory().removeItem(potion);
                            hero.addMoney((int)(potion.getPrice()*0.5));
                            valid = false;
                            System.out.printf("Transaction successful! Now you money is: %d.",hero.getMoney());
                        }
                    }
                    break;
                case "weaponry":
                    for(Weapon weapon : WeaponFactory.getInstance().getItems()){
                        if(name.equals(weapon.getName())) {
                            hero.getInventory().removeItem(weapon);
                            hero.addMoney((int)(weapon.getPrice()*0.5));
                            valid = false;
                            System.out.printf("Transaction successful! Now you money is: %d.",hero.getMoney());
                        }
                    }
                    break;
                default:
                    System.out.println("It's not a proper type!");
                    break;
            }
        }
    }

    private void buyFromMarket(Hero hero) {
        System.out.printf("Your money: %d. Your level: %d\n",
                hero.getMoney(), hero.getLevel());
        instruction();
        String input = scanner.next();
        while (true) {
            if(input.equalsIgnoreCase("q")) {
                break;
            } else if(input.equalsIgnoreCase("w")) {
                buyWeapon(hero);
            } else if(input.equalsIgnoreCase("a")) {
                buyArmor(hero);
            } else if(input.equalsIgnoreCase("p")) {
                buyPotion(hero);
            } else if(input.equalsIgnoreCase("s")) {
                buySpell(hero);
            } else {
                System.out.println("Invalid input! Please enter again: ");
                input = scanner.next();
                continue;
            }
            System.out.printf("Your money: %d. Your level: %d\n",
                    hero.getMoney(), hero.getLevel());
            System.out.println(hero.getName() + ", continue shopping? (Y/N)");
            String res;
            Boolean keep;
            do {
                res = scanner.next();
                keep = res.equalsIgnoreCase("Y");
            } while (!(res.equalsIgnoreCase("Y")) && !(res.equalsIgnoreCase("N")));

            if (keep) {
                System.out.println("Please enter the character again: ");
                input = scanner.next();
            } else {
                break;
            }
        }
    }

    private void buySpell(Hero hero) {
        while (true) {
            System.out.println("Please enter the number of potion you want to buy(enter f/i/l + number starting from 0):");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String input = parts[0];
            int index = Integer.parseInt(parts[1]);
            if(input.equals("f")) {
                if(index < SpellFactory.getInstance().getFireSpellSize() && index >= 0) {
                    FireSpell fp = SpellFactory.getInstance().createFireSpell(index);
                    hero.buy(fp);
                    break;
                }
            } else if(input.equals("i")) {
                if(index < SpellFactory.getInstance().getIceSpellSize() && index >= 0) {
                    IceSpell ip = SpellFactory.getInstance().createIceSpell(index);
                    hero.buy(ip);
                    break;
                }
            } else if(input.equals("l")) {
                if(index < SpellFactory.getInstance().getLightningSpellSize() && index >= 0) {
                    LightningSpell lp = SpellFactory.getInstance().createLightningSpell(index);
                    hero.buy(lp);
                    break;
                }
            } else {
                System.out.println("Invalid input! Please enter again!");
            }
        }
    }

    private void buyPotion(Hero hero) {
        System.out.println("Please enter the number of potion you want to buy(starting from 0):");
        int index = inputCheck(PotionFactory.getInstance().getItemsSize()-1);
        Potion potion = PotionFactory.getInstance().create(index);
        hero.buy(potion);
    }

    private void buyArmor(Hero hero) {
        System.out.println("Please enter the number of armor you want to buy(starting from 0):");
        int index = inputCheck(ArmorFactory.getInstance().getItemsSize()-1);
        Armor armor = ArmorFactory.getInstance().create(index);
        hero.buy(armor);
    }

    private void buyWeapon(Hero hero) {
        System.out.println("Please enter the number of weapon you want to buy(starting from 0):");
        int index = inputCheck(WeaponFactory.getInstance().getItemsSize()-1);
        Weapon weapon = WeaponFactory.getInstance().create(index);
        hero.buy(weapon);
    }

    private void instruction() {
        System.out.println("Please enter the item you want to buy:\n" +
                "'w': weapon\n" +
                "'a': armor\n" +
                "'p': potion\n" +
                "'s': spell\n" +
                "'q': quit the market");
    }

    private void listAllItems() {
        System.out.println("All weapons:");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-12s %-12s %-12s %-12s", "NAME", "COST", "LEVEL", "HANDS", "DAMAGE");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Weapon weapon : WeaponFactory.getInstance().getItems()) {
            System.out.format("%-12s %-12s %-12s %-12s %-12s", weapon.getName(), weapon.getPrice(), weapon.getLevelReq(), weapon.getHandsReq(), weapon.getDamage());
            System.out.println();
        }

        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.println("All armors:");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-20s","NAME", "COST", "LEVEL", "DAMAGE REDUCTION");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Armor armor : ArmorFactory.getInstance().getItems()) {
            System.out.format("%-15s %-15s %-15s %-20s", armor.getName(), armor.getPrice(), armor.getLevelReq(), armor.getDefense());
            System.out.println();
        }

        System.out.println("----------------------------------------------------------------------------------------------");

        System.out.println("All potions:");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-15s %-15s %-45s", "NAME", "COST", "LEVEL", "INCREASE AMOUNT", "ATTRIBUTES");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");

        for (Potion potion : PotionFactory.getInstance().getItems()) {
            System.out.format("%-15s %-15s %-15s %-15s %-45s", potion.getName(), potion.getPrice(), potion.getLevelReq(), potion.getIncreaseAmount(), potion.getAttributesAffected().toString().replace("[", "").replace("]", ""));
            System.out.println();
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        System.out.println("All spells:");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-15s %-15s %-15s %-15s", "TYPE", "NAME", "COST", "MANA COST", "LEVEL", "DAMAGE");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

        for (Spell spell : SpellFactory.getInstance().getItems()) {
            String typeOfSpell = "";

            if (spell instanceof FireSpell) {
                typeOfSpell = "Fire";
            } else if (spell instanceof IceSpell) {
                typeOfSpell = "Ice";
            } else if (spell instanceof LightningSpell) {
                typeOfSpell = "Lightning";
            }

            System.out.format("%-15s %-20s %-15s %-15s %-15s %-15s", typeOfSpell, spell.getName(), spell.getPrice(), spell.getManaCost(), spell.getLevelReq(), spell.getDamage());
            System.out.println();
        }

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static int inputCheck(int max) {
        Scanner scanner = new Scanner(System.in);
        int index = 0;
        boolean validInput = false;
        while (!validInput) {
            if (scanner.hasNextInt()) {
                index = scanner.nextInt();
                if (index <= max && index >= 0) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 0 and " + max);
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        return index;
    }
}
