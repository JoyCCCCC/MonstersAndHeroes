import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> inventoryList;

    public Inventory() {
        this.inventoryList = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventoryList.add(item);
    }

    public void removeItem(Item item) {
        inventoryList.remove(item);
    }
    public List<Item> getInventoryList() {return this.inventoryList;}
}
