package service.serviceImpl;

import factory.service.MailServiceFactory;
import factory.service.OrderServiceFactory;
import model.Code;
import model.Order;
import model.Product;
import model.User;
import service.CartService;
import service.MailService;
import service.OrderService;
import util.RandomHelper;

import java.util.ArrayList;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private static ArrayList<Product> cartProducts = new ArrayList<>();
    private static final MailService mailService = MailServiceFactory.getInstance();
    private static final OrderService orderService = OrderServiceFactory.getInstance();

    @Override
    public void addCartProduct(Product product) {
        cartProducts.add(product);
    }

    @Override
    public Optional<ArrayList<Product>> getCartProducts() {
        return cartProducts.size() == 0 ?
                Optional.empty()
                : Optional.of(cartProducts);
    }

    @Override
    public int cartSize() {
        return cartProducts.size();
    }

    @Override
    public Code sendConfirmationCode(String email) {
        String confirmationCode = RandomHelper.generateCode();
        Code code = new Code(confirmationCode);
        mailService.sendConfirmCode(code, email);
        return code;
    }

    @Override
    public void createNewOrder(User user, String deliveryAddress, ArrayList<Product> products) {
        Order userOrder = new Order(user, deliveryAddress, products) ;
        orderService.addOrder(userOrder);
    }
}
