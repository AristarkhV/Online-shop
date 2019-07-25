package dao.daoJDBC;

import model.Order;
import model.User;

import java.util.Optional;

public interface OrderDao {

    void addOrder(Order value);

    Optional<Order> getUserOrder(User value);
}
