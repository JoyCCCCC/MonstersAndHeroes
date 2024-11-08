import java.util.List;
public interface ItemFactory<T extends Item> {
    T create(int index);
    List<T> getItems();
    void generateItems();
    int getItemsSize();
}
