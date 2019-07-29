package controller.user;

import dao.impl.UserDaoImpl;
import factory.service.RoleServiceFactory;
import factory.service.UserServiceFactory;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/add/user")
public class AddUserServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);
    private UserService userService = UserServiceFactory.getInstance();
    private RoleService roleService = RoleServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("userID");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("rpassword");
        String roleName = request.getParameter("role");
        if (id.equals("") && userService.getUserByEmail(email).isPresent()) {
            request.setAttribute("error", "Already registered");
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        } else {
            if (password.isEmpty() || email.isEmpty() || repeatPassword.isEmpty()) {
                if (!email.isEmpty()) {
                    request.setAttribute("error", "Password field is empty");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/addUser.jsp").forward(request, response);
                }
                request.setAttribute("error", "Empty fields :(");
                request.getRequestDispatcher("/addUser.jsp").forward(request, response);
            } else {
                if (password.equals(repeatPassword)) {
                    Role role = roleService.getRoleByName(roleName).get();
                    if (!id.isEmpty()) {
                        Optional<User> editUser = userService.getUserById(Long.parseLong(id));
                        if (editUser.isPresent()) {
                            logger.info("Try to edit  " + editUser + " ... \n");
                            editUser.get().setEmail(email);
                            editUser.get().setPassword(password);
                            editUser.get().setRole(role);
                            userService.editUser(editUser.get());
                            response.sendRedirect("/admin/users");
                        } else {
                            logger.info("User not found  \n" + id);
                        }
                    } else {
                        User user = new User(email, password, role);
                        logger.info("Try to add  " + user + " ... \n");
                        userService.addUser(user);
                        response.sendRedirect("/admin/users");
                    }
                } else {
                    request.setAttribute("error", "Not equals passwords");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/addUser.jsp").forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/addUser.jsp");
    }
}
