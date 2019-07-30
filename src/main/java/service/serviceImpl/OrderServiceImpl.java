package service.serviceImpl;

import dao.CodeDao;
import dao.OrderDao;
import factory.CodeFactory;
import factory.OrderFactory;
import factory.service.MailServiceFactory;
import model.Code;
import model.Order;
import model.Product;
import model.User;
import service.MailService;
import service.OrderService;
import util.RandomHelper;

import java.util.ArrayList;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final OrderDao orderDao = OrderFactory.getInstance();
    private static final CodeDao codeDao = CodeFactory.getInstance();
    private static final MailService mailService = MailServiceFactory.getInstance();

    @Override
    public Code sendConfirmationCode(String email, Order order) {
        String confirmationCode = RandomHelper.generateCode();
        Code code = new Code(confirmationCode, order, email);
        codeDao.addCode(code);
        mailService.sendConfirmCode(code, email);
        return code;
    }

    @Override
    public void createOrder(User user, String email, String deliveryAddress, ArrayList<Product> products) {
        Order userOrder = new Order(user, email, deliveryAddress, products) ;
        orderDao.addOrder(userOrder);
    }

    @Override
    public Optional<Order> getUserOrder(User value) {
        return orderDao.getUserOrder(value);
    }

    @Override
    public void addOrder(Order value) {
        orderDao.addOrder(value);
    }
}
