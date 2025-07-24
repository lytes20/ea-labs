package customers;

public class Product {
    public int productNumber;
    public String name;
    public double price;
    private Supplier supplier;

    public Product(int productNumber, String name, double price, Supplier supplier) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    public int getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setProductNumber(int productNumber) {
        this.productNumber = productNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNumber=" + productNumber +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", supplier=" + supplier +
                '}';
    }
}
