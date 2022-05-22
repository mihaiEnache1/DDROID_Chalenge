package presentation;

import connectivity.ConnectionFactory;
import model.Product;
import model.ShippingRate;

import java.math.RoundingMode;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private GUI gui;
    private ConnectionFactory connectionFactory;
    private Map<Product, Integer> order;
    private ArrayList<Product> products;
    private ArrayList<ShippingRate> shippingRates;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Controller(GUI gui, ConnectionFactory connectionFactory) {
        this.gui = gui;
        this.connectionFactory = connectionFactory;
        this.order = new HashMap<>();
        products = this.connectionFactory.getProducts();
        shippingRates = this.connectionFactory.getShippingRates();
        createTable();

        this.gui.addButtonListener(e -> {
            if(e.getSource() == this.gui.getAddButton()) {
                StringBuilder text = new StringBuilder();
                Product product = this.products.get(this.gui.getTable().getSelectedRow());
                if(order.containsKey(product)) {
                    for(Map.Entry<Product, Integer> set : order.entrySet()) {
                        if(set.getKey().equals(product)) {
                            set.setValue(set.getValue() + 1);
                        }
                    }
                } else {
                    order.put(product, 1);
                }
                for(Map.Entry<Product, Integer> set : order.entrySet()) {
                    if(set.getKey() != null) {
                        text.append(set.getKey().getName()).append(" x ").append(set.getValue()).append("\n");
                    }
                }
                this.gui.setTextArea(text.toString());
            }
        });

        this.gui.addCheckListener(e -> {
            if(e.getSource() == this.gui.getCheckOutButton()) {
                df.setRoundingMode(RoundingMode.UP);
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
                String text = "Subtotal: $" + df.format(subtotal) +
                            "\n" + "Shipping: $" + df.format(shipping) + "\n";
                VAT = subtotal * 19 /100;
                text += "VAT: $" + df.format(VAT) + "\n\n";
                if(keyboard) {
                    text += "Discounts:" +  "\n" + "10% off keyboards: -$" + df.format(countKeyboards * discount) + "\n";
                    subtotal -= countKeyboards * discount;
                }
                if(order.size() >= 2) {
                    text += "$10 off shipping: -$10" + "\n";
                    shipping -= 10;
                }
                text += "Total: $" + df.format(subtotal + shipping +VAT);
                this.gui.setCheckArea(text);
            }
        });
    }

    public void createTable() {
        ArrayList<Product> productList = this.connectionFactory.getProducts();
        ArrayList<ShippingRate> shippingRates = this.connectionFactory.getShippingRates();
        for(Product product : productList) {
            for(ShippingRate shippingRate : shippingRates) {
                if(product.getShippedFrom().equals(shippingRate.getCountry())) {
                    this.gui.getDefaultTableModel().addRow(new Object[]{
                            product.getName(),
                            product.getPrice(),
                            product.getShippedFrom(),
                            product.getWeight(),
                            shippingRate.getRate(),
                    });
                }
            }

        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = ConnectionFactory.getConnection();
        new Controller(gui, connectionFactory);
    }
}
