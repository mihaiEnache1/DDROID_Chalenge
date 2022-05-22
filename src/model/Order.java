package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<Product, Integer> order;

    public Order() {
        order = new HashMap<>();
    }

    public Map<Product, Integer> getOrder() {
        return order;
    }

    public void setOrder(Map<Product, Integer> order) {
        this.order = order;
    }

    public ArrayList<Double> compute(Map<Product, Integer> order, ArrayList<ShippingRate> shippingRates) {
        ArrayList<Double> invoice = new ArrayList<>();
        double subtotal = 0;
        double shipping = 0;
        boolean keyboard = false;
        double discount = 0;
        double VAT;
        int countKeyboards = 0;
        for(Map.Entry<Product, Integer> set : order.entrySet()) {
            if(set.getKey().getName().equals("Keyboard")) {
                discount = set.getKey().getPrice() * 10 / 100;
                keyboard = true;
                countKeyboards = set.getValue();
            }
            subtotal += set.getKey().getPrice() * set.getValue();
            for(ShippingRate shippingRate : shippingRates) {
                if(set.getKey().getShippedFrom().equals(shippingRate.getCountry())) {
                    shipping += set.getValue() * shippingRate.getRate() * set.getKey().getWeight() * 10;
                }
            }
        }
        VAT = subtotal * 19 / 100;
        invoice.add(subtotal); //initial subtotal
        invoice.add(shipping); //initial shipping
        invoice.add(discount * countKeyboards); //total discount
        invoice.add(VAT); //VAT
        if(keyboard) {
            subtotal -= countKeyboards * discount;
        }
        if(order.size() >= 2) {
            shipping -= 10;
        }
        invoice.add(subtotal + shipping + VAT); //total
        return invoice;
    }
}
