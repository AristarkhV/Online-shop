package controller;

import factory.service.ProductServiceFactory;
import factory.service.RoleServiceFactory;
import factory.service.UserServiceFactory;
import model.Product;
import model.Role;
import model.User;
import service.ProductService;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(value = "/", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    private static final RoleService roleService = RoleServiceFactory.getInstance();
    private static final UserService userService = UserServiceFactory.getInstance();
    private ProductService productService = ProductServiceFactory.getInstance();

    @Override
    public void init() throws ServletException {

        Role admin = new Role("admin");
        Role user = new Role("user");
        roleService.addRole(admin);
        roleService.addRole(user);
        userService.addUser(new User("user@u.u", "0", user));
        userService.addUser(new User("admin@a.a", "1", admin));
        for (int i = 0; i < 20; i++){
            productService.addProduct(new Product("Name " + i, "Description", i * 0.5));
        }
    }
}
