import java.util.List;

public class Tile {
    private Piece piece; // Represents the piece on the tile

    public Tile(Piece piece) {this.piece = piece;}

//    public abstract List<Hero> comeTo(List<Hero> heroes);
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    //get the piece on the tile or tile number if not occupied by piece
    public Piece getPiece() {
        return this.piece;
    }
}
