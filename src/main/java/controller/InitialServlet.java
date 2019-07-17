package controller;

import factory.ProductServiceFactory;
import factory.UserServiceFactory;
import model.IdCreator;
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
        User user = new User("user@u.u", "111", new Role("user"));
        userService.addUser(user);
        User admin = new User("admin@a.a", "111", new Role("admin"));
        userService.addUser(admin);

        for(int i = 0; i < 50; i++){
            productService.addProduct(new Product("name" + i,
                    "description",
                    (double) i));
        }
    }
}
