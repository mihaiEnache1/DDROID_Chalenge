package connectivity;

import model.Product;
import model.ShippingRate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionFactory {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/productsdb", "root", "root");
            connection.setAutoCommit(false);
            System.out.println("Connection successfully");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try {
            products = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setShippedFrom(resultSet.getString("shippedFrom"));
                product.setWeight(resultSet.getDouble("weight"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<ShippingRate> getShippingRates() {
        ArrayList<ShippingRate> shippingRates = new ArrayList<>();
        String query = "SELECT * FROM shippingrates";
        try {
            shippingRates = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next()) {
                ShippingRate shippingRate = new ShippingRate();
                shippingRate.setCountry(resultSet.getString("country"));
                shippingRate.setRate(resultSet.getDouble("rate"));
                shippingRates.add(shippingRate);
            }
            return shippingRates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shippingRates;
    }
}
