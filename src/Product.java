public class Product extends Item {
    public Product(String name) {
        super(name);
    }

    @Override
    public String getDescription() {
        return "Produkt:"+name;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}