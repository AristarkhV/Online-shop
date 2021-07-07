package dao.impl;

import dao.CartDao;
import model.Cart;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class CartDaoImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addCart(Cart value) {

        String sql = String.format("INSERT INTO cart(idUser) VALUES('%s')",
                                    value.getUser().getUserID());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            logger.info(value + " added to db");
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
    }

    private Optional<Long> getCartID(User value) {

        String sqlCartID = String.format("SELECT idCart FROM cart " +
                                         "WHERE idUser = " + value.getUserID() +
                                         " ORDER BY idCart DESC LIMIT 1");
        try (Connection Connection = DBConnection.getConnection();
             Statement statement = Connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlCartID)) {
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong("idCart"));
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
        return Optional.empty();
    }

    private ArrayList<Product> getCartProducts(User value){

        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT product.idProduct, name, price, description FROM cart " +
                "INNER JOIN product_cart ON product_cart.idCart = cart.idCart " +
                "INNER JOIN product ON product.idProduct = product_cart.idProduct " +
                "WHERE cart.idUser = '" + value.getUserID() + "'";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong("idProduct"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
        return products;
    }

    @Override
    public Optional<Cart> getCart(User value) {

        Optional<Cart> cart = Optional.empty();
        Optional<Long> cartID = getCartID(value);
        if (cartID.isPresent()) {
            cart = Optional.of(new Cart(cartID.get(), getCartProducts(value), value));
        }
        return cart;
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {

        String sql = String.format("INSERT INTO product_cart (idCart, idProduct) VALUES('%s', '%s')",
                                    cart.getCartID(), product.getProductID());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            logger.info(product + " added to product_count table");
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
    }

    @Override
    public void cleanCart(Cart cart) {

        String sql = String.format("DELETE * FROM product_cart WHERE (idCart) VALUES('%d')",
                                                                              cart.getCartID());
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            logger.info(cart + " is clean");
        } catch (SQLException e) {
            logger.error("SQl exception " + e);
        }
    }
}
