import model.Product;
import model.ShippingRate;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<ShippingRate> shippingRates = new ArrayList<>();
        Product p1 = new Product("Mouse", 10.99, "RO", 0.2);
        Product p2 = new Product("Keyboard", 40.99, "UK", 0.7);
        Product p3 = new Product("Monitor", 164.99, "US", 1.9);
        Product p4 = new Product("Webcam", 84.99, "RO", 0.2);
        Product p5 = new Product("Headphones", 59.99, "US", 0.6);
        Product p6 = new Product("Desk Lamp", 89.99, "UK", 1.3);

        ShippingRate shippingRate1 = new ShippingRate("RO", 1);
        ShippingRate shippingRate2 = new ShippingRate("UK", 2);
        ShippingRate shippingRate3 = new ShippingRate("US", 3);

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
        products.add(p6);

        shippingRates.add(shippingRate1);
        shippingRates.add(shippingRate2);
        shippingRates.add(shippingRate3);

        for(Product product : products) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }
    }
}
