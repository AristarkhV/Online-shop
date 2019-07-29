package dao.impl;

import dao.OrderDao;
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

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addOrder(Order value) {

        String sqlOrder = String.format("INSERT INTO user_order(idUser, email, delivery_address) " +
                                         "VALUES('%s', '%s', '%s')", value.getUser().getUserID(),
                                                                     value.getEmail(),
                                                                     value.getDeliveryAddress());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlOrder);
            logger.info(value + " added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
        addProducts(value, getOrderID(value).get());
    }

    private Optional<Long> getOrderID(Order value) {
        String sqlOrderID = String.format("SELECT idOrder FROM user_order " +
                                          "WHERE idUser = " + value.getUser().getUserID() +
                                          " ORDER BY idOrder DESC LIMIT 1");
        try (Connection Connection = DBConnection.getConnection();
             Statement statement = Connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlOrderID)) {
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong("idOrder"));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
        return Optional.empty();
    }

    private void addProducts(Order value, Long orderID) {
        List<Product> products = value.getOrderProducts();
        int i = 0;
        while (i < products.size()) {
            String sql = String.format("INSERT INTO order_product (idOrder, idProduct) " +
                                       "VALUES('%d', '%d')", orderID, products.get(i).getProductID());
            try (Connection connection = DBConnection.getConnection();
                 Statement productStatement = connection.createStatement()) {
                productStatement.execute(sql);
                logger.info(products.get(i) + " added to order_product table");
            } catch (SQLException e) {
                logger.error("SQl exception " + e);
            }
            i++;
        }
    }

    @Override
    public Optional<Order> getUserOrder(User value) {

        Optional<Order> order = Optional.empty();
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT user_order.idOrder, user_order.email, user_order.delivery_address, " +
                            "product.idProduct, name, price, description " +
                     "FROM user_order " +
                     "INNER JOIN order_product ON order_product.idOrder = user_order.idOrder " +
                     "INNER JOIN product ON product.idProduct = order_product.idProduct " +
                     "WHERE user_order.idUser = " + value.getUserID() +
                     " ORDER BY user_order.idOrder DESC LIMIT 1";
        try (Connection Connection = DBConnection.getConnection();
             Statement statement = Connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                order = Optional.of(new Order(resultSet.getLong("idOrder"), value,
                                              resultSet.getString("email"),
                                              resultSet.getString("delivery_address"),
                                              products));
                Product product = new Product(resultSet.getLong("idProduct"),
                                              resultSet.getString("name"),
                                              resultSet.getString("description"),
                                              resultSet.getDouble("price"));
                products.add(product);
            }
            order.get().setOrderProducts(products);
            return order;
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
            return Optional.empty();
        }
    }
}
