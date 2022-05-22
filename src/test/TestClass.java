package test;
import model.Order;
import model.Product;
import model.ShippingRate;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


public class TestClass {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @Test
    public void testCompute() {
        df.setRoundingMode(RoundingMode.UP);
        Map<Product, Integer> order = new HashMap<>();
        Product product1 = new Product("Keyboard", 40.99, "UK", 0.7);
        Product product2 = new Product("Monitor", 164.99, "US", 1.9);
        Order newOrder = new Order();
        ArrayList<ShippingRate> shippingRates = new ArrayList<>();
        ShippingRate shippingRate1 = new ShippingRate("RO", 1);
        ShippingRate shippingRate2 = new ShippingRate("UK", 2);
        ShippingRate shippingRate3 = new ShippingRate("US", 3);
        shippingRates.add(shippingRate1);
        shippingRates.add(shippingRate2);
        shippingRates.add(shippingRate3);
        order.put(product1, 1);
        order.put(product2, 2);
        ArrayList<Double> invoice = newOrder.compute(order, shippingRates);
        assertEquals(df.format(invoice.get(0)), "370.97");
        assertEquals(df.format(invoice.get(1)), "128.00");
        assertEquals(df.format(invoice.get(2)), "4.10");
        assertEquals(df.format(invoice.get(3)), "70.49");
        assertEquals(df.format(invoice.get(4)), "555.36");
    }
}
