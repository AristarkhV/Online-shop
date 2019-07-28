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

@WebServlet("/admin/edit/user")
public class EditUserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("userID");
        if (!id.isEmpty()) {
            Optional<User> editUser = userService.getUserById(Long.parseLong(id));
            request.setAttribute("email", editUser.get().getEmail());
            request.setAttribute("userID", editUser.get().getUserID());
            request.getRequestDispatcher("/addUser.jsp").forward(request, response);
        } else {
            LOGGER.info("User is not found");
            response.sendRedirect("/addUser");
        }
    }
}
