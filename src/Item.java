public class Item{
    private String name;
    private int price;
    private int minLevelReq;

    public Item(String name, int price, int minLevelReq) {
        this.name = name;
        this.price = price;
        this.minLevelReq = minLevelReq;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelReq() {
        return minLevelReq;
    }
}
