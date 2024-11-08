import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Hero> heroes;
    private Scanner scanner = new Scanner(System.in);

    public Game(List<Hero> heroes) {
        this.heroes = heroes;
        this.board = new Board(8,8, heroes);
        this.board.initHeroesPosition();
    }

    public void play() {
        boolean flag = true;
        while (flag) {
            board.display();
            handleControlInput();
        }
    }

    private void handleControlInput() {
        System.out.println("Please enter an operation(w/a/s/d/i/q/m):");
        String input = scanner.next();
        while (true) {
            if(input.equalsIgnoreCase("q")) {
                System.out.println("Good game!");
                System.exit(0);
            } else if(input.equalsIgnoreCase("w") ||
                    input.equalsIgnoreCase("a") ||
                    input.equalsIgnoreCase("s") ||
                    input.equalsIgnoreCase("d")) {
                board.makeMove(heroes, input);
                break;
            } else if(input.equalsIgnoreCase("i")) {
                showTeamInventory();
                break;
            } else {
                System.out.println("Invalid input! Please enter again: ");
                input = scanner.next();
            }
        }
    }

    private void showTeamInventory() {
        for(Hero hero : heroes) {
            System.out.println(hero.getName() + "'s inventory: ");
            for(Item item : hero.getInventory().getInventoryList()) {
                System.out.println(item.getName());
            }
        }
    }
}
