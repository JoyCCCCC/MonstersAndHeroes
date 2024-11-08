import java.util.List;

public class Board {
    private Tile[][] tiles;
    private int row;
    private int col;
    private List<Hero> heroes;

    public Board(int row, int col, List<Hero> heroes) {
        this.tiles = new Tile[row][col];
        this.row = row;
        this.col = col;
        this.heroes = heroes;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                tiles[i][j] = generatePiece(i, j);
            }
        }
    }

    public int[] initHeroesPosition() {
        while(true) {
            int initRow = (int)(Math.random() * this.row);
            int initCol = (int)(Math.random() * this.col);
            if(tiles[initRow][initCol].getPiece() instanceof Common) {
                int[] heroesPosition = new int[]{initRow, initCol};
                for(Hero hero : heroes) {
                    hero.setPosition(heroesPosition);
                    hero.setRow(initRow);
                    hero.setCol(initCol);
                }
                tiles[initRow][initCol].setPiece(heroes.get(0).getSymbol());
                return heroesPosition;
            }
        }
    }

    private Tile generatePiece(int row, int col) {
        // 20% inaccessible spaces
        // 30% market spaces
        // 50% common spaces
        double randNum = Math.random();
        if(randNum < 0.2) {
            Inaccessible inaccessible = new Inaccessible("X");
            inaccessible.setCol(col);
            inaccessible.setRow(row);
            return new Tile(inaccessible);
        } else if(randNum < 0.5) {
            Market market = new Market("M");
            market.setCol(col);
            market.setRow(row);
            return new Tile(market);
        } else {
            Common common = new Common(" ");
            common.setCol(col);
            common.setRow(row);
            return new Tile(common);

        }
    }


    public void display() {
        System.out.println("'M' represents markets, 'X' represents inaccessible places, 'T' represents your current place.");
        for(int i = 0; i < row; i++) {
            printBar();
            printRow(i);
        }
        printBar();
    }

    private void printBar() {
        for(int i = 0; i < col; i++) {
            System.out.print("+--");
        }
        System.out.println("+");
    }

    private void printRow(int row) {
        for(int j = 0; j < col; j++) {
            System.out.print("|");
            if(row == heroes.get(0).getRow() && j == heroes.get(0).getCol()) {
                System.out.print("T ");
            } else {
                System.out.print(tiles[row][j].getPiece().getName() + " ");
            }
        }
        System.out.println("|");
    }

    public void makeMove(List<Hero> heroes, String movement) {
        int sourceRow = heroes.get(0).getRow();
        int sourceCol = heroes.get(0).getCol();
        int destineRow = heroes.get(0).getRow();
        int destineCol = heroes.get(0).getCol();
        if(movement.equalsIgnoreCase("w")) {
            // move up
            destineRow -= 1;
        } else if(movement.equalsIgnoreCase("a")) {
            // move left
            destineCol -= 1;
        } else if(movement.equalsIgnoreCase("s")) {
            // move down
            destineRow += 1;
        } else if(movement.equalsIgnoreCase("d")) {
            // move right
            destineCol += 1;
        } else {
            System.out.println("Invalid direction!");
        }
        if(!isValidMove(destineRow, destineCol)) {
            System.out.println("Invalid move. Out of bound or hit the wall!");
        } else {
            Piece newPosition = tiles[destineRow][destineCol].getPiece();
            Piece oldPosition = heroes.get(0).getOld();
            for(Hero hero : heroes) {
                hero.setCol(destineCol);
                hero.setRow(destineRow);
                hero.setPosition(new int[]{destineRow, destineCol});
                hero.setOld(newPosition);
            }
            tiles[destineRow][destineCol].getPiece().event(heroes);
            tiles[destineRow][destineCol].setPiece(heroes.get(0).getSymbol()); //Update the player's new position.
            tiles[sourceRow][sourceCol].setPiece(oldPosition); //Clear previous player position.
        }
    }

    //Cannot go beyond borders and cannot enter Inaccessible places.
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < this.row &&
                col >= 0 && col < this.col && (tiles[row][col].getPiece().getClass() != Inaccessible.class);
    }
}
