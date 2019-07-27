package controller.user;

import dao.impl.UserDaoImpl;
import factory.UserServiceFactory;
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

@WebServlet("/admin/edit/user")
public class EditUserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("userId");
        if (id != null) {
            Optional<User> editUser = userService.getUserById(currentID(id).get());
            request.setAttribute("email", editUser.get().getEmail());
            request.setAttribute("userId", editUser.get().getUserId());
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        } else {
            LOGGER.info("User is not found");
            response.sendRedirect("/addUser.jsp");
        }
    }

    private Optional<Long> currentID(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(Long.parseLong(id));
    }
}
