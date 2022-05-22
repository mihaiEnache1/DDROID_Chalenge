package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String shippedFrom;
    private double weight;

    public Product() {
    }

    public Product(String name, double price, String shippedFrom, double weight) {
        this.name = name;
        this.price = price;
        this.shippedFrom = shippedFrom;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShippedFrom() {
        return shippedFrom;
    }

    public void setShippedFrom(String shippedFrom) {
        this.shippedFrom = shippedFrom;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", shippedFrom='" + shippedFrom + '\'' +
                ", weight=" + weight +
                '}';
    }
}
