package filters;

import model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
