import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }
    public static void start() {
        Scanner scanner = new Scanner(System.in);
        List<Hero> heroes = new ArrayList<>();
        while (true) {
            // show menu
            System.out.println("Welcome to Legends: Monsters and Heroes! Please choose a number(input an integer):");
            System.out.println("1) Start");
            System.out.println("2) Instructions");
            System.out.println("3) Exit");

            int choice = scanner.nextInt();
            int teamMemberNum = 1;

            if (choice == 1) {
                System.out.println("Please select the number of heroes for the game(1-3): ");
                boolean validInput = false;
                while (!validInput) {
                    if (scanner.hasNextInt()) {
                        teamMemberNum = scanner.nextInt();
                        if (teamMemberNum <= 3 && teamMemberNum >= 1) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a number between 1 and 3.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                    }
                }
                for(int i = 0; i < teamMemberNum; i++){
                    listAllHeroes();
                    heroes.add(chooseHero());
                }
                Game game = new Game(heroes);
                game.play();
            } else if (choice == 2) {
                // show instructions
                System.out.println("W/w: move up");
                System.out.println("A/a: move left");
                System.out.println("S/s: move down");
                System.out.println("D/d: move right");
                System.out.println("Q/q: quit game");
                System.out.println("I/i: show inventory");
                System.out.println("M/m: enter market");
            } else if (choice == 3) {
                System.out.println("Good Game!");
                break;
            } else{
                System.out.println("Wrong choice. Try again.");
            }
        }
    }

    public static Hero chooseHero() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose a hero type(enter w/s/p + number starting from 0):");
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String input = parts[0];
            int index = Integer.parseInt(parts[1]);
            if(input.equals("w")) {
                if(index < HeroFactory.getInstance().getWarriorSize() && index >= 0) {
                    Warrior warrior = HeroFactory.getInstance().createWarrior(index);
                    return warrior;
                }
            } else if(input.equals("s")) {
                if(index < HeroFactory.getInstance().getSorcererSize() && index >= 0) {
                    Sorcerer sorcerer = HeroFactory.getInstance().createSorcerer(index);
                    return sorcerer;
                }
            } else if(input.equals("p")) {
                if(index < HeroFactory.getInstance().getPaladinSize() && index >= 0) {
                    Paladin paladin = HeroFactory.getInstance().createPaladin(index);
                    return paladin;
                }
            } else {
                System.out.println("invalid input! Please enter again!");
            }
        }
    }

    public static void listAllHeroes() {
        System.out.println("All heroes:");
        System.out.println("-----------------------------------------------------------------------------------------------");
        String format = "%-12s %-20s %-8s %-8s %-8s %-8s %-15s %-15s%n";

        System.out.printf(format, "Type", "Name", "Mana", "Strength", "Agility", "Dexterity", "Starting Money", "Starting Experience");
        System.out.println("-----------------------------------------------------------------------------------------------");

        for (Hero hero : HeroFactory.getInstance().getHeroes()) {
            String typeOfSpell = "";

            if (hero instanceof Warrior) {
                typeOfSpell = "Warrior";
            } else if (hero instanceof Sorcerer) {
                typeOfSpell = "Sorcerer";
            } else if (hero instanceof Paladin) {
                typeOfSpell = "Paladin";
            }

            System.out.printf(format, typeOfSpell, hero.getName(), hero.getMana(), hero.getStrength(), hero.getAgility(), hero.getDexterity(), hero.getMoney(), hero.getExperience());
        }

        System.out.println("-----------------------------------------------------------------------------------------------");
    }
}
