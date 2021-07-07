package controller;

import factory.service.ProductServiceFactory;
import factory.service.UserServiceFactory;
import model.Product;
import model.Role;
import model.User;
import service.ProductService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {
    private static final UserService userService = UserServiceFactory.getInstance();
    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {

    }
}
