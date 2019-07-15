package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

@WebFilter(urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        User userFromSession = (User) httpRequest.getSession().getAttribute("user");
        if(userFromSession != null && userFromSession.getRole().getRole().equals("admin")){
            chain.doFilter(request, response);
        }else{
            request.setAttribute("error", "Sign in, please");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            httpResponse.sendRedirect("/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
