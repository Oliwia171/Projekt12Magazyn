public class Product extends Item {

    // dziedziczenie po klasie Item
    // przesłanianie metod
    // przeciążony konstruktor
    // hermetyzacja

    private double price;


    public Product(String name) {
        super(name);
        this.price = 0.0;
    }

    @Override
    public String getDescription() {
        return "Produkt: " + getName();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
