package controller.user;

import dao.impl.UserDaoImpl;
import factory.service.UserServiceFactory;
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

@WebServlet(value = "/delete/user")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long deleteUserId = Long.parseLong(request.getParameter("userID"));
        Optional<User> deleteUser = userService.getUserById(deleteUserId);
        if (deleteUser.isPresent()) {
            LOGGER.info("Try to delete  " + deleteUser.get() + " user ... \n");
            userService.deleteUser(deleteUser.get());
            request.setAttribute("message", "Deleted");
        } else {
            request.setAttribute("message", "Can't");
        }
        response.sendRedirect("/admin//users");
    }
}
