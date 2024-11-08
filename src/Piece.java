import java.util.List;

public class Piece {
    private String name;
    private int row;
    private int col;

    public Piece(String name) {
        this.name = name;
    }

    // get the name of the piece
    public String getName() {
        return name;
    }

    public void event(List<Hero> heroes) {};
    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}

