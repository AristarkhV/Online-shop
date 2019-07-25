package dao.daoJDBC.impl;

import dao.daoJDBC.OrderDao;
import model.Order;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addOrder(Order value) {

        String sqlOrder = String.format("INSERT INTO user_order(idUser, email, delivery_address) " +
                                           "VALUES('%s', '%s', '%s')",
                                            value.getUser().getUserID(), value.getEmail(),
                                            value.getDeliveryAddress());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlOrder);
            LOGGER.info(value + " added to db");
        } catch (SQLException e) {
            LOGGER.error("SQl exception " + e.getMessage());
        }
        List<Product> products  =  value.getOrderProducts();
        int i = 0;
        while (products.size() != i) {
            String sql = String.format("INSERT INTO order_product (idOrder, idProduct) " +
                                        "VALUES('%d', '%d')",
                                         value.getOrderID(), products.get(i).getProductID());
            try (Connection connection = DBConnection.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.execute(sql);
                LOGGER.info(products.get(i) + " added to order_product table");
            } catch (SQLException e) {
                LOGGER.error("SQl exception " + e.getMessage());
            }
            i++;
        }
    }

    @Override
    public Optional<Order> getUserOrder(User value) {

        Optional<Order> order = Optional.empty();
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT product.idProduct, name, price, description FROM user_order " +
                        "INNER JOIN order_product on order_product.idOrder = user_order.idOrder " +
                        "INNER JOIN product on product.idProduct = order_product.idProduct " +
                        "WHERE user_order.idUser = '" + value.getUserID() + "'";
        try (Connection Connection = DBConnection.getConnection();
             Statement statement = Connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                order = Optional.of(new Order(resultSet.getLong("idOrder"), value,
                                              resultSet.getString("email"),
                                              resultSet.getString("delivery_address"),
                                              products, false));
                Product product = new Product(resultSet.getLong("idProduct"),
                                              resultSet.getString("name"),
                                              resultSet.getString("description"),
                                              resultSet.getDouble("price"));
                products.add(product);
            }
            order.get().setOrderProducts(products);
            return order;
        } catch (SQLException e) {
            LOGGER.error("SQl exception " + e.getMessage());
            return Optional.empty();
        }
    }
}
