package controller.user;

import dao.impl.UserDaoImpl;
import factory.UserServiceFactory;
import model.Role;
import model.User;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("userId");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("rpassword");
        if (id == null && userService.getUserByEmail(email).isPresent()) {
            request.setAttribute("error", "Already registered");
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        } else {
            if (password.equals("") || email.equals("") || repeatPassword.equals("")) {
                if (!email.equals("")) {
                    request.setAttribute("error", "Password field is empty");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("/addUser.jsp").forward(request, response);
                }
                request.setAttribute("error", "Empty fields :(");
                request.getRequestDispatcher("/addUser.jsp").forward(request, response);
            } else {
                if (password.equals(repeatPassword)) {
                    Role role = new Role(request.getParameter("role"));
                    if (!id.equals("") && id != null) {
                        Optional<User> editUser = userService.getUserById(currentID(id).get());
                        if (editUser.isPresent()) {
                            LOGGER.info("Try to edit  " + editUser + " ... \n");
                            userService.editUser(editUser.get(), email, password, role);
                            request.setAttribute("users", userService.getAll());
                            request.getRequestDispatcher("/users.jsp").forward(request, response);
                            response.sendRedirect("/admin/users");
                        } else {
                            LOGGER.info("User not found  \n" + id);
                        }
                    } else {
                        User user = new User(email, password, role);
                        LOGGER.info("Try to add  " + user + " ... \n");
                        userService.addUser(user);
                        request.setAttribute("users", userService.getAll());
                        request.getRequestDispatcher("/users.jsp").forward(request, response);
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

    private Optional<Long> currentID(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(Long.parseLong(id));
    }
}
