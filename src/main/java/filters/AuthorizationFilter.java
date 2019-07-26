//package filters;
//
//import model.User;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = {"/admin/*"})
//public class AuthorizationFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        User userFromSession = (User) httpRequest.getSession().getAttribute("user");
//
//        if (userFromSession != null && userFromSession.getRole().getRole().equals("admin")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            if (userFromSession != null && userFromSession.getRole().equals("user")) {
//                httpResponse.sendRedirect("/store");
//            } else {
//                httpResponse.sendRedirect("/login");
//            }
//        }
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//}
