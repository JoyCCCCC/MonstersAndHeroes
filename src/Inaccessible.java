import java.util.List;

public class Inaccessible extends Piece {
    public Inaccessible(String name) {
        super(name);
    }

    @Override
    public void event(List<Hero> heroes) {
        System.out.println("Oops! This place is inaccessible!");
    }
}
