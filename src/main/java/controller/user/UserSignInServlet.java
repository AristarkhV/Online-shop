package controller.user;

import factory.UserServiceFactory;
import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/login")
public class UserSignInServlet extends HttpServlet {

    private UserService userService = UserServiceFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Optional<User> currentUser = userService.getUserByEmail(email);
        if (password.isEmpty()) {
            if (!email.isEmpty()) {
                request.setAttribute("error", "Password field is empty");
                request.setAttribute("email", email);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Empty fields :(");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else {
            if (currentUser.isPresent()) {
                String userPassword = currentUser
                        .get()
                        .getPassword();
                if (userPassword.equals(password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", currentUser.get());
                    request.getRequestDispatcher("/products.jsp").forward(request, response);
                    response.sendRedirect("/products");
                } else {
                    request.setAttribute("email", email);
                    request.setAttribute("error", "Bad email or password");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "You are not registered");
                request.setAttribute("email", email);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            request.setAttribute("users", userService.getAll());
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        }
    }
}
