package service.serviceImpl;

import dao.daoJDBC.CartDao;
import factory.CartFactory;
import model.Cart;
import model.Product;
import model.User;
import service.CartService;

import java.util.Optional;

public class CartServiceImpl implements CartService {

    private static final CartDao cartDao = CartFactory.getInstance();

    @Override
    public void addCart(Cart value) {
        cartDao.addCart(value);
    }

    @Override
    public Optional<Cart> getCart(User value) {
        return cartDao.getCart(value);
    }

    @Override
    public void addProductToCart(Cart cart, Product product) {
        cartDao.addProductToCart(cart, product);
    }

    @Override
    public int cartSize(User value) {
        return cartDao.getCart(value).isPresent()
                ? cartDao.getCart(value).get().getProducts().size()
                : 0;
    }
}
